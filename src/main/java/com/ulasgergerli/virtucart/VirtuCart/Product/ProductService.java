package com.ulasgergerli.virtucart.VirtuCart.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product addProduct(Product product) {
        return productRepository
                .save(product);
    }

    public Product updateProduct(Product product) {
        return productRepository
                .save(product);
    }

    public void deleteProduct(Long id) {
        productRepository
                .deleteById(id);
    }

    public Product getProduct(Long id) {
        return productRepository
                .findById(id)
                .orElse(null);
    }

    public Product getProductByName(String name) {
        return productRepository
                .findByName(name);
    }

    public List<Product> getProductsByNameContaining(String keyword) {
        return productRepository
                .findAllByNameContaining(keyword);
    }

}
