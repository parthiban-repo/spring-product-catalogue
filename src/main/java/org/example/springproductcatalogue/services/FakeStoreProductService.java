package org.example.springproductcatalogue.services;

import org.example.springproductcatalogue.dtos.FakeStoreProductDto;
import org.example.springproductcatalogue.exceptions.ProductNotFoundException;
import org.example.springproductcatalogue.models.Category;
import org.example.springproductcatalogue.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service implementation for interacting with products from the FakeStoreAPI.
 * This service retrieves, creates, updates, and deletes products using the FakeStoreAPI endpoints.
 *
 * <p>
 *     This service communicates with the FakeStoreAPI to perform various operations
 *     such as retrieving all products, getting products by category, creating new products,
 *     updating existing products, and deleting products.
 * </p>
 *
 * @author Parthiban Rajendran
 */
@Service("fakeStoreProductService")
//@Primary // annotating as the primary implementation of ProductService
public class FakeStoreProductService implements ProductService {

    private final RestTemplate restTemplate;

    /**
     * Constructs a new FakeStoreProductService with the given RestTemplate.
     *
     * @param restTemplate The RestTemplate instance used to make HTTP requests.
     */
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    //****** Implementation of ProductService methods ******//

    /**
     * Retrieves all product categories.
     *
     * @return A list of Category objects representing all available product categories.
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
     * Retrieves details of a single product by its ID.
     *
     * @param productId The ID of the product to retrieve.
     * @return The Product object corresponding to the specified ID.
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
     * Retrieves all products.
     *
     * @return A list of all Product objects.
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
     * Retrieves products belonging to a specific category.
     *
     * @param categoryTitle The title of the category.
     * @return A list of Product objects belonging to the specified category.
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
     * Creates a new product.
     *
     * @param product The Product object representing the new product.
     * @return The created Product object.
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
     * Updates an existing product.
     *
     * @param productId The ID of the product to update.
     * @param productUpdate   The Product object containing the updated details.
     * @return The updated Product object.
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
     * Deletes a product by its ID.
     *
     * @param productId The ID of the product to delete.
     * @return The ID of the deleted product.
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

    /**
     * Retrieves products with titles containing the specified keyword.
     *
     * @param productTitle The keyword to search for in product titles.
     * @return A list of Product objects matching the search criteria.
     * @throws ProductNotFoundException If no products matching the search criteria are found.
     */
    @Override
    public List<Product> getProductsLikeTitle(String productTitle) throws ProductNotFoundException {
        return null;
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
    public Page<Product> getProducts(int pageSize, int pageNumber, Optional<String> sortColumn, Optional<String> sortDirection) {
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
