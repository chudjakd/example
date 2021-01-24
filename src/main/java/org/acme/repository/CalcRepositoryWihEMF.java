package org.acme.repository;


import org.acme.model.Calc;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@ApplicationScoped
public class CalcRepositoryWihEMF {

    @Inject
    EntityManager entityManager;

    public List<Calc> getAllCalcFromDB(){
        Query query= entityManager.createNativeQuery("select * from calc",Calc.class);
        return query.getResultList();
    }

    public Calc getCalcById(Long id){
        return entityManager.find(Calc.class,id);
    }

    public Calc getCalcByCount(int count){
        Query query= entityManager.createNativeQuery("SELECT * FROM CALC WHERE countofnumbers=:count",Calc.class);
        query.setParameter("count",count);
        return (Calc)query.getResultList().get(0);
    }

}
