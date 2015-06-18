package org.gislers.esb.product.consumers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by jgisle on 6/18/15.
 */
@Component("consumerB")
public class ConsumerB implements MessageListener {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerB.class);

    @Override
    public void onMessage(Message message) {
        processMessage(message);
    }

    void processMessage(Message message) {
        try {
            String messageVersion = message.getStringProperty("MESSAGE_VERSION");
            String environment = message.getStringProperty("ENV");
            logger.info(
                    "messageVersion=" + messageVersion + "&" +
                            "env=" + environment
            );

            TextMessage textMessage = (TextMessage) message;
            logger.info(textMessage.getText());
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
