package academy.microservices.store.product.controller;

import academy.microservices.store.product.entity.Category;
import academy.microservices.store.product.entity.Product;
import academy.microservices.store.product.service.ProductService;
import academy.microservices.store.product.util.controller.ErrorMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> listProduct(@RequestParam(value = "categoryId", required = false) Long categoryId) {
        List<Product> products = new ArrayList<>();
        if (categoryId == null) {
            products = this.productService.listAllProduct();
            if (products.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
        } else {
            products = this.productService.findByCategory(Category.builder().id(categoryId).build());
            if (products.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
        }

        return ResponseEntity.ok(products);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable(value = "id") Long productId) {
        Product product = productService.getProduct(productId);

        if (product == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(product);
        }

    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product, BindingResult result) {
        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        Product productCreate = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productCreate);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") Long productId, @RequestBody Product product) {
        product.setId(productId);
        Product productUpdated = this.productService.updateProduct(product);
        if (productUpdated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productUpdated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable(value = "id") Long ProductId) {
        Product productDelete = this.productService.deleteProduct(ProductId);
        if (productDelete == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(productDelete);
    }

    @PutMapping("/update/{id}/stock")
    public ResponseEntity<Product> updateStockProduct(@PathVariable(value = "id") Long productId, @RequestParam("quantity") Double quantity) {
        Product productUpdated = this.productService.updateStock(productId, quantity);
        if (productUpdated == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(productUpdated);
    }

    private String formatMessage(BindingResult result) {
        List<Map<String, String>> errors = result.getFieldErrors().stream()
                .map(err -> {
                    Map<String, String> error = new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;
                }).collect(Collectors.toList());

        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors).build();

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";

        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return jsonString;
    }
}
