package org.example.springproductcatalogue.repositories;

import org.example.springproductcatalogue.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository interface: Manages data operations for products.
 *
 * <p>The ProductRepository interface extends JpaRepository to provide CRUD operations for Product entities.
 * It also includes additional custom query methods for specific data retrieval.</p>
 */
public interface ProductRepository extends JpaRepository<Product,Long> {

    // *** Define custom methods *** //

    /**
     * Retrieves products with titles containing the specified keyword.
     *
     * @param productTitle The keyword to search for in product titles.
     * @return A list of Product objects matching the search criteria.
     */
    @Query("select p from Product p where p.title like %:productTitle%")
    List<Product> getProductsLikeName(@Param("productTitle") String productTitle);

    /**
     * Retrieves all products belonging to a category with the specified title.
     *
     * @param categoryTitle The title of the category.
     * @return A list of Product objects belonging to the specified category.
     */
    List<Product> findAllByCategoryTitle(String categoryTitle);

}
