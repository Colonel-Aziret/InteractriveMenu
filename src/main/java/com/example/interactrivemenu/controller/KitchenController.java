package com.example.interactrivemenu.controller;

import com.example.interactrivemenu.model.Kitchen;
import com.example.interactrivemenu.service.KitchenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class KitchenController {
    private final KitchenService kitchenService;

    @Autowired
    public KitchenController(KitchenService kitchenService) {
        this.kitchenService = kitchenService;
    }

    // Роут для добавления новой кухни
    @PostMapping("/add-kitchen")
    public ResponseEntity<String> addKitchen(@RequestBody Kitchen kitchen) {
        // Ваш код для добавления новой кухни
        kitchenService.addKitchen(kitchen);
        return ResponseEntity.ok("Kitchen added successfully!");
    }

    // Роут для изменения существующей кухни
    @PutMapping("/update-kitchen/{kitchenId}")
    public ResponseEntity<String> updateKitchen(@PathVariable Long kitchenId, @RequestBody Kitchen updatedKitchen) {
        // Ваш код для обновления информации о кухне
        kitchenService.updateKitchen(kitchenId, updatedKitchen);
        return ResponseEntity.ok("Kitchen updated successfully!");
    }

    // Роут для удаления кухни
    @DeleteMapping("/delete-kitchen/{kitchenId}")
    public ResponseEntity<String> deleteKitchen(@PathVariable Long kitchenId) {
        // Ваш код для удаления кухни
        kitchenService.deleteKitchen(kitchenId);
        return ResponseEntity.ok("Kitchen deleted successfully!");
    }

    // Роут для получения всех кухонь
    @GetMapping("/all-kitchens")
    public ResponseEntity<List<Kitchen>> getAllKitchens() {
        List<Kitchen> kitchens = kitchenService.getAllKitchens();
        return ResponseEntity.ok(kitchens);
    }

    // Роут для получения кухни по её идентификатору
    @GetMapping("/kitchen/{kitchenId}")
    public ResponseEntity<Kitchen> getKitchenById(@PathVariable Long kitchenId) {
        Kitchen kitchen = kitchenService.getKitchenById(kitchenId);
        if (kitchen != null) {
            return ResponseEntity.ok(kitchen);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
