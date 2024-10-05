package com.example.ecommerceapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.ecommerceapp.Classes.Product;
import com.example.ecommerceapp.Api.WishListManager;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.InputStream;

public class ProductDetailActivity extends AppCompatActivity {
    private ImageView photoImageView;
    private TextView titleTextView, priceTextView, descriptionTextView, dateTextView, featuresTextView;
    private Button buyButton, wishlistButton;
    private ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        // Инициализация виджетов
        photoImageView = findViewById(R.id.photoImageView);
        titleTextView = findViewById(R.id.titleTextView);
        priceTextView = findViewById(R.id.priceTextView);
        descriptionTextView = findViewById(R.id.descriptionTextView);
        dateTextView = findViewById(R.id.dateTextView);
        featuresTextView = findViewById(R.id.featuresTextView);
        buyButton = findViewById(R.id.buyButton);
        wishlistButton = findViewById(R.id.wishlistButton);
        backButton = findViewById(R.id.backButton);

        // Получаем ID продукта из Intent
        Intent intent = getIntent();
        String productId = intent.getStringExtra("productId");

        // Показываем переданный ID для отладки
        Toast.makeText(this, "Переданный ID: " + productId, Toast.LENGTH_LONG).show();

        // Загружаем продукт из JSON по productId
        Product product = loadProductFromJson(productId);

        if (product != null) {
            // Устанавливаем данные в виджеты
            titleTextView.setText(product.getProductTitle());
            priceTextView.setText("Цена: $" + product.getProductPrice());
            descriptionTextView.setText(product.getProductDesc());
            dateTextView.setText("Дата добавления: " + product.getDate());
            featuresTextView.setText("Ключевые слова: " + product.getProductFeatures());

            // Загружаем изображение с помощью Glide
            Glide.with(this)
                    .load("file:///android_asset/" + product.getProductImg1())
                    .placeholder(R.drawable.air_soft_guns)
                    .into(photoImageView);
        } else {
            Toast.makeText(this, "Продукт не найден", Toast.LENGTH_SHORT).show();
        }

        // Логика для кнопки "Купить"
        buyButton.setOnClickListener(v -> {
            if (product != null) {
                Toast.makeText(this, "Товар куплен!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Продукт не найден", Toast.LENGTH_SHORT).show();
            }
        });

        // Логика для кнопки "Добавить в WishList"
        wishlistButton.setOnClickListener(v -> {
            WishListManager.getInstance().addProductToWishList(product);
            Toast.makeText(this, "Товар добавлен в избранное", Toast.LENGTH_SHORT).show();
        });

        // Логика для кнопки "Назад"
        backButton.setOnClickListener(v -> finish());
    }

    // Метод для загрузки продукта из JSON по его ID
    private Product loadProductFromJson(String productId) {
        Product product = null;
        try {
            // Загружаем JSON из файла
            InputStream inputStream = getAssets().open("products.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String json = new String(buffer, "UTF-8");

            // Парсим JSON
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
            JsonArray productsArray = jsonObject.getAsJsonArray("products");

            // Логируем переданный productId
            System.out.println("Поиск продукта с ID: " + productId);

            // Ищем продукт по ID
            for (int i = 0; i < productsArray.size(); i++) {
                JsonObject productObject = productsArray.get(i).getAsJsonObject();
                String id = productObject.get("product_id").getAsString();

                // Логируем ID продукта в JSON
                System.out.println("Проверяем продукт с ID: " + id);

                if (id.equals(productId)) {
                    // Продукт найден
                    product = gson.fromJson(productObject, Product.class);
                    break;
                }
            }

            if (product == null) {
                System.out.println("Продукт не найден в JSON для ID: " + productId);
            }

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Ошибка при загрузке продукта", Toast.LENGTH_SHORT).show();
        }
        return product;
    }
}
