package org.acme.json;


import javax.enterprise.context.ApplicationScoped;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class MessageIngoingHere {

//    @Incoming("kokotina-picovina")
//    public CompletionStage<Void> getujemSpravuAsynchronne(Message<String> skurvenaSprava){
//
//        skurvenaSprava.getPayload().lines().forEach(s -> System.out.println("TOTOT JE VLASTNE MESSAGE:"+s));
//        return skurvenaSprava.ack();
//    }
//
//    @Incoming("calc-read")
//    public CompletionStage<Void> getujemSpravuAsynchronneCalc(Message<Calc> calc){
//
//        System.out.println(calc.getPayload());
//        return calc.ack();
//    }
//}
}
