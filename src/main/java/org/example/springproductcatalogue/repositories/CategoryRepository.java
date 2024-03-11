package org.example.springproductcatalogue.repositories;

import org.example.springproductcatalogue.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * CategoryRepository interface to manage data operations on the data models
 */
public interface CategoryRepository extends JpaRepository<Category,Long> {

    /**
     * Find category by title
     *
     * @param title Title of the category
     * @return Category object
     */
    Category findByTitle(String title);
}
