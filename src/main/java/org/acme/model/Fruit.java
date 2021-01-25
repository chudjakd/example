package org.acme.model;

import io.vertx.reactivex.pgclient.PgPool;
import io.vertx.reactivex.sqlclient.Row;
import io.vertx.reactivex.sqlclient.RowSet;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@Entity
public class Fruit {
    @Id
//    @SequenceGenerator(name = "fruitSeq", sequenceName = "fruit_id_seq", allocationSize = 1, initialValue = 9)
//    @GeneratedValue(generator = "fruitSeq")
    public Long id;
    @NotBlank(message = "Name must not be blank")
    public String name;
    @Max(value = 10)
    public String season;

    public Fruit() {
    }

    public Fruit(Long id, String name, String season) {
        this.id = id;
        this.name = name;
        this.season = season;
    }

    public Fruit(String name, String season) {
        this.name = name;
        this.season = season;
    }



    public void setName(String name) {
        this.name = name;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getSeason() {
        return season;
    }

    public static void getAllFruits(PgPool client){
         client.preparedQuery("SELECT * FROM FRUIT ORDER BY ID ASC").execute(asyncResult -> {
            if(asyncResult.succeeded()){
                RowSet<Row> result= asyncResult.result();
                System.out.println("This is result"+result);
            }else{
                System.out.println("Selhali sme toto je dovod: "+ asyncResult.cause().getMessage());
            }
            client.close();
        });
    }

//    public Uni<Long> saveFruit(PgPool client){
//        return client.preparedQuery("INSERT INTO fruit (name,season) VALUES ($1,$2) RETURNING (id)")
//                .execute(Tuple.of(name,season))
//    }
}
