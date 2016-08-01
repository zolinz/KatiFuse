package pni.beans;

import org.apache.camel.Exchange;

public class MessageProcessor01 {

	
	public void processMSGBody(Exchange ex){
		String inBody = (String) ex.getIn().getBody();
		
		String inBodyNew = "Hello " + inBody + " from camel";
		
		ex.getIn().setBody(inBodyNew);
		//throw new RuntimeException("ERROR from ZOLI");
	}
	
}
