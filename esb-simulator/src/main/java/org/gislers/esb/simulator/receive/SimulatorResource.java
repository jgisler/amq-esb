package org.gislers.esb.simulator.receive;

import org.gislers.esb.simulator.MessageTracker;
import org.gislers.esb.simulator.send.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * Created by jgisle on 6/22/15.
 */
@Component
@Path("/simulator/")
public class SimulatorResource {

    @Autowired
    private MessageTracker messageTracker;

    @Autowired
    private MessageSender messageSender;

    @GET
    @Path("/start/{messageCount}")
    public Response startTest(@PathParam("messageCount") int messageCount) {
        messageSender.sendMessages(messageCount);
        return Response.ok(messageTracker.verifyTransactions()).build();
    }
}
