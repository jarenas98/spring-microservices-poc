package academy.microservices.store.product.service;

import academy.microservices.store.product.entity.Category;
import academy.microservices.store.product.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {
    public List<Product> listAllProduct();

    public Product getProduct(Long id);

    public Product createProduct(Product product);

    public Product updateProduct(Product product);

    public Product deleteProduct(Long id);

    public List<Product> findByCategory(Category category);

    public Product updateStock(Long id, Double quantity);
}
