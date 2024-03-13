package org.example.springproductcatalogue.repositories;

import org.example.springproductcatalogue.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * ProductRepository interface to manage data operations on the data models
 */
public interface ProductRepository extends JpaRepository<Product,Long> {

    // *** Define custom methods *** //
    List<Product> findAllByCategoryTitle(String categoryTitle);

}
