package com.Dashboard.dashboard.api.repository.custom;

import com.Dashboard.dashboard.api.model.ArtigoEventoAutores;
import com.Dashboard.dashboard.api.model.PeriodicosAutores;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PeriodicosQueryImpl implements PeriodicosQuery {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<PeriodicosAutores> periodicosFiltroPaginado(Long fk_curriculo, Pageable pageable){
        List<PeriodicosAutores> periodicosAutores = periodicosFiltro(fk_curriculo,pageable);
        Long total = periodicosFiltroTotal(fk_curriculo,pageable);
        return new PageImpl<>(periodicosAutores,pageable,total);
    }

    public List<PeriodicosAutores> periodicosFiltro(Long fk_curriculo, Pageable pageable){
        StringBuilder hql = new StringBuilder();

        hql.append("select c from PeriodicosAutores c where 1=1 ");
        HashMap<String,Object> param = getFiltro(hql, fk_curriculo);
        hql.append(" order by c.fkPeriodicos.ANO_DO_ARTIGO desc");

        TypedQuery<PeriodicosAutores> query = entityManager.createQuery(hql.toString(), PeriodicosAutores.class);

        for (Map.Entry<String, Object> value : param.entrySet()) {
            query.setParameter(value.getKey(), value.getValue());
        }
        query.setMaxResults(pageable.getPageSize());
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());

        return query.getResultList();

    }

    public Long periodicosFiltroTotal(Long fk_curriculo, Pageable pageable){
        StringBuilder hql = new StringBuilder();

        hql.append("select count(c) from PeriodicosAutores c where 1=1 ");
        HashMap<String,Object> param = getFiltro(hql,fk_curriculo);

        Query query = entityManager.createQuery(hql.toString());

        for (Map.Entry<String, Object> value : param.entrySet()) {
            query.setParameter(value.getKey(), value.getValue());
        }
        return (Long) query.getSingleResult();
    }

    public HashMap<String,Object> getFiltro(StringBuilder hql, Long fk_curriculo){
        HashMap<String,Object> param = new HashMap<>();

        if(fk_curriculo != null){
            hql.append("and c.curriculo.id = :fkCurriculo");
            param.put("fkCurriculo", fk_curriculo);
        }
        return param;
    }
}
