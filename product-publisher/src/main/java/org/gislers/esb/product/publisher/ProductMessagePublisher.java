package org.gislers.esb.product.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.ws.rs.core.HttpHeaders;
import java.util.UUID;

/**
 * Created by jgisle on 6/10/15.
 */
@Component
public class ProductMessagePublisher {

    private static final Logger logger = LoggerFactory.getLogger(ProductMessagePublisher.class);

    @Autowired
    private JmsTemplate inboundProductNotifyTemplate;

    public UUID sendMessage(final HttpHeaders httpHeaders, final String message) {

        final UUID transactionId = UUID.randomUUID();
        logger.info(String.format("Publishing message with txId='%s'", transactionId.toString()));
        inboundProductNotifyTemplate.send(
                new MessageCreator() {
                    @Override
                    public Message createMessage(Session session) throws JMSException {
                        TextMessage textMessage = session.createTextMessage();
                        textMessage.setStringProperty("TRANSACTION_ID", transactionId.toString());
                        textMessage.setStringProperty("UUID", httpHeaders.getRequestHeader("uuid").get(0));
                        textMessage.setStringProperty("ENV", httpHeaders.getRequestHeader("env").get(0));
                        textMessage.setStringProperty("MESSAGE_VERSION", httpHeaders.getRequestHeader("message_version").get(0));
                        textMessage.setText(message);
                        return textMessage;
                    }
                }
        );
        return transactionId;
    }
}
