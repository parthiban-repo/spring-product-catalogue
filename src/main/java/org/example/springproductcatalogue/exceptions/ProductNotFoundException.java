package org.example.springproductcatalogue.exceptions;

public class ProductNotFoundException extends Exception {

    public ProductNotFoundException(String message) {
        super(message);
    }
}