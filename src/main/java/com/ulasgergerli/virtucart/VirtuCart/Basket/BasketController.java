package com.ulasgergerli.virtucart.VirtuCart.Basket;

import com.ulasgergerli.virtucart.VirtuCart.Dtos.BasketDto;
import com.ulasgergerli.virtucart.VirtuCart.Dtos.BasketItemDto;
import com.ulasgergerli.virtucart.VirtuCart.Factory.BasketFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Controller("/api/v1/basket")
public class BasketController {
    private final BasketService basketService;
    private final BasketFactory basketFactory = BasketFactory.getInstance();

    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @GetMapping("/get")
    public BasketDto getBasket(Long id) {
        var basket = basketService.getBasket(id);

        return basketFactory.createBasketDto(basket);
    }

    @GetMapping("/create")
    public BasketDto createBasket(BasketDto basketDto) {
        var basket = basketService.createBasket(new Basket());

        return basketFactory.createBasketDto(basket);
    }

    @GetMapping("/delete")
    public void deleteBasket(Long id) {
        basketService.deleteBasket(id);
    }

    @GetMapping("/addItemToBasket")
    public BasketDto addItemToBasket(BasketItemDto basketDto) {
        var basketItem = basketService.addItemToBasket(new BasketItem());

        return basketFactory.createBasketDto(basketItem.getBasket());
    }

    @GetMapping("/deleteBasketItem")
    public void deleteBasketItem(Long id) {
        basketService.deleteBasketItem(id);
    }

    @GetMapping("/getBasketItems")
    public List<BasketItemDto> getBasketItems(Long id) {
        var basketItems = basketService.getBasketItems(id);

        return List.of();
    }

    @GetMapping("/getBaskets")
    public List<BasketDto> getBaskets(Long userId) {
        var baskets = basketService.getBaskets(userId);

        return List.of();
    }



}
