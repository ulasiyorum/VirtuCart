package com.ulasgergerli.virtucart.VirtuCart.Subscribers;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductNotificationRepository extends JpaRepository<ProductNotification, Long> {
}
