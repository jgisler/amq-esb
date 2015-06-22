package org.gislers.esb.simulator.receive;

import org.gislers.esb.simulator.MessageTracker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

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
    public Response receiverA(String message) {
        return receiveMessage(message);
    }

    @POST
    @Path("/consumerB")
    public Response receiverB(String message) {
        return receiveMessage(message);
    }

    @POST
    @Path("/consumerC")
    public Response receiverC(String message) {
        return receiveMessage(message);
    }

    @POST
    @Path("/consumerD")
    public Response receiverD(String message) {
        return receiveMessage(message);
    }

    Response receiveMessage(String transactionId) {
        messageTracker.recordMessageRecieved(transactionId);
        return Response.ok().build();
    }
}
