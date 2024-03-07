package org.example.springproductcatalogue.dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.springproductcatalogue.models.Category;
import org.example.springproductcatalogue.models.Product;

/**
 * DTO to enable data communication between API calls on FakeStoreAPI.com
 */
@Getter
@Setter
public class FakeStoreProductDto {

    private Long id;
    private String title;
    private String description;
    private String category;
    private Double price;
    private String image;

    /**
     * Map the response product details with the DTO properties
     * @return Product object
     */
    public Product toProduct() {

        Product product = new Product();
        product.setId(id);
        product.setTitle(title);
        product.setDescription(description);
        product.setCategory(new Category(null,category));
        product.setPrice(price);
        product.setImageUrl(image);

        return product;
    }

}
