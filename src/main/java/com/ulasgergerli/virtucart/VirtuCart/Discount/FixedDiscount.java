package com.ulasgergerli.virtucart.VirtuCart.Discount;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FixedDiscount implements DiscountStrategy {

    private final double fixedDiscount;

    public FixedDiscount(@Value("${discount.fixedAmount}") double fixedDiscount) {
        this.fixedDiscount = fixedDiscount;
    }

    @Override
    public double applyDiscount(double price) {

        if(price < fixedDiscount)
            return 0.0;

        return price - fixedDiscount;
    }
}
