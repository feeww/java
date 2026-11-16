module shop.model {
    requires static lombok;
    requires javafaker;
    requires java.sql;

    exports com.shop.model;
    exports com.shop.model.order;
    exports com.shop.model.factory;
}
