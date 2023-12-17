package com.ulasgergerli.virtucart.VirtuCart.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller("/api/v1/product")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    public Product addProduct(Product product) {
        return productService
                .addProduct(product);
    }

    @PutMapping("/update")
    public Product updateProduct(Product product) {
        return productService
                .updateProduct(product);
    }

    @DeleteMapping("/delete")
    public void deleteProduct(Long id) {
        productService
                .deleteProduct(id);
    }

    @GetMapping("/get")
    public Product getProduct(Long id) {
        return productService
                .getProduct(id);
    }

    @GetMapping("/getByName")
    public Product getProductByName(String name) {
        return productService
                .getProductByName(name);
    }

    @GetMapping("/productKeywordSearch")
    public Product getProductByNameContaining(String keyword) {
        return productService
                .getProductByNameContaining(keyword);
    }
}
