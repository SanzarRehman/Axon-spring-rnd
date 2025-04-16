package com.example.axonpoc.order.event;



public class OrderProcessedEvent {
    private final String orderId;

    public OrderProcessedEvent(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }
}

