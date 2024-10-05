package com.example.ecommerceapp.FragmentBackEnd;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ecommerceapp.Api.ApiResponse;
import com.example.ecommerceapp.Classes.Customer;
import com.example.ecommerceapp.Api.RetrofitClient;
import com.example.ecommerceapp.Api.ApiService;
import com.example.ecommerceapp.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {
    private TextView profileName, profileEmail, profilePhone, profileAddress, profileCity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Инициализация TextView элементов
        profileName = view.findViewById(R.id.profile_name);
        profileEmail = view.findViewById(R.id.profile_email);
        profilePhone = view.findViewById(R.id.profile_contact_phone);
        profileAddress = view.findViewById(R.id.profile_address);
        profileCity = view.findViewById(R.id.profile_city);

        // Загрузка данных профиля
        loadUserProfile();

        return view;
    }

    private void loadUserProfile() {
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);

        // Используйте данные, которые пользователь ввел для авторизации
        String customerEmail = "Auez@mail.ru";  // Замените на фактический email пользователя
        String customerPassword = "123";  // Замените на фактический пароль пользователя

        Call<ApiResponse> call = apiService.loginUser(customerEmail, customerPassword);

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponse apiResponse = response.body();
                    if ("success".equals(apiResponse.getStatus())) {
                        Customer customer = apiResponse.getCustomer();

                        // Проверяем, что объект customer не равен null
                        if (customer != null) {
                            profileName.setText(customer.getCustomer_name());
                            profileEmail.setText(customer.getCustomer_email());
                            profilePhone.setText(customer.getCustomer_contact());
                            profileAddress.setText(customer.getCustomer_address());
                            profileCity.setText(customer.getCustomer_city());
                        } else {
                            Toast.makeText(getContext(), "Customer data is missing", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getContext(), apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Failed to retrieve data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
