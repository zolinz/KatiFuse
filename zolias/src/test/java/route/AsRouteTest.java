package route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Zoli on 20/02/2017.
 */
public class AsRouteTest extends CamelBlueprintTestSupport {
    public static final Logger LOG = LoggerFactory.getLogger(AsRouteTest.class);


    @Override
    protected String getBlueprintDescriptor() {
        return "OSGI-INF/blueprint/blueprint.xml";
    }



    @Override
    public boolean isUseAdviceWith() {
        return true;
    }

    @Override
    public boolean isCreateCamelContextPerClass() {
        return true;
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        RouteDefinition routeDef = context.getRouteDefinition("zoli-as");
        try {
            routeDef.adviceWith(context, new AdviceWithRouteBuilder() {
                @Override
                public void configure() {
                    replaceFromWith("direct:zoli.in");

                }
            });
            routeDef.adviceWith(context, new AdviceWithRouteBuilder() {
                @Override
                public void configure() throws Exception {
                    interceptSendToEndpoint("amq:platform.in?replyTo=platform.out&requestTimeout=22000&timeToLive=23000").process(new Processor() {
                        @Override
                        public void process(Exchange exchange) throws Exception {
                            exchange.getIn().setBody("hello Zolika");
                        }
                    })
                            .skipSendToOriginalEndpoint().to("mock:zoli.out");
                }
            });


            //if using isAdviceWith true then must start context
            context.start();

        } catch (Exception e) {
            LOG.error("Did not create routeDef", e);
        }
    }


    @Test
    public void testAsRoute1(){
    MockEndpoint zoliOut = getMockEndpoint("mock:zoli.out");
    MockEndpoint zoliFinal = getMockEndpoint("mock:final.out");
    template.sendBody("direct:zoli.in", "hello world");

    System.out.println(zoliOut.getExchanges().get(0).getIn().getBody());


    }


    @Test
    public void testAsRoute2(){
        MockEndpoint zoliOut = getMockEndpoint("mock:zoli.out");
        MockEndpoint zoliFinal = getMockEndpoint("mock:final.out");
        template.sendBody("direct:zoli.in", "hello world");

        System.out.println(zoliOut.getExchanges().get(0).getIn().getBody());


    }



    @Test
    public void testAsRoute3(){
        MockEndpoint zoliOut = getMockEndpoint("mock:zoli.out");
        MockEndpoint zoliFinal = getMockEndpoint("mock:final.out");
        template.sendBody("direct:zoli.in", "hello world");

        System.out.println(zoliOut.getExchanges().get(0).getIn().getBody());
    }


    @Test
    public void testAsRoute4(){
        MockEndpoint zoliOut = getMockEndpoint("mock:zoli.out");
        MockEndpoint zoliFinal = getMockEndpoint("mock:final.out");
        template.sendBody("direct:zoli.in", "hello world");

        System.out.println(zoliOut.getExchanges().get(0).getIn().getBody());


    }


    @Test
    public void testAsRoute5(){
        MockEndpoint zoliOut = getMockEndpoint("mock:zoli.out");
        MockEndpoint zoliFinal = getMockEndpoint("mock:final.out");
        template.sendBody("direct:zoli.in", "hello world");

        System.out.println(zoliOut.getExchanges().get(0).getIn().getBody());


    }


    @Test
    public void testAsRoute6(){
        MockEndpoint zoliOut = getMockEndpoint("mock:zoli.out");
        MockEndpoint zoliFinal = getMockEndpoint("mock:final.out");
        template.sendBody("direct:zoli.in", "hello world");

        System.out.println(zoliOut.getExchanges().get(0).getIn().getBody());


    }
}
