package org.acme.resteasy;


import org.acme.model.Fruit;
import org.acme.repository.FruitRepositoryWithEMF;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Set;

@Path("/fruit")
@ApplicationScoped
public class FruitResource {

//    @Inject
//    Validator validator

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

        return Response.ok(fruitRepositoryWithEMF.getAll()).build();
    }
}
