package org.acme.model;

import io.quarkus.hibernate.orm.PersistenceUnit;

import javax.persistence.*;

@Entity(name ="calc")
public class Calc  {

    @Id
    @SequenceGenerator(name = "calcSeq", sequenceName = "calc_id_seq", allocationSize = 1, initialValue = 3)
    @GeneratedValue(generator = "calcSeq")
    private Long id;
    public int number1;
    public int number2;
    @Column(name = "countofnumbers")
    public int countofnumbers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCountofnumbers() {
        return countofnumbers;
    }

    public void setCountofnumbers(int count) {
        this.countofnumbers = count;
    }

    public void countTheResult(){
        this.countofnumbers =this.number1+this.number2;
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
                ", countOfNumbers=" + countofnumbers +
                '}';
    }
}
