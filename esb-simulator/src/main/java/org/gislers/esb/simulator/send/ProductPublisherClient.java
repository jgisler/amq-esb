package org.gislers.esb.simulator.send;

import com.sun.jersey.api.client.AsyncWebResource;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.ws.rs.core.Response;
import java.util.concurrent.Future;

/**
 * Created by jim on 6/20/2015.
 */
@Component
public class ProductPublisherClient {

    private static final Logger logger = LoggerFactory.getLogger( ProductPublisherClient.class );

    @Value("${product.endpoint.url}")
    private String productEndpointUrl;

    private Client jerseyClient;
    private WebResource webResource;

    @PostConstruct
    public void init() {
        jerseyClient = Client.create();
        webResource = jerseyClient.resource("http://localhost:8080/product-publisher/product");
    }

    public String sendProduct(String env, String version, String uuid, String product) {
        return webResource.header( "ENV", env )
                .header( "MESSAGE_VERSION", version )
                .header("UUID", uuid)
                .post( String.class, product );
    }
}
