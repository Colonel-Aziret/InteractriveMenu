package com.example.interactrivemenu.model;

import javax.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "kitchens")
@Data
public class Kitchen {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "kitchen_sequence")
    @SequenceGenerator(name = "kitchen_sequence", sequenceName = "kitchen_sequence", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    // ID блюда
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "kitchen_id")
    private List<Dish> dishes;
}
