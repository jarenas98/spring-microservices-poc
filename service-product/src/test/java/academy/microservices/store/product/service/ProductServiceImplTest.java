package academy.microservices.store.product.service;

import academy.microservices.store.product.entity.Category;
import academy.microservices.store.product.entity.Product;
import academy.microservices.store.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.productService = new ProductServiceImpl(productRepository);

        Product computer = Product.builder()
                .name("computer")
                .category(Category.builder().id(1L).build())
                .description("computer description")
                .stock(Double.parseDouble("10"))
                .price(Double.parseDouble("1240.99"))
                .createAt(new Date())
                .build();

        Mockito.when(this.productRepository.findById(1L))
                .thenReturn(Optional.of(computer));

        Mockito.when(this.productRepository.save(computer)).thenReturn(computer);
    }

    @Test
    void whenValidGetId_ThenReturnProduct() {
        Product found = this.productService.getProduct(1L);
        assert (found.getName()).equals("computer");
    }

    @Test
    void whenValidateUpdateStock_ThenReturnNewStock() {
        Product newStock = this.productService.updateStock(1L, Double.parseDouble("8"));
        assert (newStock.getStock()).equals(Double.parseDouble("18"));
    }
}