package org.acme.repository;

import org.acme.dao.api.Dao;
import org.acme.model.Fruit;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class FruitRepository implements Dao<Fruit> {

    @Inject
    EntityManager entityManager;


    @Override
    public Fruit getById(long id) {
        Fruit fruit= entityManager.find(Fruit.class,id);
        return fruit;
    }

    @Override
    @Transactional
    public List<Fruit> getAll() {
        Query getAllFruitsFromDB=entityManager.createNativeQuery("select * from fruit",Fruit.class);
        List<Fruit> allFruits=getAllFruitsFromDB.getResultList();
        System.out.println(allFruits);
        return allFruits;
    }

    public Fruit getFruitByName(String name){
        Query query= entityManager.createNativeQuery("SELECT * FROM FRUIT WHERE NAME=:name",Fruit.class);
        query.setParameter("name",name);
        return (Fruit)query.getResultList().get(0);
    }

    @Override
    @Transactional
    public void save(Fruit fruit) {

        entityManager.persist(fruit);

    }

    @Override
    public void update(Fruit fruit, long id) {

    }

    @Override
    public void delete(long id) {

    }
}
