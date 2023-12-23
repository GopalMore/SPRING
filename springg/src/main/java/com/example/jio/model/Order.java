package com.example.jio.model;

import javax.persistence.*;

@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    @Column(name = "ORDERS")
    private String Id;

    public Order(String id) {
        Id = id;
    }

    public Order() {
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}