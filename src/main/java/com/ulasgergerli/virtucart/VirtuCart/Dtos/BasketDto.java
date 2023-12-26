package com.ulasgergerli.virtucart.VirtuCart.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasketDto {

    @Nullable
    private Long id;
    private Long userId;
    private List<BasketItemDto> basketItems;
    private DiscountDto discount;

    private double totalPrice;

    public Long getUserId() {
        return userId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public DiscountDto getDiscount() {
        return discount;
    }

    public void setDiscount(DiscountDto discount) {
        this.discount = discount;
    }

    public List<BasketItemDto> getBasketItems() {
        return basketItems;
    }

    public void setBasketItems(List<BasketItemDto> basketItems) {
        this.basketItems = basketItems;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
