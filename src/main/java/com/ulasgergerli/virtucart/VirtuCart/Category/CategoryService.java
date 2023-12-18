package com.ulasgergerli.virtucart.VirtuCart.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category addCategory(Category category) {
        return categoryRepository
                .save(category);
    }

    public Category updateCategory(Category category) {
        return categoryRepository
                .save(category);
    }

    public void deleteCategory(Long id) {
        categoryRepository
                .deleteById(id);
    }

    public Category getCategory(Long id) {
        return categoryRepository
                .findById(id)
                .orElse(null);
    }

    public Category getCategoryByName(String name) {
        return categoryRepository
                .findByName(name);
    }

    public List<Category> getCategories() {
        return categoryRepository
                .findAll();
    }

    public List<Category> getCategories(List<Long> ids) {
        return categoryRepository
                .findAllById(ids);
    }
}
