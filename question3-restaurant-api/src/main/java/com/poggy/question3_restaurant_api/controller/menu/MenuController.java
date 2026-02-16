package com.poggy.question3_restaurant_api.controller.menu;

import com.poggy.question3_restaurant_api.model.menu.MenuItem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    private List<MenuItem> menuItems = new ArrayList<>();

    public MenuController() {
        menuItems.add(new MenuItem(1L, "Bruschetta", "Grilled bread with tomatoes", 5.99, "Appetizer", true));
        menuItems.add(new MenuItem(2L, "Caesar Salad", "Romaine lettuce with Caesar dressing", 7.49, "Appetizer", true));
        menuItems.add(new MenuItem(3L, "Margherita Pizza", "Classic pizza with tomato and mozzarella", 11.99, "Main Course", true));
        menuItems.add(new MenuItem(4L, "Grilled Salmon", "Salmon with lemon butter sauce", 15.99, "Main Course", false));
        menuItems.add(new MenuItem(5L, "Chocolate Cake", "Rich chocolate layered cake", 6.49, "Dessert", true));
        menuItems.add(new MenuItem(6L, "Ice Cream Sundae", "Vanilla ice cream with toppings", 4.99, "Dessert", true));
        menuItems.add(new MenuItem(7L, "Lemonade", "Freshly squeezed lemonade", 2.99, "Beverage", true));
        menuItems.add(new MenuItem(8L, "Coffee", "Hot brewed coffee", 1.99, "Beverage", false));
    }

    @GetMapping
    public ResponseEntity<List<MenuItem>> getAllMenuItems() {
        return ResponseEntity.ok(menuItems);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItem> getMenuItemById(@PathVariable Long id) {
        for (MenuItem item : menuItems) {
            if (item.getId().equals(id)) {
                return ResponseEntity.ok(item);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<MenuItem>> getItemsByCategory(@PathVariable String category) {
        List<MenuItem> result = new ArrayList<>();
        for (MenuItem item : menuItems) {
            if (item.getCategory() != null && item.getCategory().equalsIgnoreCase(category)) {
                result.add(item);
            }
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/available")
    public ResponseEntity<List<MenuItem>> getItemsByAvailability(@RequestParam("available") boolean available) {
        List<MenuItem> result = new ArrayList<>();
        for (MenuItem item : menuItems) {
            if (item.isAvailable() == available) {
                result.add(item);
            }
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/search")
    public ResponseEntity<List<MenuItem>> searchMenuItemsByName(@RequestParam("name") String name) {
        List<MenuItem> result = new ArrayList<>();
        for (MenuItem item : menuItems) {
            if (item.getName() != null && item.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(item);
            }
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<MenuItem> addMenuItem(@RequestBody MenuItem menuItem) {
        menuItems.add(menuItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(menuItem);
    }

    @PutMapping("/{id}/availability")
    public ResponseEntity<MenuItem> toggleAvailability(@PathVariable Long id) {
        for (MenuItem item : menuItems) {
            if (item.getId().equals(id)) {
                item.setAvailable(!item.isAvailable());
                return ResponseEntity.ok(item);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id) {
        for (MenuItem item : menuItems) {
            if (item.getId().equals(id)) {
                menuItems.remove(item);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
