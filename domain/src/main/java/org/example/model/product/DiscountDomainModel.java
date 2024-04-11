package org.example.model.product;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DiscountDomainModel {
    private Integer priceAfterDiscount;
    private Integer discountInPercent;
}