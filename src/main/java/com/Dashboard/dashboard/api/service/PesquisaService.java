package com.Dashboard.dashboard.api.service;


import com.Dashboard.dashboard.api.model.*;
import org.springframework.stereotype.Service;

import java.io.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
public class PesquisaService {
    public void Inicializador(String defFolder) throws ClassNotFoundException, SQLException {
        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream("config.properties");

            // load a properties file
            prop.load(input);
            Inicio.defFolder = defFolder;
            Inicio.hostdb = prop.getProperty("hostdb");
            Inicio.namedb = prop.getProperty("namedb");
            Inicio.userdb = prop.getProperty("userdb");
            Inicio.passdb = prop.getProperty("passdb");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Class.forName("org.postgresql.Driver");
        System.out.println(Inicio.passdb);
        Inicio.c = DriverManager
                .getConnection("jdbc:postgresql://" + Inicio.hostdb + ":5432/" + Inicio.namedb, Inicio.userdb, Inicio.passdb);
        System.out.println(Inicio.c);

    }

    public void finalizar() {
        try {
            Inicio.c.close();
        }catch(Exception e) {

        }
    }

    public void parseOneLattes(String identificador, String arquivo, HashSet<String> setores) {
        System.out.print("Importando: " + arquivo + " -> ");

        extratorLSG imp = new extratorLSG(identificador);
        imp.fazerParsing(arquivo);

        try {
            imp.cur.persist(Inicio.c);
        } catch (Exception e) {
            System.out.print("<erro-curriculo>");
        }

        //setores
        if (setores != null) {
            Setor s = null;
            boolean first = true;
            for (String setor : setores) {
                s = new Setor();
                //se houver barra, tira tudo que estÃ¡ depois
                s.setNome(setor);
                try {
                    if (first) {
                        s.persist(Inicio.c, imp.cur, first);
                        first = false;
                    } else {
                        s.persist(Inicio.c, imp.cur, first);
                    }
                }catch (Exception e) {
                    System.out.print("<erro-setor>");
                }

            }
        }

        for (Formacao a : imp.formacao) {
            try {
                a.persist(Inicio.c, imp.cur);
            } catch (Exception e) {
                System.out.print("<erro-formacao>");
            }
        }

        for (Artigo a : imp.artigos) {
            try {
                a.persist(Inicio.c, imp.cur);
            } catch (Exception e) {
                System.out.print("<erro-artigo>");
            }
        }

        for (TrabalhoEvento a : imp.evento) {
            try {
                a.persist(Inicio.c, imp.cur);
            } catch (Exception e) {
                System.out.print("<erro-trabalho_evento>");
            }
        }

        for (CapituloELivro a : imp.capitulo) {
            try {
                a.persist(Inicio.c, imp.cur);
            } catch (Exception e) {
                System.out.print("<erro-capitulo>");
            }
        }

        for (Tecnica a : imp.tecnica) {
            try {
                a.persist(Inicio.c, imp.cur);
            } catch (Exception e) {
                System.out.print("<erro-tecnica>");
            }
        }

        for (Projeto a : imp.projeto) {
            try {
                a.persist(Inicio.c, imp.cur);
            } catch (Exception e) {
                System.out.print("<erro-projeto>");
            }
        }

        for (Orientacao a : imp.orientacao) {
            try {
                a.persist(Inicio.c, imp.cur);
            } catch (Exception e) {
                System.out.print("<erro-orientacao>");
            }
        }

        /**
         * *
         */
        System.out.print("Finalizado\n");
    }

    public void unzip(String zipFile, String outputFolder){
        byte[] buffer = new byte[1024];
        try{
            File folder = new File(outputFolder);
            if(!folder.exists()){
                folder.mkdir();
            }
            //get the zip file content
            ZipInputStream zis =
                    new ZipInputStream(new FileInputStream(zipFile));
            //get the zipped file list entry
            ZipEntry ze = zis.getNextEntry();
            while(ze!=null) {
                String fileName = ze.getName();
                File newFile = new File(outputFolder + File.separator + fileName);

                //System.out.println("file unzip : "+ newFile.getAbsoluteFile());

                //create all non exists folders
                //else you will hit FileNotFoundException for compressed folder
                new File(newFile.getParent()).mkdirs();

                FileOutputStream fos = new FileOutputStream(newFile);

                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }

                fos.close();
                ze = zis.getNextEntry();
            }
            zis.closeEntry();
            zis.close();

            //System.out.println("Done");

        }catch(IOException ex){
            ex.printStackTrace();
        }
        
    }
}
