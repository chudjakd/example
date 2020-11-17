package org.acme.webSockets.logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.json.JsonObject;
import org.acme.model.Fruit;
import org.acme.repository.FruitRepositoryWithEMF;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class FruitSocketLogicRepository {

    ObjectMapper mapper  = new ObjectMapper();

    @Inject
    FruitRepositoryWithEMF fruitRepository;

    public String allFruitFromDBInit(){
        try {
            JsonObject combine= new JsonObject();
            combine.put("Data",fruitRepository.getAll());
            combine.put("Response","ResponseGetAllFruit");

            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(combine);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public String createFruit(JsonObject jsonFromWebSocket){



        Fruit fruit= new Fruit();
        fruit.setName(jsonFromWebSocket.getJsonObject("data").getString("name"));
        fruit.setSeason(jsonFromWebSocket.getJsonObject("data").getString("season"));
        fruitRepository.save(fruit);

        try {

            JsonObject combine= new JsonObject();
            combine.put("Data",fruitRepository.getAll());
            combine.put("Response","ResponseGetAllFruit");

            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(combine);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public String allFruitFromDB(){
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(fruitRepository.getAll());
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public String updateFruit(JsonObject jsonFromWebSocket){

        Fruit fruit= new Fruit();
        fruit.setName(jsonFromWebSocket.getJsonObject("data").getString("name"));
        fruit.setSeason(jsonFromWebSocket.getJsonObject("data").getString("season"));
        Long id=jsonFromWebSocket.getJsonObject("data").getLong("id");

        fruitRepository.update(fruit,id);

        try {

            JsonObject combine= new JsonObject();
            combine.put("Data",fruitRepository.getAll());
            combine.put("Response","ResponseGetAllFruit");

            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(combine);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public String deleteFruit(JsonObject jsonFromWebSocket){

        Long id=jsonFromWebSocket.getJsonObject("data").getLong("id");

        fruitRepository.delete(id);

        try {

            JsonObject combine= new JsonObject();
            combine.put("Data",fruitRepository.getAll());
            combine.put("Response","ResponseGetAllFruit");

            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(combine);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
