package org.example.rest.order.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CreateOrderDto {
    @NotNull
    @Positive
    private Integer productId;

    @NotNull
    @Positive
    private Integer amount;
}