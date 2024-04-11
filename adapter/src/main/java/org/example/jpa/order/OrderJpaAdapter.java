package org.example.jpa.order;


import lombok.RequiredArgsConstructor;
import org.example.jpa.order.repository.OrderRepository;
import org.example.model.order.OrderDomainModel;
import org.example.port.spi.order.OrderJpaPort;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.example.jpa.order.mapper.OrderJpaMapper.ORDER_JPA_MAPPER;

@Component
@RequiredArgsConstructor
public class OrderJpaAdapter implements OrderJpaPort {

    private final OrderRepository orderRepository;

    @Override
    public List<OrderDomainModel> getAllOrders() {
        return ORDER_JPA_MAPPER.toDomainModelList(orderRepository.findAll());
    }

    @Override
    public OrderDomainModel createOrder(OrderDomainModel order) {
        var createdOrder = orderRepository.save(ORDER_JPA_MAPPER.toEntity(order));

        return ORDER_JPA_MAPPER.toDomainModel(createdOrder);
    }
}