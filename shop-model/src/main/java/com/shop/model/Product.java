package com.shop.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public abstract class Product {

    private final String id;
    private final String name;
    private final double price;

    protected Product(String id, String name, double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Ціна не може бути від'ємною: " + price);
        }
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public abstract String category();
}
