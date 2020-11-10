package org.acme.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Calc  {

    @Id
    @SequenceGenerator(name = "calcSeq", sequenceName = "calc_id_seq", allocationSize = 1, initialValue = 3)
    @GeneratedValue(generator = "calcSeq")
    private Long id;
    public int number1;
    public int number2;
    public int count;

    public void countTheResult(){
        this.count=this.number1+this.number2;
    }

    public int getNumber1() {
        return number1;
    }

    public void setNumber1(int number1) {
        this.number1 = number1;
    }

    public int getNumber2() {
        return number2;
    }

    public void setNumber2(int number2) {
        this.number2 = number2;
    }

    @Override
    public String toString() {
        return "Calc{" +
                "id=" + id +
                ", number1=" + number1 +
                ", number2=" + number2 +
                ", count=" + count +
                '}';
    }
}
