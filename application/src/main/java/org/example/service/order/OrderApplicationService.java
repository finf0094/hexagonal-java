package org.example.service.order;

import lombok.RequiredArgsConstructor;
import org.example.model.order.OrderDomainModel;
import org.example.port.api.order.OrderServicePort;
import org.example.port.api.product.ProductServicePort;
import org.example.port.spi.order.OrderJpaPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderApplicationService implements OrderServicePort {

    public static final String AMOUNT_ERROR_TEMPLATE = "The ordered amount is lesser than available in warehouse! Ordered: %s, available %s pieces of the ";

    private final ProductServicePort productServicePort;

    private final OrderJpaPort orderJpaPort;

    @Override
    public List<OrderDomainModel> getAllOrders() {
        return orderJpaPort.getAllOrders();
    }

    @Override
    @Transactional
    public OrderDomainModel createOrder(OrderDomainModel orderDomainModel) {
        var product = productServicePort.getProductById(orderDomainModel.getProduct().getId());

        if (product.getAvailableInWarehouse() < orderDomainModel.getAmount()) {
            throw new IllegalArgumentException(String.format(AMOUNT_ERROR_TEMPLATE + product.getName(),
                    orderDomainModel.getAmount(), product.getAvailableInWarehouse()));
        }

        var createdJpaOrder = orderJpaPort.createOrder(orderDomainModel);
        productServicePort.removeFromWarehouse(orderDomainModel.getAmount(), product);

        return createdJpaOrder;
    }
}