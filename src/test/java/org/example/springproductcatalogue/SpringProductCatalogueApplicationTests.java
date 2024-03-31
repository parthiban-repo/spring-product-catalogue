package org.example.springproductcatalogue;

import jakarta.transaction.Transactional;
import org.example.springproductcatalogue.models.Category;
import org.example.springproductcatalogue.models.Product;
import org.example.springproductcatalogue.repositories.CategoryRepository;
import org.example.springproductcatalogue.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class SpringProductCatalogueApplicationTests {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;

	@Test
	void contextLoads() {
	}

	@Transactional
	@Test
	void queryTests() {
		Optional<Category> category = categoryRepository.findById(1L);
		if(category.isPresent()) {
			List<Product> products = category.get().getProducts();
			System.out.println(products.size());
		}
	}

}
