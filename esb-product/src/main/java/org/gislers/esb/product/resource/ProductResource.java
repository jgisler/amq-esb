package org.gislers.esb.product.resource;

import org.gislers.esb.product.publisher.ProductMessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.util.UUID;

/**
 * Created by jgisle on 6/9/15.
 */
@Component
@Path("/product")
public class ProductResource {

    @Autowired
    private ProductMessagePublisher publisher;

    @POST
    public Response publishProductInfo(@Context HttpHeaders httpHeaders, String message) {
        UUID txId = publisher.sendMessage(httpHeaders, message);
        return Response.ok(txId.toString()).build();
    }
}
