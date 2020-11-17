package org.acme.webSockets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.json.JsonObject;
import org.acme.model.Fruit;
import org.acme.repository.FruitRepository;
import org.acme.repository.FruitRepositoryWithEMF;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/fruit/jazda/getbyid")
@ApplicationScoped
public class FruitSocketGetById {

    Map<String, Session> sessions= new ConcurrentHashMap<>();
    ObjectMapper mapper  = new ObjectMapper();

    @Inject
    FruitRepositoryWithEMF fruitRepository;

    @OnOpen
    public void onOpen(Session session) {
        sessions.put("username", session);
    }
    @OnMessage
    public void onMessage(String message) {
        broadcast(message);
    }

    private void broadcast(String message) {

        String dataToFrontend=getFruitById(message);

        sessions.values().forEach(s -> {

            s.getAsyncRemote().sendObject(dataToFrontend, result ->  {
                if (result.getException() != null) {
                    System.out.println("Unable to send message: " + result.getException());
                }
            });

        });
    }

    public String getFruitById(String message){
        JsonObject jsonFromWebSocket= new JsonObject(message);
        Long id=Long.parseLong(jsonFromWebSocket.getString("id"));
        try {
            Fruit fruit=fruitRepository.getById(id);
            String jsonStr= mapper.writeValueAsString(fruit);
            System.out.println(jsonStr);
            JsonObject combine= new JsonObject();
            combine.put("Data",jsonStr);
            combine.put("Response","ResponseGetById");
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(combine);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

}

