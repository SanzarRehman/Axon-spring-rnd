package com.example.axonpoc.order.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class ProcessOrderCommand {
    @TargetAggregateIdentifier
    private final String orderId;

    public ProcessOrderCommand(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }
}
