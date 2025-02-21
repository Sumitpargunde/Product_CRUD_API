package com.example.product_category_crud_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import com.example.product_category_crud_api.dto.CustomPageResponse;
import com.example.product_category_crud_api.model.Category;
import com.example.product_category_crud_api.repository.CategoryRepository;

import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    // GET all categories with pagination (default page size = 5)
    @GetMapping
    public CustomPageResponse<Category> getAllCategories(@RequestParam(defaultValue = "0") int page) {
        Page<Category> categoryPage = categoryRepository.findAll(PageRequest.of(page, 10));
        return new CustomPageResponse<>(categoryPage.getContent(), categoryPage.getNumber());
    }

    // POST - Create a new category
    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryRepository.save(category);
    }

    // GET - Retrieve a category by id
    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable("id") Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.orElse(null);
    }

    // PUT - Update a category by id
    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable("id") Long id, @RequestBody Category categoryDetails) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            category.setName(categoryDetails.getName());
            // (Additional fields can be updated here if needed)
            return categoryRepository.save(category);
        }
        return null;
    }

    // DELETE - Delete a category by id
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable("id") Long id) {
        categoryRepository.deleteById(id);
    }
}
