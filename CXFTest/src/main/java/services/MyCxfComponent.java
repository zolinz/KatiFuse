package services;

import org.apache.camel.Endpoint;
import org.apache.camel.component.cxf.CxfComponent;
import org.apache.camel.component.cxf.CxfEndpoint;
import org.apache.camel.component.cxf.transport.CamelTransportFactory;
import org.apache.camel.example.reportincident.ReportIncidentEndpoint;
import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.binding.soap.Soap11;
import org.apache.cxf.binding.soap.SoapBindingConfiguration;
import org.apache.cxf.transport.ConduitInitiatorManager;
import org.apache.cxf.transport.DestinationFactoryManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zoli on 12/12/2016.
 */

public class MyCxfComponent extends CxfComponent{
/*
    public MyCxfComponent(CamelContext context){
        super(context);
    }*/

    @Override
    protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
       // CxfEndpoint serviceEndpoint = new CxfEndpoint("http://localhost:8900/myincident", this);
        //serviceEndpoint.setBeanId("reportEndpoint");


        //the following block will use amq component as address which is registered with camle context
        // amq component needs to be in camel context
        // create a new empty endpoint
        CxfEndpoint serviceEndpoint = new CxfEndpoint();
        // setup the internal cxf bus
        BusFactory bf = BusFactory.newInstance();
        Bus bus = bf.createBus();
        BusFactory.setDefaultBus(bus);
        // configure the bus to be camel transport aware (eg camel://jms:queue:HELLOWORLD)
        CamelTransportFactory camelTransportFactory = new CamelTransportFactory();
        camelTransportFactory.setBus(bus);
        camelTransportFactory.setCamelContext(getCamelContext());
        // register the camel transport against the conduit
        ConduitInitiatorManager cim = bus.getExtension(ConduitInitiatorManager.class);
        cim.registerConduitInitiator(CamelTransportFactory.TRANSPORT_ID, camelTransportFactory);
        // register the camel transport against the JMS DestinationFactory (soap/jms)
        DestinationFactoryManager dfm = bus.getExtension(DestinationFactoryManager.class);
        dfm.registerDestinationFactory(CamelTransportFactory.TRANSPORT_ID, camelTransportFactory);
        serviceEndpoint.setBus(bus);
        SoapBindingConfiguration sbf = new SoapBindingConfiguration();
        sbf.setVersion(Soap11.getInstance());
        serviceEndpoint.setBindingConfig(sbf);
        //serviceEndpoint.setAddress("camel://amq:queue:zoli.in?replyTo=zoli.out");

        // this does not work with camel://http://localhost:89000/myincident
        // becuase it is looking for a component in camel context and here http is just a protocol
        //it works with the following:
        //serviceEndpoint.setAddress("camel://jetty:http://localhost:8900/myincident");

        // cannot consume from http endpoint http is to send only jetty or servlet can consume
        serviceEndpoint.setAddress("http://localhost:8900/myincident");

        // from here it's the same for both http and all crap
        serviceEndpoint.setServiceClass(ReportIncidentEndpoint.class);
        Map<String, Object> properties = serviceEndpoint.getProperties() != null ? serviceEndpoint.getProperties() : new HashMap<String, Object>();
        properties.put("schema-validation-enabled", true);
        //properties.put("jaxb-validation-event-handler", CxfSchemaValidationOffValidationEventHandler.INSTANCE);
       serviceEndpoint.setProperties(properties);
        serviceEndpoint.setLoggingFeatureEnabled(true);
        return serviceEndpoint;
    }



}
