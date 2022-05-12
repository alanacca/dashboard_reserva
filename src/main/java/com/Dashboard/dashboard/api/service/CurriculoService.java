package com.Dashboard.dashboard.api.service;

import com.Dashboard.dashboard.api.model.Curriculos;
import com.Dashboard.dashboard.api.repository.AvaliacaoPeriodQualisAtualRepository;
import com.Dashboard.dashboard.api.repository.CurriculosRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class CurriculoService {

    @Getter
    @Autowired
    CurriculosRepository repo;

    @Autowired
    AvaliacaoPeriodQualisAtualRepository qualisRepo;

    private Integer countA1 = 0;
    private Integer countA2 = 0;
    private Integer countA3 = 0;
    private Integer countA4 = 0;
    private Integer countB1 = 0;
    private Integer countB2 = 0;
    private Integer countB3 = 0;
    private Integer countB4 = 0;

    private Double countA12Forma = 0.0;
    private Double countA22Forma = 0.0;
    private Double countA32Forma = 0.0;
    private Double countA42Forma = 0.0;
    private Double countB12Forma = 0.0;
    private Double countB22Forma = 0.0;
    private Double countB32Forma = 0.0;
    private Double countB42Forma = 0.0;
    private Integer countA1Total = 0;
    private Integer countA2Total = 0;
    private Integer countA3Total = 0;
    private Integer countA4Total = 0;
    private Integer countB1Total = 0;
    private Integer countB2Total = 0;
    private Integer countB3Total = 0;
    private Integer countB4Total = 0;
    private Double iGeralTotal = 0.0;
    private Double iRestritoTotal = 0.0;
    private Double iNaoRestritoTotal = 0.0;
    private Integer TotalDiscenteDoutorado =0;

    public Curriculos findByCurriculo(Long idCurriculo) {
        return this.repo.findByCurriculo(idCurriculo);
    }

//    public List<HashMap<String, String>> estratoCurriculo(Integer ano_inicio, Integer ano_final) {
//
//        List<HashMap<String, String>> listHash = new ArrayList<>();
//        List<Long> curriculos = this.repo.findAllIdMestrado();
//        curriculos.stream().forEach(idCurriculo -> {
//            HashMap<String, String> list = new HashMap<>();
//            List<String> estratosCurriculo = this.qualisRepo.estratosCurriculo(idCurriculo, ano_inicio, ano_final);
//            estratosCurriculo.stream().forEach(estrato -> {
//                if (estrato.equalsIgnoreCase("A1")) {
//                    this.countA1++;
//                } else if (estrato.equalsIgnoreCase("A2")) {
//                    this.countA2++;
//                } else if (estrato.equalsIgnoreCase("A3")) {
//                    this.countA3++;
//                } else if (estrato.equalsIgnoreCase("A4")) {
//                    this.countA4++;
//                } else if (estrato.equalsIgnoreCase("B1")) {
//                    this.countB1++;
//                } else if (estrato.equalsIgnoreCase("B2")) {
//                    this.countB2++;
//                } else if (estrato.equalsIgnoreCase("B3")) {
//                    this.countB3++;
//                } else if (estrato.equalsIgnoreCase("B4")) {
//                    this.countB4++;
//                }
//            });
////            System.out.println(this.countA1);
//            Double iRestrito = this.countA1 * 1 + this.countA2 * 0.85 + this.countA3 * 0.725 + this.countA4 * 0.625;
//            Double iNao_Restrito = this.countB1 * 0.5 + this.countB2 * 0.25 + this.countB3 * 0.1 + this.countB4 * 0.05;
//            Double iGeral = iRestrito + iNao_Restrito;
//            String nomeCompleto = this.repo.findNomeCompleto(idCurriculo);
//
//            list.put("nomeCompleto", nomeCompleto);
//            list.put("iRestrito", String.format("%.2f", iRestrito));
//            list.put("iNao_Restrito", String.format("%.2f", iNao_Restrito));
//            list.put("iGeral", String.format("%.2f", iGeral));
//            listHash.add(list);
//
//            this.countA1 = 0;
//            this.countA2 = 0;
//            this.countA3 = 0;
//            this.countA4 = 0;
//            this.countB1 = 0;
//            this.countB2 = 0;
//            this.countB3 = 0;
//            this.countB4 = 0;
//        });
//        return listHash;
//    }

//    public List<HashMap<String,String>> estratoCurriculoMestrado2Forma(Integer ano_inicio,Integer ano_final){
//
//        List<HashMap<String,String>> listHash = new ArrayList<>();
//        List<Long> curriculos = this.repo.findAllIdMestrado();
//        curriculos.stream().forEach(idCurriculo-> {
//            System.out.println(idCurriculo);
//            HashMap<String,String> list = new HashMap<>();
//            HashMap<String,Integer> estratoPeriodicos = this.AuxIndice2Forma(this.qualisRepo.estratosCurriculo2Forma(idCurriculo,ano_inicio,ano_final));
//            for(Map.Entry<String, Integer> entry : estratoPeriodicos.entrySet()) {
//                String key = entry.getKey();
//                Integer value = entry.getValue();
//                System.out.println(this.qualisRepo.countAutoresPeriodicoMestrado(value));
//                Double quantidadeAutores = 1.0/this.qualisRepo.countAutoresPeriodicoMestrado(value);
//                if (key.equalsIgnoreCase("A1")) {
//                    this.countA12Forma += quantidadeAutores;
//                } else if (key.equalsIgnoreCase("A2")) {
//                    this.countA22Forma += quantidadeAutores;
//                } else if (key.equalsIgnoreCase("A3")) {
//                    this.countA32Forma += quantidadeAutores;
//                } else if (key.equalsIgnoreCase("A4")) {
//                    this.countA42Forma += quantidadeAutores;
//                } else if (key.equalsIgnoreCase("B1")) {
//                    this.countB12Forma += quantidadeAutores;
//                } else if (key.equalsIgnoreCase("B2")) {
//                    this.countB22Forma += quantidadeAutores;
//                } else if (key.equalsIgnoreCase("B3")) {
//                    this.countB32Forma += quantidadeAutores;
//                } else if (key.equalsIgnoreCase("B4")) {
//                    this.countB42Forma += quantidadeAutores;
//                }
//            }
////            System.out.println(this.countA1);
//            Double iRestrito =  this.countA12Forma * 1 + this.countA22Forma * 0.85 + this.countA32Forma * 0.725 + this.countA42Forma * 0.625;
//            Double iNao_Restrito = this.countB12Forma * 0.5 + this.countB22Forma * 0.25 + this.countB32Forma * 0.1 + this.countB42Forma * 0.05;
//            Double iGeral = iRestrito + iNao_Restrito;
//            String nomeCompleto = this.repo.findNomeCompleto(idCurriculo);
//
//            list.put("nomeCompleto", nomeCompleto);
//            list.put("iRestrito", String.format("%.2f",iRestrito));
//            list.put("iNao_Restrito", String.format("%.2f",iNao_Restrito));
//            list.put("iGeral", String.format("%.2f",iGeral));
//            listHash.add(list);
//
//            this.countA12Forma = 0.0;
//            this.countA22Forma = 0.0;
//            this.countA32Forma = 0.0;
//            this.countA42Forma = 0.0;
//            this.countB12Forma = 0.0;
//            this.countB22Forma = 0.0;
//            this.countB32Forma = 0.0;
//            this.countB42Forma = 0.0;
//        });
//        return listHash;
//    }

    public List<HashMap<String,String>> estratosCurriculosMestrado(Integer ano_inicio, Integer ano_final){
        List<Long> curriculos = this.repo.findAllIdMestrado();
        List<HashMap<String,String>> listHash = new ArrayList<>();
        curriculos.stream().forEach(idCurriculo-> {
            HashMap<String,String> teste = new HashMap<>();
            teste.putAll(estratoCurriculoMestrado2FormaTeste(idCurriculo,ano_inicio,ano_final));
            teste.putAll(estratoCurriculoMestradoTeste(idCurriculo,ano_inicio,ano_final));
            listHash.add(teste);
        });
        return listHash;
    }
    public HashMap<String, String> estratoCurriculoMestradoTeste(Long idCurriculo,Integer ano_inicio, Integer ano_final) {

//        List<HashMap<String, String>> listHash = new ArrayList<>();
//        List<Long> curriculos = this.repo.findAllIdMestrado();
//        curriculos.stream().forEach(idCurriculo -> {
            HashMap<String, String> list = new HashMap<>();
            List<String> estratosCurriculo = this.qualisRepo.estratosCurriculo(idCurriculo, ano_inicio, ano_final);
            estratosCurriculo.stream().forEach(estrato -> {
                if (estrato.equalsIgnoreCase("A1")) {
                    this.countA1++;
                } else if (estrato.equalsIgnoreCase("A2")) {
                    this.countA2++;
                } else if (estrato.equalsIgnoreCase("A3")) {
                    this.countA3++;
                } else if (estrato.equalsIgnoreCase("A4")) {
                    this.countA4++;
                } else if (estrato.equalsIgnoreCase("B1")) {
                    this.countB1++;
                } else if (estrato.equalsIgnoreCase("B2")) {
                    this.countB2++;
                } else if (estrato.equalsIgnoreCase("B3")) {
                    this.countB3++;
                } else if (estrato.equalsIgnoreCase("B4")) {
                    this.countB4++;
                }
            });
//            System.out.println(this.countA1);
            Double iRestrito = this.countA1 * 1 + this.countA2 * 0.85 + this.countA3 * 0.725 + this.countA4 * 0.625;
            Double iNao_Restrito = this.countB1 * 0.5 + this.countB2 * 0.25 + this.countB3 * 0.1 + this.countB4 * 0.05;
            Double iGeral = iRestrito + iNao_Restrito;
            String nomeCompleto = this.repo.findNomeCompleto(idCurriculo);

            list.put("nomeCompleto", nomeCompleto);
            list.put("iRestrito", String.format("%.2f", iRestrito));
            list.put("iNao_Restrito", String.format("%.2f", iNao_Restrito));
            list.put("iGeral", String.format("%.2f", iGeral));
//            listHash.add(list);

            this.countA1 = 0;
            this.countA2 = 0;
            this.countA3 = 0;
            this.countA4 = 0;
            this.countB1 = 0;
            this.countB2 = 0;
            this.countB3 = 0;
            this.countB4 = 0;
//        });
        return list;
    }
    public HashMap<String,String> estratoCurriculoMestrado2FormaTeste(Long idCurriculo,Integer ano_inicio,Integer ano_final){

//        List<HashMap<String,String>> listHash = new ArrayList<>();
//        List<Long> curriculos = this.repo.findAllIdMestrado();
//        curriculos.stream().forEach(idCurriculo-> {
//            System.out.println(idCurriculo);
            HashMap<String,String> list = new HashMap<>();
            HashMap<Integer,String> estratoPeriodicos =
                    this.AuxIndice2Forma(this.qualisRepo.estratosCurriculo2Forma(idCurriculo,ano_inicio,ano_final),
                    this.qualisRepo.estratosCurriculoEventos2Forma(idCurriculo,ano_inicio,ano_final));
            for(Map.Entry<Integer, String> entry : estratoPeriodicos.entrySet()) {
                Integer key = entry.getKey();
                String value = entry.getValue();
//                System.out.println(this.qualisRepo.countAutoresPeriodicoMestrado(value));
                Double quantidadeAutores = 0.0;
                if(!Double.isInfinite(1/this.qualisRepo.countAutoresPeriodicoMestrado(key))){
                    quantidadeAutores = 1.0/this.qualisRepo.countAutoresPeriodicoMestrado(key);
                }else if(!Double.isInfinite(1/this.qualisRepo.countAutoresEventoMestrado(key))){
                    quantidadeAutores = 1.0/this.qualisRepo.countAutoresEventoMestrado(key);
                }
//                Double quantidadeAutores = 1.0/this.qualisRepo.countAutoresPeriodicoMestrado(value);
                System.out.println("quantidade autores");
                System.out.println(quantidadeAutores);
                if (value.equalsIgnoreCase("A1")) {
                    this.countA12Forma += quantidadeAutores;
                } else if (value.equalsIgnoreCase("A2")) {
                    this.countA22Forma += quantidadeAutores;
                } else if (value.equalsIgnoreCase("A3")) {
                    this.countA32Forma += quantidadeAutores;
                } else if (value.equalsIgnoreCase("A4")) {
                    this.countA42Forma += quantidadeAutores;
                } else if (value.equalsIgnoreCase("B1")) {
                    this.countB12Forma += quantidadeAutores;
                } else if (value.equalsIgnoreCase("B2")) {
                    this.countB22Forma += quantidadeAutores;
                } else if (value.equalsIgnoreCase("B3")) {
                    this.countB32Forma += quantidadeAutores;
                } else if (value.equalsIgnoreCase("B4")) {
                    this.countB42Forma += quantidadeAutores;
                }
            }
//            System.out.println(this.countA1);
            Double iRestrito =  this.countA12Forma * 1 + this.countA22Forma * 0.85 + this.countA32Forma * 0.725 + this.countA42Forma * 0.625;
            Double iNao_Restrito = this.countB12Forma * 0.5 + this.countB22Forma * 0.25 + this.countB32Forma * 0.1 + this.countB42Forma * 0.05;
            Double iGeral = iRestrito + iNao_Restrito;
            String nomeCompleto = this.repo.findNomeCompleto(idCurriculo);

            list.put("nomeCompleto", nomeCompleto);
            list.put("iRestrito_2_forma", String.format("%.2f",iRestrito));
            list.put("iNao_Restrito_2_forma", String.format("%.2f",iNao_Restrito));
            list.put("iGeral_2_forma", String.format("%.2f",iGeral));
//            listHash.add(list);

            this.countA12Forma = 0.0;
            this.countA22Forma = 0.0;
            this.countA32Forma = 0.0;
            this.countA42Forma = 0.0;
            this.countB12Forma = 0.0;
            this.countB22Forma = 0.0;
            this.countB32Forma = 0.0;
            this.countB42Forma = 0.0;
//        });
        return list;
    }

    public List<HashMap<String, String>> indicesPPGCC(Integer ano_inicio, Integer ano_final) {
        List<HashMap<String, String>> listHash = new ArrayList<>();
        List<Long> curriculos = this.repo.findAllIdMestrado();
        HashMap<String, String> list = new HashMap<>();
        curriculos.stream().forEach(idCurriculo -> {
            List<String> estratosCurriculo = this.qualisRepo.estratosCurriculo(idCurriculo, ano_inicio, ano_final);
            estratosCurriculo.stream().forEach(estrato -> {
                if (estrato.equalsIgnoreCase("A1")) {
                    this.countA1Total++;
                } else if (estrato.equalsIgnoreCase("A2")) {
                    this.countA2Total++;
                } else if (estrato.equalsIgnoreCase("A3")) {
                    this.countA3Total++;
                } else if (estrato.equalsIgnoreCase("A4")) {
                    this.countA4Total++;
                } else if (estrato.equalsIgnoreCase("B1")) {
                    this.countB1Total++;
                } else if (estrato.equalsIgnoreCase("B2")) {
                    this.countB2Total++;
                } else if (estrato.equalsIgnoreCase("B3")) {
                    this.countB3Total++;
                } else if (estrato.equalsIgnoreCase("B4")) {
                    this.countB4Total++;
                }
            });

        });

        Double iRestrito = this.countA1Total * 1 + this.countA2Total * 0.85 + this.countA3Total * 0.725 + this.countA4Total * 0.625;
        Double iNao_Restrito = this.countB1Total * 0.5 + this.countB2Total * 0.25 + this.countB3Total * 0.1 + this.countB4Total * 0.05;
        Double iGeral = iRestrito + iNao_Restrito;

        list.put("iRestrito_PPGCC", String.format("%.2f", iRestrito));
        list.put("iNao_Restrito_PPGCC", String.format("%.2f", iNao_Restrito));
        list.put("iGeral_PPGCC", String.format("%.2f", iGeral));
        listHash.add(list);
        this.countA1Total = 0;
        this.countA2Total = 0;
        this.countA3Total = 0;
        this.countA4Total = 0;
        this.countB1Total = 0;
        this.countB2Total = 0;
        this.countB3Total = 0;
        this.countB4Total = 0;
        return listHash;

    }
//    public List<HashMap<String,String>> estratoCurriculoDoutorado(Integer ano_inicio,Integer ano_final){
//
//        List<HashMap<String,String>> listHash = new ArrayList<>();
//        List<Long> curriculos = this.repo.findAllIdDoutorado();
//        curriculos.stream().forEach(idCurriculo-> {
//            HashMap<String,String> list = new HashMap<>();
//            List < String > estratosCurriculo = this.qualisRepo.estratosCurriculo(idCurriculo,ano_inicio, ano_final);
//            estratosCurriculo.stream().forEach(estrato -> {
//                if (estrato.equalsIgnoreCase("A1")) {
//                    this.countA1++;
//                } else if (estrato.equalsIgnoreCase("A2")) {
//                    this.countA2++;
//                } else if (estrato.equalsIgnoreCase("A3")) {
//                    this.countA3++;
//                }else if (estrato.equalsIgnoreCase("A4")) {
//                    this.countA4++;
//                }else if (estrato.equalsIgnoreCase("B1")) {
//                    this.countB1++;
//                }else if (estrato.equalsIgnoreCase("B2")) {
//                    this.countB2++;
//                }else if (estrato.equalsIgnoreCase("B3")) {
//                    this.countB3++;
//                }else if (estrato.equalsIgnoreCase("B4")) {
//                    this.countB4++;
//                }
//            });
////            System.out.println(this.countA1);
//            Double iRestrito =  this.countA1 * 1 + this.countA2 * 0.85 + this.countA3 * 0.725 + this.countA4 * 0.625;
//            Double iNao_Restrito = this.countB1 * 0.5 + this.countB2 * 0.25 + this.countB3 * 0.1 + this.countB4 * 0.05;
//            Double iGeral = iRestrito + iNao_Restrito;
//            String nomeCompleto = this.repo.findNomeCompleto(idCurriculo);
//
//            list.put("nomeCompleto", nomeCompleto);
//            list.put("iRestrito", String.format("%.2f",iRestrito));
//            list.put("iNao_Restrito", String.format("%.2f",iNao_Restrito));
//            list.put("iGeral", String.format("%.2f",iGeral));
//            listHash.add(list);
//
//            this.countA1 = 0;
//            this.countA2 = 0;
//            this.countA3 = 0;
//            this.countA4 = 0;
//            this.countB1 = 0;
//            this.countB2 = 0;
//            this.countB3 = 0;
//            this.countB4 = 0;
//        });
//        return listHash;
//    }

//    public List<HashMap<String,String>> estratoCurriculoDoutorado2Forma(Integer ano_inicio,Integer ano_final){
//
//        List<HashMap<String,String>> listHash = new ArrayList<>();
//        List<Long> curriculos = this.repo.findAllIdDoutorado();
//        curriculos.stream().forEach(idCurriculo-> {
////            System.out.println(idCurriculo);
//            HashMap<String,String> list = new HashMap<>();
//            HashMap<String,Integer> estratoPeriodicos = this.AuxIndice2Forma(this.qualisRepo.estratosCurriculo2Forma(idCurriculo,ano_inicio,ano_final));
//            for(Map.Entry<String, Integer> entry : estratoPeriodicos.entrySet()) {
//                String key = entry.getKey();
//                Integer value = entry.getValue();
////                System.out.println(this.qualisRepo.countAutoresPeriodicoDoutorado(value));
//                Double quantidadeAutores = 1.0/this.qualisRepo.countAutoresPeriodicoDoutorado(value);
//                    if (key.equalsIgnoreCase("A1")) {
//                        this.countA12Forma += quantidadeAutores;
//                    } else if (key.equalsIgnoreCase("A2")) {
//                        this.countA22Forma += quantidadeAutores;
//                    } else if (key.equalsIgnoreCase("A3")) {
//                        this.countA32Forma += quantidadeAutores;
//                    } else if (key.equalsIgnoreCase("A4")) {
//                        this.countA42Forma += quantidadeAutores;
//                    } else if (key.equalsIgnoreCase("B1")) {
//                        this.countB12Forma += quantidadeAutores;
//                    } else if (key.equalsIgnoreCase("B2")) {
//                        this.countB22Forma += quantidadeAutores;
//                    } else if (key.equalsIgnoreCase("B3")) {
//                        this.countB32Forma += quantidadeAutores;
//                    } else if (key.equalsIgnoreCase("B4")) {
//                        this.countB42Forma += quantidadeAutores;
//                    }
//            }
////            System.out.println(this.countA1);
//            Double iRestrito =  this.countA12Forma * 1 + this.countA22Forma * 0.85 + this.countA32Forma * 0.725 + this.countA42Forma * 0.625;
//            Double iNao_Restrito = this.countB12Forma * 0.5 + this.countB22Forma * 0.25 + this.countB32Forma * 0.1 + this.countB42Forma * 0.05;
//            Double iGeral = iRestrito + iNao_Restrito;
////            String nomeCompleto = this.repo.findNomeCompleto(idCurriculo);
//
////            list.put("nomeCompleto", nomeCompleto);
//            list.put("iRestrito_2_forma", String.format("%.2f",iRestrito));
//            list.put("iNao_Restrito_2_forma", String.format("%.2f",iNao_Restrito));
//            list.put("iGeral_2_forma", String.format("%.2f",iGeral));
//            listHash.add(list);
//
//            this.countA12Forma = 0.0;
//            this.countA22Forma = 0.0;
//            this.countA32Forma = 0.0;
//            this.countA42Forma = 0.0;
//            this.countB12Forma = 0.0;
//            this.countB22Forma = 0.0;
//            this.countB32Forma = 0.0;
//            this.countB42Forma = 0.0;
//        });
//        return listHash;
//    }

    public List<HashMap<String,String>> estratosCurriculosDoutorado(Integer ano_inicio, Integer ano_final){
        List<Long> curriculos = this.repo.findAllIdDoutorado();
        List<HashMap<String,String>> listHash = new ArrayList<>();
        curriculos.stream().forEach(idCurriculo-> {
            HashMap<String,String> teste = new HashMap<>();
            teste.putAll(estratoCurriculoDoutorado2FormaTeste(idCurriculo,ano_inicio,ano_final));
            teste.putAll(estratoCurriculoDoutoradoTeste(idCurriculo,ano_inicio,ano_final));
            listHash.add(teste);
        });
        return listHash;
    }

    public HashMap<String,String> estratoCurriculoDoutoradoTeste(Long idCurriculo,Integer ano_inicio,Integer ano_final){

//        List<HashMap<String,String>> listHash = new ArrayList<>();
//        List<Long> curriculos = this.repo.findAllIdDoutorado();
//        curriculos.stream().forEach(idCurriculo-> {
            HashMap<String,String> list = new HashMap<>();
            List < String > estratosCurriculo = this.qualisRepo.estratosCurriculo(idCurriculo,ano_inicio, ano_final);
            estratosCurriculo.stream().forEach(estrato -> {
                if (estrato.equalsIgnoreCase("A1")) {
                    this.countA1++;
                } else if (estrato.equalsIgnoreCase("A2")) {
                    this.countA2++;
                } else if (estrato.equalsIgnoreCase("A3")) {
                    this.countA3++;
                }else if (estrato.equalsIgnoreCase("A4")) {
                    this.countA4++;
                }else if (estrato.equalsIgnoreCase("B1")) {
                    this.countB1++;
                }else if (estrato.equalsIgnoreCase("B2")) {
                    this.countB2++;
                }else if (estrato.equalsIgnoreCase("B3")) {
                    this.countB3++;
                }else if (estrato.equalsIgnoreCase("B4")) {
                    this.countB4++;
                }
            });
//            System.out.println(this.countA1);
            Double iRestrito =  this.countA1 * 1 + this.countA2 * 0.85 + this.countA3 * 0.725 + this.countA4 * 0.625;
            Double iNao_Restrito = this.countB1 * 0.5 + this.countB2 * 0.25 + this.countB3 * 0.1 + this.countB4 * 0.05;
            Double iGeral = iRestrito + iNao_Restrito;
            String nomeCompleto = this.repo.findNomeCompleto(idCurriculo);

            list.put("nomeCompleto", nomeCompleto);
            list.put("iRestrito", String.format("%.2f",iRestrito));
            list.put("iNao_Restrito", String.format("%.2f",iNao_Restrito));
            list.put("iGeral", String.format("%.2f",iGeral));
//            listHash.add(list);

            this.countA1 = 0;
            this.countA2 = 0;
            this.countA3 = 0;
            this.countA4 = 0;
            this.countB1 = 0;
            this.countB2 = 0;
            this.countB3 = 0;
            this.countB4 = 0;
//        });
        return list;
    }

    public HashMap<String,String> estratoCurriculoDoutorado2FormaTeste(Long idCurriculo, Integer ano_inicio,Integer ano_final){

        List<HashMap<String,String>> listHash = new ArrayList<>();
//        List<Long> curriculos = this.repo.findAllIdDoutorado();
//        curriculos.stream().forEach(idCurriculo-> {
//            System.out.println(idCurriculo);
            HashMap<String,String> list = new HashMap<>();
            HashMap<Integer,String> estratoPeriodicos = this.AuxIndice2Forma(this.qualisRepo.estratosCurriculo2Forma(idCurriculo,ano_inicio,ano_final), this.qualisRepo.estratosCurriculoEventos2Forma(idCurriculo,ano_inicio,ano_final));
            for(Map.Entry<Integer, String> entry : estratoPeriodicos.entrySet()) {
                Integer key = entry.getKey();
                String value = entry.getValue();
//                System.out.println(this.qualisRepo.countAutoresPeriodicoDoutorado(value));
                Double quantidadeAutores = 1.0/this.qualisRepo.countAutoresPeriodicoDoutorado(key);
                if (value.equalsIgnoreCase("A1")) {
                    this.countA12Forma += quantidadeAutores;
                } else if (value.equalsIgnoreCase("A2")) {
                    this.countA22Forma += quantidadeAutores;
                } else if (value.equalsIgnoreCase("A3")) {
                    this.countA32Forma += quantidadeAutores;
                } else if (value.equalsIgnoreCase("A4")) {
                    this.countA42Forma += quantidadeAutores;
                } else if (value.equalsIgnoreCase("B1")) {
                    this.countB12Forma += quantidadeAutores;
                } else if (value.equalsIgnoreCase("B2")) {
                    this.countB22Forma += quantidadeAutores;
                } else if (value.equalsIgnoreCase("B3")) {
                    this.countB32Forma += quantidadeAutores;
                } else if (value.equalsIgnoreCase("B4")) {
                    this.countB42Forma += quantidadeAutores;
                }
            }
//            System.out.println(this.countA1);
            Double iRestrito =  this.countA12Forma * 1 + this.countA22Forma * 0.85 + this.countA32Forma * 0.725 + this.countA42Forma * 0.625;
            Double iNao_Restrito = this.countB12Forma * 0.5 + this.countB22Forma * 0.25 + this.countB32Forma * 0.1 + this.countB42Forma * 0.05;
            Double iGeral = iRestrito + iNao_Restrito;
//            String nomeCompleto = this.repo.findNomeCompleto(idCurriculo);

//            list.put("nomeCompleto", nomeCompleto);
            list.put("iRestrito_2_forma", String.format("%.2f",iRestrito));
            list.put("iNao_Restrito_2_forma", String.format("%.2f",iNao_Restrito));
            list.put("iGeral_2_forma", String.format("%.2f",iGeral));

            this.countA12Forma = 0.0;
            this.countA22Forma = 0.0;
            this.countA32Forma = 0.0;
            this.countA42Forma = 0.0;
            this.countB12Forma = 0.0;
            this.countB22Forma = 0.0;
            this.countB32Forma = 0.0;
            this.countB42Forma = 0.0;
//        });
        return list;
    }

    public List<HashMap<String, String>> indicesDCCMAPI(Integer ano_inicio, Integer ano_final) {
        List<HashMap<String,String>> listHash = new ArrayList<>();
        List<String> periodicos= new ArrayList<>();
        List<String> nomesTrabalhos = new ArrayList<>();
        List<Long> curriculos = this.repo.findAllIdDoutorado();
//
        HashMap<String, String> list = new HashMap<>();
        curriculos.stream().forEach(idCurriculo -> {
            this.TotalDiscenteDoutorado++;
            HashMap<String,String> periodicosCurriculo = this.AuxIndiceDCCMAPI(this.qualisRepo.periodicosCurriculo(idCurriculo,ano_inicio,ano_final));
            for(Map.Entry<String, String> entry : periodicosCurriculo.entrySet()){
                String key = entry.getKey();
                String value = entry.getValue();
                if(!nomesTrabalhos.contains(key)){
                    periodicos.add(value);
                    nomesTrabalhos.add(key);
                    if(this.qualisRepo.estratoPeriodico(value).equalsIgnoreCase("A1")) {
                        this.countA1Total++;
                    }else if(this.qualisRepo.estratoPeriodico(value).equalsIgnoreCase("A2")){
                        this.countA2Total++;
                    }else if(this.qualisRepo.estratoPeriodico(value).equalsIgnoreCase("A3")){
                        this.countA3Total++;
                    }else if(this.qualisRepo.estratoPeriodico(value).equalsIgnoreCase("A4")){
                        this.countA4Total++;
                    }else if(this.qualisRepo.estratoPeriodico(value).equalsIgnoreCase("B1")){
                        this.countB1Total++;
                    }else if(this.qualisRepo.estratoPeriodico(value).equalsIgnoreCase("B2")){
                        this.countB2Total++;
                    }else if(this.qualisRepo.estratoPeriodico(value).equalsIgnoreCase("B3")){
                        this.countB3Total++;
                    }else if(this.qualisRepo.estratoPeriodico(value).equalsIgnoreCase("B4")){
                        this.countB4Total++;
                    }
                }
            };
//
        });
        this.iRestritoTotal = this.countA1Total * 1 + this.countA2Total * 0.85 + this.countA3Total * 0.725 + this.countA4Total * 0.625;
        this.iNaoRestritoTotal = this.countB1Total * 0.5 + this.countB2Total * 0.25 + this.countB3Total * 0.1 + this.countB4Total * 0.05;
        this.iGeralTotal = iRestritoTotal + iNaoRestritoTotal;

        list.put("iRestrito_DCC", String.format("%.2f", (iRestritoTotal/TotalDiscenteDoutorado)));
        list.put("iNao_Restrito_DCC", String.format("%.2f", iNaoRestritoTotal/TotalDiscenteDoutorado));
        list.put("iGeral_DCC", String.format("%.2f", iGeralTotal/TotalDiscenteDoutorado));
        listHash.add(list);

        this.countA1Total = 0;
        this.countA2Total = 0;
        this.countA3Total = 0;
        this.countA4Total = 0;
        this.countB1Total = 0;
        this.countB2Total = 0;
        this.countB3Total = 0;
        this.countB4Total = 0;
        this.iRestritoTotal = 0.0;
        this.iGeralTotal = 0.0;
        this.TotalDiscenteDoutorado = 0;
        return listHash;

    }

    public HashMap<Integer,String> AuxIndice2Forma(List<Object[]> result,List<Object[]> result2){
//        System.out.println(result);
        HashMap<Integer,String> resultado = new HashMap<Integer,String>();
        for(Object[] borderTypes: result){
            resultado.put((Integer)borderTypes[0], (String)borderTypes[1]);
        }
        for(Object[] borderTypes: result2){
            resultado.put((Integer)borderTypes[0], (String)borderTypes[1]);
        }
        return resultado;
    }

    public HashMap<String,String> AuxIndiceDCCMAPI(List<Object[]> result){
//        System.out.println(result);
        HashMap<String,String> resultado = new HashMap<String,String>();
        for(Object[] borderTypes: result){
            resultado.put((String)borderTypes[0], (String)borderTypes[1]);
        }
        return resultado;
    }


}
