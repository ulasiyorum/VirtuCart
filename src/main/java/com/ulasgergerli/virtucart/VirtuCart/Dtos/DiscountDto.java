package com.ulasgergerli.virtucart.VirtuCart.Dtos;

import com.ulasgergerli.virtucart.VirtuCart.Discount.DiscountType;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class DiscountDto {
    private Long id;
    private DiscountType discountType;
    private double discountAmount;
}
