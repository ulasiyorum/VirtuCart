package com.ulasgergerli.virtucart.VirtuCart.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Controller("/api/v1/category")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/add")
    public Category addCategory(Category category) {
        return categoryService
                .addCategory(category);
    }

    @PutMapping("/update")
    public Category updateCategory(Category category) {
        return categoryService
                .updateCategory(category);
    }

    @DeleteMapping("/delete")
    public void deleteCategory(Long id) {
        categoryService
                .deleteCategory(id);
    }

    @GetMapping("/get")
    public Category getCategory(Long id) {
        return categoryService
                .getCategory(id);
    }

    @GetMapping("/getByName")
    public Category getCategoryByName(String name) {
        return categoryService
                .getCategoryByName(name);
    }

    @GetMapping("/getAll")
    public List<Category> getCategories() {
        return categoryService
                .getCategories();
    }
}
