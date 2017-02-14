package services;

import org.apache.camel.Endpoint;
import org.apache.camel.component.cxf.CxfComponent;
import org.apache.camel.component.cxf.CxfEndpoint;
import org.apache.camel.example.reportincident.ReportIncidentEndpoint;

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
        CxfEndpoint serviceEndpoint = new CxfEndpoint("http://localhost:8900/myincident", this);
        //serviceEndpoint.setBeanId("reportEndpoint");
        serviceEndpoint.setServiceClass(ReportIncidentEndpoint.class);
        Map<String, Object> properties = serviceEndpoint.getProperties() != null ? serviceEndpoint.getProperties() : new HashMap<String, Object>();
        properties.put("schema-validation-enabled", true);
        //properties.put("jaxb-validation-event-handler", CxfSchemaValidationOffValidationEventHandler.INSTANCE);
       serviceEndpoint.setProperties(properties);
        serviceEndpoint.setLoggingFeatureEnabled(true);
        return serviceEndpoint;
    }



}
