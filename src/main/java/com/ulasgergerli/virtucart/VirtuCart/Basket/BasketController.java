package com.ulasgergerli.virtucart.VirtuCart.Basket;

import com.ulasgergerli.virtucart.VirtuCart.Discount.DiscountService;
import com.ulasgergerli.virtucart.VirtuCart.Dtos.BasketDto;
import com.ulasgergerli.virtucart.VirtuCart.Dtos.BasketItemDto;
import com.ulasgergerli.virtucart.VirtuCart.Factory.BasketFactory;
import com.ulasgergerli.virtucart.VirtuCart.Product.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/basket")
public class BasketController {
    private final BasketService basketService;
    private final ProductService productService;
    private final DiscountService discountService;
    private final BasketFactory basketFactory = BasketFactory.getInstance();

    public BasketController(BasketService basketService,
                            ProductService productService,
                            DiscountService discountService) {
        this.basketService = basketService;
        this.productService = productService;
        this.discountService = discountService;
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

        var basket = basketService.getBasket(basketDto.getBasketId());

        if(basket == null)
            throw new RuntimeException("Basket not found");

        var basketItem = basketService.addOrGetBasketItem(basketFactory.createBasketItem(basket, product));
        return basketFactory.createBasketDto(basketItem.getBasket());
    }

    @DeleteMapping("/deleteBasketItem")
    public void deleteBasketItem(Long id) {
        basketService.deleteBasketItem(id);
    }

    @DeleteMapping("/decreaseBasketItemQuantity")
    public void decreaseBasketItemQuantity(Long id) {
        basketService.decreaseBasketItemQuantity(id);
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

    @GetMapping("/getBasketWithDiscount")
    public BasketDto getBasketWithDiscount(Long basketId, Long discountId) {
        var basket = basketService.getBasket(basketId);

        if(basket == null)
            throw new RuntimeException("Basket not found");

        var discount = discountService.getDiscount(discountId);

        if(discount == null)
            throw new RuntimeException("Discount not found");

        return basketFactory.createBasketDto(basket, discount);
    }

    @PostMapping("/applyDiscount")
    public BasketDto applyDiscountToBasket(Long basketId, Long discountId) {
        var discount = discountService.getDiscount(discountId);

        var basket = basketService.applyDiscountToBasket(basketId, discount);

        return basketFactory.createBasketDto(basket, discount);
    }

}
