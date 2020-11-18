package org.acme.health;


import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Liveness;

import javax.enterprise.context.ApplicationScoped;

@Liveness
@ApplicationScoped
public class SimpleHealthCheck implements HealthCheck {

    private boolean databaseUp=false;


    @Override
    public HealthCheckResponse call() {

        HealthCheckResponseBuilder responseBuilder= HealthCheckResponse.named("Is database connect");

        try{

            databaseConnectionCheck();
            return responseBuilder.up().withData("H2","ready").withData("Postgres","ready").build();
        }catch (Exception e){
            return responseBuilder.down().withData("Error",e.getMessage()).build();
        }

    }

    private void databaseConnectionCheck() throws Exception {
        if(databaseUp){

        }else{
            throw new Exception("Database is not connected");
        }
    }
}
