package org.gislers.esb.product.consumers;

import org.slf4j.Logger;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by jgisle on 6/19/15.
 */
public abstract class BaseProductConsumer implements MessageListener {

    protected abstract Logger getLogger();

    @Override
    public void onMessage(Message message) {
        try {
            String messageVersion = message.getStringProperty("MESSAGE_VERSION");
            String environment = message.getStringProperty("ENV");
            getLogger().info(
                    "messageVersion=" + messageVersion + "&" +
                            "env=" + environment
            );

            TextMessage textMessage = (TextMessage) message;
            getLogger().info(textMessage.getText());
            message.acknowledge();
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
