package com.example.axonpoc.order.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity

public class OrderEntity {

    @Id
    private String id;
    private String product;
    private String status;

    public OrderEntity() {}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public OrderEntity(String id, String product) {
        this.id = id;
        this.product = product;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
