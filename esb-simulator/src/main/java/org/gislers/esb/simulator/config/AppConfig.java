package org.gislers.esb.simulator.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by jim on 6/20/2015.
 */
@Configuration
@ComponentScan(basePackages="org.gislers.esb.simulator")
@PropertySource("classpath:esb.properties")
public class AppConfig {

    @Value("${product.endpoint.url}")
    private String productEndpointUrl;

    @Bean
    public String productEndpointUrl() {
        return productEndpointUrl;
    }
}
