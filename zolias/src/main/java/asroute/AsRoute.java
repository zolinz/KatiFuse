package asroute;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AsRoute extends RouteBuilder {

    private static final Logger logger = LoggerFactory.getLogger(AsRoute.class);
    private static final String errorQ = "zoli.error";

    

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





        from("amq:as.in?replyTo=as.out")
                .routeId("zoli-as")
                .log(LoggingLevel.INFO, logger, " AS Received  request to process ${headers} and body : ${body}")
                .log(LoggingLevel.DEBUG, logger, "******************DEBUG")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        System.out.println("got to AS");
                    }
                })
                //.setHeader("JMSReplyTo" , "amq:as.out")
                .inOut("amq:platform.in?replyTo=platform.out&requestTimeout=22000&timeToLive=23000")
                .log(LoggingLevel.INFO, logger, " AS Got the response back ${headers} and body : ${body}");
    }

}
