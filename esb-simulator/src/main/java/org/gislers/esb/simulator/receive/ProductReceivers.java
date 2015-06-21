package org.gislers.esb.simulator.receive;

import org.gislers.esb.simulator.MessageTracker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.util.UUID;

/**
 * Created by jim on 6/21/2015.
 */
@Component
@Path("/receive/product")
public class ProductReceivers {

    @Autowired
    private MessageTracker messageTracker;

    @POST
    @Path("/consumerA")
    public Response receiverA(@Context HttpHeaders httpHeaders, String message) {
        return revieveMessage(httpHeaders.getRequestHeader("TRANSACTION_ID").get(0));
    }

    @POST
    @Path("/consumerB")
    public Response receiverB(@Context HttpHeaders httpHeaders, String message) {
       return revieveMessage( httpHeaders.getRequestHeader("TRANSACTION_ID").get(0) );
    }

    @POST
    @Path("/consumerC")
    public Response receiverC(@Context HttpHeaders httpHeaders, String message) {
        return revieveMessage( httpHeaders.getRequestHeader("TRANSACTION_ID").get(0) );
    }

    @POST
    @Path("/consumerD")
    public Response receiverD(@Context HttpHeaders httpHeaders, String message) {
        return revieveMessage( httpHeaders.getRequestHeader("TRANSACTION_ID").get(0) );
    }

    Response revieveMessage( String transactionId ) {
        messageTracker.recordMessageRecieved(transactionId);
        return Response.ok().build();
    }
}
