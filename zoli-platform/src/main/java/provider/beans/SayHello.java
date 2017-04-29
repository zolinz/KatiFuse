package provider.beans;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SayHello {
    public static final Logger LOG = LoggerFactory.getLogger(SayHello.class);
    
    public void processMSGBody(Exchange ex) throws Exception{

        //throw new RuntimeException();

        //Thread.sleep(30000);

        //ex.getIn().removeHeader("JMSReplyTo");


       /* try{
            String s = "FOOBAR";
            int i = Integer.parseInt(s);
            // this line of code will never be reached
            System.out.println("from try");
        }catch(NumberFormatException e){
            e.printStackTrace();
        }*/

        StringBuffer inBody = new StringBuffer();
        inBody.append("Hello  ");
        inBody.append(ex.getIn().getBody());
        LOG.info("in process method");
        inBody.append(" from Camel");
        ex.getIn().setBody(inBody.toString());
        //throw new RuntimeException("ERROR from ZOLI");
    }
    
}
