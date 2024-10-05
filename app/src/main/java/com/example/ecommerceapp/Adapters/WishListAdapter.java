package com.example.ecommerceapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ecommerceapp.Classes.Product;
import com.example.ecommerceapp.R;

import java.util.List;

public class WishListAdapter extends RecyclerView.Adapter<WishListAdapter.WishListViewHolder> {

    private List<Product> wishListItems;

    public WishListAdapter(List<Product> wishListItems) {
        this.wishListItems = wishListItems;
    }

    @NonNull
    @Override
    public WishListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wishlist, parent, false);
        return new WishListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WishListViewHolder holder, int position) {
        Product item = wishListItems.get(position);
        holder.productTitleTextView.setText(item.getProductTitle());  // Используем актуальные геттеры
        holder.productPriceTextView.setText("Цена: $" + item.getProductPrice());

        // Используем Glide для загрузки изображения
        Glide.with(holder.itemView.getContext())
                .load("file:///android_asset/" + item.getProductImg1())  // Если изображение в assets
                .placeholder(R.drawable.air_soft_guns)  // Замените на ваш ресурс плейсхолдера
                .into(holder.productImageView);
    }

    @Override
    public int getItemCount() {
        return wishListItems.size();
    }

    public static class WishListViewHolder extends RecyclerView.ViewHolder {
        TextView productTitleTextView, productPriceTextView;
        ImageView productImageView;

        public WishListViewHolder(@NonNull View itemView) {
            super(itemView);
            productTitleTextView = itemView.findViewById(R.id.productTitleTextView);
            productPriceTextView = itemView.findViewById(R.id.productPriceTextView);
            productImageView = itemView.findViewById(R.id.productImageView);
        }
    }
}
