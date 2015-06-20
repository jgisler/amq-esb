package org.gislers.esb.product.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;

import javax.jms.JMSException;
import javax.jms.Session;

/**
 * Created by jgisle on 6/18/15.
 */
@Configuration
@EnableJms
@ComponentScan(basePackages="org.gislers.esb.product")
@PropertySource("classpath:jms.properties")
public class AppConfig {

    private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);

    @Value("${broker.url}")
    private String brokerUrl;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyJmsProperties() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL( brokerUrl );
        activeMQConnectionFactory.setStatsEnabled(true);
        activeMQConnectionFactory.setAlwaysSessionAsync(true);
        return activeMQConnectionFactory;
    }

    @Bean
    public CachingConnectionFactory cachingConnectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setCacheConsumers( true );
        connectionFactory.setReconnectOnException(true);
        connectionFactory.setTargetConnectionFactory(connectionFactory());
        connectionFactory.setClientId( "productClient" );
        return connectionFactory;
    }

    @Bean
    public DefaultJmsListenerContainerFactory durableJmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(cachingConnectionFactory());
        factory.setCacheLevelName("CACHE_CONSUMER");
        factory.setPubSubDomain(true);
        factory.setSubscriptionDurable(true);
        factory.setConcurrency("1");
        factory.setSessionAcknowledgeMode(Session.AUTO_ACKNOWLEDGE);
        return factory;
    }

    @Bean
    public DefaultJmsListenerContainerFactory errorListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(cachingConnectionFactory());
        factory.setCacheLevelName("CACHE_CONSUMER");
        factory.setPubSubDomain(false);
        factory.setConcurrency("1");
        return factory;
    }
}
