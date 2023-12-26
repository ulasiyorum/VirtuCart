package com.ulasgergerli.virtucart.VirtuCart.Factory;

import com.ulasgergerli.virtucart.VirtuCart.Basket.Basket;
import com.ulasgergerli.virtucart.VirtuCart.Basket.BasketItem;
import com.ulasgergerli.virtucart.VirtuCart.Discount.Discount;
import com.ulasgergerli.virtucart.VirtuCart.Discount.DiscountStrategy;
import com.ulasgergerli.virtucart.VirtuCart.Discount.FixedDiscount;
import com.ulasgergerli.virtucart.VirtuCart.Discount.PercentageDiscount;
import com.ulasgergerli.virtucart.VirtuCart.Dtos.BasketDto;
import com.ulasgergerli.virtucart.VirtuCart.Dtos.BasketItemDto;
import com.ulasgergerli.virtucart.VirtuCart.Dtos.DiscountDto;
import com.ulasgergerli.virtucart.VirtuCart.Product.Product;
import com.ulasgergerli.virtucart.VirtuCart.User.User;
import org.springframework.lang.Nullable;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class BasketFactory {

    private static BasketFactory instance;
    private BasketFactory() {
    }

    public static BasketFactory getInstance() {
        if(instance == null) {
            instance = new BasketFactory();
        }
        return instance;
    }

    public BasketDto createBasketDto(Basket basket) {
        return createBasketDto(basket, null);
    }

    public BasketDto createBasketDto(Basket basket, @Nullable Discount discount) {

        var builder = BasketDto.builder()
                .userId(basket.getUser().getId())
                .basketItems(createBasketItemDtos(basket.getBasketItems()))
                .totalPrice(basket.getBasketItems()
                        .stream()
                        .mapToDouble(basketItem -> basketItem.getProduct().getPrice())
                        .sum())
                .build();
        if(discount != null) {
            DiscountStrategy discountStrategy;
            DiscountDto discountDto = DiscountDto.builder()
                    .id(discount.getId())
                    .discountType(discount.getDiscountType())
                    .discountAmount(discount.getDiscountAmount())
                    .build();

            switch(discount.getDiscountType()) {
                case PERCENTAGE:
                    discountStrategy = new PercentageDiscount(discount.getDiscountAmount());
                    builder.setTotalPrice(discountStrategy.applyDiscount(builder.getTotalPrice()));
                    break;
                case FIXED:
                    discountStrategy = new FixedDiscount(discount.getDiscountAmount());
                    builder.setTotalPrice(discountStrategy.applyDiscount(builder.getTotalPrice()));
                    break;
                default:
                    throw new RuntimeException("Discount type not found");
            }

            builder.setDiscount(discountDto);
        }

        return builder;
    }

    public List<BasketItemDto> createBasketItemDtos(List<BasketItem> basketItems) {
        return basketItems
                .stream()
                .collect(Collectors.groupingBy(basketItem
                        -> basketItem.getProduct().getId()))
                .entrySet()
                .stream()
                .map(entry -> BasketItemDto.builder()
                        .productId(entry.getKey())
                        .product(ProductFactory.getInstance()
                                .createProductDto(entry.getValue().get(0).getProduct()))
                        .quantity(entry.getValue().size())
                        .build())
                .collect(Collectors.toList());
    }

    public Basket createBasket(User user) {
        return Basket.builder()
                .createdAt(new Date())
                .user(user)
                .basketItems(List.of())
                .build();
    }

    public BasketItem createBasketItem(Basket basket, Product product) {
        BasketItem item = basket.getBasketItems().stream().filter(basketItem -> basketItem.getProduct().getId().equals(product.getId())).findFirst().orElse(null);

        if(item != null) {
            item.setQuantity(item.getQuantity() + 1);
            return item;
        }

        return BasketItem.builder()
                .basket(basket)
                .product(product)
                .quantity(1)
                .build();
    }
}
