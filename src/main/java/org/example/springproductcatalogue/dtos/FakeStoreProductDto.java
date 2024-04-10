package org.example.springproductcatalogue.dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.springproductcatalogue.models.Category;
import org.example.springproductcatalogue.models.Product;

/**
 * DTO class: Represents product-related data communication between API calls to FakeStoreAPI.com.
 *
 * <p>This DTO class is used to facilitate communication of product-related data between API calls
 * made to the FakeStoreAPI.com service. It encapsulates product ID, title, description, category, price,
 * and image information.</p>
 *
 */
@Getter
@Setter
public class FakeStoreProductDto {

    // IMPORTANT: property names should match the FakeStoreAPI attribute names
    private Long id;
    private String title;
    private String description;
    private String category;
    private Double price;
    private String image;

    /**
     * Converts the response product details to a Product object.
     *
     * <p>This method creates a new Product object and sets its attributes using the values from
     * the corresponding fields in the DTO instance. It then returns the created Product object.</p>
     *
     * @return The Product object representing the mapped product details.
     */
    public Product toProduct() {

        Product product = new Product();
        product.setId(id);
        product.setTitle(title);
        product.setDescription(description);
        product.setCategory(new Category(category));
        product.setPrice(price);
        product.setImageUrl(image);

        return product;
    }

}
