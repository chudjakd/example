package org.acme.repository;

import org.acme.dao.api.Dao;
import org.acme.model.Fruit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class FruitRepositoryWithEMF implements Dao<Fruit> {

    EntityManager entityManager=null;

    @Inject
    EntityManagerFactory entityManagerFactory;

    @PostConstruct
    private void createEntityManager(){
        entityManager=entityManagerFactory.createEntityManager();
    }

    @PreDestroy
    private void closeEntityManager(){
        if(entityManager!=null){
            entityManager.close();
        }
    }

    @Override
    public Fruit getById(long id) {
        Fruit fruit= entityManager.find(Fruit.class,id);
        return fruit;
    }

    @Override
    public List<Fruit> getAll() {

        Query getAllFruitsFromDB=entityManager.createNativeQuery("select * from fruit",Fruit.class);
        List<Fruit> allFruits=getAllFruitsFromDB.getResultList();
        return allFruits;
    }

    @Override
    public void save(Fruit fruit) {
        entityManager.getTransaction().begin();
        entityManager.persist(fruit);
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(Fruit fruit, long id) {

        Fruit updatedFruit=entityManager.find(Fruit.class,id);
        entityManager.getTransaction().begin();
        updatedFruit.setSeason(fruit.season);
        updatedFruit.setName(fruit.name);
        entityManager.getTransaction().commit();

    }

    @Override
    public void delete(long id) {
        Fruit fruit= entityManager.find(Fruit.class,id);
        entityManager.getTransaction().begin();
        entityManager.remove(fruit);
        entityManager.getTransaction().commit();
    }
}
