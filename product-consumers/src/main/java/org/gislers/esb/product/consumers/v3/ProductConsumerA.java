package org.gislers.esb.product.consumers.v3;

import com.sun.jersey.api.client.ClientResponse;
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
public class ProductConsumerA extends BaseConsumer {

    private static final Logger logger = LoggerFactory.getLogger(ProductConsumerA.class);

    @JmsListener(
            id = "productConsumerA",
            containerFactory = "durableJmsListenerContainerFactory",
            destination = "product.out.v3",
            concurrency = "1",
            subscription = "product.consumer.A"
    )
    public void process(@Headers Map<String, Object> headerMap, String message) {
        ClientResponse clientResponse = getRestClient().sendToConsumerA(buildRequest(headerMap, message));
        if (clientResponse.getStatus() != 200) {
            logger.error("Send failed");
        }
    }


}
