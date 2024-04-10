package org.example.springproductcatalogue.exceptions;

/**
 * Exception thrown when a requested product cannot be found.
 *
 * <p>This exception is typically thrown when an attempt is made to retrieve or access a product that does not exist
 * in the system. It indicates that the requested product could not be found, possibly due to an incorrect ID or
 * because the product has been removed or is not available.</p>
 */
public class ProductNotFoundException extends Exception {

    /**
     * Constructs a new ProductNotFoundException with the specified detail message.
     *
     * @param message the detail message.
     */
    public ProductNotFoundException(String message) {
        super(message);
    }
}