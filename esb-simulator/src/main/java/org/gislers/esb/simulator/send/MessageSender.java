package org.gislers.esb.simulator.send;

import org.gislers.esb.simulator.MessageTracker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Random;

/**
 * Created by jim on 6/20/2015.
 */
@Component
public class MessageSender {

    private static final Logger logger = LoggerFactory.getLogger( MessageSender.class );

    @Autowired
    private MessageTracker messageTracker;

    @Autowired
    private ProductPublisherClient productClient;

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    private Random random;

    @PostConstruct
    public void init() {
        random = new Random( System.currentTimeMillis() );
    }

    public void sendMessages(int messageCount) {

        for (int i = 0; i < messageCount; i++) {

            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    sendRandomMessage();
                }
            };
            try {
                taskExecutor.execute(runnable);
            } catch (TaskRejectedException e) {
                try {
                    Thread.sleep(100l);
                } catch (InterruptedException ex) {
                    logger.error(e.getMessage());
                }
                taskExecutor.execute(runnable);
            }

            logger.info("Messages sent: " + (i + 1));
        }
    }

    void sendRandomMessage() {
        String randomVersion = getRandomVersion();
        String response = productClient.sendProduct(randomVersion, "{ \"product\"=\"test product\" }");
        messageTracker.recordMessageSent(response);
    }

    String getRandomVersion() {
        int randomInt = random.nextInt(2);

        if( randomInt == 0 ) {
            return "2.0";
        }
        else {
            return "4.0";
        }
    }
}
