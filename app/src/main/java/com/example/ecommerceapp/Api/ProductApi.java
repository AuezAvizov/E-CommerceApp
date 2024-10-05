package com.example.ecommerceapp.Api;

import com.example.ecommerceapp.Classes.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductApi {
    @GET("products") // Укажите здесь правильный путь к вашему API
    Call<List<Product>> getProducts();
}