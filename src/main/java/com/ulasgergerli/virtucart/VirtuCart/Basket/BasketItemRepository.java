package com.ulasgergerli.virtucart.VirtuCart.Basket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasketItemRepository extends JpaRepository<BasketItem, Long> {
    List<BasketItem> findAllByBasketId(Long basketId);

}
