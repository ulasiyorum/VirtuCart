package com.ulasgergerli.virtucart.VirtuCart.Basket;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BasketItemRepository extends JpaRepository<BasketItem, Long> {
    List<BasketItem> findAllByBasketId(Long basketId);

}
