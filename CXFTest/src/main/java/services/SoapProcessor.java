package services;

import org.apache.camel.Exchange;
import org.apache.camel.example.reportincident.OutputReportIncident;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Zoli on 31/08/2016.
 */
public class SoapProcessor {
    private static final Logger LOG = LoggerFactory.getLogger(SoapProcessor.class);

    public SoapProcessor() {
        super();
    }

    public void processMSGBody(Exchange ex){


        LOG.info(ex.getIn().toString());
        OutputReportIncident or = new OutputReportIncident();
        or.setCode("incident accepted");
        ex.getOut().setBody(or);


        //throw new RuntimeException("ERROR from ZOLI");
    }

}
