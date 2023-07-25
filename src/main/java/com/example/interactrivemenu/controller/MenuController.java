package com.example.interactrivemenu.controller;

import com.example.interactrivemenu.model.Dish;
import com.example.interactrivemenu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class MenuController {
    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    // Роут для добавления нового блюда в меню
    @PostMapping("/add-dish")
    public ResponseEntity<String> addDish(@RequestBody Dish dish) {
        // Ваш код для добавления нового блюда в меню
        menuService.addDish(dish);
        return ResponseEntity.ok("Dish added to the menu successfully!");
    }

    // Роут для изменения существующего блюда в меню
    @PutMapping("/update-dish/{dishId}")
    public ResponseEntity<String> updateDish(@PathVariable Long dishId, @RequestBody Dish updatedDish) {
        // Ваш код для обновления информации о блюде в меню
        menuService.updateDish(dishId, updatedDish);
        return ResponseEntity.ok("Dish updated successfully!");
    }

    // Роут для удаления блюда из меню
    @DeleteMapping("/delete-dish/{dishId}")
    public ResponseEntity<String> deleteDish(@PathVariable Long dishId) {
        // Ваш код для удаления блюда из меню
        menuService.deleteDish(dishId);
        return ResponseEntity.ok("Dish deleted from the menu successfully!");
    }

    // Роут для выдачи всех блюд
    @GetMapping("/all-dishes")
    public ResponseEntity<List<Dish>> getAllDishes() {
        List<Dish> dishes = menuService.getAllDishes();
        return ResponseEntity.ok(dishes);
    }

    // Роут для выдачи блюда по его идентификатору
    @GetMapping("/dish/{dishId}")
    public ResponseEntity<Dish> getDishById(@PathVariable Long dishId) {
        Dish dish = menuService.getDishById(dishId);
        if (dish != null) {
            return ResponseEntity.ok(dish);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
