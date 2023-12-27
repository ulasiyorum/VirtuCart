package com.ulasgergerli.virtucart.VirtuCart.Subscribers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductNotificationRepository extends JpaRepository<ProductNotification, Long> {
}
