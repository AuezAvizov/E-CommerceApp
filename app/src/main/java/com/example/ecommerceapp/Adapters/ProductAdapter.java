package com.example.ecommerceapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ecommerceapp.Classes.Product;
import com.example.ecommerceapp.ProductDetailActivity;
import com.example.ecommerceapp.R;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<Product> productList;
    private Context context;

    public ProductAdapter(List<Product> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.titleTextView.setText(product.getProductTitle());
        holder.priceTextView.setText("$" + product.getProductPrice());

        // Load product image using Glide
        Glide.with(context)
                .load("file:///android_asset/" + product.getProductImg1())
                .placeholder(R.drawable.air_soft_guns)
                .into(holder.productImageView);

        // Set an OnClickListener on the item to handle clicks
        holder.itemView.setOnClickListener(v -> {
            // Create an Intent to start ProductDetailActivity
            Intent intent = new Intent(context, ProductDetailActivity.class);
            intent.putExtra("productId", product.getProductId());  // Pass product ID
            context.startActivity(intent);  // Start the activity
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    // Method to update the product list
    public void updateProductList(List<Product> newProductList) {
        productList.clear();
        productList.addAll(newProductList);
        notifyDataSetChanged();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView productImageView;
        TextView titleTextView, priceTextView;

        public ProductViewHolder(View itemView) {
            super(itemView);
            productImageView = itemView.findViewById(R.id.productImageView);
            titleTextView = itemView.findViewById(R.id.productNameTextView);
            priceTextView = itemView.findViewById(R.id.productPriceTextView);
        }
    }
}
