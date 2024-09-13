package com.example.theshop.Activities;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theshop.Adapters.DrawBasketAdapter;
import com.example.theshop.R;
import com.example.theshop.data.Data;

public class BasketActivity extends AppCompatActivity {

    private ImageView iv_backArrow;
    private RecyclerView rv_basketList;
    private DrawBasketAdapter basketAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_basket);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initGui();
        setAdapterToBasketList();
    }

    void initGui(){
        rv_basketList = findViewById(R.id.rv_basketList);
        iv_backArrow = findViewById(R.id.iv_backArrow);
        iv_backArrow.setOnClickListener(x -> onClickBack());
    }

    void setAdapterToBasketList(){
        rv_basketList.setLayoutManager(new LinearLayoutManager(this));
        basketAdapter = new DrawBasketAdapter(this, Data.getShoppingCart());
        rv_basketList.setAdapter(basketAdapter);
    }

    void onClickBack(){
        finish();
    }
}