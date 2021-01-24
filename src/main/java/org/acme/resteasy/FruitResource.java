package org.acme.resteasy;


import org.acme.model.Fruit;
import org.acme.repository.FruitRepositoryWithEMF;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/fruit")
@ApplicationScoped
public class FruitResource {

//    @Inject
//    Validator validator
    private static final Logger LOG= Logger.getLogger(FruitResource.class);

    @Inject
    FruitRepositoryWithEMF fruitRepositoryWithEMF;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createFruit(Fruit fruit){

        //TODO odskusat @Valid do pici akosi to nejde
        //TODO pozriet tie viacere datasources v PDF dokumente strana 13
        try{
//            Set<ConstraintViolation<Fruit>> violations =
//                    validator.validate(fruit);
//            System.out.println(violations.isEmpty());
            fruitRepositoryWithEMF.save(fruit);
            System.out.println("Vlozenie uspesne");
        }catch (ConstraintViolationException e){
            System.out.println("Bol tam nejaky problem:"+e);
        }


        return Response.created(URI.create(fruit.season)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllFruit(){

        LOG.info("HELLO INFO");
        return Response.ok(fruitRepositoryWithEMF.getAll()).build();
    }
}
