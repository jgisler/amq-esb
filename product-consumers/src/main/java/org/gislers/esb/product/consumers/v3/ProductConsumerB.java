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
public class ProductConsumerB extends BaseConsumer {

    private static final Logger logger = LoggerFactory.getLogger(ProductConsumerB.class);

    @JmsListener(
            id = "productConsumerB",
            containerFactory = "durableJmsListenerContainerFactory",
            destination = "product.out.v3",
            concurrency = "1",
            subscription = "product.consumer.B"
    )
    public void process(@Headers Map<String, Object> headerMap, String message) {
        ClientResponse clientResponse = getRestClient().sendToConsumerB(buildRequest(headerMap, message));
        if (clientResponse.getStatus() != 200) {
            logger.error("Send failed");
        } else {
            String txId = (String) headerMap.get("TRANSACTION_ID");
            logger.info(String.format("Processing txId '%s'", txId));
        }
    }
}
