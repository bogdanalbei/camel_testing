package com.bogdan.camel.processor;

import com.bogdan.camel.model.Fruit;
import com.bogdan.camel.model.Vegetable;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VegetableProcessor implements Processor {
    public static final Logger LOG = LoggerFactory.getLogger(FruitProcessor.class);


    @Override
    public void process(Exchange exchange) throws Exception {
        Vegetable vegetable = exchange.getIn().getBody(Vegetable.class);
        LOG.info("VegetableProcessor is processing " + vegetable);
    }
}
