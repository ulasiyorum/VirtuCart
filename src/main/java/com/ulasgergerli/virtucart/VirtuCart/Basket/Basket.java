package com.ulasgergerli.virtucart.VirtuCart.Basket;

import com.ulasgergerli.virtucart.VirtuCart.Milliseconds.Milliseconds;
import com.ulasgergerli.virtucart.VirtuCart.Product.Product;
import com.ulasgergerli.virtucart.VirtuCart.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Basket {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User user;

    private Date createdAt;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<BasketItem> basketItems;

    public boolean isExpired() {
        return Milliseconds.fromSpanNow(createdAt) > Milliseconds.fromHours(12);
    }
}
