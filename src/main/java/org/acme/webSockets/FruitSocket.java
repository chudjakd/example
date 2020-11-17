package org.acme.webSockets;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.acme.model.Fruit;
import org.acme.repository.FruitRepository;
import org.acme.webSockets.logic.FruitSocketLogic;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/fruit/jazda")
@ApplicationScoped
public class FruitSocket {

    Map<String, Session> sessions= new ConcurrentHashMap<>();
    ObjectMapper mapper  = new ObjectMapper();

    @Inject
    FruitSocketLogic fruitSocketLogic;

    @OnOpen
    public void onOpen(Session session) {
        sessions.put("username", session);
        broadcast("allFruitFromDB");
    }
    @OnMessage
    public void onMessage(String message) {
        broadcast(message);
    }

    private void broadcast(String message) {

        String dataToFrontend=fruitSocketLogic.getTypeAndMakeDecision(message);

        System.out.println("DATA TO FRONTEND: "+dataToFrontend);

        sessions.values().forEach(s -> {

                s.getAsyncRemote().sendObject(dataToFrontend, result ->  {
                    if (result.getException() != null) {
                        System.out.println("Unable to send message: " + result.getException());
                    }
                });

        });
    }

}
