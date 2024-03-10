package org.example.springproductcatalogue.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Application Configuration Details
 */
@Configuration
public class ApplicationConfiguration {

    /**
     * Create a RestTemplate for the application
     *
     * @return RestTemplate
     */
    @Bean
    public RestTemplate createRestTemplate() {
        return new RestTemplate();
    }
}
