import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.builder.RouteBuilder;

import javax.jms.ConnectionFactory;

/**
 * Created by Zoli on 6/02/2017.
 */
public class MyRoute extends RouteBuilder {

    ConnectionFactory connectionFactory =
            new ActiveMQConnectionFactory("vm://localhost");




    public void configure() throws Exception {
       // getContext().addComponent("amq", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));

        from("amq:in").id("myroute").to("amq:out");
    }


}
