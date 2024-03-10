package org.example.springproductcatalogue.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Model 'Category'
 * Class to handle category attributes and related methods
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Category extends BaseModel {
    private String title;
    /*
    define cardinality
    mappedBy: specify that this cardinality has been already
    established in the Product model by the "category" property with 'mappedBy'
    CascadeType.REMOVE remove the matching rows in the child table
     */
    @OneToMany(mappedBy = "category", cascade = {CascadeType.REMOVE})
    private List<Product> products;

    /**
     * Category construction
     * @param categoryTitle Title of the category
     */
    public Category(String categoryTitle) {
       this.title = categoryTitle;
    }
}
