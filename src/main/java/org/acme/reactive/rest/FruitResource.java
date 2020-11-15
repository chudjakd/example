package org.acme.reactive.rest;


import io.vertx.reactivex.pgclient.PgPool;
import org.acme.model.Fruit;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/fruits")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FruitResource {

    @Inject
    PgPool client;

    @GET
    public void getAllFruits(){
        Fruit.getAllFruits(client);
    }

}
