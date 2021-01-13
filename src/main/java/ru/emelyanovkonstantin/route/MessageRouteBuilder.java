package ru.emelyanovkonstantin.route;

import javax.xml.bind.JAXBContext;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.springframework.core.io.ClassPathResource;
import ru.emelyanovkonstantin.model.Message;
import ru.emelyanovkonstantin.processor.MessageProcessor;

import java.io.File;


public class MessageRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        // XML Data Format
        JaxbDataFormat xmlDataFormat = new JaxbDataFormat();
        JAXBContext con = JAXBContext.newInstance(Message.class);
        xmlDataFormat.setContext(con);

        // JSON Data Format
        JacksonDataFormat jsonDataFormat = new JacksonDataFormat(Message.class);

        // path to message folder
        File resource = new ClassPathResource(
                "messages").getFile();

        from("file:" + resource).doTry().unmarshal(jsonDataFormat).process(new MessageProcessor())
                .marshal(xmlDataFormat).to("jms:queue:messages").doCatch(Exception.class)
                .process(new Processor() {
                    public void process(Exchange exchange) throws Exception {
                        Exception cause = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
                        System.out.println(cause);
                    }
                });
    }
}
