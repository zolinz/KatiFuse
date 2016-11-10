package services;

import org.apache.camel.Exchange;
import org.apache.camel.example.reportincident.OutputReportIncident;
import org.apache.camel.processor.FailOverLoadBalanceTest;
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
        OutputReportIncident or = new OutputReportIncident();
        or.setCode("incident accepted");
        ex.getOut().setBody(or);


        throw new MyException("************************************Hello Zolika");
    }

}
