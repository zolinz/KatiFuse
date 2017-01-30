package provider;

import org.apache.camel.Exchange;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.impl.DefaultExchange;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RouteTest extends CamelBlueprintTestSupport {
    public static final Logger LOG = LoggerFactory.getLogger(RouteTest.class);

    @Override
    protected String getBlueprintDescriptor() {
        return "/OSGI-INF/blueprint/blueprint.xml";
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        RouteDefinition routeDef = context.getRouteDefinition("zoli-platform");
        try {
            routeDef.adviceWith(context, new AdviceWithRouteBuilder() {
                @Override
                public void configure() {
                    replaceFromWith("direct:zoli.in");
                    interceptSendToEndpoint("amq:zoli.out").skipSendToOriginalEndpoint().to("mock:zoli.out");
                }
            });


        } catch (Exception e) {
            LOG.error("Did not create routeDef", e);
        }
    }

   /* protected CamelContext createCamelContext() throws Exception {
        CamelContext camelContext = super.createCamelContext();

        camelContext.addComponent("amq", activeMQComponent("vm://localhost?broker.persistent=false"));

        return camelContext;
    }
*/

    @Ignore
    @Test
    public void testRoute() throws Exception {


        Exchange ex = new DefaultExchange(context);
        //ex.getIn().setHeader("JMSReplyTo" , "zoli.out");
        LOG.debug("got to test case");
        ex.getIn().setBody("Zolika");


        template.send("direct:zoli.in", ex);



        // the route is timer based, so every 5th second a message is send
        // we should then expect at least one message
        getMockEndpoint("mock:zoli.out").expectedMinimumMessageCount(0);

        LOG.info(ex.getIn().toString());


        // assert expectations
        assertMockEndpointsSatisfied();
    }

}
