package com.example.ecommerceapp.Api;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {

    // Метод для логина пользователя
    @FormUrlEncoded
    @POST("login.php") // Укажите реальный путь к вашему скрипту login.php
    Call<ApiResponse> loginUser(
            @Field("customer_email") String email,
            @Field("customer_pass") String password
    );
}
