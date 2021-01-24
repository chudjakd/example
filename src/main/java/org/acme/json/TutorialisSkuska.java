package org.acme.json;

import io.smallrye.mutiny.Multi;

import javax.enterprise.context.ApplicationScoped;
import javax.json.Json;
import javax.json.stream.JsonParser;
import java.io.StringReader;

@ApplicationScoped
public class TutorialisSkuska {


    public void getJsonParser(){

        StringReader string = new StringReader("{\"hello\":\"world\"}");
        JsonParser parser = Json.createParser(string);
        while (parser.hasNext()) {
            JsonParser.Event event = parser.next();
            switch (event) {
                case START_OBJECT:
                case END_OBJECT:
                    System.out.println("TOTO JE PRI END OBJECT:"+event.toString());
                    break;
                case KEY_NAME:
                    System.out.println("KEY_NAME " + parser.getString() + " - ");
                    System.out.println("SKUSKA GET NEXT PARSER NEXT NAME:"+parser.next().name() +" PARSER NEXT STRING: "+parser.getString());
                    break;
                case VALUE_STRING:
                    System.out.println("VALUE_STRING " + parser.getString());
                    break;
            }
        }

    }

    public void reactineMutinySkuska(){

        Multi.createFrom().items("Carla Bley", "John Coltrane", "Juliette Gréco")
                .onItem().invoke(item -> item.toUpperCase())
                .onCompletion().invoke(() -> System.out.println("Completed"))
                .onFailure().invoke(failure -> System.out.println("Failed " + failure.getMessage()))
                .subscribe().with(item -> System.out.println("Subscriber " + item));

        Multi<String> multicko=Multi.createFrom().items("Carla Bley", "John Coltrane", "Juliette Gréco");

        multicko=multicko.onItem().invoke(s -> s.toUpperCase());

        multicko.subscribe().with(item -> System.out.println("AFTER MULTI: "+item) );
    }

//    @Outgoing("picovina-kokotina")
//    public Message<String> skusameMessaging(){
//
//        return Message.of("SKOKOTENA PICOVINA LAGY JAK KOKOT");
//    }
//
//    @Outgoing("calc")
//    public Calc skusameMessagingCalc(){
//
//        Calc calc= new Calc();
//        calc.setNumber1(1);
//        calc.setNumber2(5);
//
//        return calc;
//    }



}
