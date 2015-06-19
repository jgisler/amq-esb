package org.gislers.esb.product.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * Created by jgisle on 6/19/15.
 */
@Component
public class ProductRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("activemq:queue:product.in")
                .choice()
                .when(header("MESSAGE_VERSION").isEqualTo("4.0"))
                .to("activemq:topic:product.out.v4")
                .when(header("MESSAGE_VERSION").isEqualTo("2.0"))
                .to("activemq:topic:product.out.v3")
                .otherwise()
                .to("activemq:queue:product.error")
                .end();
    }
}
