package org.example.springproductcatalogue.dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.springproductcatalogue.models.Category;
import org.example.springproductcatalogue.models.Product;

/**
 * DTO to handle POST requests data communications between API calls
 */
@Getter
@Setter
public class ProductRequestDto {

    private Long productId;
    private String title;
    private String description;
    private Double price;
    private String imageURL;
    private String category;

    /**
     * Map the response - product details with the DTO properties
     * @return Product object
     */
    public Product toProduct() {

        var product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setCategory(new Category(null,category));
        product.setPrice(price);
        product.setImageURL(imageURL);

        return product;
    }
}
