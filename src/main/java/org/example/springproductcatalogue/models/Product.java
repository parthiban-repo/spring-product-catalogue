package org.example.springproductcatalogue.models;

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
public class Product {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String imageUrl;
    private Category category;
}
