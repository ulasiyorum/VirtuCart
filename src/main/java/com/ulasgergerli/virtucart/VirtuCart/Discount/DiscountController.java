package com.ulasgergerli.virtucart.VirtuCart.Discount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller("/api/v1/discount")
public class DiscountController {

    private final DiscountService discountService;

    @Autowired
    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @GetMapping("/{discountType}/{id}")
    public Discount getDiscount(@PathVariable DiscountType discountType,
                                @PathVariable long id) {
        return discountService.getDiscount(discountType, id);
    }
}
