package org.gislers.esb.simulator.receive;

import org.gislers.esb.simulator.MessageTracker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Created by jim on 6/21/2015.
 */
@Component
@Path("/receive/product")
public class ProductReceivers {

    private static final Logger logger = LoggerFactory.getLogger(ProductReceivers.class);

    @Autowired
    private MessageTracker messageTracker;

    @POST
    @Path("/consumerA")
    public Response receiverA(@HeaderParam("TRANSACTION_ID") String txId, String message) {
        logger.info("ReceiverA: " + getDuration(txId) + "ms");
        return Response.ok().build();
    }

    @POST
    @Path("/consumerB")
    public Response receiverB(@HeaderParam("TRANSACTION_ID") String txId, String message) {
        logger.info("ReceiverB: " + getDuration(txId) + "ms");
        return Response.ok().build();
    }

    @POST
    @Path("/consumerC")
    public Response receiverC(@HeaderParam("TRANSACTION_ID") String txId, String message) {
        logger.info("ReceiverC: " + getDuration(txId) + "ms");
        return Response.ok().build();
    }

    @POST
    @Path("/consumerD")
    public Response receiverD(@HeaderParam("TRANSACTION_ID") String txId, String message) {
        logger.info("ReceiverD: " + getDuration(txId) + "ms");
        return Response.ok().build();
    }

    @POST
    @Path("/error")
    public Response error(@HeaderParam("TRANSACTION_ID") String txId, String message) {
        logger.info("Error: " + getDuration(txId) + "ms");
        return Response.ok().build();
    }

    long getDuration(String transactionId) {
        return System.currentTimeMillis() - messageTracker.getSentTime(transactionId);
    }
}
