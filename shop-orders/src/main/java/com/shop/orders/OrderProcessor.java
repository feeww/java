package com.shop.orders;

import com.shop.model.Product;
import com.shop.model.order.Order;
import com.shop.model.order.OrderStatus;
import com.shop.orders.internal.PriceValidator;

public class OrderProcessor<T extends Product> {

    private final Order<T> order;

    public OrderProcessor(Order<T> order) {
        if (order == null || order.getProduct() == null) {
            throw new InvalidOrderException("Order or product cannot be null");
        }
        this.order = order;
    }

    public Order<T> getOrder() {
        return order;
    }

    public void processOrder() {
        if (!PriceValidator.isValid(order.getProduct().getPrice())) {
            order.setStatus(OrderStatus.CANCELLED);
            throw new InvalidOrderException("Invalid price for order " + order.getId() + ": " + order.getProduct().getPrice());
        }

        order.setStatus(OrderStatus.PROCESSING);
        System.out.printf("[%s] Processing %s order (%s): %s (%.2f $)%n",
                Thread.currentThread().getName(),
                order.getProduct().category(),
                order.getId(),
                order.getProduct().getName(),
                order.getProduct().getPrice());
        order.setStatus(OrderStatus.COMPLETED);
    }
}
