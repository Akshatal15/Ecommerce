package app.ecom.controllers;

import app.ecom.entities.Categories;
import app.ecom.services.CategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoriesController {

    private final CategoriesService categoriesService;

    // Get all categories
    @GetMapping
    public List<Categories> getAllCategories() {
        return categoriesService.getAllCategories();
    }

    // Get category by ID
    @GetMapping("/{id}")
    public ResponseEntity<Categories> getCategoryById(@PathVariable Integer id) {
        return categoriesService.getCategoryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get category by name
    @GetMapping("/name/{name}")
    public ResponseEntity<Categories> getCategoryByName(@PathVariable String name) {
        try {
            Categories.CategoryName categoryName = Categories.CategoryName.valueOf(name.toUpperCase());
            Categories category = categoriesService.getCategoryByName(categoryName);
            if (category != null) return ResponseEntity.ok(category);
        } catch (IllegalArgumentException e) {
            // Invalid enum value
        }
        return ResponseEntity.notFound().build();
    }

    // Create a new category
    @PostMapping
    public Categories createCategory(@RequestBody Categories category) {
        return categoriesService.saveCategory(category);
    }

    // Update a category
    @PutMapping("/{id}")
    public ResponseEntity<Categories> updateCategory(@PathVariable Integer id, @RequestBody Categories updatedCategory) {
        return categoriesService.getCategoryById(id)
                .map(existingCategory -> {
                    existingCategory.setCategoryName(updatedCategory.getCategoryName());
                    Categories saved = categoriesService.saveCategory(existingCategory);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete a category
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer id) {
        if (categoriesService.getCategoryById(id).isPresent()) {
            categoriesService.deleteCategory(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
