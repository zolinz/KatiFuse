package notifications;

import java.util.EventObject;

import org.apache.camel.management.event.ExchangeCreatedEvent;
import org.apache.camel.management.event.ExchangeSentEvent;
import org.apache.camel.support.EventNotifierSupport;

public class MyLoggingEventNotifier extends EventNotifierSupport {
 
    public void notify(EventObject event) throws Exception {
 
        if (event instanceof ExchangeSentEvent) {
            ExchangeSentEvent sent = (ExchangeSentEvent) event;
            log.info("Took " + sent.getTimeTaken() + " millis to send to: " + sent.getEndpoint());
        }
        
        if (event instanceof ExchangeCreatedEvent) {
        	ExchangeCreatedEvent created = (ExchangeCreatedEvent) event;
            log.info("ExchangeCreated with id:  " + created.getExchange().getExchangeId());
        }
 
    }
 
    public boolean isEnabled(EventObject event) {
        // we only want the sent events
        return event instanceof ExchangeCreatedEvent;
    }
 
    protected void doStart() throws Exception {
        // noop
    }
 
    protected void doStop() throws Exception {
        // noop
    }

}
