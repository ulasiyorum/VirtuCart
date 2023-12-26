package com.ulasgergerli.virtucart.VirtuCart.Subscribers;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProductNotification {

    @Id
    private Long id;

    @ManyToOne
    private ProductSubscriber productSubscriber;

    private boolean isNotified;
    private double initialPrice;
    private double changedPrice;
}
