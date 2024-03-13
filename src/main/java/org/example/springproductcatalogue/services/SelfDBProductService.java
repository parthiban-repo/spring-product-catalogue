package org.example.springproductcatalogue.services;

import org.example.springproductcatalogue.exceptions.ProductNotFoundException;
import org.example.springproductcatalogue.models.Category;
import org.example.springproductcatalogue.models.Product;
import org.example.springproductcatalogue.repositories.CategoryRepository;
import org.example.springproductcatalogue.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

/**
 * Service 'SelfDBProductService' implementing 'ProductService' service
 * Serve all API requests on own database connected to this application
 *
 * @author Parthiban Rajendran
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
    public Product getSingleProduct(Long productId) throws ProductNotFoundException {

        Optional<Product> productOptional = productRepository.findById(productId);

        if (productOptional.isPresent()) {
            return productOptional.get();
        } else {
            throw new ProductNotFoundException("Product not found with id: " + productId);
        }
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
    public Product createProduct(Product product) throws Exception {

        Category category = categoryRepository.findByTitle(product.getCategory().getTitle());
        if (category != null) {
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
     * @param product   &lt;Product&gt; object
     * @return &lt;Product&gt; object
     */
    @Override
    public Product updateProduct(Long productId, Product product) throws ProductNotFoundException, IllegalAccessException {
        Product existingProduct = getSingleProduct(productId);

        if (existingProduct != null) {
            // Get all fields of the Product class
            Field[] fields = Product.class.getDeclaredFields();
            for (Field field : fields) {
                // Make the field accessible in case it is private
                field.setAccessible(true);
                // skip if the current field is Category class type. Handle this separately
                if (field.getType() == Category.class) {
                    continue;
                }
                // Get the value of the field from the provided product object
                Object value = field.get(product);
                // If the value is not null, update the corresponding field in the existing product
                if (value != null) {
                    field.set(existingProduct, value);
                }
            }
        } else {
            throw new ProductNotFoundException("Product not found with id: " + productId);
        }

        // Update the product category
        if (product.getCategory().getTitle() != null) {
            Category category = categoryRepository.findByTitle(product.getCategory().getTitle());
            if (category != null) {
                existingProduct.setCategory(category);
            }
        }

        return productRepository.save(existingProduct);
    }

    /**
     * Delete a product
     *
     * @param productId Product ID
     * @return productId Deleted Product ID
     */
    @Override
    public Long deleteProduct(Long productId) throws ProductNotFoundException {

        if (productRepository.existsById(productId)) {
            productRepository.deleteById(productId);
        } else {
            throw new ProductNotFoundException("Product not found with id: " + productId);
        }

        return productId;
    }
}
