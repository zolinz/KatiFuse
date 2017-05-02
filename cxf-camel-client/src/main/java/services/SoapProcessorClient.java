package services;

import org.apache.camel.Exchange;
import org.apache.camel.example.reportincident.InputReportIncident;
import org.apache.camel.example.reportincident.OutputReportIncident;
import org.apache.camel.impl.DefaultMessage;
import org.apache.cxf.message.MessageContentsList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zoliexceptions.MyException;

/**
 * Created by Zoli on 31/08/2016.
 */
public class SoapProcessorClient {
    private static final Logger LOG = LoggerFactory.getLogger(SoapProcessorClient.class);


    public void processMSGBody(Exchange ex) throws MyException{

        LOG.info(ex.getIn().toString());
        DefaultMessage in = (DefaultMessage) ex.getIn();
        InputReportIncident inri = new InputReportIncident();
        inri.setEmail("zk");
        inri.setFamilyName("kovacs");
        inri.setGivenName("zoltan");
        inri.setIncidentDate("0202");
        inri.setDetails("hello");
        inri.setIncidentId("001");
        inri.setSummary("summary");
        inri.setPhone("0439");


        //LOG.info(inri.getEmail());


        ex.getIn().setBody(inri);

        //throw new Error("************************************Error from soap processor");
        //throw new RuntimeException("************************************Runtime  Exception ");
        //throw new MyException("************************************Hello Zolika");
    }


    public void processResponse(Exchange ex){
        OutputReportIncident response = (OutputReportIncident) ((MessageContentsList) ex.getIn().getBody()).get(0);
        ex.getIn().setBody(response.getCode());
    }

}
