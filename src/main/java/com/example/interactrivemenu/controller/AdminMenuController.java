package com.example.interactrivemenu.controller;

import com.example.interactrivemenu.model.Dish;
import com.example.interactrivemenu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminMenuController {
    private final MenuService menuService;

    @Autowired
    public AdminMenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    // Роут для добавления нового блюда в меню
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addDish")
    public ResponseEntity<String> addDish(@RequestBody Dish dish) {
        // Ваш код для добавления нового блюда в меню
        menuService.addDish(dish);
        return ResponseEntity.ok("Dish added to the menu successfully!");
    }

    // Роут для изменения существующего блюда в меню
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/updateDish/{dishId}")
    public ResponseEntity<String> updateDish(@PathVariable Long dishId, @RequestBody Dish updatedDish) {
        // Ваш код для обновления информации о блюде в меню
        menuService.updateDish(dishId, updatedDish);
        return ResponseEntity.ok("Dish updated successfully!");
    }

    // Роут для удаления блюда из меню
    @DeleteMapping("/deleteDish/{dishId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteDish(@PathVariable Long dishId) {
        // Ваш код для удаления блюда из меню
        menuService.deleteDish(dishId);
        return ResponseEntity.ok("Dish deleted from the menu successfully!");
    }
}