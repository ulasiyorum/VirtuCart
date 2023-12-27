package com.ulasgergerli.virtucart.VirtuCart.Discount;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

public class PercentageDiscount implements DiscountStrategy {

    private final double percentage;

    public PercentageDiscount(double percentage) {
        this.percentage = percentage;
    }

    @Override
    public double applyDiscount(double price) {

        if(price < (price * percentage / 100))
            return 0.0;

        return price - (price * percentage / 100);
    }
}
