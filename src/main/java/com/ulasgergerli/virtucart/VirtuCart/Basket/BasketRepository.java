package com.ulasgergerli.virtucart.VirtuCart.Basket;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BasketRepository extends JpaRepository<Basket, Long> {
    List<Basket> findAllByUserId(Long userId);
}
