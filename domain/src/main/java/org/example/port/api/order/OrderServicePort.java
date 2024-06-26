package org.example.port.api.order;

import org.example.model.order.OrderDomainModel;

import java.util.List;

/**
 * Primary port is used to manage orders
 */
public interface OrderServicePort {

    /**
     * Gets all created orders
     *
     * @return list of orders
     */
    List<OrderDomainModel> getAllOrders();

    /**
     * Creates a new order of the given productId and amount
     *
     * @param orderDomainModel data about product to create it
     * @return a newly created order
     */
    OrderDomainModel createOrder(OrderDomainModel orderDomainModel);
}