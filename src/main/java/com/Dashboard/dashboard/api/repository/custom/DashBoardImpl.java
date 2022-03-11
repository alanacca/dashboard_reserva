package com.Dashboard.dashboard.api.repository.custom;

import com.Dashboard.dashboard.api.model.CapitulosAutores;
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

public class DashBoardImpl implements DashBoardQuery{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<CapitulosAutores> capitulosPaginado(Long fk_curriculo, Pageable pageable){
        List<CapitulosAutores> capitulosAutores = capitulosFiltro(fk_curriculo,pageable);
        Long total = capitulosFiltroTotal(fk_curriculo,pageable);
        return new PageImpl<>(capitulosAutores,pageable,total);
    }

    public List<CapitulosAutores> capitulosFiltro(Long fk_curriculo, Pageable pageable){
        StringBuilder hql = new StringBuilder();

        hql.append("select c from CapitulosAutores c where 1=1 ");
        HashMap<String,Object> param = getFiltro(hql, fk_curriculo);
        hql.append("order by c.fkCapitulo.ANO_TRABALHO");

        TypedQuery<CapitulosAutores> query = entityManager.createQuery(hql.toString(), CapitulosAutores.class);

        for (Map.Entry<String, Object> value : param.entrySet()) {
            query.setParameter(value.getKey(), value.getValue());
        }
        query.setMaxResults(pageable.getPageSize());
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());

        return query.getResultList();

    }

    public Long capitulosFiltroTotal(Long fk_curriculo, Pageable pageable){
        StringBuilder hql = new StringBuilder();

        hql.append("select count(c) from CapitulosAutores c where 1=1");
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
