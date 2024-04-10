package org.example.springproductcatalogue.controllers;

import org.example.springproductcatalogue.dtos.ProductRequestDto;
import org.example.springproductcatalogue.exceptions.ProductCreationException;
import org.example.springproductcatalogue.exceptions.ProductNotFoundException;
import org.example.springproductcatalogue.models.Product;
import org.example.springproductcatalogue.models.Category;
import org.example.springproductcatalogue.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller class responsible for handling HTTP requests related to products.
 * This class receives incoming requests and delegates the business logic to the ProductService.
 *
 * <p>
 * This controller primarily deals with mapping incoming HTTP requests to corresponding
 * methods and returning appropriate responses to the client. It interacts with the
 * ProductService to perform CRUD operations on products and manage product-related
 * functionalities.
 * </p>
 *
 * @author Parthiban Rajendran
 */
@RestController
public class ProductController {

    private final ProductService productService;

    /**
     * Constructor for creating a ProductController instance.
     *
     * @param productService ProductService implementation used for handling product operations.
     */
    public ProductController(@Qualifier("SelfDBProductService") ProductService productService) {
        this.productService = productService;
    }

    /**
     * Controller method: Creates a new product and updates the product cache.
     *
     * <p>Caching: After saving the product into the database, the product details are stored in the product cache for faster future retrieval.</p>
     *
     * @param request The ProductRequestDto containing product details.
     * @return The newly created Product object.
     * @throws ProductCreationException If there is an issue creating the product.
     */
    @CachePut(value = "product", key = "#product.id", unless = "#product == null || #product.title == null")
    @PostMapping({"/products", "/products/"})
    public Product createProduct(@RequestBody ProductRequestDto request) throws ProductCreationException {
        try {
            return productService.createProduct(request.toProduct());
            //return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (Exception e) {
            // Handle specific exception
            throw new ProductCreationException("Failed to create product: " + e.getMessage());
        }
    }

    /**
     * Controller method: Retrieves details of a product, fetching from cache if available, otherwise from the database.
     *     *
     * @param productId The ID of the product to retrieve. Must be a positive long integer.
     * @return The Product object representing the details of the requested product.
     * @throws ProductNotFoundException If the requested product is not found.
     */
    @Cacheable(value = "product")
    @GetMapping({"/products/{id}", "/products/{id}/"})
    public Product getProductDetails(@PathVariable("id") Long productId) throws ProductNotFoundException {
        return productService.getSingleProduct(productId);
    }

    /**
     * Controller method: Retrieves all products with titles similar to the provided title.
     *
     * <p>The title parameter represents the title of the products to search for.</p>
     *
     * @param title The title of the products to search for.
     * @return ResponseEntity containing a list of Product objects matching the provided title pattern.
     * @throws ProductNotFoundException If no products are found with titles similar to the provided title.
     */
    @GetMapping({"/products-list/{title}", "/products-list/{title}/"})
    public ResponseEntity<List<Product>> getProductLikeTitle(@PathVariable("title") String title) throws ProductNotFoundException {
        List<Product>  products = productService.getProductsLikeTitle(title);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * Controller method: Retrieves all products.
     *
     * @return ResponseEntity containing a list of all Product objects.
     * @throws Exception If an error occurs while retrieving the products.
     */
    @GetMapping({"/products", "/products/"})
    public ResponseEntity<List<Product>> getAllProducts() throws Exception{
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * Controller to retrieve products by page with optional sorting.
     * Sample end point: "http://localhost:8080/products/p/5/0?sortColumn=title&sortDirection=desc"
     *
     * @param pageSize      The number of products per page
     * @param pageNumber    The page number
     * @param sortColumn    (Optional) The column to sort by
     * @param sortDirection (Optional) The direction of sorting (asc or desc)
     * @return              A ResponseEntity containing a list of products
     */
    @GetMapping({"/products/p/{pageSize}/{pageNumber}", "/products/p/{pageSize}/{pageNumber}/"})
    public ResponseEntity<List<Product>> getProductsByPage(@PathVariable int pageSize,
                                                           @PathVariable int pageNumber,
                                                           @RequestParam(required = false) String sortColumn,
                                                           @RequestParam(required = false) String sortDirection) {
        try {
            Page<Product> products = productService.getProducts(pageSize, pageNumber,
                    Optional.ofNullable(sortColumn),
                    Optional.ofNullable(sortDirection));
            return new ResponseEntity<>(products.getContent(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Controller method: Retrieves products in a specific category.
     *
     * @param categoryTitle Title of the category.
     * @return ResponseEntity containing a list of Product objects in the specified category.
     * @throws ProductNotFoundException If no products are found in the specified category.
     */
    @GetMapping({"/products/category/{categoryTitle}", "/products/category/{categoryTitle}/"})
    public ResponseEntity<List<Product>> getProductsInCategory(@PathVariable("categoryTitle") String categoryTitle) throws ProductNotFoundException {
        List<Product> productList = productService.getProductsInCategory(categoryTitle);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    /**
     * Controller method: Retrieves all categories.
     *
     * @return ResponseEntity containing a list of all Category objects.
     */
    @GetMapping({"/products/categories", "/products/categories/"})
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = productService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    /**
     * Controller method: Updates details of a product and updates the product cache.
     *
     * <p>After updating the product, the updated Product object is stored in the product cache with the
     * specified product ID as the cache key.</p>
     *
     * @param productId The ID of the product to update. Must be a positive long integer.
     * @param request The ProductRequestDto containing the updated product details.
     * @return The updated Product object.
     * @throws ProductNotFoundException If the specified product is not found.
     * @throws IllegalAccessException If the update operation is not permitted.
     */
    @CachePut(value = "product", key = "#productId")
    @PutMapping({"/products/{id}", "/products/{id}/"})
    public Product updateProduct(@PathVariable("id") Long productId, @RequestBody ProductRequestDto request) throws ProductNotFoundException, IllegalAccessException {
        return productService.updateProduct(productId, request.toProduct());
    }

    /**
     * Controller method: Deletes a product and removes it from the product cache.
     *
     * <p>After deleting the product, its entry is evicted from the product cache using the specified product ID as the cache key.</p>
     *
     * @param productId The ID of the product to delete. Must be a positive long integer.
     * @return The ID of the deleted product.
     * @throws ProductNotFoundException If the specified product is not found.
     */
    @CacheEvict(value = "product", key = "#productId")
    @DeleteMapping({"/products/{id}", "/products/{id}/"})
    public long deleteProduct(@PathVariable("id") Long productId) throws ProductNotFoundException {
        return productService.deleteProduct(productId);
    }

}
