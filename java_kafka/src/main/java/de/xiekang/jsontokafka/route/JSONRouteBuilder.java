package de.xiekang.jsontokafka.route;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.xiekang.jsontokafka.JsonToKafka;
import de.xiekang.jsontokafka.beans.TripMainWrapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class JSONRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("timer:repeatedTimer?period=60000&repeatCount=-1&delay=0")
                .setHeader("Authorization", constant("57c5dbbbf1fe4d0001000018ae5aa15fe06241fd8300565280a8b5c9"))
                .to("https:api.opentransportdata.swiss/gtfsrt2020?format=JSON")
                .to("direct:getData");
        from("direct:getData")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        String messages = exchange.getMessage().getBody(String.class);

                        ObjectMapper objectMappermapper = new ObjectMapper();
                        TripMainWrapper tripMainWrapper = objectMappermapper.readValue(messages, TripMainWrapper.class);
                        JsonToKafka jsonToKafka = new JsonToKafka("34.159.19.237:9092", "TripUpdate");

                        jsonToKafka.getKeysFromFile();
                        System.out.println(tripMainWrapper.getEntity().size());
                        Set<String> keys = new HashSet<>(jsonToKafka.getKeys());
                        tripMainWrapper.getEntity().forEach(
                                tripMain -> {
                                    ObjectMapper mapper = new ObjectMapper();
                                    try {
                                        keys.add(tripMain.getId() + " " + tripMain.getTripUpdate().getTrip().getStartDate() + " " + tripMain.getTripUpdate().getTrip().getStartTime());
                                        if (!jsonToKafka.getKeys().contains(tripMain.getId() + " " + tripMain.getTripUpdate().getTrip().getStartDate() + " " + tripMain.getTripUpdate().getTrip().getStartTime())) {
                                            //System.out.println("Starting transfer...");
                                            //System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(tripMain));
                                            jsonToKafka.producerMessage(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(tripMain));
                                        } else {
                                            System.out.println("Message is already existed...");
                                        }
                                    } catch (JsonProcessingException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                        );
                        jsonToKafka.updateKeysFile(keys.stream().collect(Collectors.joining(System.lineSeparator())));
                    }
                })
                .to("mock:result");
    }
}
