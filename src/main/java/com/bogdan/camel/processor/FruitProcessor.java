package com.bogdan.camel.processor;

import com.bogdan.camel.model.Fruit;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FruitProcessor implements Processor {
    public static final Logger LOG = LoggerFactory.getLogger(FruitProcessor.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        Fruit fruit = exchange.getIn().getBody(Fruit.class);
        LOG.info("FruitProcessor is processing " + fruit);
    }
}
