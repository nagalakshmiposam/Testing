package com.nagalakshmi.democamel.Route;

import com.nagalakshmi.democamel.processor.MyProcessor;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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
        fileCopier();
        renameTheFile();
    }

    private void renameTheFile() {
        from("file:" + sourcelocation)
                .routeId("renamedFile")
                .log(LoggingLevel.INFO,"existed file name ${file:name}")
//                .setHeader(Exchange.FILE_NAME,simple("${file:name.noext}_modified.${file:name.ext}"))
                .setHeader(Exchange.FILE_NAME,simple("${file:name.noext}_${date:now:yyyyMMdd}.${file:name.ext}"))
//                .setHeader(Exchange.FILE_NAME,simple("${fileName:nags}"))
                .log(LoggingLevel.INFO,"renamed file name ${file:name}")
                .to("file:" + destination);


    }


    private void fileCopier() {
        from("file:" + sourcelocation)
                .routeId("demo-route-ID")
//                .setBody(simple ( "${body}. file has been updated"))

                .process(Processor)
                .to("file:" + destination)


                .log(LoggingLevel.INFO, "file is moved to destination \n ${body}");

    }

}
