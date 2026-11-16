package com.shop.storage;

import com.shop.model.Product;
import com.shop.model.order.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;

public class OrderStorage<T extends Product> {

    private final List<Order<T>> orders = new CopyOnWriteArrayList<>();

    public void add(Order<T> order) {
        orders.add(order);
    }

    public List<Order<T>> getAll() {
        return new ArrayList<>(orders);
    }

    public List<Order<T>> filter(Predicate<Order<T>> condition) {
        return orders.stream()
                .filter(condition)
                .toList();
    }

    public Order<T> findById(String id) {
        return orders.stream()
                .filter(o -> o.getId().equalsIgnoreCase(id))
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException("Order not found: " + id));
    }
}
