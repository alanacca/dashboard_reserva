package com.Dashboard.dashboard.api.service;

import com.Dashboard.dashboard.api.model.Curriculos;
import com.Dashboard.dashboard.api.repository.AvaliacaoPeriodQualisAtualRepository;
import com.Dashboard.dashboard.api.repository.CurriculosRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

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
    private Integer TotalDiscenteDoutorado =0;

    public Curriculos findByCurriculo(Long idCurriculo) {
        return this.repo.findByCurriculo(idCurriculo);
    }

    public List<HashMap<String, String>> estratoCurriculo(Integer ano_inicio, Integer ano_final) {

        List<HashMap<String, String>> listHash = new ArrayList<>();
        List<Long> curriculos = this.repo.findAllIdMestrado();
        curriculos.stream().forEach(idCurriculo -> {
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
            listHash.add(list);

            this.countA1 = 0;
            this.countA2 = 0;
            this.countA3 = 0;
            this.countA4 = 0;
            this.countB1 = 0;
            this.countB2 = 0;
            this.countB3 = 0;
            this.countB4 = 0;
        });
        return listHash;
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
    public List<HashMap<String,String>> estratoCurriculoDoutorado(Integer ano_inicio,Integer ano_final){

        List<HashMap<String,String>> listHash = new ArrayList<>();
        List<Long> curriculos = this.repo.findAllIdDoutorado();
        curriculos.stream().forEach(idCurriculo-> {
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
            listHash.add(list);

            this.countA1 = 0;
            this.countA2 = 0;
            this.countA3 = 0;
            this.countA4 = 0;
            this.countB1 = 0;
            this.countB2 = 0;
            this.countB3 = 0;
            this.countB4 = 0;
        });
        return listHash;
    }

    public List<HashMap<String, String>> indicesDCCMAPI(Integer ano_inicio, Integer ano_final) {
        List<HashMap<String, String>> listHash = new ArrayList<>();
        List<Long> curriculos = this.repo.findAllIdDoutorado();

        HashMap<String, String> list = new HashMap<>();
        curriculos.stream().forEach(idCurriculo -> {
            this.TotalDiscenteDoutorado++;
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
            Double iRestrito = this.countA1Total * 1 + this.countA2Total * 0.85 + this.countA3Total * 0.725 + this.countA4Total * 0.625;
            Double iNao_Restrito = this.countB1Total * 0.5 + this.countB2Total * 0.25 + this.countB3Total * 0.1 + this.countB4Total * 0.05;
            Double iGeral = iRestrito + iNao_Restrito;
            System.out.println("teste: "+iRestrito);
            this.iRestritoTotal +=iRestrito;
            this.iGeralTotal +=iGeral;
            this.countA1Total = 0;
            this.countA2Total = 0;
            this.countA3Total = 0;
            this.countA4Total = 0;
            this.countB1Total = 0;
            this.countB2Total = 0;
            this.countB3Total = 0;
            this.countB4Total = 0;

        });


        list.put("iRestrito_DCC", String.format("%.2f", (iRestritoTotal/TotalDiscenteDoutorado)));
//        list.put("iNao_Restrito_PPGCC", String.format("%.2f", iNao_Restrito));
        list.put("iGeral_DCC", String.format("%.2f", iGeralTotal/TotalDiscenteDoutorado));
        listHash.add(list);

        this.iRestritoTotal = 0.0;
        this.iGeralTotal = 0.0;
        return listHash;

    }
}
