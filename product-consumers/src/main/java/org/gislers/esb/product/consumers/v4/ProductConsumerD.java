package org.gislers.esb.product.consumers.v4;

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
public class ProductConsumerD extends BaseConsumer {

    private static final Logger logger = LoggerFactory.getLogger(ProductConsumerD.class);

    @JmsListener(
            id = "productConsumerD",
            containerFactory = "durableJmsListenerContainerFactory",
            destination = "product.out.v4",
            concurrency = "1",
            subscription = "product.consumer.D"
    )
    public void process(@Headers Map<String, Object> headerMap, String message) {
        ClientResponse clientResponse = getRestClient().sendToConsumerD(buildRequest(headerMap, message));
        if (clientResponse.getStatus() != 200) {
            logger.error("Send failed");
        }
    }
}
