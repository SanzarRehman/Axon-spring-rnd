# Axon POC - Order Management System

This project is a proof-of-concept (POC) for an order management system built using **Java**, **Spring Boot**, **Axon Framework**, and **Gradle**. It demonstrates the use of **CQRS** (Command Query Responsibility Segregation), **Event Sourcing**, and **Saga** patterns for managing complex business workflows.

---

## Features

- **Command Handling**: Commands like `PlaceOrderCommand`, `ProcessOrderCommand`, and `UpdateInventoryCommand` are handled by the `OrderAggregate`.
- **Event Sourcing**: Events such as `OrderPlacedEvent`, `OrderProcessedEvent`, and `InventoryUpdatedEvent` are applied to the aggregate and stored.
- **Saga Coordination**: The `OrderManagementSaga` coordinates the order processing workflow across multiple steps.
- **Event Handling**: The `OrderEventHandler` listens to events and updates the database accordingly.
- **REST API**: Exposes endpoints for placing orders and retrieving order statuses.

---

## Project Structure

### Key Components

1. **Controller**:
    - `OrderController`: Handles REST API requests for placing orders and retrieving order statuses.

2. **Aggregate**:
    - `OrderAggregate`: Manages the state of an order and handles commands.

3. **Saga**:
    - `OrderManagementSaga`: Coordinates the order processing workflow using events.

4. **Event Handlers**:
    - `OrderEventHandler`: Listens to events and updates the database.

5. **Repository**:
    - `OrderRepository`: Manages persistence of order entities.

---

## Endpoints

### 1. Place an Order
**POST** `/orders`  
**Request Parameters**:
- `product` (String): The name of the product to order.  
  **Response**:
- Returns the `orderId` of the placed order.

### 2. Get Order Status
**GET** `/orders/{id}`  
**Path Variable**:
- `id` (String): The ID of the order.  
  **Response**:
- Returns the status of the order (`PLACED`, `PROCESSED`, `INVENTORY_UPDATED`, etc.).

---

## How It Works

1. **Placing an Order**:
    - A `PlaceOrderCommand` is sent via the `CommandGateway`.
    - The `OrderAggregate` applies an `OrderPlacedEvent`.

2. **Saga Workflow**:
    - The `OrderManagementSaga` listens to the `OrderPlacedEvent` and sends a `ProcessOrderCommand`.
    - After processing, it sends an `UpdateInventoryCommand`.

3. **Event Handling**:
    - The `OrderEventHandler` listens to events and updates the database with the current order status.

---

## Prerequisites

- **Java 17+**
- **Gradle 7+**
- **Spring Boot 3+**

---

## Running the Application

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd axon-poc
   ```

2. Build the project:
   ```bash
   ./gradlew build
   ```

3. Run the application:
   ```bash
   ./gradlew bootRun
   ```

4. Access the API at `http://localhost:8080`.

---

## Technologies Used

- **Java**: Programming language.
- **Spring Boot**: Framework for building REST APIs.
- **Axon Framework**: For implementing CQRS, Event Sourcing, and Sagas.
- **Gradle**: Build tool.

---

## Notes

- The `OrderEventHandler` uses `AggregateLifecycle.apply()` to trigger events, which is unconventional and should be avoided in production. Events should only be applied within aggregates.
- Simulated delays (`Thread.sleep()`) are used in the saga for demonstration purposes and should be replaced with proper asynchronous handling.

---

## License

This project is for educational purposes and is not licensed for production use.