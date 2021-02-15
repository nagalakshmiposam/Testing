package com.nagalakshmi.democamel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class NewFile extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("file:C:/training/input/").to("file:C:/training/output/");

    }
}
