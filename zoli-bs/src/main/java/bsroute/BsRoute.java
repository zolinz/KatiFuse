package bsroute;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BsRoute extends RouteBuilder {

    private static final Logger logger = LoggerFactory.getLogger(BsRoute.class);
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





        from("amq:bs.in")
                .routeId("zoli-bs")
                .log(LoggingLevel.INFO, logger, " BS Received  request to process ${headers} and body : ${body}")

                //.setHeader("JMSReplyTo" , "amq:as.out")
                .inOut("amq:as.in?replyTo=as.out&requestTimeout=25000&timeToLive=26000")
                .log(LoggingLevel.INFO, logger, " BS Received  respons to process ${headers} and body : ${body}");
    }

}
