package org.gislers.esb.product.consumers;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

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

    public ClientResponse sendToConsumerA(String uuid) {
        return send("/consumerA", uuid);
    }

    public ClientResponse sendToConsumerB(String uuid) {
        return send("/consumerB", uuid);
    }

    public ClientResponse sendToConsumerC(String uuid) {
        return send("/consumerC", uuid);
    }

    public ClientResponse sendToConsumerD(String uuid) {
        return send("/consumerD", uuid);
    }

    ClientResponse send(String path, String uuid) {
        return webResource.path(path)
                .post(ClientResponse.class, uuid);
    }
}
