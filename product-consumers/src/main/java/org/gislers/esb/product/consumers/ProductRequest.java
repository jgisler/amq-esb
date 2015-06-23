package org.gislers.esb.product.consumers;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jgisle on 6/23/15.
 */
public class ProductRequest {

    private Map<String, String> headers = new HashMap<>();
    private String body;

    public ProductRequest() {
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
