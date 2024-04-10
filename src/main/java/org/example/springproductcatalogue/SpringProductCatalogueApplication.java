package org.example.springproductcatalogue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * The main class for the Product Catalogue application built on Spring Boot.
 * This class initializes and runs the Spring Boot application.
 *
 * This class also enables caching to optimize performance.
 *
 * @author Parthiban Rajendran
 * @version 1.0
 */
@SpringBootApplication
@EnableCaching
public class SpringProductCatalogueApplication {

	/**
	 * The main entry point for the Spring Product Catalogue application.
	 * It initializes the Spring application context and starts the embedded server.
	 *
	 * @param args Command-line arguments passed to the application.
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringProductCatalogueApplication.class, args);
	}

}
