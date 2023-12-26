package com.ulasgergerli.virtucart.VirtuCart.Subscribers;

import com.ulasgergerli.virtucart.VirtuCart.Product.Product;
import com.ulasgergerli.virtucart.VirtuCart.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProductSubscriber {

    @Id
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Product product;
}
