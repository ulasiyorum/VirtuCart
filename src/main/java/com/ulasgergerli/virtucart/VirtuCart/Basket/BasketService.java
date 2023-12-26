package com.ulasgergerli.virtucart.VirtuCart.Basket;

import com.ulasgergerli.virtucart.VirtuCart.Discount.Discount;
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
        return basketRepository.findAllByUserId(userId).stream().filter(basket -> !basket.isExpired()).toList();
    }

    public Basket getBasket(Long basketId) {

        var basket = basketRepository.findById(basketId).orElseThrow();
        if(basket.isExpired()) {
            throw new RuntimeException("Basket is expired");
        }
        return basket;
    }

    public Basket createBasket(Basket basket) {
        return basketRepository.save(basket);
    }

    public void deleteBasket(Long basketId) {
        basketRepository.deleteById(basketId);
    }

    public List<BasketItem> getBasketItems(Long basketId) {

        var items = basketItemRepository.findAllByBasketId(basketId);

        if(items.isEmpty()) {
            throw new RuntimeException("Basket is empty");
        }

        if(items.get(0).getBasket().isExpired())
            throw new RuntimeException("Basket is expired");

        return items;
    }

    public BasketItem addOrGetBasketItem(BasketItem basketItem) {

        var basket = basketItem.getBasket();

        if(basket.isExpired()) {
            throw new RuntimeException("Basket is expired");
        }

        var basketItems = basketItemRepository.findAllByBasketId(basket.getId());

        basketItems.stream()
                .filter(basketItem1 -> basketItem1.getProduct().getId().equals(basketItem.getProduct().getId())).findFirst()
                .ifPresent(item -> item.setId(item.getId()));

        return basketItemRepository.save(basketItem);
    }

    public void deleteBasketItem(Long basketItemId) {
        basketItemRepository.deleteById(basketItemId);
    }

    public User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow();
    }

    public Basket applyDiscountToBasket(Long basketId, Discount discountToApply) {

        if(discountToApply == null) {
            throw new RuntimeException("Discount is null");
        }

        var basket = basketRepository.findById(basketId).orElseThrow();
        var discount = basket.getDiscount();
        if(discount != null) {
            throw new RuntimeException("Discount already applied");
        }
        basket.setDiscount(discountToApply);
        return basketRepository.save(basket);
    }
}
