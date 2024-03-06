package org.example.springproductcatalogue.controllers;

import org.example.springproductcatalogue.dtos.ProductRequestDto;
import org.example.springproductcatalogue.models.Product;
import org.example.springproductcatalogue.services.ProductService;
import org.springframework.web.bind.annotation.*;

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
     * category:
     * imageURL:
     * }
     * @param request ProductRequestDto
     * @return Product Object
     */
    @PostMapping("/products/")
    public Product createProduct(@RequestBody ProductRequestDto request) {

        return productService.createProduct(request.toProduct());

    }

    /**
     * Controller method to get details of a product
     * GET /products/{id}
     * Eg. /products/22
     *
     * @param productId - id of the product - positive long integer
     */
    @GetMapping("/products/{id}")
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