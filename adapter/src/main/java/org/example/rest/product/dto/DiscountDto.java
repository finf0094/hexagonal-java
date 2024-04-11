package org.example.rest.product.dto;

import lombok.Data;

@Data
public class DiscountDto {
    private Integer priceAfterDiscount;
    private Integer discountInPercent;
}