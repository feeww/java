package com.shop.app;

import com.shop.concurrency.OrderThreadManager;
import com.shop.model.Clothing;
import com.shop.model.Electronics;
import com.shop.model.Product;
import com.shop.model.factory.ProductFactory;
import com.shop.model.order.Order;
import com.shop.orders.InvalidOrderException;
import com.shop.orders.OrderProcessor;
import com.shop.storage.OrderStorage;
import com.shop.storage.ProductNotFoundException;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        OrderStorage<Product> storage = new OrderStorage<>();

        for (int i = 0; i < 3; i++) {
            storage.add(ProductFactory.createOrder(ProductFactory.createElectronics()));
            storage.add(ProductFactory.createOrder(ProductFactory.createClothing()));
        }

        storage.add(new Order<>("ORD-INVALID", Electronics.builder()
                .id("E-99")
                .name("Broken Device")
                .price(0.0)
                .brand("Brand 0")
                .warrantyMonths(0)
                .build()));

        System.out.println("=== 1. All orders ===");
        storage.getAll().forEach(System.out::println);

        System.out.println("\n=== 2. Electronics only ===");
        List<Order<Product>> electronicsOrders = storage.filter(o -> o.getProduct() instanceof Electronics);
        electronicsOrders.forEach(System.out::println);

        System.out.println("\n=== 3. Single thread execution ===");
        Order<Product> singleOrder = electronicsOrders.get(0);
        Runnable orderTask = () -> new OrderProcessor<>(singleOrder).processOrder();
        Thread thread = new Thread(orderTask);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException ignored) {
        }

        System.out.println("\n=== 4. Method reference execution ===");
        List<OrderProcessor<Product>> processors = storage.getAll().stream()
                .map(OrderProcessor::new)
                .toList();
        processors.subList(0, 2).forEach(OrderProcessor::processOrder);

        System.out.println("\n=== 5. Multithreaded processing ===");
        OrderThreadManager manager = new OrderThreadManager(3);
        manager.submitAll(storage.getAll());
        manager.shutdown();

        System.out.println("\n=== 6. Exception handling ===");
        try {
            new OrderProcessor<Product>(null);
        } catch (InvalidOrderException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }

        try {
            storage.findById("ORD-NON-EXISTENT");
        } catch (ProductNotFoundException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }
    }
}
