package org.example.springproductcatalogue.services;

import org.example.springproductcatalogue.models.Category;
import org.example.springproductcatalogue.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Service 'DatabaseProductService' implementing ProductService service
 * Serve all API requests on own database connected to this application
 */
@Service
public class DatabaseProductService implements ProductService {

    /**
     * Get all product categories
     *
     * @return List &lt;Category&gt; object
     */
    @Override
    public List<Category> getAllCategories() {
        return null;
    }

    /**
     * Get details of a single product with given productId
     *
     * @param productId - id of the product - Long positive integer
     * @return Product object
     */
    @Override
    public Product getSingleProduct(Long productId) {
        return null;
    }

    /**
     * Get all products from the datasource
     *
     * @return List &lt;Product&gt; object
     */
    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    /**
     * Get products in a specific category
     *
     * @param categoryTitle Title of the category
     * @return List &lt;Product&gt; object
     */
    @Override
    public List<Product> getProductsInCategory(String categoryTitle) {
        return null;
    }

    /**
     * Create a new product
     *
     * @param product &lt;Product&gt; object
     * @return &lt;Product&gt; object
     */
    @Override
    public Product createProduct(Product product) {
        return null;
    }

    /**
     * Update a product
     *
     * @param productId Product ID
     * @param product   &lt;Product&gt; object
     * @return &lt;Product&gt; object
     */
    @Override
    public Product updateProduct(Long productId, Product product) {
        return null;
    }

    /**
     * Delete a product
     *
     * @param productId Product ID
     * @return &lt;Product&gt; object
     */
    @Override
    public Product deleteProduct(Long productId) {
        return null;
    }
}
