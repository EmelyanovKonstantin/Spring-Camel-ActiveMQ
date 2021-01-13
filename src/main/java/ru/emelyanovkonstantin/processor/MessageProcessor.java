package ru.emelyanovkonstantin.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import ru.emelyanovkonstantin.model.Message;

import java.util.List;

public class MessageProcessor implements Processor{

    public void process(Exchange exchange) {

        Message msg = exchange.getIn().getBody(Message.class);

        List<String> serviceRoute = msg.getServiceRoute();

        if (serviceRoute != null) {
            serviceRoute.add("Apache Camel Service");
        }

        exchange.getIn().setBody(msg);
    }
}