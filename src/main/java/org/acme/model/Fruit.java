package org.acme.model;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.reactivex.pgclient.PgPool;
import io.vertx.reactivex.sqlclient.Row;
import io.vertx.reactivex.sqlclient.RowSet;
import io.vertx.reactivex.sqlclient.Tuple;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.util.List;
import java.util.stream.StreamSupport;

@Entity
public class Fruit {
    @Id
    @SequenceGenerator(name = "fruitSeq", sequenceName = "fruit_id_seq", allocationSize = 1, initialValue = 9)
    @GeneratedValue(generator = "fruitSeq")
    public Long id;
    public String name;
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
