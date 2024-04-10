package org.example.springproductcatalogue.dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.springproductcatalogue.models.Category;
import org.example.springproductcatalogue.models.Product;

/**
 * DTO class: Handles Create Product POST/PUT/PATCH request data communications between API calls.
 *
 * <p>This DTO class is used to facilitate communication of product creation/update request data between
 * API calls. It encapsulates product attributes such as ID, title, description, price, image, and category.</p>
 *
 */
@Getter
@Setter
public class ProductRequestDto {

    private Long id;
    private String title;
    private String description;
    private Double price;
    private String image;
    private String category;

    /**
     * Converts the request data to a Product object.
     *
     * <p>This method creates a new Product object and initializes its attributes using the
     * data from the corresponding fields in the DTO instance. It then returns the created Product object.</p>
     *
     * @return The Product object representing the mapped product details.
     */
    public Product toProduct() {

        Product product = new Product();
        product.setId(id);
        product.setTitle(title);
        product.setDescription(description);
        product.setCategory(new Category(category));
        product.setImageUrl(image);
        product.setPrice(price);

        return product;
    }
}
