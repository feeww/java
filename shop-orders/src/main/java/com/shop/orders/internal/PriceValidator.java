package com.shop.orders.internal;

public class PriceValidator {

    public static boolean isValid(double price) {
        return price > 0;
    }
}
