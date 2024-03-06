package org.example.springproductcatalogue.services;

import org.example.springproductcatalogue.models.Product;

import java.util.List;

/**
 * Interface to serve all product related requests
 */
public interface ProductService {

    /**
     * Get details of a product from the datasource
     *
     * @param productId Id of the product
     * @return List &lt;Product&gt; object
     */
    Product getSingleProduct(Long productId);
    List<Product> getAllProducts();
    Product createProduct(Product product);

}
