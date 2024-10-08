package com.example.theshop.Models;

import com.example.theshop.Enums.Categories;

import java.io.Serializable;

public class Product implements Serializable {

    private int id;
    private String title;
    private String description;
    private double price;
    private int amount;
    private int imageResId;
    private String imageUrl;
    private Categories category;

    public Product() {
    }

    public Product(String title, String description, double price, int amount, int imageResId, Categories category) {
//        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.amount = amount;
        this.imageResId = imageResId;
        this.category = category;
    }

    public Product(int id, String title, String description, double price, int amount, int imageResId, Categories category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.amount = amount;
        this.imageResId = imageResId;
        this.category = category;
    }

    public Product(int id, String title, String description, double price, int amount, int imageResId, String imageUrl, Categories category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.amount = amount;
        this.imageResId = imageResId;
        this.imageUrl = imageUrl;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
