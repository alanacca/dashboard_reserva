package com.Dashboard.dashboard.api.model;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;

public class extratorLSG extends DefaultHandler {
    private String tagAtual;
    public Curriculo cur;
    public ArrayList<Artigo> artigos;
    public Artigo lastArtigo;
    public ArrayList<TrabalhoEvento> evento;
    public TrabalhoEvento lastEvento;
    public ArrayList<CapituloELivro> capitulo;
    public CapituloELivro lastCapitulo;
    public ArrayList<Formacao> formacao;
    public Formacao lastFormacao;
    public ArrayList<Projeto> projeto;
    public Projeto lastProjeto;
    public ArrayList<Orientacao> orientacao;
    public Orientacao lastOrientacao;
    public ArrayList<Tecnica> tecnica;
    public Tecnica lastTecnica;
    public String arquivo;

    public extratorLSG(String arquivo) {
        super();
        this.cur = new Curriculo();
        this.artigos = new ArrayList<>();
        this.evento = new ArrayList<>();
        this.capitulo = new ArrayList<>();
        this.formacao = new ArrayList<>();
        this.projeto = new ArrayList<>();
        this.orientacao = new ArrayList<>();
        this.tecnica = new ArrayList<>();
        this.arquivo = arquivo;
    }
    public void fazerParsing(String pathArq) {

        // Passo 1: cria instÃ¢ncia da classe SAXParser, atravÃ©s da factory
        // SAXParserFactory
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser;

        try {
            saxParser = factory.newSAXParser();

            // Passo 2: comanda o inÃ­cio do parsing
            saxParser.parse(pathArq,this); // o "this" indica que a prÃ³pria
            // classe "DevmediaSAX" atuarÃ¡ como
            // gerenciadora de eventos SAX.

            // Passo 3: tratamento de exceÃ§Ãµes.
        } catch (ParserConfigurationException | SAXException | IOException e) {
            StringBuffer msg = new StringBuffer();
            msg.append("Erro:\n");
            msg.append(e.getMessage() + "\n");
            msg.append(e.toString());
            System.out.println(msg);
        }
    }

