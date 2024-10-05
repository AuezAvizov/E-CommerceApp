package com.example.ecommerceapp.MenuBackEnd;

import android.content.Intent;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ecommerceapp.DashboardActivity;
import com.example.ecommerceapp.Others.StatusFragment;
import com.example.ecommerceapp.R;
import com.example.ecommerceapp.FragmentBackEnd.WishListFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.ecommerceapp.FragmentBackEnd.ProfileFragment;

public class MenuActivityHelper {

    // Метод для настройки логики BottomNavigationView
    public static void setupBottomNavigation(BottomNavigationView bottomNavigationView, final AppCompatActivity activity) {

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment selectedFragment = null;

                switch (item.getItemId()) {
                    case R.id.dashboard:
                        // Логика для открытия DashboardActivity
                        Intent intent = new Intent(activity, DashboardActivity.class);
                        activity.startActivity(intent);
                        return true;

                    case R.id.profile:
                        // Логика для отображения ProfileFragment
                        selectedFragment = new ProfileFragment();
                        break;
                    case R.id.wishlist:
                        selectedFragment = new WishListFragment();
                        break;
                    case R.id.status:
                        selectedFragment = new StatusFragment();
                        break;

                    default:
                        return false;
                }

                // Показываем выбранный фрагмент, если он есть
                if (selectedFragment != null) {
                    FragmentManager fragmentManager = activity.getSupportFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.fragment_container, selectedFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    return true;
                }
                return false;
            }
        });
    }
}
