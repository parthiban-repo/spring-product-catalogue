package org.example.springproductcatalogue.services;

import org.example.springproductcatalogue.exceptions.ProductNotFoundException;
import org.example.springproductcatalogue.models.Category;
import org.example.springproductcatalogue.models.Product;
import org.example.springproductcatalogue.repositories.CategoryRepository;
import org.example.springproductcatalogue.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

/**
 * Service implementation for handling product-related requests using the application's own database.
 * This service interacts with the application's database to perform CRUD operations on products and categories.
 *
 * <p>
*     This service class is responsible for handling various API requests related to products
*     and categories by utilizing the application's own database. It serves as an interface between
*     the controller layer and the database layer, providing methods to retrieve, create, update,
*     and delete products and categories.
 * </p>
 *
 * @author Parthiban Rajendran
 */
@Service("SelfDBProductService")
//@Primary // annotating as the primary implementation of ProductService
public class SelfDBProductService implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    /**
     * Constructs a new SelfDBProductService with the specified repositories.
     *
     * @param productRepo The ProductRepository instance used to access product data.
     * @param categoryRepo The CategoryRepository instance used to access category data.
     */
    public SelfDBProductService(ProductRepository productRepo, CategoryRepository categoryRepo) {
        this.productRepository = productRepo;
        this.categoryRepository = categoryRepo;
    }

    //****** Implementation of ProductService methods ******//

    /**
     * Retrieves all product categories.
     *
     * @return A list of Category objects representing all available product categories.
     */
    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    /**
     * Retrieves details of a single product by its ID.
     *
     * @param productId The ID of the product to retrieve.
     * @return The Product object corresponding to the specified ID.
     * @throws ProductNotFoundException If the product with the given ID does not exist.
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
     * Retrieves all products.
     *
     * @return A list of all Product objects.
     */
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Retrieves products belonging to a specific category.
     *
     * @param categoryTitle The title of the category.
     * @return A list of Product objects belonging to the specified category.
     */
    @Override
    public List<Product> getProductsInCategory(String categoryTitle) {
        return productRepository.findAllByCategoryTitle(categoryTitle);
    }

    /**
     * Creates a new product.
     *
     * @param product The Product object representing the new product.
     * @return The created Product object.
     * @throws Exception If an error occurs during product creation.
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
     * Updates an existing product.
     *
     * @param productId The ID of the product to update.
     * @param product   The Product object containing the updated details.
     * @return The updated Product object.
     * @throws ProductNotFoundException If the product with the given ID does not exist.
     * @throws IllegalAccessException  If the user does not have permission to update the product.
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
     * Deletes a product by its ID.
     *
     * @param productId The ID of the product to delete.
     * @return The ID of the deleted product.
     * @throws ProductNotFoundException If the product with the given ID does not exist.
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

    /**
     * Retrieves products with titles containing the specified keyword.
     *
     * @param productTitle The keyword to search for in product titles.
     * @return A list of Product objects matching the search criteria.
     */
    @Override
    public List<Product> getProductsLikeTitle(String productTitle) {
        return productRepository.getProductsLikeName(productTitle);
    }

    /**
     * Retrieves a page of products with pagination and optional sorting.
     *
     * @param pageSize       The number of products per page.
     * @param pageNumber     The page number to retrieve.
     * @param sortColumn     (Optional) The column to sort the results by.
     * @param sortDirection  (Optional) The direction of sorting (ascending or descending).
     * @return A Page object containing the requested products.
     */
    @Override
    public Page<Product> getProducts(int pageSize, int pageNumber,
                                     Optional<String> sortColumn,
                                     Optional<String> sortDirection) {
        Pageable pageable;

        if (sortColumn.isPresent()) {
            // If sorDirection is present, check and return desc or asc
            Sort.Direction direction = sortDirection.map(s -> s.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC)
                    .orElse(Sort.Direction.ASC);
            pageable = PageRequest.of(pageNumber, pageSize, direction, sortColumn.get());
        } else {
            pageable = PageRequest.of(pageNumber, pageSize);
        }

        return productRepository.findAll(pageable);
    }

}
