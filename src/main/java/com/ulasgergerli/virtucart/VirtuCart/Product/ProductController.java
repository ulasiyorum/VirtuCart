package com.ulasgergerli.virtucart.VirtuCart.Product;

import com.ulasgergerli.virtucart.VirtuCart.Category.Category;
import com.ulasgergerli.virtucart.VirtuCart.Category.CategoryService;
import com.ulasgergerli.virtucart.VirtuCart.Discount.DiscountService;
import com.ulasgergerli.virtucart.VirtuCart.Dtos.ProductDto;
import com.ulasgergerli.virtucart.VirtuCart.Factory.ProductFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    private final ProductFactory productFactory = ProductFactory.getInstance();

    @Autowired
    public ProductController(ProductService productService,
                             CategoryService categoryService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @PostMapping("/add")
    @Secured("ROLE_ADMIN")

    public ProductDto addProduct(ProductDto productDto) {
        List<Category> categories = categoryService.getCategories(productDto.getCategoryIds());
        Product product = productFactory.createProduct(productDto, categories);

        product = productService
                .addProduct(product);

        return productFactory.createProductDto(product);
    }

    @GetMapping("/getAll")

    public List<ProductDto> getAllProducts() {
        List<Product> products = productService
                                    .getAllProducts();

        List<ProductDto> productDtos = new ArrayList<>();
        for(Product product : products) {
            List<Long> categoryIds = new ArrayList<>();
            for(Category category : product.getCategories()) {
                categoryIds.add(category.getId());
            }

            product.setCategories(categoryService.getCategories(categoryIds));

            productDtos.add(productFactory.createProductDto(product));
        }

        return productDtos;
    }

    @PutMapping("/update")
    @Secured("ROLE_ADMIN")

    public ProductDto updateProduct(ProductDto productDto) {
        List<Category> categories = categoryService.getCategories(productDto.getCategoryIds());
        Product product = productFactory.createProduct(productDto, categories);

        product = productService
                .updateProduct(product);

        return productFactory.createProductDto(product);
    }

    @DeleteMapping("/delete")
    @Secured("ROLE_ADMIN")

    public void deleteProduct(Long id) {
        productService
                .deleteProduct(id);
    }

    @GetMapping("/get")
    public ProductDto getProduct(Long id) {
        Product product = productService
                            .getProduct(id);

        List<Long> categoryIds = new ArrayList<>();
        for(Category category : product.getCategories()) {
            categoryIds.add(category.getId());
        }

        product.setCategories(categoryService.getCategories(categoryIds));

        return productFactory.createProductDto(product);
    }

    @GetMapping("/getByName")
    public ProductDto getProductByName(String name) {
        Product product = productService
                            .getProductByName(name);

        List<Long> categoryIds = new ArrayList<>();
        for(Category category : product.getCategories()) {
            categoryIds.add(category.getId());
        }

        product.setCategories(categoryService.getCategories(categoryIds));

        return productFactory.createProductDto(product);
    }

    @GetMapping("/productKeywordSearch")
    public List<ProductDto> getProductByNameContaining(String keyword) {
        List<Product> products = productService
                                    .getProductsByNameContaining(keyword);

        List<ProductDto> productDtos = new ArrayList<>();
        for(Product product : products) {
            List<Long> categoryIds = new ArrayList<>();
            for(Category category : product.getCategories()) {
                categoryIds.add(category.getId());
            }

            product.setCategories(categoryService.getCategories(categoryIds));

            productDtos.add(productFactory.createProductDto(product));
        }

        return productDtos;
    }

    @PostMapping("/subscribe")
    public void subscribeToProduct(Long userId, Long productId) {
        productService.subscribeToProduct(userId, productId);
    }
}
