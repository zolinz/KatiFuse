package notification;

import org.apache.camel.Exchange;
import org.apache.camel.management.event.ExchangeCompletedEvent;
import org.apache.camel.management.event.ExchangeCreatedEvent;
import org.apache.camel.management.event.ExchangeSendingEvent;
import org.apache.camel.management.event.ExchangeSentEvent;
import org.apache.camel.support.EventNotifierSupport;

import java.util.EventObject;

/**
 * Created by Zoli on 27/03/2017.
 */
public class AsLoggingEventNotifier extends EventNotifierSupport{

    @Override
    public void notify(EventObject event) throws Exception {


        if (event instanceof ExchangeCreatedEvent) {
            String recipientListEndpoint = (String) ((Exchange) event.getSource()).getProperties().get("CamelRecipientListEndpoint");

            System.out.println(recipientListEndpoint);
            ExchangeCreatedEvent created = (ExchangeCreatedEvent) event;
            log.info("ExchangeCreated with id:  " + created.getExchange().getExchangeId());
        }

        if (event instanceof ExchangeSendingEvent) {
            ExchangeSendingEvent sending = (ExchangeSendingEvent) event;
            log.info("ExchangeSendingEvent" + sending.getEndpoint()+  sending.getExchange().getIn().getBody().toString());
        }

        if (event instanceof ExchangeSentEvent) {
            ExchangeSentEvent sent = (ExchangeSentEvent) event;
            log.info("ExchangeSentEvent" + sent.getEndpoint() );//+ sent.getExchange().getOut().getBody().toString());
        }

        if (event instanceof ExchangeCompletedEvent) {
            ExchangeCompletedEvent completed = (ExchangeCompletedEvent) event;
            log.info("ExchangeCompletedEvent with id:  " + completed.getExchange().getExchangeId());
        }

    }

    @Override
    public boolean isEnabled(EventObject eventObject) {
        boolean result = false;



        if (eventObject instanceof ExchangeCreatedEvent) {
            result = false;
        } else if (eventObject instanceof ExchangeSendingEvent) {
            result = true;
        } else if (eventObject instanceof ExchangeSentEvent) {
            result = true;
        } else if (eventObject instanceof ExchangeCompletedEvent) {
            result = true;
        }
        return result;


    }

}
