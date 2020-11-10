package org.acme.resteasy;

import org.acme.model.Calc;
import org.acme.repository.CalcRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/calc")
public class CalcResource {

    @Inject
    CalcRepository calcRepository;



    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public void createCalc(Calc calc){
        calcRepository.createCalc(calc);
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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Calc>getAllCalcWithResponse() {
        return calcRepository.getAllCalc();
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
    @Path("/update/{id}")
    public void updateCalc(Calc calc,@PathParam("id")String id){
        calcRepository.updateCalc(calc,Long.parseLong(id));
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional
    @Path("/delete/{id}")
    public String deleteCalcById(@PathParam("id") String id){
        boolean deleted=calcRepository.deleteCalcById(Long.parseLong(id));
       return String.valueOf(deleted);
    }




}