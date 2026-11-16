package com.shop.model.order;

import com.shop.model.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class Order<T extends Product> {

    private final String id;
    private final T product;
    @Setter
    private OrderStatus status;

    public Order(String id, T product) {
        this.id = id;
        this.product = product;
        this.status = OrderStatus.NEW;
    }
}
