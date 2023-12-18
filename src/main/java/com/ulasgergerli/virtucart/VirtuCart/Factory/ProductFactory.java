package com.ulasgergerli.virtucart.VirtuCart.Factory;

import com.ulasgergerli.virtucart.VirtuCart.Category.Category;
import com.ulasgergerli.virtucart.VirtuCart.Discount.Discount;
import com.ulasgergerli.virtucart.VirtuCart.Discount.FixedDiscount;
import com.ulasgergerli.virtucart.VirtuCart.Discount.PercentageDiscount;
import com.ulasgergerli.virtucart.VirtuCart.Dtos.ProductDto;
import com.ulasgergerli.virtucart.VirtuCart.Product.Product;
import jakarta.annotation.Nullable;

import java.util.List;
import java.util.Optional;

public class ProductFactory {

    private static ProductFactory instance;

    private ProductFactory() {
    }

    public static ProductFactory getInstance() {
        if(instance == null) {
            instance = new ProductFactory();
        }
        return instance;
    }

    public Product createProduct(ProductDto productDto, List<Category> categories) {
        return new Product(productDto.getName(), productDto.getDescription(),
        productDto.getPrice(), productDto.getImages(), categories);
    }

    public ProductDto createProductDto(Product product, @Nullable Discount discount) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setImages(product.getImages());

        if(discount == null) {
            productDto.setDiscountedPrice(product.getPrice());
            return productDto;
        }

        productDto.setDiscountId(discount.getId());

        switch(discount.getDiscountType()) {
            case PERCENTAGE:
                PercentageDiscount percentageDiscount = new PercentageDiscount(discount.getDiscountAmount());
                productDto.setDiscountedPrice(percentageDiscount.applyDiscount(product.getPrice()));
                break;
            case FIXED:
                FixedDiscount fixedDiscount = new FixedDiscount(discount.getDiscountAmount());
                productDto.setDiscountedPrice(fixedDiscount.applyDiscount(product.getPrice()));
                break;
            default:
                productDto.setDiscountedPrice(product.getPrice());
                break;
        }

        return productDto;
    }
}
