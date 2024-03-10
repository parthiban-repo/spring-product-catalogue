package org.example.springproductcatalogue.dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.springproductcatalogue.models.Category;
import org.example.springproductcatalogue.models.Product;

/**
 * DTO to handle Create Product POST/PUT/PATCH request data communications between API calls
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
     * Map the response - product details with the DTO properties
     *
     * @return Product object
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
