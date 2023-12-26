package com.ulasgergerli.virtucart.VirtuCart.Subscribers;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductSubscriberRepository extends JpaRepository<ProductSubscriber, Long> {
    List<ProductSubscriber> findAllByProductId(Long productId);
    Optional<ProductSubscriber> findByProductIdAndUserId(Long productId, Long userId);
}
