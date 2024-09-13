package com.example.theshop.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.theshop.Models.Product;
import com.example.theshop.R;
import com.example.theshop.Activities.ProductOverviewActivity;
import com.example.theshop.data.Data;

import java.util.ArrayList;
import java.util.List;


public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ShopItemViewHolder> {

    public Context context;
    private List<Product> productList;

    public ProductsAdapter(Context context, List<Product> productList){
        this.context = context;
        Data.setProductList(productList);
        this.productList = productList;
    }

    @NonNull
    @Override
    public ShopItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_item, parent, false);
        return new ShopItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsAdapter.ShopItemViewHolder holder, int position) {
        Product product = productList.get(position);

        holder.shopItemTitle.setText(product.getTitle());
        if(product.getCategory() != null){
            holder.shopItemCategory.setText(product.getCategory().toString());
        }

        holder.shopItemAmount.setText("Amount: " + product.getAmount());
        holder.shopItemPrice.setText("Price: $" + product.getPrice());

//        holder.shopItemImage.setImageResource(product.getImageResId());

        Glide.with(context)
                .load(product.getImageUrl())
                .into(holder.shopItemImage);


        if(product.getAmount() <= 0){
            holder.addToBasketButton.setEnabled(false);
            holder.addToBasketButton.setText(R.string.productItem_Out);
        }
        else{
            holder.addToBasketButton.setEnabled(true);
            holder.addToBasketButton.setText(R.string.productItem_Add);
        }
        holder.addToBasketButton.setOnClickListener(x -> addItemToBasket(product, position));
        holder.sli_product_item.setOnClickListener(x -> showProductOverview(product));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ShopItemViewHolder extends RecyclerView.ViewHolder{
        ImageView shopItemImage;
        TextView shopItemTitle, shopItemCategory, shopItemAmount, shopItemPrice;
        Button addToBasketButton;
        View sli_product_item;

        public ShopItemViewHolder(@NonNull View itemView){
            super(itemView);
            shopItemImage = itemView.findViewById(R.id.sli_image);
            shopItemTitle = itemView.findViewById(R.id.sli_Title);
            shopItemCategory = itemView.findViewById(R.id.sli_Category);
            shopItemAmount = itemView.findViewById(R.id.sli_Amount);
            shopItemPrice = itemView.findViewById(R.id.sli_Price);
            addToBasketButton  = itemView.findViewById(R.id.sli_btn_addToBasket);
            sli_product_item = itemView.findViewById(R.id.sli_product_item);
        }
    }

    void addItemToBasket(Product item, int postion){
        item.setAmount(item.getAmount()-1);
        Data.addItemToBasket(item);
        notifyItemChanged(postion);

        Toast.makeText(context, item.getTitle() + " added to basket!", Toast.LENGTH_SHORT).show();
    }

    public void returnItemToList(Product product, int position){

        for (Product p : productList){
            if (p.getId() == product.getId()){
                p.setAmount(p.getAmount()+1);
            }
        }
        notifyItemChanged(position);
    }

    void showProductOverview(Product product){
        Intent intent = new Intent(context.getApplicationContext(), ProductOverviewActivity.class);
        intent.putExtra("product", product);
        context.startActivity(intent);
    }
}
