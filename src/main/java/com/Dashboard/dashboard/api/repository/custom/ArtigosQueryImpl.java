package com.Dashboard.dashboard.api.repository.custom;

import com.Dashboard.dashboard.api.model.ArtigoEventoAutores;
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

public class ArtigosQueryImpl implements ArtigosQuery{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<ArtigoEventoAutores> artigosFiltroPaginado(Long fk_curriculo, Pageable pageable){
        List<ArtigoEventoAutores> artigosAutores = artigosFiltro(fk_curriculo,pageable);
        Long total = artigosFiltroTotal(fk_curriculo,pageable);
        return new PageImpl<>(artigosAutores,pageable,total);
    }

    public List<ArtigoEventoAutores> artigosFiltro(Long fk_curriculo, Pageable pageable){
        StringBuilder hql = new StringBuilder();

        hql.append("select c from ArtigoEventoAutores c where 1=1 ");
        HashMap<String,Object> param = getFiltro(hql, fk_curriculo);
        hql.append(" order by c.fkArtigoEvento.ANO_TRABALHO desc");

        TypedQuery<ArtigoEventoAutores> query = entityManager.createQuery(hql.toString(), ArtigoEventoAutores.class);

        for (Map.Entry<String, Object> value : param.entrySet()) {
            query.setParameter(value.getKey(), value.getValue());
        }
        query.setMaxResults(pageable.getPageSize());
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());

        return query.getResultList();

    }

    public Long artigosFiltroTotal(Long fk_curriculo, Pageable pageable){
        StringBuilder hql = new StringBuilder();

        hql.append("select count(c) from ArtigoEventoAutores c where 1=1 ");
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
