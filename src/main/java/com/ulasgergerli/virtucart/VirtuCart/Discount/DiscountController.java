package com.ulasgergerli.virtucart.VirtuCart.Discount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/discount")
public class DiscountController {

    private final DiscountService discountService;

    @Autowired
    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @GetMapping("/{id}")
    public Discount getDiscount(@PathVariable long id) {
        return discountService.getDiscount(id);
    }

    @PostMapping("/admin/add")
    public Discount addDiscount(Discount discount) {
        return discountService.addDiscount(discount);
    }

    @DeleteMapping("/admin/delete")
    public void deleteDiscount(long id) {
        discountService.deleteDiscount(id);
    }
}
