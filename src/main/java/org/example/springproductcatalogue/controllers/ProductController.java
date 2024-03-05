package org.example.springproductcatalogue.controllers;

import org.example.springproductcatalogue.models.Product;
import org.example.springproductcatalogue.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ProductController
 * Controller to receive requests and pass it on to a Service
 *
 * @author Parthiban Rajendran
 */
@RestController
public class ProductController {

    private ProductService productService;

    /**
     * Constructor
     *
     * @param productService Type:ProductService
     */
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Controller method to create a new product
     * POST /products
     * RequestBody
     * {
     * title:
     * description:
     * price:
     * }
     */
    @PostMapping("/products")
    public void createProduct() {

    }

    /**
     * Controller method to get details of a product
     * GET /products/{id}
     * Eg. /products/22
     *
     * @param productId - id of the product - positive long integer
     */
    @GetMapping("products/{id}")
    public Product getProductDetails(@PathVariable("id") Long productId) {

        return productService.getSingleProduct(productId);
    }

    /**
     * Controller method to get all products
     */
    public void getAllProducts() {

    }

    /**
     * Controller method to update details of a product
     *
     * @param productId - id of the product - positive long integer
     */
    public void updateProduct(Long productId) {

    }

    /**
     * Controller method to delete a product
     *
     * @param productId - id of the product - positive long integer
     */
    public void deleteProduct(Long productId) {

    }

}
