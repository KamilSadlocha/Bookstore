package org.Bookstore;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
public class Order {
    private int orderId;
    private Book book;
    private OrderType orderType;
    private LocalDate date;
    private UUID customerId;

    public Order(Book book, OrderType orderType, UUID customerId) {
        this.book = book;
        this.orderType = orderType;
        this.customerId = customerId;
        this.date = LocalDate.now();

    }
}
