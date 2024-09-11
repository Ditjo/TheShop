package com.example.theshop.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theshop.Models.Product;
import com.example.theshop.R;
import com.example.theshop.data.Data;

import java.util.Date;
import java.util.List;

public class DrawBasketAdapter extends RecyclerView.Adapter<DrawBasketAdapter.ProductViewHolder> {

    private Context context;
    private List<Product> productList;

    public DrawBasketAdapter(Context context, List<Product> productList){
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public DrawBasketAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.draw_basket_item, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DrawBasketAdapter.ProductViewHolder holder, int position) {
        Product product = productList.get(position);

        holder.drawBasket_title.setText(product.getTitle());
        holder.drawBasket_amount.setText(String.valueOf(product.getAmount()));
        holder.drawBasket_price.setText("$" + product.getPrice());

        holder.drawBasket_btn_remove.setOnClickListener(x -> removeItemFromBasket(product, position));

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder{

        ImageView drawBasket_image, drawBasket_btn_remove;
        TextView drawBasket_title, drawBasket_amount, drawBasket_price;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            drawBasket_image = itemView.findViewById(R.id.drawBasket_image);
            drawBasket_title = itemView.findViewById(R.id.drawBasket_title);
            drawBasket_amount = itemView.findViewById(R.id.drawBasket_amount);
            drawBasket_price = itemView.findViewById(R.id.drawBasket_price);
            drawBasket_btn_remove = itemView.findViewById(R.id.drawBasket_btn_remove);
        }
    }

    void removeItemFromBasket(Product product, int position){


        Data.removeItemFromBasket(product);
        notifyItemChanged(position);
    }
}
