package com.example.axonpoc.order.event;



public class InventoryUpdatedEvent {
    private final String orderId;

    public InventoryUpdatedEvent(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }
}
