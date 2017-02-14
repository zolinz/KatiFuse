import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

;

/**
 * Created by Zoli on 6/02/2017.
 */
public class MyRouteTest extends CamelTestSupport {





    public void setUp() throws Exception {
        super.setUp();
        //context.addComponent("amq", new JmsComponent());
        context.addComponent("amq", ActiveMQComponent.activeMQComponent("vm://localhost?broker.persistent=false"));
        RouteBuilder myRoute = new MyRoute();
        context.addRoutes(myRoute);
        RouteDefinition rf = context.getRouteDefinition("myroute");


        rf.adviceWith(context, new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {
                replaceFromWith("amq:in");
            }
        });

        rf.adviceWith(context, new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {

                interceptSendToEndpoint("direct:out").skipSendToOriginalEndpoint().to("mock:out");

            }
        });

    }

    @Test
    public void testMyRoute() throws Exception{

        MockEndpoint ep = getMockEndpoint("mock:out");
        template.sendBody("direct:in", "Hello Zolika");

        System.out.println(ep.getExchanges().get(0).getIn().getBody().toString());


    }


}
