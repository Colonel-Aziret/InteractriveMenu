package com.example.interactrivemenu.service;

import com.example.interactrivemenu.model.Kitchen;
import com.example.interactrivemenu.repository.DishRepository;
import com.example.interactrivemenu.repository.KitchenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class KitchenService {
    private final KitchenRepository kitchenRepository;
    private final DishRepository dishRepository;

    @Autowired
    public KitchenService(KitchenRepository kitchenRepository, DishRepository dishRepository) {
        this.kitchenRepository = kitchenRepository;
        this.dishRepository = dishRepository;
    }

    // Метод для добавления новой кухни
    public void addKitchen(Kitchen kitchen) {
        // Ваша бизнес-логика для добавления новой кухни
        kitchenRepository.save(kitchen);
    }

    // Метод для обновления информации о существующей кухне
    public void updateKitchen(Long kitchenId, Kitchen updatedKitchen) {
        // Ваша бизнес-логика для обновления информации о кухне
        Kitchen existingKitchen = kitchenRepository.findById(kitchenId)
                .orElseThrow(() -> new EntityNotFoundException("Kitchen with id " + kitchenId + " not found."));
        existingKitchen.setName(updatedKitchen.getName());
        // Если нужно обновить другие поля кухни, добавьте их здесь
        kitchenRepository.save(existingKitchen);
    }

    // Метод для удаления кухни
    public void deleteKitchen(Long kitchenId) {
        // Ваша бизнес-логика для удаления кухни
        kitchenRepository.deleteById(kitchenId);
    }

    // Метод для получения всех кухонь
    public List<Kitchen> getAllKitchens() {
        return kitchenRepository.findAll();
    }

    // Метод для получения кухни по её идентификатору
    public Kitchen getKitchenById(Long kitchenId) {
        Optional<Kitchen> kitchenOptional = kitchenRepository.findById(kitchenId);
        return kitchenOptional.orElse(null);
    }
}
