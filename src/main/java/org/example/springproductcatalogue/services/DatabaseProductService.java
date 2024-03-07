package org.example.springproductcatalogue.services;

import org.example.springproductcatalogue.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseProductService implements ProductService {

    /**
     * Get details of a single product with given productId
     *
     * @param productId - id of the product - Long positive integer
     * @return Product object
     */
    @Override
    public Product getSingleProduct(Long productId) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public List<Product> getProductsInCategory(String categoryTitle) {
        return null;
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }
}
