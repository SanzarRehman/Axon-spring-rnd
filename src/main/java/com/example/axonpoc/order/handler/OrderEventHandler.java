package com.example.axonpoc.order.handler;


import com.example.axonpoc.order.entity.OrderEntity;
import com.example.axonpoc.order.event.InventoryUpdatedEvent;
import com.example.axonpoc.order.event.OrderPlacedEvent;
import com.example.axonpoc.order.event.OrderProcessedEvent;
import com.example.axonpoc.order.repository.OrderRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Component
public class OrderEventHandler {

    private final OrderRepository orderRepository;

    public OrderEventHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @EventHandler
    public void on(OrderPlacedEvent event) {
        OrderEntity order = new OrderEntity(event.getOrderId(), event.getProduct());
        order.setStatus("PLACED");
        orderRepository.save(order);

        // Trigger the next event
        apply(new OrderProcessedEvent(event.getOrderId()));
    }

    @EventHandler
    public void on(OrderProcessedEvent event) {
        orderRepository.findById(event.getOrderId()).ifPresent(order -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            order.setStatus("PROCESSED");
            orderRepository.save(order);
        });

        apply(new InventoryUpdatedEvent(event.getOrderId()));
    }

    @EventHandler
    public void on(InventoryUpdatedEvent event) {
        orderRepository.findById(event.getOrderId()).ifPresent(order -> {
            order.setStatus("INVENTORY_UPDATED");
            orderRepository.save(order);
        });
    }


}
