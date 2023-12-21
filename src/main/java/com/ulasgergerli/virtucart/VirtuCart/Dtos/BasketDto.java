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


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
