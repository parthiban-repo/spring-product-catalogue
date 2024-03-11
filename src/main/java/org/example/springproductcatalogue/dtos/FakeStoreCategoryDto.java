package org.example.springproductcatalogue.dtos;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO to enable category related data communication between API calls on FakeStoreAPI.com
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
