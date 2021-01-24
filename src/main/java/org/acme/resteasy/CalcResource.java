package org.acme.resteasy;

import io.quarkus.vertx.web.ReactiveRoutes;
import io.quarkus.vertx.web.Route;
import io.quarkus.vertx.web.RoutingExchange;
import io.smallrye.mutiny.Multi;
import io.vertx.core.http.HttpMethod;
import org.acme.HttpProxy.GetRatesByHttpProxy;
import org.acme.json.TutorialisSkuska;
import org.acme.model.Calc;
import org.acme.repository.CalcRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Random;

@Path("/calc")
public class CalcResource {

    @Inject
    CalcRepository calcRepository;

    @Inject
    TutorialisSkuska parser;

    @Inject
    GetRatesByHttpProxy getRatesByHttpProxy;


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

    @GET
    @Path("/proxy")
    @Produces(MediaType.TEXT_PLAIN)
    public String skuskaProxy(){
        getRatesByHttpProxy.createHttpProxy();
        return "Jazda";
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
    @Path("/{id : \\d+}")
    public Calc getCalcById(@PathParam("id")long id){

        try{
            Calc calc=calcRepository.getById(id);
            if(calc==null){
                throw new WebApplicationException(Response.Status.NOT_FOUND);
            }
            return calc;
        }catch (Exception e){
            System.out.println(e);
            return null;
        }

    }

    @Path("/jsonparser")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getJsonParser(){
        parser.getJsonParser();
        return "HELLOOOO";
    }

    @Path("/reactivemutiny")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getReactiveMutiny(){
        parser.reactineMutinySkuska();
        return "HELLOOOO I AM FROM REACTIVE MUTINY";
    }

//    @Path("/messaging")
//    @GET
//    @Produces(MediaType.TEXT_PLAIN)
//    public String getMessaging(){
//        parser.skusameMessagingCalc();
//        return "HELLOOOO I AM FROM MESSAGING";
//    }


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