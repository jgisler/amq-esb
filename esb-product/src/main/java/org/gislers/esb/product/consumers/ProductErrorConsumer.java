package org.gislers.esb.product.consumers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by jgisle on 6/19/15.
 */
@Component("productErrorConsumer")
public class ProductErrorConsumer extends BaseProductConsumer {

    private static final Logger logger = LoggerFactory.getLogger(ProductErrorConsumer.class);

    @Override
    protected Logger getLogger() {
        return logger;
    }
}
