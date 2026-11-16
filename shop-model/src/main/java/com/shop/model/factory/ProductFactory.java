package com.shop.model.factory;

import com.github.javafaker.Faker;
import com.shop.model.Clothing;
import com.shop.model.Electronics;
import com.shop.model.Product;
import com.shop.model.order.Order;

public class ProductFactory {

    private static final Faker faker = new Faker();
    private static int electronicsCount = 1;
    private static int clothingCount = 1;
    private static int orderCount = 1;

    public static Electronics createElectronics() {
        int index = electronicsCount++;
        return Electronics.builder()
                .id("E-" + index)
                .name("Device " + index)
                .price(faker.number().randomDouble(2, 100, 2000))
                .brand("Brand " + faker.number().numberBetween(1, 5))
                .warrantyMonths(faker.number().numberBetween(6, 36))
                .build();
    }

    public static Clothing createClothing() {
        int index = clothingCount++;
        return Clothing.builder()
                .id("C-" + index)
                .name("Clothing " + index)
                .price(faker.number().randomDouble(2, 15, 200))
                .size(faker.options().option("S", "M", "L", "XL"))
                .material(faker.options().option("Cotton", "Wool", "Silk", "Linen"))
                .build();
    }

    public static <T extends Product> Order<T> createOrder(T product) {
        return new Order<>("ORD-" + (orderCount++), product);
    }
}
