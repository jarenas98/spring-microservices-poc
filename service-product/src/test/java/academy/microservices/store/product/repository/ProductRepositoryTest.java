package academy.microservices.store.product.repository;

import academy.microservices.store.product.entity.Category;
import academy.microservices.store.product.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    void whenFindByCategory_then_returnProductList() {
        Product product01 = Product.builder()
                .name("computer")
                .category(Category.builder().id(1L).build())
                .description("computer description")
                .stock(Double.parseDouble("10"))
                .price(Double.parseDouble("1240.99"))
                .status("Created")
                .createAt(new Date())
                .build();

        //productRepository.save(product01);

        List<Product> founds = productRepository.findByCategory(product01.getCategory());

        assertEquals(founds.size(), 2);
    }
}