package org.example.springproductcatalogue.repositories;

import org.example.springproductcatalogue.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface: Manages data operations for categories.
 *
 * <p>The CategoryRepository interface extends JpaRepository to provide CRUD operations for Category entities.
 * It also includes additional custom query methods for specific data retrieval.</p>
 */
public interface CategoryRepository extends JpaRepository<Category,Long> {

    /**
     * Retrieves a category by its title.
     *
     * @param title The title of the category to retrieve.
     * @return The Category object with the specified title, or null if not found.
     */
    Category findByTitle(String title);
}
