package org.acme.dao.model;

import org.acme.dao.api.Dao;
import org.acme.model.Fruit;

import java.util.List;
import java.util.Optional;

public class FruitDao implements Dao<Fruit> {
    @Override
    public Optional<Fruit> getById(long id) {
        return Optional.empty();
    }

    @Override
    public List<Fruit> getAll() {
        return null;
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
