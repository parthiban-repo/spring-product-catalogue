package org.example.springproductcatalogue.services;

import org.example.springproductcatalogue.exceptions.ProductNotFoundException;
import org.example.springproductcatalogue.models.Category;
import org.example.springproductcatalogue.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

/**
 * Service interface: Handles all product-related operations.
 *
 * <p>The ProductService interface defines methods to interact with product data, including CRUD operations,
 * retrieval of products by category, creation, and deletion of products, and more.</p>
 *
 * @author Parthiban Rajendran
 */
public interface ProductService {

    /**
     * Retrieves all product categories.
     *
     * @return A list of Category objects representing all available product categories.
     */
    List<Category> getAllCategories();

    /**
     * Retrieves details of a single product by its ID.
     *
     * @param productId The ID of the product to retrieve.
     * @return The Product object corresponding to the specified ID.
     * @throws ProductNotFoundException If the product with the given ID does not exist.
     */
    Product getSingleProduct(Long productId) throws ProductNotFoundException;

    /**
     * Retrieves all products.
     *
     * @return A list of all Product objects.
     */
    List<Product> getAllProducts();

    /**
     * Retrieves products belonging to a specific category.
     *
     * @param categoryTitle The title of the category.
     * @return A list of Product objects belonging to the specified category.
     */
    List<Product> getProductsInCategory(String categoryTitle);

    /**
     * Creates a new product.
     *
     * @param product The Product object representing the new product.
     * @return The created Product object.
     * @throws Exception If an error occurs during product creation.
     */
    Product createProduct(Product product) throws Exception;

    /**
     * Updates an existing product.
     *
     * @param productId The ID of the product to update.
     * @param product   The Product object containing the updated details.
     * @return The updated Product object.
     * @throws ProductNotFoundException If the product with the given ID does not exist.
     * @throws IllegalAccessException  If the user does not have permission to update the product.
     */
    Product updateProduct(Long productId, Product product) throws ProductNotFoundException, IllegalAccessException;

    /**
     * Deletes a product by its ID.
     *
     * @param productId The ID of the product to delete.
     * @return The ID of the deleted product.
     * @throws ProductNotFoundException If the product with the given ID does not exist.
     */
    Long deleteProduct(Long productId) throws ProductNotFoundException;

    /**
     * Retrieves products with titles containing the specified keyword.
     *
     * @param productTitle The keyword to search for in product titles.
     * @return A list of Product objects matching the search criteria.
     * @throws ProductNotFoundException If no products matching the search criteria are found.
     */
    public List<Product> getProductsLikeTitle(String productTitle) throws ProductNotFoundException;

    /**
     * Retrieves a page of products with pagination and optional sorting.
     *
     * @param pageSize       The number of products per page.
     * @param pageNumber     The page number to retrieve.
     * @param sortColumn     (Optional) The column to sort the results by.
     * @param sortDirection  (Optional) The direction of sorting (ascending or descending).
     * @return A Page object containing the requested products.
     */
    Page<Product> getProducts(int pageSize, int pageNumber, Optional<String> sortColumn, Optional<String> sortDirection);
}
