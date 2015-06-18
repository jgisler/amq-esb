package org.gislers.esb.product.consumers;

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
public class ConsumerServiceA {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerServiceA.class);

    @JmsListener(
            id = "ConsumerServiceA",
            destination = "product.esb.outbound.topic",
            concurrency = "1",
            subscription = "durableTopic"
    )
    public void processOrder(String message, @Headers Map<String, Object> headerMap) {

        StringBuilder sb = new StringBuilder();
        for (String key : headerMap.keySet()) {
            sb.append(key).append("=").append(headerMap.get(key));
            if (sb.length() > 0) {
                sb.append("&");
            }
        }
        sb.append(message);
        logger.info(sb.toString());
    }
}
