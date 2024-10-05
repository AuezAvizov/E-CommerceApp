package com.example.ecommerceapp.FragmentBackEnd;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerceapp.Classes.Product;
import com.example.ecommerceapp.Adapters.ProductAdapter;
import com.example.ecommerceapp.R;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private List<Product> productList;
    private EditText searchEditText;
    private LinearLayout categoryAirsoftGuns, categoryTacticalGear, categoryAccessories;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        searchEditText = view.findViewById(R.id.searchEditText);
        categoryAirsoftGuns = view.findViewById(R.id.categoryAirsoftGuns);
        categoryTacticalGear = view.findViewById(R.id.categoryTacticalGear);
        categoryAccessories = view.findViewById(R.id.categoryAccessories);

        // Чтение продуктов из JSON файла
        productList = loadProductsFromJson();

        // Инициализация адаптера и привязка к RecyclerView
        productAdapter = new ProductAdapter(productList, getContext());
        recyclerView.setAdapter(productAdapter);

        // Слушатели для категорий
        categoryAirsoftGuns.setOnClickListener(v -> filterByCategory("4"));  // cat_id = 4 для Airsoft Guns
        categoryTacticalGear.setOnClickListener(v -> filterByCategory("5")); // cat_id = 5 для Tactical Gear
        categoryAccessories.setOnClickListener(v -> filterByCategory("6"));  // cat_id = 6 для Accessories

        return view;
    }

    // Метод для чтения JSON и парсинга продуктов
    private List<Product> loadProductsFromJson() {
        List<Product> productList = new ArrayList<>();
        try {
            InputStream inputStream = getContext().getAssets().open("products.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            String json = new String(buffer, "UTF-8");

            Gson gson = new Gson();
            // Если JSON является объектом с массивом продуктов внутри
            JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
            JsonArray productArray = jsonObject.getAsJsonArray("products");

            Type productListType = new TypeToken<ArrayList<Product>>(){}.getType();
            List<Product> products = gson.fromJson(productArray, productListType);

            // Проверка наличия обязательных полей
            if (products != null) {
                productList.addAll(products);
            }

        } catch (IOException e) {
            e.printStackTrace();
            // Если произошла ошибка, можно уведомить пользователя через Toast
            Toast.makeText(getContext(), "Error loading products", Toast.LENGTH_SHORT).show();
        }

        return productList;
    }

    // Метод для фильтрации по категориям (используем cat_id)
    private void filterByCategory(String categoryId) {
        List<Product> filteredList = new ArrayList<>();
        for (Product product : productList) {
            if (product.getCatId() != null && product.getCatId().equals(categoryId)) {
                filteredList.add(product);
            }
        }
        productAdapter.updateProductList(filteredList);
    }
}
