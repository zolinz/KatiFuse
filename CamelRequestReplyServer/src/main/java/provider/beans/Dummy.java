package provider.beans;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;

public class Dummy {
        
        public void processMSGBody(Exchange ex){
                String inBody = (String) ex.getIn().getBody();
                
                ex.setPattern(ExchangePattern.InOnly);
                
                ex.getIn().setBody(inBody);
                
        }
        

}
