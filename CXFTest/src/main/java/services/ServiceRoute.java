package services;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.example.reportincident.OutputReportIncident;
import org.apache.camel.impl.CompositeRegistry;
import org.apache.camel.impl.PropertyPlaceholderDelegateRegistry;
import org.apache.camel.impl.SimpleRegistry;
import org.apache.camel.spi.Registry;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zoliexceptions.MyException;

/**
 * Created by Zoli on 31/08/2016.
 */
public class ServiceRoute extends RouteBuilder {
    SoapProcessor sp = new SoapProcessor();

    private static final Logger LOG = LoggerFactory.getLogger(ServiceRoute.class);


    @Override
    public void configure()  {

        onException(MyException.class)
                .useOriginalMessage()
                .handled(true)
                .log(LoggingLevel.ERROR, LOG, " ZOLIKA's exception logged").process(new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {
                OutputReportIncident ori = new OutputReportIncident();
                ori.setCode("Throwable");
                exchange.getIn().setBody(ori);
            }
        })
        .end();

        /*onException(MyException.class)
                .log(LoggingLevel.ERROR, LOG, " Generic crap thrown").process(new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {
                OutputReportIncident ori = new OutputReportIncident();
                ori.setCode("Exception");
                exchange.getIn().setBody(ori);
                exchange.setProperty(exchange.EXCEPTION_CAUGHT, new RuntimeException("new runtime"));
                //exchange.EXCEPTION_CAUGHT
            }
        })
       .end();*/

        //createing a cxf endopoint
       // CxfComponent cxfComponent = new CxfComponent(getContext());

        //MyCxfComponent myComp = new MyCxfComponent(getContext());
        //getContext().addComponent("file" , myComp);

       // Component com = getContext().getComponent("cxfEndpoint");
        //CxfEndpoint serviceEndpoint = new CxfEndpoint("http://localhost:8900/myincident", myCxfComponent);
        //serviceEndpoint.setBeanId("reportEndpoint");
        //serviceEndpoint.setServiceClass(ReportIncidentEndpoint.class);
        //serviceEndpoint.setAddress("http://localhost:8900/incident");
        //serviceEndpoint.


        //http://camel.465427.n5.nabble.com/Programatically-adding-beans-to-a-registry-in-a-RouteBuilder-td5729358.html
        // this is a CompositeRegistry
        Registry registry = getContext().getRegistry();
        if (registry instanceof PropertyPlaceholderDelegateRegistry)
            registry =
                    ((PropertyPlaceholderDelegateRegistry)registry).getRegistry();

        // need to add the endpoint there
        SimpleRegistry sr = new SimpleRegistry();
        sr.put("cxfEndpoint", new MyCxfComponent());
        ((CompositeRegistry)registry).addRegistry(sr);


       BundleContext ctx = FrameworkUtil.getBundle(getClass()).getBundleContext();
     // ctx.

      //  MyCxfComponent myCxfComponent = new MyCxfComponent();
      //  myCxfComponent.setCamelContext(getContext());

       // getContext().addComponent("cxfEndpoint", myCxfComponent );

        from("cxfEndpoint:hello")
          .bean(sp, "processMSGBody");


    }
}
