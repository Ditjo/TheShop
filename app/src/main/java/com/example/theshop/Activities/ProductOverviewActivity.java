package com.example.theshop.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.theshop.Models.Product;
import com.example.theshop.R;

public class ProductOverviewActivity extends AppCompatActivity {

    private Product product;

    TextView ov_title, ov_price, ov_amount, ov_description;
    ImageView ov_image, iv_backArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_overview);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        product = (Product) intent.getSerializableExtra("product");

        initGui();
    }

    void initGui(){
        ov_title = findViewById(R.id.overview_title);
        ov_price = findViewById(R.id.overview_price);
        ov_amount = findViewById(R.id.overview_amount);
        ov_description = findViewById(R.id.overview_description);
        ov_image = findViewById(R.id.overview_image);

        ov_title.setText(product.getTitle());
        ov_price.setText(String.valueOf(product.getPrice()));
        ov_amount.setText(String.valueOf(product.getAmount()));
        ov_description.setText(product.getDescription());
        ov_image.setImageResource(product.getImageResId());

        iv_backArrow = findViewById(R.id.iv_backArrow);
        iv_backArrow.setOnClickListener(x -> onClickBack());
    }

    void onClickBack(){
        finish();
    }

}