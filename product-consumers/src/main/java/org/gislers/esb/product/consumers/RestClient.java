package org.gislers.esb.product.consumers;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * Created by jgisle on 6/22/15.
 */
@Component
public class RestClient {

    @Value("${simulator.receiver.base.url}")
    private String receiverBaseUrl;

    private Client jerseyClient;
    private WebResource webResource;

    @PostConstruct
    public void init() {
        jerseyClient = Client.create();
        webResource = jerseyClient.resource(receiverBaseUrl);
    }

    public ClientResponse sendToConsumerA(ProductRequest productRequest) {
        return send("/consumerA", productRequest);
    }

    public ClientResponse sendToConsumerB(ProductRequest productRequest) {
        return send("/consumerB", productRequest);
    }

    public ClientResponse sendToConsumerC(ProductRequest productRequest) {
        return send("/consumerC", productRequest);
    }

    public ClientResponse sendToConsumerD(ProductRequest productRequest) {
        return send("/consumerD", productRequest);
    }

    public ClientResponse sendToErrorConsumer(ProductRequest productRequest) {
        return send("/error", productRequest);
    }

    ClientResponse send(String path, ProductRequest productRequest) {
        WebResource.Builder builder = webResource.path(path).getRequestBuilder();

        Map<String, String> headerMap = productRequest.getHeaders();
        for (String key : headerMap.keySet()) {
            builder = builder.header(key, headerMap.get(key));
        }

        return builder.post(ClientResponse.class, productRequest.getBody());
    }
}
