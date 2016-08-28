package provider.beans;

import org.apache.camel.Exchange;

public class SayHello {

    
    public void processMSGBody(Exchange ex){
        String inBody = (String) ex.getIn().getBody();
        
        String inBodyNew = "Hello Bello" + inBody + " from camel";
        
        ex.getIn().setBody(inBodyNew);
        //throw new RuntimeException("ERROR from ZOLI");
    }
    
}
