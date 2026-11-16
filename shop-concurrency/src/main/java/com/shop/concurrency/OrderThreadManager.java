package com.shop.concurrency;

import com.shop.model.Product;
import com.shop.model.order.Order;
import com.shop.orders.InvalidOrderException;
import com.shop.orders.OrderProcessor;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class OrderThreadManager {

    private final ExecutorService executor;

    public OrderThreadManager(int threadCount) {
        this.executor = Executors.newFixedThreadPool(threadCount);
    }

    public void submitAll(List<? extends Order<? extends Product>> orders) {
        for (Order<? extends Product> order : orders) {
            executor.submit(() -> {
                try {
                    OrderProcessor<? extends Product> processor = new OrderProcessor<>(order);
                    processor.processOrder();
                } catch (InvalidOrderException e) {
                    System.out.printf("[%s] Error: %s%n", Thread.currentThread().getName(), e.getMessage());
                }
            });
        }
    }

    public void shutdown() {
        executor.shutdown();
        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
