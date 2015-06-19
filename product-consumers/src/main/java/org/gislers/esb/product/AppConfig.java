package org.gislers.esb.product;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

/**
 * Created by jgisle on 6/18/15.
 */
@Configuration
@EnableJms
@ComponentScan(basePackages = "org.gislers.esb.product")
public class AppConfig {

    private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);

    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        logger.info("connectionFactory()");
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL("failover:(tcp://localhost:61616,tcp://localhost:61626)");
        return activeMQConnectionFactory;
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        logger.info("jmsListenerContainerFactory()");
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setCacheLevelName("CACHE_CONNECTION");
        factory.setPubSubDomain(true);
        factory.setConcurrency("1");
        return factory;
    }
}
