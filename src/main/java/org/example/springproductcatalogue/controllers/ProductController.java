package org.example.springproductcatalogue.controllers;

import org.example.springproductcatalogue.dtos.ProductRequestDto;
import org.example.springproductcatalogue.exceptions.ProductNotFoundException;
import org.example.springproductcatalogue.models.Product;
import org.example.springproductcatalogue.models.Category;
import org.example.springproductcatalogue.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * ProductController
 * Controller to receive requests and pass it on to a Service
 *
 * @author Parthiban Rajendran
 */
@RestController
public class ProductController {

    private final ProductService productService;

    /**
     * Constructor
     *
     * @param productService Type:ProductService
     */
    public ProductController(@Qualifier("SelfDBProductService") ProductService productService) {
        this.productService = productService;
    }

    /**
     * Controller method to create a new product
     * POST /products
     * RequestBody
     * {
     * title:, description:, price:, category:,image:
     * }
     *
     * @param request ProductRequestDto
     * @return Product Object
     */
    @PostMapping({"/products", "/products/"})
    public ResponseEntity<Product> createProduct(@RequestBody ProductRequestDto request) throws Exception {
        Product product = productService.createProduct(request.toProduct());
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    /**
     * Controller method to get details of a product
     * GET /products/{id}
     * E.g. /products/22
     *
     * @param productId - id of the product - positive long integer
     * @return Product object
     */
    @GetMapping({"/products/{id}", "/products/{id}/"})
    public ResponseEntity<Product> getProductDetails(@PathVariable("id") Long productId) throws ProductNotFoundException {
        Product product = productService.getSingleProduct(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    /**
     * Controller method to get all products
     *
     * @return List&lt;Product&gt;
     */
    @GetMapping({"/products", "/products/"})
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * Controller method to get products in a specific category
     *
     * @param categoryTitle - Title of the category
     * @return List&lt;Product&gt;
     */
    @GetMapping({"/products/category/{categoryTitle}", "/products/category/{categoryTitle}/"})
    public ResponseEntity<List<Product>> getProductsInCategory(@PathVariable("categoryTitle") String categoryTitle) {
        List<Product> productList = productService.getProductsInCategory(categoryTitle);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    /**
     * Controller method to get all categories
     *
     * @return ResponseEntity&lt;List&lt;Category&gt;&gt;
     */
    @GetMapping({"/products/categories", "/products/categories/"})
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = productService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    /**
     * Controller method to update details of a product
     *
     * @param productId - id of the product - positive long integer
     * @param request   - @RequestBody
     * @return Product Object
     */
    @PutMapping({"/products/{id}", "/products/{id}/"})
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long productId, @RequestBody ProductRequestDto request) throws ProductNotFoundException, IllegalAccessException {
        Product product = productService.updateProduct(productId, request.toProduct());
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    /**
     * Controller method to delete a product
     *
     * @param productId - id of the product - positive long integer
     * @return Deleted product id
     */
    @DeleteMapping({"/products/{id}", "/products/{id}/"})
    public ResponseEntity<Long> deleteProduct(@PathVariable("id") Long productId) throws ProductNotFoundException {
        productId = productService.deleteProduct(productId);
        return new ResponseEntity<>(productId, HttpStatus.OK);
    }

}
