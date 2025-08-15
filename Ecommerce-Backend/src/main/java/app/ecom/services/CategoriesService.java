package app.ecom.services;

import app.ecom.entities.Categories;

import app.ecom.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriesService {

    private final CategoryRepository categoriesRepository;

    // Get all categories
    public List<Categories> getAllCategories() {
        return categoriesRepository.findAll();
    }

    // Get category by ID
    public Optional<Categories> getCategoryById(Integer id) {
        return categoriesRepository.findById(id);
    }

    // Get category by name
    public Categories getCategoryByName(Categories.CategoryName name) {
        return categoriesRepository.findByCategoryName(name);
    }

    // Create or update category
    public Categories saveCategory(Categories category) {
        return categoriesRepository.save(category);
    }

    // Delete category by ID
    public void deleteCategory(Integer id) {
        categoriesRepository.deleteById(id);
    }
}

