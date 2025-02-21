package com.example.product_category_crud_api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long product_id;

   @Column(nullable = false)
   private String product_name;

   @Column(nullable = false)
   private double product_price;

   // Many products belong to one category
   @ManyToOne
   @JoinColumn(name = "category_id", nullable = false)
   private Category category;

   public Product() {
   }

   public Product(String name, double price, Category category) {
       this.product_name = name;
       this.product_price = price;
       this.category = category;
   }

   // Getters and setters

   public Long getId() {
       return product_id;
   }

   public void setId(Long id) {
       this.product_id = id;
   }

   public String getName() {
       return product_name;
   }

   public void setName(String name) {
       this.product_name = name;
   }

   public double getPrice() {
       return product_price;
   }

   public void setPrice(double price) {
       this.product_price = price;
   }

   public Category getCategory() {
       return category;
   }

   public void setCategory(Category category) {
       this.category = category;
   }
}
