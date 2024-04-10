package org.example.springproductcatalogue.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * Model class: Represents a category of products.
 *
 * <p>The Category class defines the attributes and relationships of a product category. Each category
 * has a unique title and may contain multiple products associated with it.</p>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Category extends BaseModel implements Serializable{
    @Column(unique=true)
    private String title;
    /*
    Define cardinality
    mappedBy: specify that this cardinality has been already
    established in the Product model by the "category" property with 'mappedBy'
    CascadeType.PERSIST: retains the child rows
    FetchType.Lazy: Will get products only when getProducts of this entity object is called
     */
    @OneToMany(mappedBy = "category", cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Product> products;

    /**
     * Constructs a new Category instance with the specified title.
     *
     * @param categoryTitle The title of the category.
     */
    public Category(String categoryTitle) {
       this.title = categoryTitle;
    }
}
