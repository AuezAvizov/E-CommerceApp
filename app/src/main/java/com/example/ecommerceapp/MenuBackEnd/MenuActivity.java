package com.example.ecommerceapp.MenuBackEnd;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ecommerceapp.FragmentBackEnd.ProfileFragment;
import com.example.ecommerceapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);  // Убедитесь, что layout activity_menu содержит контейнер для фрагментов

        // Инициализация BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Настройка логики работы с меню
        MenuActivityHelper.setupBottomNavigation(bottomNavigationView, this);

        // Загрузка фрагмента по умолчанию (например, Dashboard или Profile)
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new ProfileFragment())  // или любой другой фрагмент по умолчанию
                    .commit();
        }
    }
}
