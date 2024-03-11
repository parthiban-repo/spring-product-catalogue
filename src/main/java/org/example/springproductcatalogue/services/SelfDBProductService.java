package org.example.springproductcatalogue.services;

import org.example.springproductcatalogue.models.Category;
import org.example.springproductcatalogue.models.Product;
import org.example.springproductcatalogue.repositories.CategoryRepository;
import org.example.springproductcatalogue.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * Service 'SelfDBProductService' implementing 'ProductService' service
 * Serve all API requests on own database connected to this application
 */
@Service("SelfDBProductService")
public class SelfDBProductService implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public SelfDBProductService(ProductRepository productRepo, CategoryRepository categoryRepo) {
        this.productRepository = productRepo;
        this.categoryRepository = categoryRepo;
    }

    /**
     * Get all product categories
     *
     * @return List &lt;Category&gt; object
     */
    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    /**
     * Get details of a single product with given productId
     *
     * @param productId - id of the product - Long positive integer
     * @return Product object
     */
    @Override
    public Product getSingleProduct(Long productId) {
        return productRepository.findProductById(productId);
    }

    /**
     * Get all products from the datasource
     *
     * @return List &lt;Product&gt; object
     */
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Get products in a specific category
     *
     * @param categoryTitle Title of the category
     * @return List &lt;Product&gt; object
     */
    @Override
    public List<Product> getProductsInCategory(String categoryTitle) {
        return productRepository.findAllByCategoryTitle(categoryTitle);
    }

    /**
     * Create a new product
     *
     * @param product &lt;Product&gt; object
     * @return &lt;Product&gt; object
     */
    @Override
    public Product createProduct(Product product) {

        Category category = categoryRepository.findByTitle(product.getCategory().getTitle());
        if(category != null) {
            product.setCategory(category);
        }
        /*
        Important Note: Since the CascadeType.PERSIST is assigned in the Product model,
        if a category title passed in the product object does not exist in the database, first, it would create a new category
        and then save the product
         */

        return productRepository.save(product);

    }

    /**
     * Update a product
     *
     * @param productId Product ID
     * @param product &lt;Product&gt; object
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
