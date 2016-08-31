package provider.beans;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SayHello {
    public static final Logger LOG = LoggerFactory.getLogger(SayHello.class);
    
    public void processMSGBody(Exchange ex){

        StringBuffer inBody = new StringBuffer();
        inBody.append("Hello Bello ");
        inBody.append(ex.getIn().getBody());
        LOG.info("in process method");
        inBody.append(" from Camel");
        ex.getIn().setBody(inBody.toString());
        //throw new RuntimeException("ERROR from ZOLI");
    }
    
}
