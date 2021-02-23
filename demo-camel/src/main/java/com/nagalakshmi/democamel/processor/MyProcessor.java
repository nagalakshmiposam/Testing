package com.nagalakshmi.democamel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MyProcessor implements Processor {
    private static Logger logger = LogManager.getLogger(MyProcessor.class);


    @Override
    public void process(Exchange exchange) throws Exception {
        String messageBody = exchange.getIn().getBody(String.class);
        Map<String, Object> exchangeproperties = exchange.getProperties();
        logger.info("message body from incoming exchange,{}", messageBody);
        logger.info("exchange properties, {}", exchangeproperties);
        messageBody = messageBody.concat("updating the body using processor");
        logger.info("updated the message body exchange, {}", messageBody);
        exchange.getIn().setBody(messageBody);


    }
}
