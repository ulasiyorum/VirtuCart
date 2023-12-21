package com.ulasgergerli.virtucart.VirtuCart.Basket;

import com.ulasgergerli.virtucart.VirtuCart.User.User;
import com.ulasgergerli.virtucart.VirtuCart.User.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasketService {
    private final BasketRepository basketRepository;
    private final BasketItemRepository basketItemRepository;
    private final UserRepository userRepository;

    public BasketService(BasketRepository basketRepository,
                         BasketItemRepository basketItemRepository,
                         UserRepository userRepository) {
        this.basketRepository = basketRepository;
        this.basketItemRepository = basketItemRepository;
        this.userRepository = userRepository;
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

    public User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow();
    }
}
