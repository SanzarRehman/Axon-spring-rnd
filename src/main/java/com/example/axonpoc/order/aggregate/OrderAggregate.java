package com.example.axonpoc.order.aggregate;


import com.example.axonpoc.order.command.PlaceOrderCommand;
import com.example.axonpoc.order.command.ProcessOrderCommand;
import com.example.axonpoc.order.command.UpdateInventoryCommand;
import com.example.axonpoc.order.event.InventoryUpdatedEvent;
import com.example.axonpoc.order.event.OrderPlacedEvent;
import com.example.axonpoc.order.event.OrderProcessedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class OrderAggregate {

    @AggregateIdentifier
    private String orderId;

    public OrderAggregate() {}

    @CommandHandler
    public OrderAggregate(PlaceOrderCommand command) {
        apply(new OrderPlacedEvent(command.getOrderId(), command.getProduct()));
    }

    @CommandHandler
    public void handle(ProcessOrderCommand command) {
        apply(new OrderProcessedEvent(command.getOrderId()));
    }

    @CommandHandler
    public void handle(UpdateInventoryCommand command) {
        apply(new InventoryUpdatedEvent(command.getOrderId()));
    }

    @EventSourcingHandler
    public void on(OrderPlacedEvent event) {
        this.orderId = event.getOrderId();
    }
}
