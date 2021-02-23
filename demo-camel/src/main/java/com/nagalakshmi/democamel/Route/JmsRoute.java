package com.nagalakshmi.democamel.Route;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JmsRoute extends RouteBuilder {
    @Value("${app.messageSource}")
    private String msgSource;
    
    @Value("${app.DestinationFile}")
    private String Destinationfile;

    @Override
    public void configure() throws Exception {
        consumeMessageFromAmq();
        producemeassagetoAmqUsindTimer();
        connectUsingDirect();
        readMsgfromFileMoveToMQ();
        moveMsgfromAmqToFile();

    }

    private void moveMsgfromAmqToFile() {
        from("activemq:queue:fileMessage")
                .log(LoggingLevel.INFO, "message in queue of ActiveMq: ${body}")
                .setHeader(Exchange.FILE_NAME,
                        simple("${file:name.noext}_received_on_${date:now:yyyyMMdd}.${file:name.ext}"))
                .routeId("msg-to-file")
                .to("file:" + Destinationfile)
                .log(LoggingLevel.INFO,"file created at the destination and the msg is: ${body}");
    }

    private void readMsgfromFileMoveToMQ() {
        from("file:" + msgSource)
                .routeId("Moving")
                .log(LoggingLevel.INFO,"existed file meassage ${body}")
                .to("activemq:queue:fileMessage")
                .log(LoggingLevel.INFO,"message moved to mq ");


    }

    private void consumeMessageFromAmq(){
        from("activemq:queue:demo")
                .routeId("myRoute")
                .log(LoggingLevel.INFO,"incomimg message ${body}");
    }
    private void producemeassagetoAmqUsindTimer() {
        from("timer:mytimer?period=5000")
                .routeId("timer-Route")
                .setBody(constant("HELLO from camel"))
//                .to("activemq:queue:demo");
                .to("direct:messageProducer");
    }
    private void connectUsingDirect() {
        from("direct:messageProducer")
                .routeId("using-direct")
                .to("activemq:queue:demo");
    }
}
