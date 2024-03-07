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

    /**
     * Get all products from the datasource
     *
     * @return List &lt;Product&gt; object
     */
    List<Product> getAllProducts();
    Product createProduct(Product product);

    /**
     * Get products in a specific category
     *
     * @param categoryTitle Title of the category
     * @return List &lt;Product&gt; object
     */
    List<Product> getProductsInCategory(String categoryTitle);

    /**
     * Create a new product
     *
     * @param product &lt;Product&gt; object
     * @return &lt;Product&gt; object
     */
    Product createProduct(Product product);

}
