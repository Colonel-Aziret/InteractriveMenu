package com.example.interactrivemenu.model;

import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "menu")
@Data
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ID кухни
    @ManyToOne
    @JoinColumn(name = "kitchen_id")
    private Kitchen kitchen;
}
