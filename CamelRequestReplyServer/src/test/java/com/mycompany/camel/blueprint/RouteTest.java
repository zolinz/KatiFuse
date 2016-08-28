package com.mycompany.camel.blueprint;

import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
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
        RouteDefinition routeDef = context.getRouteDefinition("provider-route-back");
        try {
            routeDef.adviceWith(context, new AdviceWithRouteBuilder() {
                @Override
                public void configure() {
                    interceptSendToEndpoint("amq:queue:zoli.out").skipSendToOriginalEndpoint().to("mock:zoli.out");
                }


            });
            routeDef.adviceWith(context, new AdviceWithRouteBuilder() {
                @Override
                public void configure() {
                    replaceFromWith("mock:zoli.in");
                }
            });
        } catch (Exception e) {
            LOG.error("Error interceptSendTo", e);
        }
    }

    @Test
    public void testRoute() throws Exception {




        // the route is timer based, so every 5th second a message is send
        // we should then expect at least one message
        getMockEndpoint("mock:zoli.out").expectedMinimumMessageCount(1);

        // assert expectations
        assertMockEndpointsSatisfied();
    }

}
