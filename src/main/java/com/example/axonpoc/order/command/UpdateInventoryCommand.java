package com.example.axonpoc.order.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class UpdateInventoryCommand {
    @TargetAggregateIdentifier
    private final String orderId;

    public UpdateInventoryCommand(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }
}
