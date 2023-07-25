package com.example.interactrivemenu.service;

import com.example.interactrivemenu.model.Dish;
import com.example.interactrivemenu.repository.DishRepository;
import com.example.interactrivemenu.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class MenuService {
    private final MenuRepository menuRepository;
    private final DishRepository dishRepository;

    @Autowired
    public MenuService(MenuRepository menuRepository, DishRepository dishRepository) {
        this.menuRepository = menuRepository;
        this.dishRepository = dishRepository;
    }

    // Метод для добавления нового блюда в меню
    public void addDish(Dish dish) {
        // Ваша бизнес-логика для добавления нового блюда в меню
        dishRepository.save(dish);
    }

    // Метод для обновления информации о существующем блюде в меню
    public void updateDish(Long dishId, Dish updatedDish) {
        // Ваша бизнес-логика для обновления информации о блюде в меню
        Dish existingDish = dishRepository.findById(dishId)
                .orElseThrow(() -> new EntityNotFoundException("Dish with id " + dishId + " not found."));
        existingDish.setName(updatedDish.getName());
        existingDish.setCompound(updatedDish.getCompound());
        existingDish.setPrice(updatedDish.getPrice());
        dishRepository.save(existingDish);
    }

    // Метод для удаления блюда из меню
    public void deleteDish(Long dishId) {
        // Ваша бизнес-логика для удаления блюда из меню
        dishRepository.deleteById(dishId);
    }

    // Метод для получения всех блюд
    public List<Dish> getAllDishes() {
        return dishRepository.findAll();
    }

    // Метод для получения блюда по его идентификатору
    public Dish getDishById(Long dishId) {
        Optional<Dish> dishOptional = dishRepository.findById(dishId);
        return dishOptional.orElse(null);
    }
}
