package com.nagalakshmi.democamel.routes;

import ch.qos.logback.classic.Logger;
import com.nagalakshmi.democamel.processor.MyProcessor;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.builder.ValidatorBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static java.lang.ProcessBuilder.Redirect.to;

@Component
public class NewFile extends RouteBuilder {

    @Value("${app.source}")
    private String sourcelocation;
    @Value("${app.destination}")
    private String destination;
    @Autowired
    private MyProcessor Processor;

    @Override
    public void configure() throws Exception {
        from("file:" + sourcelocation)
                .routeId("demo-route-ID")
//                .setBody(simple ( "${body}. file has been updated"))

                .process(Processor)
                .to("file:" + destination)


                .log(LoggingLevel.INFO, "file is moved to destination \n ${body}");

    }


}
