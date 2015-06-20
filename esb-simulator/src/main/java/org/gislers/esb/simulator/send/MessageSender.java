package org.gislers.esb.simulator.send;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Random;
import java.util.UUID;

/**
 * Created by jim on 6/20/2015.
 */
@Component
public class MessageSender {

    private static final Logger logger = LoggerFactory.getLogger( MessageSender.class );

    @Autowired
    private ProductPublisherClient productClient;

    private Random random;

    @PostConstruct
    public void init() {
        random = new Random( System.currentTimeMillis() );
    }

    public void sendRandomMessage() {

        long start = System.currentTimeMillis();
        String response = productClient.sendProduct(
                "dev",
                getRandomVersion(),
                UUID.randomUUID().toString(),
                "{ \"product\"=\"test product\" }"
        );
        long end = System.currentTimeMillis();
        logger.info( response + " : " + (end-start) + "ms" );
    }

    String getRandomVersion() {
        int randomInt = random.nextInt(3);

        if( randomInt == 0 ) {
            return "1.0";
        }
        else if( randomInt == 1 ) {
            return "2.0";
        }
        else {
            return "4.0";
        }
    }
}