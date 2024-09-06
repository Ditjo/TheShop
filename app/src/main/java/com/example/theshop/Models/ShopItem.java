package com.example.theshop.Models;

import com.example.theshop.Categories;

public class ShopItem {

    private int Id;
    private String Title;
    private String Description;
    private double Price;
    private int Amount;
    private int imageResId;
    private Categories Category;

    public ShopItem() {
    }

    public ShopItem(String title, String description, double price, int amount, int imageResId, Categories category) {
//        Id = id;
        Title = title;
        Description = description;
        Price = price;
        Amount = amount;
        this.imageResId = imageResId;
        Category = category;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public Categories getCategory() {
        return Category;
    }

    public void setCategory(Categories category) {
        Category = category;
    }
}
