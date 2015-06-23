package org.gislers.esb.product.consumers;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Created by jim on 6/19/2015.
 */
public class BaseConsumer {

    @Autowired
    private RestClient restClient;

    protected RestClient getRestClient() {
        return restClient;
    }

    protected ProductRequest buildRequest(Map<String, Object> headerMap, String message) {
        ProductRequest productRequest = new ProductRequest();
        productRequest.getHeaders().put("TRANSACTION_ID", (String) headerMap.get("TRANSACTION_ID"));
        productRequest.setBody(message);
        return productRequest;
    }
}
