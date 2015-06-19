package org.gislers.esb.product.consumers.v3;

import org.gislers.esb.product.consumers.BaseProductConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by jgisle on 6/18/15.
 */
@Component("consumerA")
public class ConsumerA extends BaseProductConsumer {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerA.class);

    @Override
    protected Logger getLogger() {
        return logger;
    }
}
