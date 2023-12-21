package com.ulasgergerli.virtucart.VirtuCart.Factory;

import com.ulasgergerli.virtucart.VirtuCart.Basket.Basket;
import com.ulasgergerli.virtucart.VirtuCart.Basket.BasketItem;
import com.ulasgergerli.virtucart.VirtuCart.Dtos.BasketDto;
import com.ulasgergerli.virtucart.VirtuCart.Dtos.BasketItemDto;
import com.ulasgergerli.virtucart.VirtuCart.Product.Product;
import com.ulasgergerli.virtucart.VirtuCart.User.User;

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
        return BasketDto.builder()
                .userId(basket.getUser().getId())
                .basketItems(createBasketItemDtos(basket.getBasketItems()))
                .build();
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
                .basketItems(List.of())
                .build();
    }

    public BasketItem createBasketItem(Basket basket, Product product) {
        return BasketItem.builder()
                .basket(basket)
                .product(product)
                .quantity(1)
                .build();
    }

    public Basket addOrUpdateItemToBasket(Basket basket, Product product) {

        BasketItem basketItem = null;
        for(BasketItem item : basket.getBasketItems()) {
            if(item.getProduct().getId().equals(product.getId())) {
                basketItem = item;
                break;
            }
        }

        if(basketItem != null) {
            basketItem.setQuantity(basketItem.getQuantity() + 1);
            return basket;
        }

        basketItem = createBasketItem(basket, product);
        basket.getBasketItems().add(basketItem);
        return basket;
    }
}
