package org.acme.webSockets;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.acme.model.Fruit;
import org.acme.repository.FruitRepository;

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
    FruitRepository fruitRepository;
    @Inject
    EntityManagerFactory entityManagerFactory;

    @OnOpen
    public void onOpen(Session session) {
        sessions.put("username", session);
        broadcast("User " + "username" + " joined");
    }
    @OnMessage
    public void onMessage(String message) {
        broadcast(">> " + "username" + ": " + message);
    }

    private void broadcast(String message) {
        Fruit fruit= new Fruit(1L,"Watermelon","Picovinka");
        Fruit fruit2= new Fruit(2L,"Orange","Jar");
        List<Fruit> allFruits= new ArrayList<>();
        allFruits.add(fruit);
        allFruits.add(fruit2);
//        getDataFromTable();
        EntityManager entityManager=entityManagerFactory.createEntityManager();

        Query query=entityManager.createNativeQuery("SELECT * FROM Fruit ",Fruit.class);

        System.out.println(query.getResultList());
        sessions.values().forEach(s -> {
            try {
                s.getAsyncRemote().sendObject(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(allFruits), result ->  {
                    if (result.getException() != null) {
                        System.out.println("Unable to send message: " + result.getException());
                    }
                });
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
    }

    public void getDataFromTable(){
        fruitRepository.getAll();
    }


}
