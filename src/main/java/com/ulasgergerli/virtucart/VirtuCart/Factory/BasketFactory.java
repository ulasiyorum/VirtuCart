package com.ulasgergerli.virtucart.VirtuCart.Factory;

import com.ulasgergerli.virtucart.VirtuCart.Basket.Basket;
import com.ulasgergerli.virtucart.VirtuCart.Basket.BasketItem;
import com.ulasgergerli.virtucart.VirtuCart.Dtos.BasketDto;
import com.ulasgergerli.virtucart.VirtuCart.Dtos.BasketItemDto;

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
}
