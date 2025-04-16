package com.example.axonpoc.saga;

import com.example.axonpoc.order.command.ProcessOrderCommand;
import com.example.axonpoc.order.command.UpdateInventoryCommand;
import com.example.axonpoc.order.event.InventoryUpdatedEvent;
import com.example.axonpoc.order.event.OrderPlacedEvent;
import com.example.axonpoc.order.event.OrderProcessedEvent;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

@Saga
public class OrderManagementSaga {

    @Autowired
    private transient CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    public void on(OrderPlacedEvent event) throws InterruptedException {
        Thread.sleep(1000); // Simulated delay
        commandGateway.send(new ProcessOrderCommand(event.getOrderId()));
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void on(OrderProcessedEvent event) throws InterruptedException {
        Thread.sleep(1000); // Simulated delay
        commandGateway.send(new UpdateInventoryCommand(event.getOrderId()));
    }

    @SagaEventHandler(associationProperty = "orderId")
    @EndSaga
    public void on(InventoryUpdatedEvent event) {


        // Saga ends
    }
}
