package org.example.springproductcatalogue.exceptions;

/**
 * Exception thrown when a product creation operation fails.
 *
 * <p>This exception is typically thrown when there is a failure during the process of creating a product,
 * such as when required data is missing or invalid, or when there is an unexpected error.</p>
 */
public class ProductCreationException extends Exception {

    /**
     * Constructs a new ProductCreationException with the specified detail message.
     *
     * @param message the detail message.
     */
    public ProductCreationException(String message) {
        super(message);
    }
}