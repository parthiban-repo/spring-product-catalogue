package org.example.springproductcatalogue.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.*;

/**
 * Model class: Represents a product.
 *
 * <p>The Product class defines the attributes and relationships of a product in the system.
 * Each product has a title, description, price, image URL, and may belong to a specific category.</p>
 */
@Getter // automatically creates the basic get methods of this class using Lombok
@Setter // automatically creates the basic set methods of this class using Lombok
@NoArgsConstructor // automatically creates the basic no args constructor of this class using Lombok
@AllArgsConstructor // automatically creates the basic all args constructor of this class using Lombok
@Entity // to identify this class as an entity for ORM operations
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product extends BaseModel implements Serializable{
    private String title;
    @Column(length = 512)
    private String description;
    private Double price;
    private String imageUrl;
    private boolean notInProduction;
    /*
    Define cardinality between Product and Category models.
    ManyToOne Many: Product, One: Category
    cascade=CascadeType.PERSIST Creates a category if it does not exist
    */
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Category category;

}
