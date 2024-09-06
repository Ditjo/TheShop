package com.example.theshop.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theshop.Models.ShopItem;
import com.example.theshop.R;

import java.util.List;


public class ShopItemAdapter extends RecyclerView.Adapter<ShopItemAdapter.ShopItemViewHolder> {

    private Context context;
    private List<ShopItem> shopItemList;

    public ShopItemAdapter(Context context, List<ShopItem> shopItemList){
        this.context = context;
        this.shopItemList = shopItemList;
    }

    @NonNull
    @Override
    public ShopItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.shop_list_item, parent, false);
        return new ShopItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopItemAdapter.ShopItemViewHolder holder, int position) {
        ShopItem shopItem = shopItemList.get(position);

        holder.shopItemTitle.setText(shopItem.getTitle());
        holder.shopItemCategory.setText(shopItem.getCategory().toString());
        holder.shopItemAmount.setText("Amount: " + shopItem.getAmount());
        holder.shopItemPrice.setText("Price: $" + shopItem.getPrice());

        holder.shopItemImage.setImageResource(shopItem.getImageResId());

        holder.addToBasketButton.setOnClickListener(x -> {
            Toast.makeText(context, "TEST WIP: Item added To Basket", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return shopItemList.size();
    }

    public static class ShopItemViewHolder extends RecyclerView.ViewHolder{
        ImageView shopItemImage;
        TextView shopItemTitle, shopItemCategory, shopItemAmount, shopItemPrice;
        Button addToBasketButton;

        public ShopItemViewHolder(@NonNull View itemView){
            super(itemView);
            shopItemImage = itemView.findViewById(R.id.sli_image);
            shopItemTitle = itemView.findViewById(R.id.sli_Title);
            shopItemCategory = itemView.findViewById(R.id.sli_Category);
            shopItemAmount = itemView.findViewById(R.id.sli_Amount);
            shopItemPrice = itemView.findViewById(R.id.sli_Price);
            addToBasketButton  = itemView.findViewById(R.id.sli_btn_addToBasket);
        }
    }
}
