package com.Dashboard.dashboard.api.model;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

public class extratorLSG extends DefaultHandler {
    private String tagAtual;
    public Curriculos cur;
    public ArrayList<Periodicos> periodicos;
    public Periodicos lastPeriodicos;
    public ArrayList<ArtigoEventos> evento;
    public ArtigoEventos lastEvento;
    public ArrayList<Capitulos> capitulo;
    public Capitulos lastCapitulo;
    public ArrayList<Formacoes> formacoes;
    public Formacoes lastFormacoes;
    public ArrayList<Projetos> projetos;
    public Projetos lastProjetos;
    public ArrayList<Orientacoes> orientacoes;
    public Orientacoes lastOrientacoes;
    public ArrayList<ProducoesTecnicas> producoesTecnicas;
    public ProducoesTecnicas lastProducoesTecnicas;
    public String arquivo;

    public extratorLSG(String arquivo) {
        super();
        this.cur = new Curriculos();
        this.periodicos = new ArrayList<>();
        this.evento = new ArrayList<>();
        this.capitulo = new ArrayList<>();
        this.formacoes = new ArrayList<>();
        this.projetos = new ArrayList<>();
        this.orientacoes = new ArrayList<>();
        this.producoesTecnicas = new ArrayList<>();
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
    // os métodos startDocument, endDocument, startElement, endElement e
    // characters, listados a seguir, representam os métodos de call-back da API
    // SAX

    /**
     * evento startDocument do SAX. Disparado antes do processamento da primeira
     * linha
     */
    public void startDocument() {
        System.out.println("\nIniciando o Parsing...\n");
    }

    /**
     * evento endDocument do SAX. Disparado depois do processamento da última
     * linha
     */
    public void endDocument() {
        System.out.println("\nFim do Parsing...");
    }


    public void startElement(String uri, String localName, String qName,
                             Attributes atts) {

        if (qName.compareTo("CURRICULO-VITAE") == 0) {
            cur.setDATA_ATUALIZACAO(atts.getValue("DATA-ATUALIZACAO"));
            cur.setId(Long.parseLong(atts.getValue("NUMERO-IDENTIFICADOR")));
            if (cur.getId().toString() == null || cur.getId().toString().isEmpty())
                cur.setId(Long.parseLong(arquivo));
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
            cur.setNOME_CITACOES(atts.getValue("NOME-EM-CITACOES-BIBLIOGRAFICAS"));
            /****/
            System.out.print("Processando: " + cur.getNOME_COMPLETO() + " .... ");
            /******/
        }

        if (qName.compareTo("RESUMO-CV") ==0){
            cur.setRESUMO_CV(atts.getValue("TEXTO-RESUMO-CV-RH"));
        }

        if (qName.compareTo("GRADUACAO") == 0) {
            lastFormacoes = new Formacoes();
            lastFormacoes.setTipo_formacao("GRADUACAO");
            lastFormacoes.setTitulo_trabalho(atts.getValue("TITULO-DO-TRABALHO-DE-CONCLUSAO-DE-CURSO"));
            lastFormacoes.setNome_orientador(atts.getValue("NOME-DO-ORIENTADOR"));
            lastFormacoes.setNome_instituicao(atts.getValue("NOME-INSTITUICAO"));
            lastFormacoes.setNome_orgao(atts.getValue("NOME-ORGAO"));
            lastFormacoes.setNome_curso(atts.getValue("NOME-CURSO"));
            lastFormacoes.setAno_inicio(atts.getValue("ANO-DE-INICIO"));
            lastFormacoes.setAno_conclusao(atts.getValue("ANO-DE-CONCLUSAO"));
            formacoes.add(lastFormacoes);
        }

        if (qName.compareTo("MESTRADO") == 0) {
            lastFormacoes = new Formacoes();
            lastFormacoes.setTipo_formacao("MESTRADO");
            lastFormacoes.setTitulo_trabalho(atts.getValue("TITULO-DA-DISSERTACAO-TESE"));
            lastFormacoes.setNome_orientador(atts.getValue("NOME-COMPLETO-DO-ORIENTADOR"));
            lastFormacoes.setNome_co_orientador(atts.getValue("NOME-DO-CO-ORIENTADOR")); ;
            lastFormacoes.setNome_instituicao(atts.getValue("NOME-INSTITUICAO"));
            lastFormacoes.setNome_orgao(atts.getValue("NOME-ORGAO"));
            lastFormacoes.setNome_curso(atts.getValue("NOME-CURSO"));
            lastFormacoes.setAno_inicio(atts.getValue("ANO-DE-INICIO"));
            lastFormacoes.setAno_conclusao(atts.getValue("ANO-DE-CONCLUSAO"));
            lastFormacoes.setAno_obtencao_titulo(atts.getValue("ANO-DE-OBTENCAO-DO-TITULO"));
            formacoes.add(lastFormacoes);
        }


        if (qName.compareTo("DOUTORADO") == 0) {
            lastFormacoes = new Formacoes();
            lastFormacoes.setTipo_formacao("DOUTORADO");
            lastFormacoes.setTitulo_trabalho(atts.getValue("TITULO-DA-DISSERTACAO-TESE"));
            lastFormacoes.setNome_orientador(atts.getValue("NOME-COMPLETO-DO-ORIENTADOR"));
            lastFormacoes.setNome_co_orientador(atts.getValue("NOME-DO-CO-ORIENTADOR"));
            lastFormacoes.setNome_instituicao(atts.getValue("NOME-INSTITUICAO"));
            lastFormacoes.setNome_orgao(atts.getValue("NOME-ORGAO"));
            lastFormacoes.setNome_curso(atts.getValue("NOME-CURSO"));
            lastFormacoes.setAno_inicio(atts.getValue("ANO-DE-INICIO"));
            lastFormacoes.setAno_conclusao(atts.getValue("ANO-DE-CONCLUSAO"));
            lastFormacoes.setAno_obtencao_titulo(atts.getValue("ANO-DE-OBTENCAO-DO-TITULO"));
            formacoes.add(lastFormacoes);
        }


        if ((qName.compareTo("ARTIGO-PUBLICADO")==0) ||
                (qName.compareTo("ARTIGO-ACEITO-PARA-PUBLICACAO")==0)){
            tagAtual = qName;
            if (lastPeriodicos == null)
                lastPeriodicos = new Periodicos();
            lastPeriodicos.setSEQUENCIA_PRODUCAO(atts.getValue("SEQUENCIA-PRODUCAO"));
            lastPeriodicos.setTIPO(tagAtual);
        }

        if (qName.compareTo("DADOS-BASICOS-DO-ARTIGO")==0) {
            if (lastPeriodicos == null)
                lastPeriodicos = new Periodicos();

            lastPeriodicos.setTITULO_DO_ARTIGO(atts.getValue("TITULO-DO-ARTIGO"));
            lastPeriodicos.setANO_DO_ARTIGO(atts.getValue("ANO-DO-ARTIGO"));
            lastPeriodicos.setDOI(atts.getValue("DOI"));
            lastPeriodicos.setNATUREZA(atts.getValue("NATUREZA"));
        }

        if (qName.compareTo("DETALHAMENTO-DO-ARTIGO")==0) {
            if (lastPeriodicos == null)
                lastPeriodicos = new Periodicos();

            lastPeriodicos.setTITULO_DO_PERIODICO_OU_REVISTA(atts.getValue("TITULO-DO-PERIODICO-OU-REVISTA"));
            lastPeriodicos.setISSN(atts.getValue("ISSN"));
            lastPeriodicos.setVOLUME(atts.getValue("VOLUME"));
            lastPeriodicos.setFASCICULO(atts.getValue("FASCICULO"));
            lastPeriodicos.setSERIE(atts.getValue("SERIE"));
            lastPeriodicos.setPAGINA_FINAL(atts.getValue("PAGINA-FINAL"));
            lastPeriodicos.setPAGINA_INICIAL(atts.getValue("PAGINA-INICIAL"));
        }

        if (qName.compareTo("TRABALHO-EM-EVENTOS")==0) {
            tagAtual = qName;
            if (lastEvento == null)
                lastEvento = new ArtigoEventos();
            lastEvento.setSEQUENCIA_PRODUCAO(atts.getValue("SEQUENCIA-PRODUCAO"));
        }

        if (qName.compareTo("DADOS-BASICOS-DO-TRABALHO")==0) {
            if (lastEvento == null)
                lastEvento = new ArtigoEventos();

            lastEvento.setTITULO(atts.getValue("TITULO-DO-TRABALHO"));

            //System.out.println("" + lastEvento.TITULO_DO_TRABALHO);

            lastEvento.setANO_TRABALHO(atts.getValue("ANO-DO-TRABALHO"));
            lastEvento.setDOI(atts.getValue("DOI"));
            lastEvento.setNATUREZA(atts.getValue("NATUREZA"));
            lastEvento.setPAIS_EVENTO(atts.getValue("PAIS-DO-EVENTO"));

        }

        if (qName.compareTo("DETALHAMENTO-DO-TRABALHO")==0) {
            if (lastEvento == null)
                lastEvento = new ArtigoEventos();

            lastEvento.setTITULO_ANAIS_OU_PROCEEDINGS(atts.getValue("TITULO-DOS-ANAIS-OU-PROCEEDINGS"));
            lastEvento.setISBN(atts.getValue("ISBN"));
            lastEvento.setVOLUME(atts.getValue("VOLUME"));
            lastEvento.setFASCICULO(atts.getValue("FASCICULO"));
            lastEvento.setSERIE(atts.getValue("SERIE"));
            lastEvento.setPAGINA_FIM(atts.getValue("PAGINA-FINAL"));
            lastEvento.setPAGINA_INICIO(atts.getValue("PAGINA-INICIAL"));
            lastEvento.setCLASSIFICACAO_EVENTO(atts.getValue("CLASSIFICACAO-DO-EVENTO"));
            lastEvento.setNOME_EVENTO(atts.getValue("NOME-DO-EVENTO"));
            lastEvento.setCIDADE_EVENTO(atts.getValue("CIDADE-DO-EVENTO"));
            lastEvento.setNOME_EDITORA(atts.getValue("NOME-DA-EDITORA"));
        }

        if (qName.compareTo("CAPITULO-DE-LIVRO-PUBLICADO")==0 ||
                qName.compareTo("LIVRO-PUBLICADO-OU-ORGANIZADO")==0) {
            tagAtual = qName;
            if (lastCapitulo == null)
                lastCapitulo = new Capitulos();
            lastCapitulo.setSEQUENCIA_PRODUCAO(atts.getValue("SEQUENCIA-PRODUCAO"));
        }

        if (qName.compareTo("DADOS-BASICOS-DO-CAPITULO")==0 ||
                qName.compareTo("DADOS-BASICOS-DO-LIVRO")==0) {
            if (lastCapitulo == null)
                lastCapitulo = new Capitulos();

            lastCapitulo.setTITULO(atts.getValue("TITULO-DO-CAPITULO-DO-LIVRO"));
            lastCapitulo.setANO_TRABALHO(atts.getValue("ANO")); ;
            lastCapitulo.setDOI(atts.getValue("DOI"));
            lastCapitulo.setCAPITULO_TIPO(atts.getValue("TIPO"));

        }

        if (qName.compareTo("DETALHAMENTO-DO-CAPITULO")==0 ||
                qName.compareTo("DETALHAMENTO-DO-LIVRO")==0) {
            if (lastCapitulo == null)
                lastCapitulo = new Capitulos();

            lastCapitulo.setLIVRO_TITULO(atts.getValue("TITULO-DO-LIVRO"));
            lastCapitulo.setISBN(atts.getValue("ISBN"));
            lastCapitulo.setLIVRO_NRO_VOLUMES(atts.getValue("NUMERO-DE-VOLUMES"));
            lastCapitulo.setLIVRO_NRO_SERIE(atts.getValue("NUMERO-DA-SERIE"));
            lastCapitulo.setPAGINA_FIM(atts.getValue("PAGINA-FINAL"));
            lastCapitulo.setPAGINA_INICIO(atts.getValue("PAGINA-INICIAL"));
            lastCapitulo.setNOME_EDITORA(atts.getValue("NOME-DA-EDITORA"));
        }


        if (qName.compareTo("AUTORES") == 0) {
            if (lastEvento!=null) {
                //if (lastEvento == null) lastEvento = new TrabalhoEvento();
                lastEvento.setAUTORES(lastEvento.getAUTORES()+atts.getValue("NOME-COMPLETO-DO-AUTOR") + ";");
            }

            if (lastPeriodicos !=null) {
                //if (lastArtigo == null) lastArtigo = new Artigo();
                lastPeriodicos.setAUTORES(lastPeriodicos.getAUTORES()+atts.getValue("NOME-COMPLETO-DO-AUTOR") + ";");
            }
            if (lastCapitulo!=null) {
                //if (lastCapitulo == null) lastCapitulo = new CapituloLivro();
                lastCapitulo.setAUTORES(lastCapitulo.getAUTORES()+atts.getValue("NOME-COMPLETO-DO-AUTOR") + ";");
            }
            if (lastProducoesTecnicas != null) {
                lastProducoesTecnicas.setAutores(lastProducoesTecnicas.getAutores()+atts.getValue("NOME-COMPLETO-DO-AUTOR") + ";");
            }

        }

        if (qName.compareTo("PROJETO-DE-PESQUISA") == 0) {
            if (lastProjetos == null)
                lastProjetos = new Projetos();

            lastProjetos.setSEQUENCIA_PROJETO(atts.getValue("SEQUENCIA-PROJETO"));
            lastProjetos.setANO_INICIO(atts.getValue("ANO-INICIO"));
            lastProjetos.setANO_FIM(atts.getValue("ANO-FIM"));
            lastProjetos.setNOME_DO_PROJETO(atts.getValue("NOME-DO-PROJETO"));
            lastProjetos.setSITUACAO(atts.getValue("SITUACAO"));
            lastProjetos.setNATUREZA(atts.getValue("NATUREZA"));
            lastProjetos.setNUMERO_GRADUACAO(atts.getValue("NUMERO-GRADUACAO"));
            lastProjetos.setNUMERO_ESPECIALIZACAO(atts.getValue("NUMERO-ESPECIALIZACAO"));
            lastProjetos.setNUMERO_MESTRADO_ACADEMICO(atts.getValue("NUMERO-MESTRADO-ACADEMICO"));
            lastProjetos.setNUMERO_MESTRADO_PROF(atts.getValue("NUMERO-MESTRADO-PROF"));
            lastProjetos.setNUMERO_DOUTORADO(atts.getValue("NUMERO-DOUTORADO"));
            lastProjetos.setDESCRICAO_DO_PROJETO(atts.getValue("DESCRICAO-DO-PROJETO"));

        }
        if (qName.compareTo("INTEGRANTES-DO-PROJETO") == 0) {
            if (lastProjetos == null)
                lastProjetos = new Projetos();

            lastProjetos.setIntegrantes(lastProjetos.getIntegrantes()+atts.getValue("NOME-COMPLETO") + ";  ");
            String resp = atts.getValue("FLAG-RESPONSAVEL");
            if ((resp.compareTo("SIM")==0) && (cur.getNOME_COMPLETO().compareTo(atts.getValue("NOME-COMPLETO")) ==0))
                lastProjetos.setIsReponsavel(true);
        }
        if (qName.compareTo("FINANCIADOR-DO-PROJETO") == 0) {
            if (lastProjetos == null)
                lastProjetos = new Projetos();
            FinanciadoresProjetos f = new FinanciadoresProjetos();
            f.setSEQUENCIA_FINANCIADOR(atts.getValue("SEQUENCIA-FINANCIADOR"));
            f.setNOME_INSTITUICAO(atts.getValue("NOME-INSTITUICAO"));
            f.setNATUREZA(atts.getValue("NATUREZA"));
            lastProjetos.getFinaciamento().add(f);
        }

        if (qName.compareTo("ORIENTACOES-CONCLUIDAS-PARA-MESTRADO") == 0 ||
                qName.compareTo("ORIENTACOES-CONCLUIDAS-PARA-DOUTORADO") ==0 ||
                qName.compareTo("OUTRAS-ORIENTACOES-CONCLUIDAS")==0 ||
                qName.compareTo("ORIENTACAO-EM-ANDAMENTO-DE-MESTRADO") ==0  ||
                qName.compareTo("ORIENTACAO-EM-ANDAMENTO-DE-DOUTORADO")==0)  {
            if (lastOrientacoes == null)
                lastOrientacoes = new Orientacoes();
            lastOrientacoes.setSequencia_orientacao(atts.getValue("SEQUENCIA-PRODUCAO"));
            if (qName.contains("CONCLUIDAS"))
                lastOrientacoes.setIs_finalizado(true);
        }
        if (qName.compareTo("DADOS-BASICOS-DE-ORIENTACOES-CONCLUIDAS-PARA-MESTRADO")==0 ||
                qName.compareTo("DADOS-BASICOS-DE-ORIENTACOES-CONCLUIDAS-PARA-DOUTORADO")==0 ||
                qName.compareTo("DADOS-BASICOS-DE-OUTRAS-ORIENTACOES-CONCLUIDAS")==0 ||
                qName.compareTo("DADOS-BASICOS-DA-ORIENTACAO-EM-ANDAMENTO-DE-MESTRADO")==0 ||
                qName.compareTo("DADOS-BASICOS-DA-ORIENTACAO-EM-ANDAMENTO-DE-DOUTORADO")==0) {
            if (lastOrientacoes == null)
                lastOrientacoes = new Orientacoes();
            lastOrientacoes.setNatureza(atts.getValue("NATUREZA"));
            lastOrientacoes.setTipo(atts.getValue("TIPO"));

            lastOrientacoes.setTitulo(atts.getValue("TITULO"));
            lastOrientacoes.setAno(atts.getValue("ANO"));
            lastOrientacoes.setDoi(atts.getValue("DOI"));
        }
        if (qName.compareTo("DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-MESTRADO") ==0 ||
                qName.compareTo("DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-DOUTORADO")==0 ||
                qName.compareTo("DETALHAMENTO-DE-OUTRAS-ORIENTACOES-CONCLUIDAS")==0 ||
                qName.compareTo("DETALHAMENTO-DA-ORIENTACAO-EM-ANDAMENTO-DE-MESTRADO")==0 ||
                qName.compareTo("DETALHAMENTO-DA-ORIENTACAO-EM-ANDAMENTO-DE-DOUTORADO")==0) {
            if (lastOrientacoes == null)
                lastOrientacoes = new Orientacoes();
            lastOrientacoes.setTipo_orientacao(atts.getValue("TIPO-DE-ORIENTACAO"));
            lastOrientacoes.setNome_orientando(atts.getValue("NOME-DO-ORIENTADO"));
            lastOrientacoes.setNome_instituicao(atts.getValue("NOME-DA-INSTITUICAO"));
            lastOrientacoes.setNome_orgao(atts.getValue("NOME-ORGAO"));
            lastOrientacoes.setNome_curso(atts.getValue("NOME-DO-CURSO"));
        }

        if (qName.compareTo("SOFTWARE")==0) {
            if (lastProducoesTecnicas == null)
                lastProducoesTecnicas = new ProducoesTecnicas();
            lastProducoesTecnicas.setTipo(qName);
            lastProducoesTecnicas.setSequencia_producao(atts.getValue("SEQUENCIA-PRODUCAO"));
        }
        if (qName.compareTo("DADOS-BASICOS-DO-SOFTWARE")==0) {
            if (lastProducoesTecnicas == null)
                lastProducoesTecnicas = new ProducoesTecnicas();
            lastProducoesTecnicas.setTitulo(atts.getValue("TITULO-DO-SOFTWARE"));
            lastProducoesTecnicas.setAno(atts.getValue("ANO"));
        }
        if (qName.compareTo("DETALHAMENTO-DO-SOFTWARE")==0) {
            if (lastProducoesTecnicas == null)
                lastProducoesTecnicas = new ProducoesTecnicas();
            lastProducoesTecnicas.setOutras_informacoes(lastProducoesTecnicas.getOutras_informacoes()+"Finalidade: " + atts.getValue("FINALIDADE") + "; ");
            lastProducoesTecnicas.setOutras_informacoes(lastProducoesTecnicas.getOutras_informacoes()+"Plataforma: " + atts.getValue("PLATAFORMA") + "; ");
            lastProducoesTecnicas.setOutras_informacoes(lastProducoesTecnicas.getOutras_informacoes()+"Ambiente: " + atts.getValue("AMBIENTE") + "; ");
            lastProducoesTecnicas.setFinanciadora(lastProducoesTecnicas.getFinanciadora()+atts.getValue("INSTITUICAO-FINANCIADORA"));
        }

        if (qName.compareTo("PATENTE")==0) {
            if (lastProducoesTecnicas == null)
                lastProducoesTecnicas = new ProducoesTecnicas();
            lastProducoesTecnicas.setTipo(qName);
            lastProducoesTecnicas.setSequencia_producao(atts.getValue("SEQUENCIA-PRODUCAO"));
        }
        if (qName.compareTo("DADOS-BASICOS-DA-PATENTE")==0) {
            if (lastProducoesTecnicas == null)
                lastProducoesTecnicas = new ProducoesTecnicas();
            lastProducoesTecnicas.setTitulo(atts.getValue("TITULO"));
            lastProducoesTecnicas.setAno(atts.getValue("ANO-DESENVOLVIMENTO"));
        }
        if (qName.compareTo("DETALHAMENTO-DA-PATENTE")==0) {
            if (lastProducoesTecnicas == null)
                lastProducoesTecnicas = new ProducoesTecnicas();
            lastProducoesTecnicas.setOutras_informacoes(lastProducoesTecnicas.getOutras_informacoes()+"Finalidade: " + atts.getValue("FINALIDADE") + "; ");
            lastProducoesTecnicas.setFinanciadora(lastProducoesTecnicas.getFinanciadora()+atts.getValue("INSTITUICAO-FINANCIADORA"));
        }
        if (qName.compareTo("REGISTRO-OU-PATENTE")==0) {
            if (lastProducoesTecnicas == null)
                lastProducoesTecnicas = new ProducoesTecnicas();

            lastProducoesTecnicas.setOutras_informacoes(lastProducoesTecnicas.getOutras_informacoes()+"Tipo Registro/Patente: " + atts.getValue("TIPO-PATENTE") + "; ");
            lastProducoesTecnicas.setOutras_informacoes(lastProducoesTecnicas.getOutras_informacoes()+"CÃ³digo: " + atts.getValue("CODIGO-DO-REGISTRO-OU-PATENTE") + "; ");
            lastProducoesTecnicas.setOutras_informacoes(lastProducoesTecnicas.getOutras_informacoes()+"Data DepÃ³sito: " + atts.getValue("DATA-PEDIDO-DE-DEPOSITO") + "; ");
            lastProducoesTecnicas.setOutras_informacoes(lastProducoesTecnicas.getOutras_informacoes()+"InstituiÃ§Ã£o: " + atts.getValue("INSTITUICAO-DEPOSITO-REGISTRO") + "; ");
            lastProducoesTecnicas.setOutras_informacoes(lastProducoesTecnicas.getOutras_informacoes()+"Depositante: " + atts.getValue("NOME-DO-DEPOSITANTE") + "; ");
            lastProducoesTecnicas.setOutras_informacoes(lastProducoesTecnicas.getOutras_informacoes()+"Titular: " + atts.getValue("NOME-DO-TITULAR") + "; ");
        }
    }
    /**
     * evento endElement do SAX. Disparado quando o processador SAX identifica o
     * fechamento de uma tag (ex: )
     */
    public void endElement(String uri, String localName, String qName)
            throws SAXException {

        tagAtual = "";

        if ((qName.compareTo("ARTIGO-PUBLICADO") == 0) ||
                (qName.compareTo("ARTIGO-ACEITO-PARA-PUBLICACAO") == 0)){
            periodicos.add(lastPeriodicos);
            lastPeriodicos = null;
        }

        if (qName.compareTo("TRABALHO-EM-EVENTOS") == 0) {
            evento.add(lastEvento);
            lastEvento = null;
        }

        if (qName.compareTo("CAPITULO-DE-LIVRO-PUBLICADO") == 0||
                qName.compareTo("LIVRO-PUBLICADO-OU-ORGANIZADO")==0) {
            capitulo.add(lastCapitulo);
            lastCapitulo = null;
        }
        if (qName.compareTo("PROJETO-DE-PESQUISA") == 0) {
            projetos.add(lastProjetos);
            lastProjetos = null;
        }

        if (qName.compareTo("ORIENTACOES-CONCLUIDAS-PARA-MESTRADO") == 0 ||
                qName.compareTo("ORIENTACOES-CONCLUIDAS-PARA-DOUTORADO") ==0 ||
                qName.compareTo("OUTRAS-ORIENTACOES-CONCLUIDAS")==0 ||
                qName.compareTo("ORIENTACAO-EM-ANDAMENTO-DE-MESTRADO") ==0  ||
                qName.compareTo("ORIENTACAO-EM-ANDAMENTO-DE-DOUTORADO")==0)  {
            orientacoes.add(lastOrientacoes);
            lastOrientacoes = null;
        }

        if (qName.compareTo("SOFTWARE")==0) {
            producoesTecnicas.add(lastProducoesTecnicas);
            lastProducoesTecnicas = null;
        }

        if (qName.compareTo("PATENTE")==0) {
            producoesTecnicas.add(lastProducoesTecnicas);
            lastProducoesTecnicas = null;
        }
    }

    /**
     * evento characters do SAX. É onde podemos recuperar as informações texto
     * contidas no documento XML (textos contidos entre tags). Neste exemplo,
     * recuperamos os nomes dos países, a população e a moeda
     *
     */
    public void characters(char[] ch, int start, int length)
            throws SAXException {

        String texto = new String(ch, start, length);

        // ------------------------------------------------------------
        // --- TRATAMENTO DAS INFORMAÇÕES DE ACORDO COM A TAG ATUAL ---
        // ------------------------------------------------------------

        if ((tagAtual != null) && (tagAtual.compareTo("DADOS-GERAIS") == 0)) {
            System.out.print(texto + " - nome: " + cur.getNOME_COMPLETO());
        }
    }
}
