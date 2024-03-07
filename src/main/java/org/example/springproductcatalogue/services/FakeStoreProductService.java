package org.example.springproductcatalogue.services;

import org.example.springproductcatalogue.dtos.FakeStoreProductDto;
import org.example.springproductcatalogue.models.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Service 'FakeStoreProductService'
 * Service to serve all API requests on <a href="https://fakestoreapi.com/">...</a>
 */
@Service
@Primary // annotating as the primary implementation of ProductService
public class FakeStoreProductService implements ProductService {

    private final RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Get details of a single product with given productId
     * @param productId ID of the product - Long positive integer
     * @return Product object
     */
    @Override
    public Product getSingleProduct(Long productId) {

        FakeStoreProductDto fakeStoreProduct = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + productId, // API url
                FakeStoreProductDto.class // data type of the response
                );

        return fakeStoreProduct != null ? fakeStoreProduct.toProduct() : null;
    }

    /**
     * Get all products from the datasource
     *
     * @return List &lt;Product&gt; object
     */
    @Override
    public List<Product> getAllProducts() {

        FakeStoreProductDto[] productsArr = restTemplate.getForObject(
                "https://fakestoreapi.com/products", // API url
                FakeStoreProductDto[].class // data type of the response
        );

        assert productsArr != null;
        return getProductListFromArray(productsArr);
    }

    /**
     * Get products in a specific category
     *
     * @param categoryTitle Title of the category
     * @return List &lt;Product&gt; object
     */
    @Override
    public List<Product> getProductsInCategory(String categoryTitle) {

        FakeStoreProductDto[] productsArr = restTemplate.getForObject(
                "https://fakestoreapi.com/products/category/" + categoryTitle, // API url
                FakeStoreProductDto[].class // data type of the response
        );

        assert productsArr != null;
        return getProductListFromArray(productsArr);
    }

    /**
     * Create a new product by calling the FakeStoreApi
     *
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
        productDto.setImage(product.getImageUrl());

        FakeStoreProductDto postResponse = restTemplate.postForObject(
                "https://fakestoreapi.com/products", // API url
                productDto, // request body
                FakeStoreProductDto.class // data type of response
                );

        return postResponse != null ? postResponse.toProduct() : null;

    }

    //**** Utility methods ****//

    /**
     * Get list of products from array of product objects
     *
     * @param products FakeStoreProductDto[]
     * @return List &lt;Product&gt; object
     */
    private List<Product> getProductListFromArray(FakeStoreProductDto[] products) {
        List<Product> listProducts = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProduct: products) {
            listProducts.add(fakeStoreProduct.toProduct());
        }

        return listProducts;
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
