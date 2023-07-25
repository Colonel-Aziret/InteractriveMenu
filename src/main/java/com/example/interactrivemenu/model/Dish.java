package com.example.interactrivemenu.model;

import javax.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "dishes")
@Data
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dish_sequence")
    @SequenceGenerator(name = "dish_sequence", sequenceName = "dish_sequence", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "compound")
    private String compound;

    @Column(name = "price")
    private int price;

    @ManyToMany
    @JoinTable(name = "order_dishes",
            joinColumns = @JoinColumn(name = "dish_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    private List<Order> orders;
}
