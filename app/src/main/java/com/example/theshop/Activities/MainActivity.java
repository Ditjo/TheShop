package com.example.theshop.Activities;

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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.theshop.Adapters.DrawBasketAdapter;
import com.example.theshop.Adapters.ProductsAdapter;
import com.example.theshop.Fragments.MainMenuFragment;
import com.example.theshop.Models.Product;
import com.example.theshop.R;
import com.example.theshop.data.Data;
import com.example.theshop.data.Logon;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ImageView iv_mainMenu, iv_settings;
    private Button btn_shop;

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

        Logon.isDebugLogin = true;

        if (!Logon.isLoggedIn && !Logon.isDebugLogin){
            openLogonActivity();
        }

        initGui();
        initMainMenuFragment();
        setGuiListeners();
    }

    void initGui(){
        drawerLayout = findViewById(R.id.main);
        iv_mainMenu = findViewById(R.id.iv_mainMenu);
//        iv_settings = findViewById(R.id.iv_settings);
//        btn_shop = findViewById(R.id.btn_shop);

    }

    void openLogonActivity(){
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }

    void setGuiListeners(){
        iv_mainMenu.setOnClickListener(x -> mainMenuDrawer());
//        iv_settings.setOnClickListener(x -> onSettingsClicked());
//        btn_shop.setOnClickListener(x -> onShopClicked());
    }

    void initMainMenuFragment(){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.main_menu_frame, new MainMenuFragment());
        ft.commit();
    }


    void mainMenuDrawer(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }



    void onSettingsClicked(){
        Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
        startActivity(intent);
    }

    void onShopClicked(){
        Intent intent = new Intent(getApplicationContext(), ShopActivity.class);
        startActivity(intent);
    }


}