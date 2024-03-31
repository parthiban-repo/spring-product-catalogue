package org.example.springproductcatalogue.services;

import org.example.springproductcatalogue.dtos.FakeStoreProductDto;
import org.example.springproductcatalogue.exceptions.ProductNotFoundException;
import org.example.springproductcatalogue.models.Category;
import org.example.springproductcatalogue.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Service 'FakeStoreProductService' implementing ProductService service
 * Serve all API requests on <a href="https://fakestoreapi.com/">...</a>
 *
 * @author Parthiban Rajendran
 */
@Service("fakeStoreProductService")
//@Primary // annotating as the primary implementation of ProductService
public class FakeStoreProductService implements ProductService {

    private final RestTemplate restTemplate;

    /**
     * FakeStoreProductService Constructor taking RestTemplate argument
     * @param restTemplate RestTemplate
     */
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Get all product categories from FakeStoreAPI
     *
     * @return List &lt;Category&gt; object
     */
    @Override
    public List<Category> getAllCategories() {
        String[] categoryArr = restTemplate.getForObject(
                "https://fakestoreapi.com/products/categories", // API url
                String[].class // data type of the response
        );

        assert categoryArr != null;
        return createCategoryListFromArray(categoryArr);

    }

    /**
     * Get details of a single product with given productId from FakeStoreAPI
     *
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
     * Get all products from FakeStoreAPI
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
        return createProductListFromArray(productsArr);

    }

    /**
     * Get products in a specific category from FakeStoreAPI
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
        return createProductListFromArray(productsArr);
    }

    /**
     * Create a new product with FakeStoreAPI
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

    /**
     * Update a product with FakeStoreApi
     *
     * @param productId Product ID
     * @return &lt;Product&gt; object
     */
    @Override
    public Product updateProduct(Long productId, Product productUpdate) {

        /*
        ** Mimicking update output **
        Get the product, update the properties and return it because FakeStoreAPI
        does not really update the product in its database after PUT request is sent
        */
        Product product = getSingleProduct(productId);

        if (productUpdate.getTitle() != null) {
            product.setTitle(productUpdate.getTitle());
        }
        if (productUpdate.getDescription() != null) {
            product.setDescription(productUpdate.getDescription());
        }
        if (productUpdate.getPrice() != null) {
            product.setPrice(productUpdate.getPrice());
        }
        if (productUpdate.getImageUrl() != null) {
            product.setImageUrl(productUpdate.getImageUrl());
        }
        if (productUpdate.getCategory().getTitle() != null) {
            product.getCategory().setTitle(productUpdate.getCategory().getTitle());
        }

        restTemplate.put(
                "https://fakestoreapi.com/products/" + productId,
                product);

        return product;

    }

    /**
     * Delete a product with FakeStoreApi
     *
     * @param productId Product ID
     * @return Long Deleted Product ID
     */
    @Override
    public Long deleteProduct(Long productId) {

        /*
        Note: In real, FakeStoreAPI does not delete any product from its database
         */
        restTemplate.delete(
                "https://fakestoreapi.com/products/" + productId);

        return productId;

    }

    @Override
    public List<Product> getProductsLikeTitle(String productTitle) throws ProductNotFoundException {
        return null;
    }

    //**** Utility methods ****//

    /**
     * Get list of products from array of product objects
     *
     * @param products FakeStoreProductDto[]
     * @return List &lt;Product&gt; object
     */
    private List<Product> createProductListFromArray(FakeStoreProductDto[] products) {

        List<Product> listProducts = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProduct : products) {
            listProducts.add(fakeStoreProduct.toProduct());
        }

        return listProducts;

    }

    /**
     * Create list of categories from array of category objects
     *
     * @param categories String[]
     * @return List &lt;Category&gt; object
     */
    private List<Category> createCategoryListFromArray(String[] categories) {

        List<Category> listCategories = new ArrayList<>();
        for (String category : categories) {
            listCategories.add(new Category(category));
        }

        return listCategories;

    }

}
