package org.example.springproductcatalogue.dtos;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO class: Represents category-related data communication between API calls to FakeStoreAPI.com.
 *
 * <p>This DTO class is used to facilitate communication of category-related data between API calls
 * made to the FakeStoreAPI.com service. It encapsulates category ID and title information.</p>
 */
@Getter
@Setter
public class FakeStoreCategoryDto {
    private Long id;
    private String title;

    /*
     * Map the response category details with the DTO properties
     * @return Category object
     */
    /*
    public Category toCategory() {
        var category = new Category();
        category.setId(id);
        category.setTitle(title);

        return category;
    }*/
}
