package org.acme.webSockets.logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.json.JsonObject;
import org.acme.model.Fruit;
import org.acme.repository.FruitRepository;
import org.acme.repository.FruitRepositoryWithEMF;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

//---ERROR WITH ENTITY MANAGER------//
// ERROR [io.und.web.jsr.request] (vert.x-eventloop-thread-0) UT026013:
// Unhandled error in annotated endpoint org.acme.webSockets.FruitSocket@76bf128e: java.lang.IllegalStateException: You have attempted to
// perform a blocking operation on a IO thread. This is not allowed, as blocking the IO thread will cause major performance issues with your application.
// If you want to perform blocking EntityManager operations make sure you are doing it from a worker thread.

@ApplicationScoped
public class FruitSocketLogic {

    @Inject
    FruitSocketLogicRepository fruitSocketLogicRepository;


    public String getTypeAndMakeDecision(String message){

        if(message.equals("allFruitFromDB")){
           return fruitSocketLogicRepository.allFruitFromDBInit();
        }

        JsonObject jsonFromWebSocket= new JsonObject(message);
        String type=jsonFromWebSocket.getString("type");

        if(type.equals("create-fruit")){
            return fruitSocketLogicRepository.createFruit(jsonFromWebSocket);
        }
        else if(type.equals("allFruitFromDB")){
           return fruitSocketLogicRepository.allFruitFromDB();
        }
        else if(type.equals("update-fruit")){
            return fruitSocketLogicRepository.updateFruit(jsonFromWebSocket);
        }
        else if(type.equals("delete-fruit")){
            return fruitSocketLogicRepository.deleteFruit(jsonFromWebSocket);
        }
        else{
            //HMM CO TO JE
            return null;
        }

    }

}
