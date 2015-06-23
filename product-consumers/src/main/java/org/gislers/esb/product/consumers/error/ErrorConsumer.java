package org.gislers.esb.product.consumers.error;

import com.sun.jersey.api.client.ClientResponse;
import org.gislers.esb.product.consumers.BaseConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by jgisle on 6/19/15.
 */
@Service
public class ErrorConsumer extends BaseConsumer {

    private static final Logger logger = LoggerFactory.getLogger(ErrorConsumer.class);

    @JmsListener(
            id = "errorConsumer",
            containerFactory = "errorListenerContainerFactory",
            destination = "product.error",
            concurrency = "1",
            subscription = "queue"
    )
    public void process(String message, @Headers Map<String, Object> headerMap) {
        ClientResponse clientResponse = getRestClient().sendToErrorConsumer(buildRequest(headerMap, message));
        if (clientResponse.getStatus() != 200) {
            logger.error("Send failed");
        }
    }
}
