package org.example.port.spi.order;

import org.example.model.order.OrderDomainModel;

import java.util.List;

/**
 * Secondary port is used to manage orders on persistence level
 */
public interface OrderJpaPort {

    /**
     * Gets all created orders
     *
     * @return list of orders
     */
    List<OrderDomainModel> getAllOrders();

    /**
     * Creates a new order
     *
     * @param order to be created
     * @return the newly created order
     */
    OrderDomainModel createOrder(OrderDomainModel order);
}