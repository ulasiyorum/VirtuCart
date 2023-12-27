package com.ulasgergerli.virtucart.VirtuCart.Discount;

import org.springframework.stereotype.Service;

@Service
public class DiscountService {

    private final DiscountRepository discountRepository;

    public DiscountService(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    public Discount getDiscount(long id) {
        return discountRepository
                .findById(id)
                .orElse(null);
    }

    public Discount addDiscount(Discount discount) {
        return discountRepository
                .save(discount);
    }

    public void deleteDiscount(long id) {
        discountRepository.deleteById(id);
    }
}
