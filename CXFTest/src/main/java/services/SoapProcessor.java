package services;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Zoli on 31/08/2016.
 */
public class SoapProcessor {
    private static final Logger logger = LoggerFactory.getLogger(SoapProcessor.class);

    public void processMSGBody(Exchange ex){


        LOG.info(ex.getIn().toString());


        //throw new RuntimeException("ERROR from ZOLI");
    }

}
