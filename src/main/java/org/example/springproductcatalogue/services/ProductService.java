package org.example.springproductcatalogue.services;

import org.example.springproductcatalogue.models.Category;
import org.example.springproductcatalogue.models.Product;

import java.util.List;

/**
 * Interface to serve all product related requests
 */
public interface ProductService {

    /**
     * Get all product categories
     *
     * @return List &lt;Category&gt; object
     */
    List<Category> getAllCategories();

    /**
     * Get details of a product from the datasource
     *
     * @param productId ID of the product
     * @return List &lt;Product&gt; object
     */
    Product getSingleProduct(Long productId);

    /**
     * Get all products from the datasource
     *
     * @return List &lt;Product&gt; object
     */
    List<Product> getAllProducts();

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

    /**
     * Update a product
     *
     * @param productId Product ID
     * @param product   &lt;Product&gt; object with updated details
     * @return &lt;Product&gt; object
     */
    Product updateProduct(Long productId, Product product);

    /**
     * Delete a product
     *
     * @param productId Product ID
     * @return Product object
     */
    Product deleteProduct(Long productId);

}
