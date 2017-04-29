package asroute;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AsRoute extends RouteBuilder {

    private static final Logger logger = LoggerFactory.getLogger(AsRoute.class);
    private static final String errorQ = "AS.ERROR";

    

    @Override
    public void configure() throws Exception {

        onException(Exception.class)
                .handled(false)
                .useOriginalMessage()
                .log(LoggingLevel.INFO, logger, "onexception block")

                .doTry()
                    //if jmsReplyTo exists then this bean has to set pattern to inonly otherwise 
                    // timeoutException after 20sec will occur on exchange

                    .to("amq:" + errorQ)
                .endDoTry()
                .doCatch(Exception.class)
                    .log(LoggingLevel.ERROR, logger, "exception when putting msg on error queue").end()
                .end();
        
       // interceptSendToEndpoint("direct:zoli.out")

        //.log(LoggingLevel.INFO,logger, "From Intercept");





        from("amq:AS.IN?concurrentConsumers=1&maxConcurrentConsumers=50")
                .routeId("zoli-as")
                .log(LoggingLevel.INFO, logger, " AS Received  request to process ${headers}") //and body : ${body}")
               /* .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        logger.info("got to processor");
                    }
                })*/
                // .dynamicRouter(header("myHeader"))
               // .recipientList(header("myHeader"))
               // .to("direct:internal")
                //.inOut("amq:PLATFORM.IN?replyTo=PLATFORM.OUT")
                .log(LoggingLevel.INFO, logger, " AS Got the response back ${headers}");
                //.to("amq:AS.OUT");


        from("direct:internal")
                .routeId("direct-internal")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        logger.info("got to processor");
                    }
                })
                .inOut("amq:PLATFORM.IN?replyTo=PLATFORM.OUT");
    }

}
