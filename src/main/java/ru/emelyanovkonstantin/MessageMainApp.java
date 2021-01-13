package ru.emelyanovkonstantin;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import ru.emelyanovkonstantin.route.MessageRouteBuilder;

public class MessageMainApp {

    public static void main(String[] args) {
        MessageRouteBuilder routeBuilder = new MessageRouteBuilder();
        CamelContext ctx = new DefaultCamelContext();

        //configure jms component
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://0.0.0.0:61616");
        ctx.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));

        try {
            ctx.addRoutes(routeBuilder);
            ctx.start();
            Thread.sleep(5 * 60 * 1000);
            ctx.stop();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
