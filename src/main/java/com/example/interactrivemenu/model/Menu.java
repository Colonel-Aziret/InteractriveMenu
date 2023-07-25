package com.example.interactrivemenu.model;

import javax.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "menu")
@Data
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menu_sequence")
    @SequenceGenerator(name = "menu_sequence", sequenceName = "menu_sequence", allocationSize = 1)
    private Long id;

    // ID кухни
    @ManyToOne
    @JoinColumn(name = "kitchen_id")
    private Kitchen kitchen;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "menu_id")
    private List<Dish> dishes;
}
