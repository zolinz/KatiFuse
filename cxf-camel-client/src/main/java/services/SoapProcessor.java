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
public class SoapProcessor {
    private static final Logger LOG = LoggerFactory.getLogger(SoapProcessor.class);


    public void processMSGBody(Exchange ex) throws MyException{

        LOG.info(ex.getIn().toString());
        DefaultMessage in = (DefaultMessage) ex.getIn();
        InputReportIncident inri = (InputReportIncident) ((MessageContentsList) in.getBody()).get(0);
        LOG.info(inri.getEmail());
        OutputReportIncident or = new OutputReportIncident();
        or.setCode("incident accepted hello");
        ex.getOut().setBody(or);

        //throw new Error("************************************Error from soap processor");
        //throw new RuntimeException("************************************Runtime  Exception ");
        //throw new MyException("************************************Hello Zolika");
    }

}
