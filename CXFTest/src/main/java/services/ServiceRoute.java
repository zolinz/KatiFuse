package services;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.cxf.CxfComponent;
import org.apache.camel.component.cxf.CxfEndpoint;
import org.apache.camel.example.reportincident.ReportIncidentEndpoint;
import org.apache.camel.impl.CompositeRegistry;
import org.apache.camel.impl.PropertyPlaceholderDelegateRegistry;
import org.apache.camel.impl.SimpleRegistry;
import org.apache.camel.spi.Registry;

/**
 * Created by Zoli on 31/08/2016.
 */
public class ServiceRoute extends RouteBuilder {
    SoapProcessor sp = new SoapProcessor();


    @Override
    public void configure() throws Exception {


        //createing a cxf endopoint
        CxfComponent cxfComponent = new CxfComponent(getContext());
        CxfEndpoint serviceEndpoint = new CxfEndpoint("reportEndpoint", cxfComponent);
        serviceEndpoint.setBeanId("reportEndpoint");
        serviceEndpoint.setServiceClass(ReportIncidentEndpoint.class);
        serviceEndpoint.setAddress("http://localhost:8900/incident");
        //serviceEndpoint.


        //http://camel.465427.n5.nabble.com/Programatically-adding-beans-to-a-registry-in-a-RouteBuilder-td5729358.html
        // this is a CompositeRegistry
        Registry registry = getContext().getRegistry();
        if (registry instanceof PropertyPlaceholderDelegateRegistry)
            registry =
                    ((PropertyPlaceholderDelegateRegistry)registry).getRegistry();

        // need to add the endpoint there
        SimpleRegistry sr = new SimpleRegistry();
        sr.put("reportEndpoint", serviceEndpoint);
        ((CompositeRegistry)registry).addRegistry(sr);

        from("cxf:bean:reportEndpoint")
          .bean(sp, "processMSGBody");


    }
}
