package pni;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pni.beans.MessageProcessor01;
import pni.beans.MessageProcessor02;

public class BackEndRoute extends RouteBuilder {

	private static final Logger logger = LoggerFactory
			.getLogger(BackEndRoute.class);
	private static final String errorQ = "zoli.error";
	private final MessageProcessor01 msgProcessor01 = new MessageProcessor01();
	private final MessageProcessor02 msgProcessor02 = new MessageProcessor02();
	

	@Override
	public void configure() throws Exception {

		onException(Exception.class)
				.handled(false)
				.useOriginalMessage()
				.log(LoggingLevel.INFO, logger, "onexception block")

				.doTry()
					//if jmsReplyTo exists then this bean has to set pattern to inonly otherwise 
					// timeoutException after 20sec will occur on exchange
					.bean(msgProcessor02, "processMSGBody")
					.to("amq:queue:" + errorQ)
				.endDoTry()
				.doCatch(Exception.class)
					.log(LoggingLevel.ERROR, logger, "exception when putting msg on error queue").end()
				.end();
		
		interceptSendToEndpoint("amq:queue:zoli.out")
	    .log(LoggingLevel.INFO,logger, "From Intercept");

		from("amq:queue:zoli.input")
				.routeId("zoli-platform")
				.log(LoggingLevel.INFO, logger, "Received platform request to process ${headers} and body : ${body}")
				.bean(msgProcessor01,"processMSGBody")	
				.log(LoggingLevel.INFO, logger, "after msg processing")

		;

	}

}
