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

    public Integer countA1 = 0;
    public Integer countA2 = 0;
    public Integer countB1 = 0;
    public Integer countB2 = 0;
    public Integer countB3 = 0;
    public Integer countB4 = 0;
    public Integer countB5 = 0;
    public Integer countC = 0;

    public Curriculos findByCurriculo(Long idCurriculo){
        return this.repo.findByCurriculo(idCurriculo);
    }

    public List<HashMap<String,String>> estratoCurriculo(Integer ano_inicio,Integer ano_final){

        List<HashMap<String,String>> listHash = new ArrayList<>();
        List<Long> curriculos = this.repo.findAllIdMestrado();
        curriculos.stream().forEach(idCurriculo-> {
                HashMap<String,String> list = new HashMap<>();
                List < String > estratosCurriculo = this.qualisRepo.estratosCurriculo(idCurriculo,ano_inicio, ano_final);
                estratosCurriculo.stream().forEach(estrato -> {
                    if (estrato.equalsIgnoreCase("A1")) {
                        this.countA1++;
                    } else if (estrato.equalsIgnoreCase("A2")) {
                        this.countA2++;
                    } else if (estrato.equalsIgnoreCase("B1")) {
                        this.countB1++;
                    }
                });

                Double nxRestrito = this.countA1 * 1 + this.countA2 * 0.85 + this.countB1 * 0.7;
                Double nxGeral = this.countA1 * 1 + this.countA2 * 0.85 + this.countB1 * 0.7;
                Double iRestrito = this.countA1 * 1 + this.countA2 * 0.85 + this.countB1 * 0.7;
                Double iGeral = this.countA1 * 1 + this.countA2 * 0.85 + this.countB1 * 0.7;
                String nomeCompleto = this.repo.findNomeCompleto(idCurriculo);

                list.put("nomeCompleto", nomeCompleto);
                list.put("NxRestrito", nxRestrito.toString());
                list.put("NxGeral", nxGeral.toString());
                list.put("iRestrito", iRestrito.toString());
                list.put("iGeral", iGeral.toString());
                listHash.add(list);

            this.countA1 = 0;
            this.countA2 = 0;
            this.countB1 = 0;

            });
        return listHash;
    }
}
