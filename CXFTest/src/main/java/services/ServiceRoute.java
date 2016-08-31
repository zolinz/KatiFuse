package services;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.cxf.CxfComponent;
import org.apache.camel.component.cxf.CxfEndpoint;

/**
 * Created by Zoli on 31/08/2016.
 */
public class ServiceRoute extends RouteBuilder {
    SoapProcessor sp = new SoapProcessor();

    @Override
    public void configure() throws Exception {
        CxfComponent cxfComponent = new CxfComponent(getContext());
        CxfEndpoint serviceEndpoint = new CxfEndpoint(, cxfComponent);
        serviceEndpoint.setServiceClass(Greeter.class);
       // from()


    }
}
