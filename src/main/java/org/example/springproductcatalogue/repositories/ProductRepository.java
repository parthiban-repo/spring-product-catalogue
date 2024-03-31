package org.example.springproductcatalogue.repositories;

import org.example.springproductcatalogue.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * ProductRepository interface to manage data operations on the data models
 */
public interface ProductRepository extends JpaRepository<Product,Long> {

    // *** Define custom methods *** //

    /**
     * Get products with title
     *
     * @param productTitle Title of the product
     * @return List &lt;Product&gt; object
     */
    @Query("select p from Product p where p.title like %:productTitle%")
    List<Product> getProductsLikeName(@Param("productTitle") String productTitle);

    /**
     * Find all products by category title
     *
     * @param categoryTitle Title of the category
     * @return List of Product objects
     */
    List<Product> findAllByCategoryTitle(String categoryTitle);

}