    public void startElement(String uri, String localName, String qName,
                             Attributes atts) {

        if (qName.compareTo("CURRICULO-VITAE") == 0) {
            cur.setDATA_ATUALIZACAO(atts.getValue("DATA-ATUALIZACAO"));
            cur.setNUMERO_IDENTIFICADOR(atts.getValue("NUMERO-IDENTIFICADOR"));
            if (cur.getNUMERO_IDENTIFICADOR() == null || cur.getNUMERO_IDENTIFICADOR().isEmpty())
                cur.setNUMERO_IDENTIFICADOR(arquivo);
        }
        if (qName.compareTo("ENDERECO-PROFISSIONAL") == 0) {
            cur.setNOME_INSTITUICAO_EMPRESA(atts.getValue("NOME-INSTITUICAO-EMPRESA"));
            cur.setNOME_ORGAO(atts.getValue("NOME-ORGAO"));
            cur.setDDD(atts.getValue("DDD"));
            cur.setTELEFONE(atts.getValue("TELEFONE"));
            cur.setHOMEPAGE(atts.getValue("HOME-PAGE"));
        }
        // se a tag for "", recupera o valor do atributo "sigla"
        if (qName.compareTo("DADOS-GERAIS") == 0) {

            cur.setNOME_COMPLETO(atts.getValue("NOME-COMPLETO"));
            cur.setNOME_EM_CITACOES_BIBLIOGRAFICAS(atts.getValue("NOME-EM-CITACOES-BIBLIOGRAFICAS"));
            /****/
            System.out.print("Processando: " + cur.getNOME_COMPLETO() + " .... ");
            /******/
        }

        if (qName.compareTo("RESUMO-CV") ==0){
            cur.setTEXTO_RESUMO_CV_RH(atts.getValue("TEXTO-RESUMO-CV-RH"));
        }

        if (qName.compareTo("GRADUACAO") == 0) {
            lastFormacao = new Formacao();
            lastFormacao.setTipo_formacao("GRADUACAO");
            lastFormacao.setTitulo_trabalho(atts.getValue("TITULO-DO-TRABALHO-DE-CONCLUSAO-DE-CURSO"));
            lastFormacao.setNome_orientador(atts.getValue("NOME-DO-ORIENTADOR"));
            lastFormacao.setNome_instituicao(atts.getValue("NOME-INSTITUICAO"));
            lastFormacao.setNome_orgao(atts.getValue("NOME-ORGAO"));
            lastFormacao.setNome_curso(atts.getValue("NOME-CURSO"));
            lastFormacao.setAno_inicio(atts.getValue("ANO-DE-INICIO"));
            lastFormacao.setAno_conclusao(atts.getValue("ANO-DE-CONCLUSAO"));
            formacao.add(lastFormacao);
        }

        if (qName.compareTo("MESTRADO") == 0) {
            lastFormacao = new Formacao();
            lastFormacao.setTipo_formacao("MESTRADO");
            lastFormacao.setTitulo_trabalho(atts.getValue("TITULO-DA-DISSERTACAO-TESE"));
            lastFormacao.setNome_orientador(atts.getValue("NOME-COMPLETO-DO-ORIENTADOR"));
            lastFormacao.setNome_co_orientador(atts.getValue("NOME-DO-CO-ORIENTADOR")); ;
            lastFormacao.setNome_instituicao(atts.getValue("NOME-INSTITUICAO"));
            lastFormacao.setNome_orgao(atts.getValue("NOME-ORGAO"));
            lastFormacao.setNome_curso(atts.getValue("NOME-CURSO"));
            lastFormacao.setAno_inicio(atts.getValue("ANO-DE-INICIO"));
            lastFormacao.setAno_conclusao(atts.getValue("ANO-DE-CONCLUSAO"));
            lastFormacao.setAno_obtencao_titulo(atts.getValue("ANO-DE-OBTENCAO-DO-TITULO"));
            formacao.add(lastFormacao);
        }


        if (qName.compareTo("DOUTORADO") == 0) {
            lastFormacao = new Formacao();
            lastFormacao.setTipo_formacao("DOUTORADO");
            lastFormacao.setTitulo_trabalho(atts.getValue("TITULO-DA-DISSERTACAO-TESE"));
            lastFormacao.setNome_orientador(atts.getValue("NOME-COMPLETO-DO-ORIENTADOR"));
            lastFormacao.setNome_co_orientador(atts.getValue("NOME-DO-CO-ORIENTADOR"));
            lastFormacao.setNome_instituicao(atts.getValue("NOME-INSTITUICAO"));
            lastFormacao.setNome_orgao(atts.getValue("NOME-ORGAO"));
            lastFormacao.setNome_curso(atts.getValue("NOME-CURSO"));
            lastFormacao.setAno_inicio(atts.getValue("ANO-DE-INICIO"));
            lastFormacao.setAno_conclusao(atts.getValue("ANO-DE-CONCLUSAO"));
            lastFormacao.setAno_obtencao_titulo(atts.getValue("ANO-DE-OBTENCAO-DO-TITULO"));
            formacao.add(lastFormacao);
        }


        if ((qName.compareTo("ARTIGO-PUBLICADO")==0) ||
                (qName.compareTo("ARTIGO-ACEITO-PARA-PUBLICACAO")==0)){
            tagAtual = qName;
            if (lastArtigo == null)
                lastArtigo = new Artigo();
            lastArtigo.setSEQUENCIA_PRODUCAO(atts.getValue("SEQUENCIA-PRODUCAO"));
            lastArtigo.setTIPO(tagAtual);
        }

        if (qName.compareTo("DADOS-BASICOS-DO-ARTIGO")==0) {
            if (lastArtigo == null)
                lastArtigo = new Artigo();

            lastArtigo.setTITULO_DO_ARTIGO(atts.getValue("TITULO-DO-ARTIGO"));
            lastArtigo.setANO_DO_ARTIGO(atts.getValue("ANO-DO-ARTIGO"));
            lastArtigo.setDOI(atts.getValue("DOI"));
            lastArtigo.setNATUREZA(atts.getValue("NATUREZA"));
        }

        if (qName.compareTo("DETALHAMENTO-DO-ARTIGO")==0) {
            if (lastArtigo == null)
                lastArtigo = new Artigo();

            lastArtigo.setTITULO_DO_PERIODICO_OU_REVISTA(atts.getValue("TITULO-DO-PERIODICO-OU-REVISTA"));
            lastArtigo.setISSN(atts.getValue("ISSN"));
            lastArtigo.setVOLUME(atts.getValue("VOLUME"));
            lastArtigo.setFASCICULO(atts.getValue("FASCICULO"));
            lastArtigo.setSERIE(atts.getValue("SERIE"));
            lastArtigo.setPAGINA_FINAL(atts.getValue("PAGINA-FINAL"));
            lastArtigo.setPAGINA_INICIAL(atts.getValue("PAGINA-INICIAL"));
        }

        if (qName.compareTo("TRABALHO-EM-EVENTOS")==0) {
            tagAtual = qName;
            if (lastEvento == null)
                lastEvento = new TrabalhoEvento();
            lastEvento.setSEQUENCIA_PRODUCAO(atts.getValue("SEQUENCIA-PRODUCAO"));
        }

        if (qName.compareTo("DADOS-BASICOS-DO-TRABALHO")==0) {
            if (lastEvento == null)
                lastEvento = new TrabalhoEvento();

            lastEvento.setTITULO_DO_TRABALHO(atts.getValue("TITULO-DO-TRABALHO"));

            //System.out.println("" + lastEvento.TITULO_DO_TRABALHO);

            lastEvento.setANO_DO_TRABALHO(atts.getValue("ANO-DO-TRABALHO"));
            lastEvento.setDOI(atts.getValue("DOI"));
            lastEvento.setNATUREZA(atts.getValue("NATUREZA"));
            lastEvento.setPAIS_DO_EVENTO(atts.getValue("PAIS-DO-EVENTO"));

        }

        if (qName.compareTo("DETALHAMENTO-DO-TRABALHO")==0) {
            if (lastEvento == null)
                lastEvento = new TrabalhoEvento();

            lastEvento.setTITULO_DOS_ANAIS_OU_PROCEEDINGS(atts.getValue("TITULO-DOS-ANAIS-OU-PROCEEDINGS"));
            lastEvento.setISBN(atts.getValue("ISBN"));
            lastEvento.setVOLUME(atts.getValue("VOLUME"));
            lastEvento.setFASCICULO(atts.getValue("FASCICULO"));
            lastEvento.setSERIE(atts.getValue("SERIE"));
            lastEvento.setPAGINA_FINAL(atts.getValue("PAGINA-FINAL"));
            lastEvento.setPAGINA_INICIAL(atts.getValue("PAGINA-INICIAL"));
            lastEvento.setCLASSIFICACAO_DO_EVENTO(atts.getValue("CLASSIFICACAO-DO-EVENTO"));
            lastEvento.setNOME_DO_EVENTO(atts.getValue("NOME-DO-EVENTO"));
            lastEvento.setCIDADE_DO_EVENTO(atts.getValue("CIDADE-DO-EVENTO"));
            lastEvento.setNOME_DA_EDITORA(atts.getValue("NOME-DA-EDITORA"));
        }

        if (qName.compareTo("CAPITULO-DE-LIVRO-PUBLICADO")==0 ||
                qName.compareTo("LIVRO-PUBLICADO-OU-ORGANIZADO")==0) {
            tagAtual = qName;
            if (lastCapitulo == null)
                lastCapitulo = new CapituloELivro();
            lastCapitulo.setSEQUENCIA_PRODUCAO(atts.getValue("SEQUENCIA-PRODUCAO"));
        }

        if (qName.compareTo("DADOS-BASICOS-DO-CAPITULO")==0 ||
                qName.compareTo("DADOS-BASICOS-DO-LIVRO")==0) {
            if (lastCapitulo == null)
                lastCapitulo = new CapituloELivro();

            lastCapitulo.setTITULO_DO_CAPITULO_DO_LIVRO(atts.getValue("TITULO-DO-CAPITULO-DO-LIVRO"));
            lastCapitulo.setANO(atts.getValue("ANO")); ;
            lastCapitulo.setDOI(atts.getValue("DOI"));
            lastCapitulo.setTIPO(atts.getValue("TIPO"));
            lastCapitulo.setPAIS_DE_PUBLICACAO(atts.getValue("PAIS-DE-PUBLICACAO"));

        }

        if (qName.compareTo("DETALHAMENTO-DO-CAPITULO")==0 ||
                qName.compareTo("DETALHAMENTO-DO-LIVRO")==0) {
            if (lastCapitulo == null)
                lastCapitulo = new CapituloELivro();

            lastCapitulo.setTITULO_DO_LIVRO(atts.getValue("TITULO-DO-LIVRO"));
            lastCapitulo.setISBN(atts.getValue("ISBN"));
            lastCapitulo.setNUMERO_DE_VOLUMES(atts.getValue("NUMERO-DE-VOLUMES"));
            lastCapitulo.setORGANIZADORES(atts.getValue("ORGANIZADORES"));
            lastCapitulo.setNUMERO_DA_SERIE(atts.getValue("NUMERO-DA-SERIE"));
            lastCapitulo.setPAGINA_FINAL(atts.getValue("PAGINA-FINAL"));
            lastCapitulo.setPAGINA_INICIAL(atts.getValue("PAGINA-INICIAL"));
            lastCapitulo.setNOME_DA_EDITORA(atts.getValue("NOME-DA-EDITORA"));
            lastCapitulo.setCIDADE_DA_EDITORA(atts.getValue("CIDADE-DA-EDITORA"));
        }


        if (qName.compareTo("AUTORES") == 0) {
            if (lastEvento!=null) {
                //if (lastEvento == null) lastEvento = new TrabalhoEvento();
                lastEvento.setAUTORES(lastEvento.getAUTORES()+atts.getValue("NOME-COMPLETO-DO-AUTOR") + ";");
            }

            if (lastArtigo!=null) {
                //if (lastArtigo == null) lastArtigo = new Artigo();
                lastArtigo.AUTORES += atts.getValue("NOME-COMPLETO-DO-AUTOR") + ";";
            }
            if (lastCapitulo!=null) {
                //if (lastCapitulo == null) lastCapitulo = new CapituloLivro();
                lastCapitulo.AUTORES += atts.getValue("NOME-COMPLETO-DO-AUTOR") + ";";
            }
            if (lastTecnica != null) {
                lastTecnica.autores += atts.getValue("NOME-COMPLETO-DO-AUTOR") + ";";
            }

        }

        if (qName.compareTo("PROJETO-DE-PESQUISA") == 0) {
            if (lastProjeto == null)
                lastProjeto = new Projeto();

            lastProjeto.SEQUENCIA_PROJETO=atts.getValue("SEQUENCIA-PROJETO");
            lastProjeto.ANO_INICIO=atts.getValue("ANO-INICIO");
            lastProjeto.ANO_FIM=atts.getValue("ANO-FIM");
            lastProjeto.NOME_DO_PROJETO=atts.getValue("NOME-DO-PROJETO");
            lastProjeto.SITUACAO=atts.getValue("SITUACAO");
            lastProjeto.NATUREZA=atts.getValue("NATUREZA");
            lastProjeto.NUMERO_GRADUACAO=atts.getValue("NUMERO-GRADUACAO");
            lastProjeto.NUMERO_ESPECIALIZACAO=atts.getValue("NUMERO-ESPECIALIZACAO");
            lastProjeto.NUMERO_MESTRADO_ACADEMICO=atts.getValue("NUMERO-MESTRADO-ACADEMICO");
            lastProjeto.NUMERO_MESTRADO_PROF=atts.getValue("NUMERO-MESTRADO-PROF");
            lastProjeto.NUMERO_DOUTORADO=atts.getValue("NUMERO-DOUTORADO");
            lastProjeto.DESCRICAO_DO_PROJETO=atts.getValue("DESCRICAO-DO-PROJETO");

        }
        if (qName.compareTo("INTEGRANTES-DO-PROJETO") == 0) {
            if (lastProjeto == null)
                lastProjeto = new Projeto();

            lastProjeto.integrantes += atts.getValue("NOME-COMPLETO") + ";  ";
            String resp = atts.getValue("FLAG-RESPONSAVEL");
            if ((resp.compareTo("SIM")==0) && (cur.NOME_COMPLETO.compareTo(atts.getValue("NOME-COMPLETO")) ==0))
                lastProjeto.isReponsavel = true;
        }
        if (qName.compareTo("FINANCIADOR-DO-PROJETO") == 0) {
            if (lastProjeto == null)
                lastProjeto = new Projeto();
            Financiador f = new Financiador();
            f.SEQUENCIA_FINANCIADOR = atts.getValue("SEQUENCIA-FINANCIADOR");
            f.NOME_INSTITUICAO = atts.getValue("NOME-INSTITUICAO");
            f.NATUREZA = atts.getValue("NATUREZA");
            lastProjeto.finaciamento.add(f);
        }

        if (qName.compareTo("ORIENTACOES-CONCLUIDAS-PARA-MESTRADO") == 0 ||
                qName.compareTo("ORIENTACOES-CONCLUIDAS-PARA-DOUTORADO") ==0 ||
                qName.compareTo("OUTRAS-ORIENTACOES-CONCLUIDAS")==0 ||
                qName.compareTo("ORIENTACAO-EM-ANDAMENTO-DE-MESTRADO") ==0  ||
                qName.compareTo("ORIENTACAO-EM-ANDAMENTO-DE-DOUTORADO")==0)  {
            if (lastOrientacao == null)
                lastOrientacao = new Orientacao();
            lastOrientacao.sequencia_orientacao = atts.getValue("SEQUENCIA-PRODUCAO");
            if (qName.contains("CONCLUIDAS"))
                lastOrientacao.is_finalizado = true;
        }
        if (qName.compareTo("DADOS-BASICOS-DE-ORIENTACOES-CONCLUIDAS-PARA-MESTRADO")==0 ||
                qName.compareTo("DADOS-BASICOS-DE-ORIENTACOES-CONCLUIDAS-PARA-DOUTORADO")==0 ||
                qName.compareTo("DADOS-BASICOS-DE-OUTRAS-ORIENTACOES-CONCLUIDAS")==0 ||
                qName.compareTo("DADOS-BASICOS-DA-ORIENTACAO-EM-ANDAMENTO-DE-MESTRADO")==0 ||
                qName.compareTo("DADOS-BASICOS-DA-ORIENTACAO-EM-ANDAMENTO-DE-DOUTORADO")==0) {
            if (lastOrientacao == null)
                lastOrientacao = new Orientacao();
            lastOrientacao.natureza = atts.getValue("NATUREZA");
            lastOrientacao.tipo = atts.getValue("TIPO");

            lastOrientacao.titulo = atts.getValue("TITULO");
            lastOrientacao.ano = atts.getValue("ANO");
            lastOrientacao.doi = atts.getValue("DOI");
        }
        if (qName.compareTo("DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-MESTRADO") ==0 ||
                qName.compareTo("DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-DOUTORADO")==0 ||
                qName.compareTo("DETALHAMENTO-DE-OUTRAS-ORIENTACOES-CONCLUIDAS")==0 ||
                qName.compareTo("DETALHAMENTO-DA-ORIENTACAO-EM-ANDAMENTO-DE-MESTRADO")==0 ||
                qName.compareTo("DETALHAMENTO-DA-ORIENTACAO-EM-ANDAMENTO-DE-DOUTORADO")==0) {
            if (lastOrientacao == null)
                lastOrientacao = new Orientacao();
            lastOrientacao.tipo_orientacao = atts.getValue("TIPO-DE-ORIENTACAO");
            lastOrientacao.nome_orientando = atts.getValue("NOME-DO-ORIENTADO");
            lastOrientacao.nome_instituicao = atts.getValue("NOME-DA-INSTITUICAO");
            lastOrientacao.nome_orgao = atts.getValue("NOME-ORGAO");
            lastOrientacao.nome_curso = atts.getValue("NOME-DO-CURSO");
        }

        if (qName.compareTo("SOFTWARE")==0) {
            if (lastTecnica == null)
                lastTecnica = new Tecnica();
            lastTecnica.tipo = qName;
            lastTecnica.sequencia_producao = atts.getValue("SEQUENCIA-PRODUCAO");
        }
        if (qName.compareTo("DADOS-BASICOS-DO-SOFTWARE")==0) {
            if (lastTecnica == null)
                lastTecnica = new Tecnica();
            lastTecnica.titulo = atts.getValue("TITULO-DO-SOFTWARE");
            lastTecnica.ano = atts.getValue("ANO");
        }
        if (qName.compareTo("DETALHAMENTO-DO-SOFTWARE")==0) {
            if (lastTecnica == null)
                lastTecnica = new Tecnica();
            lastTecnica.outras_informacoes += "Finalidade: " + atts.getValue("FINALIDADE") + "; ";
            lastTecnica.outras_informacoes += "Plataforma: " + atts.getValue("PLATAFORMA") + "; ";
            lastTecnica.outras_informacoes += "Ambiente: " + atts.getValue("AMBIENTE") + "; ";
            lastTecnica.financiadora += atts.getValue("INSTITUICAO-FINANCIADORA");
        }

        if (qName.compareTo("PATENTE")==0) {
            if (lastTecnica == null)
                lastTecnica = new Tecnica();
            lastTecnica.tipo = qName;
            lastTecnica.sequencia_producao = atts.getValue("SEQUENCIA-PRODUCAO");
        }
        if (qName.compareTo("DADOS-BASICOS-DA-PATENTE")==0) {
            if (lastTecnica == null)
                lastTecnica = new Tecnica();
            lastTecnica.titulo = atts.getValue("TITULO");
            lastTecnica.ano = atts.getValue("ANO-DESENVOLVIMENTO");
        }
        if (qName.compareTo("DETALHAMENTO-DA-PATENTE")==0) {
            if (lastTecnica == null)
                lastTecnica = new Tecnica();
            lastTecnica.outras_informacoes += "Finalidade: " + atts.getValue("FINALIDADE") + "; ";
            lastTecnica.financiadora += atts.getValue("INSTITUICAO-FINANCIADORA");
        }
        if (qName.compareTo("REGISTRO-OU-PATENTE")==0) {
            if (lastTecnica == null)
                lastTecnica = new Tecnica();

            lastTecnica.outras_informacoes += "Tipo Registro/Patente: " + atts.getValue("TIPO-PATENTE") + "; ";
            lastTecnica.outras_informacoes += "CÃ³digo: " + atts.getValue("CODIGO-DO-REGISTRO-OU-PATENTE") + "; ";
            lastTecnica.outras_informacoes += "Data DepÃ³sito: " + atts.getValue("DATA-PEDIDO-DE-DEPOSITO") + "; ";
            lastTecnica.outras_informacoes += "InstituiÃ§Ã£o: " + atts.getValue("INSTITUICAO-DEPOSITO-REGISTRO") + "; ";
            lastTecnica.outras_informacoes += "Depositante: " + atts.getValue("NOME-DO-DEPOSITANTE") + "; ";
            lastTecnica.outras_informacoes += "Titular: " + atts.getValue("NOME-DO-TITULAR") + "; ";
        }
    }
}