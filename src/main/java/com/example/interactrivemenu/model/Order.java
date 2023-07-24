package com.example.interactrivemenu.model;

import com.example.interactrivemenu.enums.Status;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_number")
    private int orderNumber;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "waiting_time")
    private int waitingTime;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private int price;

    // ID блюда
    @ManyToOne
    @JoinColumn(name = "dish_id")
    private Dish dish;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

}
