package org.acme.repository;


import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.acme.model.Calc;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class CalcRepository implements PanacheRepository<Calc> {



    public List<Calc> getAllCalc(){
        return listAll();
    }

    public Calc getById(Long id){
        return findById(id);
    }

    public void createCalc(Calc calc){
        calc.countTheResult();
        System.out.println("-------------------------Vytvaram Calc--------------------------------");
        System.out.println(calc);
        persist(calc);
    }

    public boolean deleteCalcById(Long id){
       return deleteById(id);
    }

    public void updateCalc(Calc calc,Long id){
        int biac=update("number1= ?1,number2=?2 where id=?3",calc.getNumber1(),calc.getNumber2(),id);
    }

}
