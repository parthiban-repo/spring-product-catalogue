package org.example.springproductcatalogue.services;

import org.example.springproductcatalogue.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseProductService implements ProductService {

    /**
     * Get details of a single product with given productId
     * @param productId - id of the product - Long positive integer
     * @return Product object
     */
    @Override
    public Product getSingleProduct(Long productId) {
        return null;
    }

    /**
     * Get all products and their details
     * @return List &lt;Product&gt; object
     */
    @Override
    public List<Product> getAllProducts() {
        return null;
    }
}
