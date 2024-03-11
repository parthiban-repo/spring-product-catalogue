package org.example.springproductcatalogue.repositories;

import org.example.springproductcatalogue.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * ProductRepository interface to manage data operations on the data models
 */
public interface ProductRepository extends JpaRepository<Product,Long> {
    //Product save(Product product);

    Product findProductById(Long id);
    List<Product> findAllByCategoryTitle(String categoryTitle);
}
