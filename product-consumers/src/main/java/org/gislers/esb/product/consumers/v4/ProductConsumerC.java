package org.gislers.esb.product.consumers.v4;

import org.gislers.esb.product.consumers.BaseConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by jgisle on 6/18/15.
 */
@Service
public class ProductConsumerC  extends BaseConsumer {

    private static final Logger logger = LoggerFactory.getLogger(ProductConsumerC.class);

    @Override
    public Logger getLogger() {
        return logger;
    }

    @JmsListener(
            id = "productConsumerC",
            containerFactory = "durableJmsListenerContainerFactory",
            destination = "product.out.v4",
            concurrency = "1",
            subscription = "product.consumer.C"
    )
    public void process(String message, @Headers Map<String, Object> headerMap) {
        super.process(message, headerMap);
    }
}
