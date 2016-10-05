package provider;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import provider.beans.SayHello;
import provider.beans.Dummy;

public class ProviderRoute extends RouteBuilder {

    private static final Logger logger = LoggerFactory.getLogger(ProviderRoute.class);
    private static final String errorQ = "zoli.error";
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

        from("amq:zoli.input")
                .routeId("zoli-platform")
                .log(LoggingLevel.INFO, logger, "Received platform request to process ${headers} and body : ${body}")
                .bean(sayHello,"processMSGBody")
                .log(LoggingLevel.INFO, logger, "after msg processing")
                .log(LoggingLevel.DEBUG, logger, "******************DEBUG")
                .to("amq:zoli.out");

    }

}