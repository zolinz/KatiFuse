package provider;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import provider.beans.Dummy;
import provider.beans.SayHello;

public class ProviderRoute extends RouteBuilder {

    private static final Logger logger = LoggerFactory.getLogger(ProviderRoute.class);
    private static final String errorQ = "PLATFORM.ERROR";
    private final SayHello sayHello = new SayHello();
    private final Dummy dummy = new Dummy();
    

    @Override
    public void configure() throws Exception {

        onException(Exception.class)
                .handled(false)
                .useOriginalMessage()
                .log(LoggingLevel.INFO, logger, "onexception block")

                .doTry()
                    //if jmsReplyTo exists then this bean has to set pattern to inonly otherwise 
                    // timeoutException after 20sec will occur on exchange
                    .bean(dummy, "processMSGBody")
                    .to("amq:" + errorQ)
                .endDoTry()
                .doCatch(Exception.class)
                    .log(LoggingLevel.ERROR, logger, "exception when putting msg on error queue").end()
                .end();
        
       // interceptSendToEndpoint("direct:zoli.out")

        //.log(LoggingLevel.INFO,logger, "From Intercept");


        //String [] ep = {"amq:zoli.input1", "amq:zoli.input2", "amq:zoli.input3"};


        from("amq:PLATFORM.IN?concurrentConsumers=1&maxConcurrentConsumers=50")
                .routeId("zoli-platform")
                .log(LoggingLevel.INFO, logger, "Received platform request to process") // ${headers}" and body : ${body}")
               .bean(sayHello,"processMSGBody")

                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .to("jetty:http://localhost:7777/test")
                .log(LoggingLevel.INFO, logger, "after msg processing ${headers} ")
                //.log(LoggingLevel.DEBUG, logger, "******************DEBUG");
                .to("amq:PLATFORM.OUT");

    }

}
