package org.example.springproductcatalogue.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.client.RestTemplate;

/**
 * Application configuration class responsible for providing configuration details.
 * This class contains configuration beans for various components used in the application.
 */
@Configuration
public class ApplicationConfiguration {

    /**
     * Creates and configures a RestTemplate instance.
     * RestTemplate is a Spring class used for making HTTP requests.
     * This method creates a default RestTemplate without any custom configurations.
     *
     * @return RestTemplate configured with default settings.
     */
    @Bean
    public RestTemplate createRestTemplate() {
        return new RestTemplate();
    }

}
