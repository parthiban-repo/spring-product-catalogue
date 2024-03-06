package org.example.springproductcatalogue.services;

import org.example.springproductcatalogue.dtos.FakeStoreProductDto;
import org.example.springproductcatalogue.models.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Service 'FakeStoreProductService'
 * Service to serve all API requests on <a href="https://fakestoreapi.com/">...</a>
 */
@Service
@Primary // annotating as the primary implementation of ProductService
public class FakeStoreProductService implements ProductService {

    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Get details of a single product with given productId
     * @param productId Id of the product - Long positive integer
     * @return Product object
     */
    @Override
    public Product getSingleProduct(Long productId) {

        FakeStoreProductDto fakeStoreProduct = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + productId, // API url
                FakeStoreProductDto.class // data ype of the response
                );

        return fakeStoreProduct != null ? fakeStoreProduct.toProduct() : null;
    }

    /**
     * Get all products and their details
     * @return List &lt;Product&gt; object
     */
    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    /**
     * Create a new product by calling the FakeStoreApi
     * @param product &lt;Product&gt; object
     * @return &lt;Product&gt; object
     */
    @Override
    public Product createProduct(Product product) {

        FakeStoreProductDto productDto = new FakeStoreProductDto();
        productDto.setTitle(product.getTitle());
        productDto.setDescription(product.getDescription());
        productDto.setCategory(product.getCategory().getTitle());
        productDto.setPrice(product.getPrice());
        productDto.setImageURL(product.getImageURL());

        FakeStoreProductDto postResponse = restTemplate.postForObject(
                "https://fakestoreapi.com/products", // API url
                productDto, // request body
                FakeStoreProductDto.class // data type of response
                );

        return postResponse != null ? postResponse.toProduct() : null;

    }

}
