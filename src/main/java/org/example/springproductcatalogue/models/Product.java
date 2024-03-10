package org.example.springproductcatalogue.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Model 'Product'
 * Product model to handle product attributes and related methods
 */
@Getter // automatically creates the basic get methods of this class using Lombok
@Setter // automatically creates the basic set methods of this class using Lombok
@NoArgsConstructor // automatically creates the basic no args constructor of this class using Lombok
@AllArgsConstructor // automatically creates the basic all args constructor of this class using Lombok
@Entity // to identify this class as an entity for ORM operations
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product extends BaseModel {
    private String title;
    private String description;
    private Double price;
    private String imageUrl;
    /*
    define cardinality between Product and Category models.
    ManyToOne Many: Product, One: Category
    cascade=CascadeType.PERSIST Creates a category if it does not exist
    */
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Category category;
}
