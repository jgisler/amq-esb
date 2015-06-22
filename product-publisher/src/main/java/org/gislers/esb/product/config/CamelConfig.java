package org.gislers.esb.product.config;

import org.apache.camel.spring.javaconfig.CamelConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by jgisle on 6/22/15.
 */
@Configuration
@ComponentScan("org.gislers.esb.product.routes")
public class CamelConfig extends CamelConfiguration {

}
