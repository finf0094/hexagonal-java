package org.example.jpa.order.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.jpa.product.entity.ProductEntity;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
@Table(name = "product_order")
public class OrderEntity {

    private static final String PRODUCT_ID_COLUMN = "product_id";

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer amount;

    @Column(nullable = false)
    private LocalDateTime creationDate;

    @ManyToOne
    @JoinColumn(name = PRODUCT_ID_COLUMN)
    private ProductEntity product;
}