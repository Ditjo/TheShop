package com.example.theshop;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theshop.Adapters.ShopItemAdapter;
import com.example.theshop.Models.ShopItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ImageView iv_mainMenu, iv_basket;

    private RecyclerView rv_mainShopList;
    private List<ShopItem> shopItemList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        shopItemList = initMockData();

        initGui();
        setGuiListeners();
    }

    void initGui(){
        drawerLayout = findViewById(R.id.main);
        iv_mainMenu = findViewById(R.id.iv_mainMenu);
        iv_basket = findViewById(R.id.iv_basket);

        rv_mainShopList = findViewById(R.id.rv_mainShopList);
        int numberOfColumns = 2;
        rv_mainShopList.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        ShopItemAdapter adapter = new ShopItemAdapter(this, shopItemList);
        rv_mainShopList.setAdapter(adapter);

    }

    void setGuiListeners(){
        iv_mainMenu.setOnClickListener(x -> mainMenuDrawer());
        iv_basket.setOnClickListener(x -> basketDrawer());
    }

    void mainMenuDrawer(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }

    void basketDrawer(){
        if(drawerLayout.isDrawerOpen(GravityCompat.END)){
            drawerLayout.closeDrawer(GravityCompat.END);
        } else {
            drawerLayout.openDrawer(GravityCompat.END);
        }
    }

    List<ShopItem> initMockData(){
        List<ShopItem> list = new ArrayList<>();
        list.add(new ShopItem("Item 1", "Very Good Description 1", 1, 1, R.drawable.ic_launcher_background, Categories.DOCUMENTS));
        list.add(new ShopItem("Item 2", "Very Good Description 2", 20, 11, R.drawable.ic_launcher_background, Categories.ELECTRONIC));
        list.add(new ShopItem("Item 3", "Very Good Description 3", 300, 111, R.drawable.ic_launcher_background, Categories.OTHER));
        return list;
    }
}