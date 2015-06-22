package org.gislers.esb.simulator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Created by jim on 6/20/2015.
 */
@Configuration
@ComponentScan(basePackages="org.gislers.esb.simulator")
@PropertySource("classpath:esb.properties")
public class AppConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyJmsProperties() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(20);
        taskExecutor.setMaxPoolSize(40);
        taskExecutor.setQueueCapacity(100);
        return taskExecutor;
    }
}
