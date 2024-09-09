package com.example.theshop;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.theshop.Adapters.ShopItemAdapter;
import com.example.theshop.Models.Product;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ImageView iv_mainMenu, iv_basket;

    private RecyclerView rv_productList;
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

        getShopProducts();

//        productList = initMockData();

        initGui();
        setGuiListeners();
    }

    void initGui(){
        drawerLayout = findViewById(R.id.main);
        iv_mainMenu = findViewById(R.id.iv_mainMenu);
        iv_basket = findViewById(R.id.iv_basket);

        rv_productList = findViewById(R.id.rv_productList);

        setAdapterToProductList();

    }

    void setGuiListeners(){
        iv_mainMenu.setOnClickListener(x -> mainMenuDrawer());
        iv_basket.setOnClickListener(x -> basketDrawer());
    }

    void setAdapterToProductList(){
        int numberOfColumns = 3;
        rv_productList.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        ShopItemAdapter adapter = new ShopItemAdapter(this, productList);
        rv_productList.setAdapter(adapter);
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

    List<Product> initMockData(){
        List<Product> list = new ArrayList<>();
        list.add(new Product("Item 1", "Very Good Description 1", 1, 1, R.drawable.ic_launcher_background, Categories.DOCUMENTS));
        list.add(new Product("Item 2", "Very Good Description 2", 20, 11, R.drawable.ic_launcher_background, Categories.ELECTRONIC));
        list.add(new Product("Item 3", "Very Good Description 3", 300, 111, R.drawable.ic_launcher_background, Categories.OTHER));
        list.add(new Product("Item 4", "Very Good Description 4", 4, 4, R.drawable.ic_launcher_background, Categories.DOCUMENTS));
        list.add(new Product("Item 5", "Very Good Description 5", 50, 55, R.drawable.ic_launcher_background, Categories.ELECTRONIC));
        list.add(new Product("Item 6", "Very Good Description 6", 600, 666, R.drawable.ic_launcher_background, Categories.OTHER));
        return list;
    }
}