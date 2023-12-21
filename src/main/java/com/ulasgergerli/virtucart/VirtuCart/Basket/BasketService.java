package com.ulasgergerli.virtucart.VirtuCart.Basket;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasketService {
    private final BasketRepository basketRepository;
    private final BasketItemRepository basketItemRepository;

    public BasketService(BasketRepository basketRepository, BasketItemRepository basketItemRepository) {
        this.basketRepository = basketRepository;
        this.basketItemRepository = basketItemRepository;
    }

    public List<Basket> getBaskets(Long userId) {
        return basketRepository.findAllByUserId(userId);
    }

    public Basket getBasket(Long basketId) {
        return basketRepository.findById(basketId).orElseThrow();
    }

    public Basket createBasket(Basket basket) {
        return basketRepository.save(basket);
    }

    public void deleteBasket(Long basketId) {
        basketRepository.deleteById(basketId);
    }

    public List<BasketItem> getBasketItems(Long basketId) {
        return basketItemRepository.findAllByBasketId(basketId);
    }

    public BasketItem addItemToBasket(BasketItem basketItem) {
        return basketItemRepository.save(basketItem);
    }

    public void deleteBasketItem(Long basketItemId) {
        basketItemRepository.deleteById(basketItemId);
    }
}
