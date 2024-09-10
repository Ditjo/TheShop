package com.example.theshop.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.theshop.Adapters.ProductsAdapter;
import com.example.theshop.Models.Product;
import com.example.theshop.R;
import com.example.theshop.data.Data;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ImageView iv_mainMenu, iv_basket, iv_settings;
    private Button btn_goToBasket;

    private RecyclerView rv_productList, rv_drawBasket;
    private List<Product> productList = new ArrayList<>();

    private String ApiUrl = "http://192.168.0.19:8080/product";
    private RequestQueue requestQueue;

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

        requestQueue = Volley.newRequestQueue(this);

//        getShopProducts();

        productList = Data.initMockData();

        initGui();
        setGuiListeners();
    }

    void initGui(){
        drawerLayout = findViewById(R.id.main);
        iv_mainMenu = findViewById(R.id.iv_mainMenu);
        iv_basket = findViewById(R.id.iv_basket);
        iv_settings = findViewById(R.id.iv_settings);
        btn_goToBasket = findViewById(R.id.btn_goToBasket);

        rv_productList = findViewById(R.id.rv_productList);
        rv_drawBasket = findViewById(R.id.rv_drawBasket);

        setAdapterToProductList();

    }

    void setGuiListeners(){
        iv_mainMenu.setOnClickListener(x -> mainMenuDrawer());
        iv_basket.setOnClickListener(x -> basketDrawer());
        iv_settings.setOnClickListener(x -> onSettingsClicked());
        btn_goToBasket.setOnClickListener(x -> onGoToBasketClicked());
    }

    void setAdapterToProductList(){
        int numberOfColumns = 3;
        rv_productList.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        ProductsAdapter adapter = new ProductsAdapter(this, productList);
        rv_productList.setAdapter(adapter);
    }

    void setAdapterToDrawBasketList(){
        rv_drawBasket.setLayoutManager(new LinearLayoutManager(this));

    }

    void getShopProducts(){
        StringRequest request = new StringRequest(
                Request.Method.GET,
                ApiUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Type listType = new TypeToken<List<Product>>() {}.getType();
                        List<Product> products = new Gson().fromJson(response, listType);
                        productList = products;
                        setAdapterToProductList();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(">>> Volley", "onGetErrorResponse", error);
                    }
                }
        );
        requestQueue.add(request);
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

    void onSettingsClicked(){
        Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
        startActivity(intent);
    }

    void onGoToBasketClicked(){
        Intent intent = new Intent(getApplicationContext(), BasketActivity.class);
        startActivity(intent);
    }

}