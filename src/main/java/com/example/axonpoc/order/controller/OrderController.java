package com.example.axonpoc.order.controller;


import com.example.axonpoc.order.command.PlaceOrderCommand;
import com.example.axonpoc.order.event.OrderPlacedEvent;
import com.example.axonpoc.order.repository.OrderRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.GenericEventMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final CommandGateway commandGateway;

    private final EventBus eventBus;

    private final OrderRepository orderRepository;

    public OrderController(CommandGateway commandGateway, EventBus eventBus, OrderRepository orderRepository) {
        this.commandGateway = commandGateway;
        this.eventBus = eventBus;
        this.orderRepository = orderRepository;
    }

    @PostMapping
    public CompletableFuture<String> placeOrder(@RequestParam String product) {
        String orderId = UUID.randomUUID().toString();

     //   eventBus.publish(GenericEventMessage.asEventMessage(new OrderPlacedEvent(orderId, product)));

        return commandGateway.send(new PlaceOrderCommand(orderId, product));
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getOrderStatus(@PathVariable String id) {
        return orderRepository.findById(id)
                .map(order -> ResponseEntity.ok(order.getStatus()))
                .orElse(ResponseEntity.notFound().build());
    }
}
