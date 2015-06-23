package org.gislers.esb.simulator.send;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.gislers.esb.simulator.MessageTracker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.UUID;

/**
 * Created by jim on 6/20/2015.
 */
@Component
public class ProductPublisherClient {

    @Value("${product.dispatch.url}")
    private String productEndpointUrl;

    private Client jerseyClient;
    private WebResource webResource;

    @Autowired
    private MessageTracker messageTracker;

    @PostConstruct
    public void init() {
        jerseyClient = Client.create();
        webResource = jerseyClient.resource(productEndpointUrl);
    }

    public String sendProduct(String version, String product) {
        String response = webResource.header("ENV", "dev")
                .header( "MESSAGE_VERSION", version )
                .header("UUID", UUID.randomUUID().toString())
                .post( String.class, product );
        messageTracker.recordMessageSent(response);
        return response;
    }
}
