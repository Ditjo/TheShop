package com.example.theshop.data;

import com.example.theshop.Activities.ShopActivity;
import com.example.theshop.Enums.Categories;
import com.example.theshop.Models.Product;
import com.example.theshop.R;

import java.util.ArrayList;
import java.util.List;

public class Data {

    private static List<Product> ShoppingCart = new ArrayList<>();

    public static List<Product> getShoppingCart(){
        return ShoppingCart;
    }

    public static void addItemToBasket(Product item){
        for (Product p : ShoppingCart){
            if(p.getId() == item.getId()){
                p.setAmount(p.getAmount()+1);
                return;
            }
        }
        Product p = new Product(
                item.getId(),
                item.getTitle(),
                item.getDescription(),
                item.getPrice(),
                1,
                item.getImageResId(),
                item.getCategory());
        p.setImageUrl(item.getImageUrl());
        ShoppingCart.add(p);
    }

    public static void removeItemFromBasket(Product item){

        for (int i = 0; i < ShoppingCart.size(); i++) {

            Product p = ShoppingCart.get(i);
            if(p.getId() == item.getId()){
                if(p.getAmount() > 1){
                    p.setAmount(p.getAmount() - 1);
                } else{
                    ShoppingCart.remove(item);
                }
//                for (int l = 0; l < ProductList.size(); l++){
//
//                    Product pl = ProductList.get(l);
//                    if (p.getId() == pl.getId()){
//                        pl.setAmount(pl.getAmount()+1);
//                    }
//                }
            }
        }
    }

    private static List<Product> ProductList = new ArrayList<>();

    public static List<Product> getProductList() { return ProductList; }
    public static void setProductList(List<Product> list) { ProductList = list; }



    public static List<Product> initMockData(){
        List<Product> list = new ArrayList<>();
        list.add(new Product(1,"Item 1", "Very Good Description 1", 1, 1, R.drawable.ic_launcher_background, Categories.DOCUMENTS));
        list.add(new Product(2,"Item 2", "Very Good Description 2", 20, 11, R.drawable.ic_launcher_background, Categories.ELECTRONICS));
        list.add(new Product(3,"Item 3", "Very Good Description 3", 300, 111, R.drawable.ic_launcher_background, Categories.OTHERS));
        list.add(new Product(4,"Item 4", "Very Good Description 4", 4, 4, R.drawable.ic_launcher_background, Categories.TOOLS));
        list.add(new Product(5,"Item 5", "Very Good Description 5", 50, 55, R.drawable.ic_launcher_background, Categories.ELECTRONICS));
        list.add(new Product(6,"Item 6", "Very Good Description 6", 600, 666, R.drawable.ic_launcher_background, Categories.WEAPONS));
        return list;
    }
}
