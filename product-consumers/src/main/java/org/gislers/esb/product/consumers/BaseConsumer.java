package org.gislers.esb.product.consumers;

import org.slf4j.Logger;

import java.util.Map;

/**
 * Created by jim on 6/19/2015.
 */
public abstract class BaseConsumer {

    public abstract Logger getLogger();

    public void process(String message, Map<String, Object> headerMap) {
        getLogger().info(buildLogMessage(message, headerMap));
    }

    String buildLogMessage( String message, Map<String, Object> headerMap ) {
        StringBuilder sb = new StringBuilder();
        for (String key : headerMap.keySet()) {
            sb.append(key).append("=").append(headerMap.get(key));
            if (sb.length() > 0) {
                sb.append(" ");
            }
        }
        return sb.append(message).toString();
    }
}
