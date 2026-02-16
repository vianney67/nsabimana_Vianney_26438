package com.poggy.question4_E_commerce_api.controller.product;

import com.poggy.question4_E_commerce_api.model.product.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private List<Product> products = new ArrayList<>();

    public ProductController() {
        products.add(new Product(1L, "Smartphone X", "5G smartphone with OLED display", 799.99, "Electronics", 25, "TechBrand"));
        products.add(new Product(2L, "Laptop Pro", "High-performance laptop", 1299.99, "Electronics", 10, "ComputeCorp"));
        products.add(new Product(3L, "Wireless Earbuds", "Noise-cancelling earbuds", 149.99, "Accessories", 0, "SoundWave"));
        products.add(new Product(4L, "Running Shoes", "Lightweight running shoes", 89.99, "Sportswear", 50, "FitFeet"));
        products.add(new Product(5L, "Office Chair", "Ergonomic office chair", 199.99, "Furniture", 15, "ComfortPlus"));
        products.add(new Product(6L, "Coffee Maker", "Automatic drip coffee maker", 59.99, "Home Appliances", 30, "HomeBrew"));
        products.add(new Product(7L, "Gaming Mouse", "RGB gaming mouse", 49.99, "Accessories", 40, "TechBrand"));
        products.add(new Product(8L, "Winter Jacket", "Insulated winter jacket", 149.49, "Clothing", 5, "NorthPeak"));
        products.add(new Product(9L, "Bluetooth Speaker", "Portable Bluetooth speaker", 79.99, "Electronics", 0, "SoundWave"));
        products.add(new Product(10L, "Desk Lamp", "LED desk lamp", 29.99, "Home Decor", 60, "BrightLite"));
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "limit", defaultValue = "10") int limit) {
        if (limit <= 0) {
            return ResponseEntity.ok(Collections.emptyList());
        }
        int fromIndex = page * limit;
        if (fromIndex >= products.size()) {
            return ResponseEntity.ok(Collections.emptyList());
        }
        int toIndex = Math.min(fromIndex + limit, products.size());
        List<Product> paged = products.subList(fromIndex, toIndex);
        return ResponseEntity.ok(paged);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        for (Product product : products) {
            if (product.getProductId().equals(productId)) {
                return ResponseEntity.ok(product);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String category) {
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getCategory() != null && product.getCategory().equalsIgnoreCase(category)) {
                result.add(product);
            }
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/brand/{brand}")
    public ResponseEntity<List<Product>> getProductsByBrand(@PathVariable String brand) {
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getBrand() != null && product.getBrand().equalsIgnoreCase(brand)) {
                result.add(product);
            }
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProductsByKeyword(@RequestParam("keyword") String keyword) {
        List<Product> result = new ArrayList<>();
        String lowerKeyword = keyword.toLowerCase();
        for (Product product : products) {
            String name = product.getName() == null ? "" : product.getName();
            String description = product.getDescription() == null ? "" : product.getDescription();
            if (name.toLowerCase().contains(lowerKeyword) || description.toLowerCase().contains(lowerKeyword)) {
                result.add(product);
            }
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/price-range")
    public ResponseEntity<List<Product>> getProductsByPriceRange(
            @RequestParam("min") double min,
            @RequestParam("max") double max) {
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getPrice() != null && product.getPrice() >= min && product.getPrice() <= max) {
                result.add(product);
            }
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/in-stock")
    public ResponseEntity<List<Product>> getProductsInStock() {
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getStockQuantity() > 0) {
                result.add(product);
            }
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        products.add(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId, @RequestBody Product updatedProduct) {
        for (Product product : products) {
            if (product.getProductId().equals(productId)) {
                product.setName(updatedProduct.getName());
                product.setDescription(updatedProduct.getDescription());
                product.setPrice(updatedProduct.getPrice());
                product.setCategory(updatedProduct.getCategory());
                product.setStockQuantity(updatedProduct.getStockQuantity());
                product.setBrand(updatedProduct.getBrand());
                return ResponseEntity.ok(product);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PatchMapping("/{productId}/stock")
    public ResponseEntity<Product> updateStockQuantity(
            @PathVariable Long productId,
            @RequestParam("quantity") int quantity) {
        for (Product product : products) {
            if (product.getProductId().equals(productId)) {
                product.setStockQuantity(quantity);
                return ResponseEntity.ok(product);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        for (Product product : products) {
            if (product.getProductId().equals(productId)) {
                products.remove(product);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
