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
    public Optional<Fruit> getById(long id) {
        return Optional.empty();
    }

    @Override
    @Transactional
    public List<Fruit> getAll() {
        Query getAllFruitsFromDB=entityManager.createNativeQuery("select * from fruit",Fruit.class);
        List<Fruit> allFruits=getAllFruitsFromDB.getResultList();
        System.out.println(allFruits);
        return allFruits;
    }

    @Override
    public void save(Fruit fruit) {

    }

    @Override
    public void update(Fruit fruit, long id) {

    }

    @Override
    public void delete(Fruit fruit) {

    }
}
