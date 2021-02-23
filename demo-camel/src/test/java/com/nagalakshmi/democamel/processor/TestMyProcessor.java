package com.nagalakshmi.democamel.processor;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.DefaultExchange;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.DefaultContext;

import javax.swing.*;
import java.lang.annotation.Target;

public class TestMyProcessor extends CamelTestSupport {
    @Test
    public void Testing() throws Exception {
        DefaultCamelContext context = new DefaultCamelContext();
        DefaultExchange exchange = new DefaultExchange(context);
        exchange.getIn().setBody("Test message body",String.class);

        MyProcessor processor=new MyProcessor();
        processor.process(exchange);
        System.out.println(exchange.getIn().getBody());


    }
}
