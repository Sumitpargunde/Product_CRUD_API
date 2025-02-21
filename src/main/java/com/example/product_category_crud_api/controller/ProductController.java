package com.example.product_category_crud_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import com.example.product_category_crud_api.dto.CustomPageResponse;
import com.example.product_category_crud_api.model.Category;

import com.example.product_category_crud_api.model.Product;
import com.example.product_category_crud_api.repository.CategoryRepository;
import com.example.product_category_crud_api.repository.ProductRepository;

import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    // GET - Get all products
    @GetMapping
    public CustomPageResponse<Product> getAllCategories(@RequestParam(defaultValue = "0") int page) {
        Page<Product> productPage = productRepository.findAll(PageRequest.of(page, 10));
        return new CustomPageResponse<>(productPage.getContent(), productPage.getNumber());
    }

    // POST - Create a new product
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        // Validate if category exists
        Long categoryId = product.getCategory().getId();
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if (!optionalCategory.isPresent()) {
            throw new RuntimeException("Category not found with id " + categoryId);
        }
        product.setCategory(optionalCategory.get());
        return productRepository.save(product);
    }

    // GET - Retrieve a product by id (includes its category details)
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);
    }

    // PUT - Update a product by id
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product productDetails) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setName(productDetails.getName());
            product.setPrice(productDetails.getPrice());
            // If updating the category, validate the new category exists
            Long categoryId = productDetails.getCategory().getId();
            Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
            if (!optionalCategory.isPresent()) {
                throw new RuntimeException("Category not found with id " + categoryId);
            }
            product.setCategory(optionalCategory.get());
            return productRepository.save(product);
        }
        return null;
    }

    // DELETE - Delete a product by id
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        productRepository.deleteById(id);
    }
}
