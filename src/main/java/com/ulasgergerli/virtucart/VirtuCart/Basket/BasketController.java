package com.ulasgergerli.virtucart.VirtuCart.Basket;

import com.ulasgergerli.virtucart.VirtuCart.Dtos.BasketDto;
import com.ulasgergerli.virtucart.VirtuCart.Dtos.BasketItemDto;
import com.ulasgergerli.virtucart.VirtuCart.Factory.BasketFactory;
import com.ulasgergerli.virtucart.VirtuCart.Product.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Controller("/api/v1/basket")
public class BasketController {
    private final BasketService basketService;
    private final ProductService productService;
    private final BasketFactory basketFactory = BasketFactory.getInstance();

    public BasketController(BasketService basketService,
                            ProductService productService) {
        this.basketService = basketService;
        this.productService = productService;
    }

    @GetMapping("/get")
    public BasketDto getBasket(Long id) {
        var basket = basketService.getBasket(id);

        return basketFactory.createBasketDto(basket);
    }

    @GetMapping("/create")
    public BasketDto createBasket(BasketDto basketDto) {
        var user = basketService.getUser(basketDto.getUserId());

        var basket = basketFactory.createBasket(user);
        basket = basketService.createBasket(basket);

        return basketFactory.createBasketDto(basket);
    }

    @GetMapping("/delete")
    public void deleteBasket(Long id) {
        basketService.deleteBasket(id);
    }

    @GetMapping("/addItemToBasket")
    public BasketDto addItemToBasket(BasketItemDto basketDto) {
        var product = productService.getProduct(basketDto.getProductId());

        if(product == null)
            throw new RuntimeException("Product not found");

        var basket = basketService.getBasket(basketDto.getId());

        if(basket == null)
            throw new RuntimeException("Basket not found");

        var basketItem = basketService.addItemToBasket(basketFactory.createBasketItem(basket, product));

        basketItem = basketService.addItemToBasket(basketItem);

        return basketFactory.createBasketDto(basketItem.getBasket());
    }

    @GetMapping("/deleteBasketItem")
    public void deleteBasketItem(Long id) {
        basketService.deleteBasketItem(id);
    }

    @GetMapping("/getBasketItems")
    public List<BasketItemDto> getBasketItems(Long id) {
        var basketItems = basketService.getBasketItems(id);

        return basketFactory.createBasketItemDtos(basketItems);
    }

    @GetMapping("/getBaskets")
    public List<BasketDto> getBaskets(Long userId) {
        var baskets = basketService.getBaskets(userId);

        return baskets
                .stream()
                .map(basketFactory::createBasketDto)
                .collect(Collectors.toList());
    }



}
