package org.acme.resteasy;

import io.quarkus.vertx.web.ReactiveRoutes;
import io.quarkus.vertx.web.Route;
import io.quarkus.vertx.web.RoutingExchange;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;
import io.vertx.reactivex.core.http.HttpServerResponse;
import org.acme.model.Calc;
import org.acme.repository.CalcRepository;
import org.acme.repository.FruitRepository;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Random;

@Path("/calc")
public class CalcResource {

    @Inject
    CalcRepository calcRepository;


    public void maybeFail(){

        System.out.println("------I AM TRYING PLEASE DONT KILL ME-----");

        if(new Random().nextBoolean()){

            throw new RuntimeException("Selhali sme");
        }
    }

    @Route(path = "/helloTry", methods = HttpMethod.GET)
    Multi<String> hello(RoutingExchange re){
        //System.out.println(rc);
        System.out.println(re);
        return ReactiveRoutes.asJsonArray(Multi.createFrom().items("HELLOOOOO WORLRRRRLLLD","AAAAAAA"));

    }


    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Calc createCalc(Calc calc){
        calcRepository.createCalc(calc);
        return calc;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Calc getCalcById(@PathParam("id")String id){

        try{
            Calc calc=calcRepository.getById(Long.parseLong(id));
            if(calc==null){
                throw new WebApplicationException(Response.Status.NOT_FOUND);
            }
            return calc;
        }catch (Exception e){
            System.out.println(e);
            return null;
        }

    }

//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    //@Retry(maxRetries = 5)
//    @Timeout(3000)
//    public Response getAllCalcWithResponse() {
//        long started=System.currentTimeMillis();
//        //this.maybeFail();
//
//        try{
//            randomDelay();
//            System.out.println(System.currentTimeMillis()-started);
//            return Response.ok(calcRepository.getAllCalc()).build();
//        }catch (InterruptedException e){
//            System.out.println("Sme v picisku");
//            return null;
//        }
//
//
//    }

//    private void randomDelay() throws InterruptedException{
//        Thread.sleep(new Random().nextInt(5000));
//    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCalcWithResponse() {
        try{
            return Response.ok(calcRepository.getAllCalc()).build();
        }catch (Exception e){
            System.out.println("Sme v picisku");
            return null;
        }


    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/count/{count}")
    public Calc getCalcByCount(@PathParam("count")int count){
        return calcRepository.getCalcByCount(count);
    }

    @PUT
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public void updateCalc(Calc calc,@PathParam("id")String id){
        calcRepository.updateCalc(calc,Long.parseLong(id));
    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional
    @Path("/{id}")
    public String deleteCalcById(@PathParam("id") String id){
        boolean deleted=calcRepository.deleteCalcById(Long.parseLong(id));
       return String.valueOf(deleted);
    }




}