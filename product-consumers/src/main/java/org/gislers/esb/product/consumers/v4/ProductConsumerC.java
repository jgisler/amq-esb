package org.gislers.esb.product.consumers.v4;

import com.sun.jersey.api.client.ClientResponse;
import org.gislers.esb.product.consumers.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by jgisle on 6/18/15.
 */
@Service
public class ProductConsumerC {

    private static final Logger logger = LoggerFactory.getLogger(ProductConsumerC.class);

    @Autowired
    private RestClient restClient;

    @JmsListener(
            id = "productConsumerC",
            containerFactory = "durableJmsListenerContainerFactory",
            destination = "product.out.v4",
            concurrency = "1",
            subscription = "product.consumer.C"
    )
    public void process(String message, @Headers Map<String, Object> headerMap) {
        String txId = (String) headerMap.get("TRANSACTION_ID");
        ClientResponse clientResponse = restClient.sendToConsumerC(txId);
        if (clientResponse.getStatus() != 200) {
            logger.error("Send failed: " + txId);
        }
    }
}
