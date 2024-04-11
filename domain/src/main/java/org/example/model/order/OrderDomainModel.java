package org.example.model.order;

import lombok.Data;
import org.example.model.product.ProductDomainModel;

import java.time.LocalDateTime;

@Data
public class OrderDomainModel {

    private Integer orderId;
    private Integer amount;
    private LocalDateTime creationDate;
    private ProductDomainModel product;
}