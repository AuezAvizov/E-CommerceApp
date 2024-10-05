package com.example.ecommerceapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerceapp.Adapters.StatusAdapter;
import com.example.ecommerceapp.Classes.Status;

import java.util.ArrayList;
import java.util.List;

public class StatusListActivity extends AppCompatActivity {

    private RecyclerView statusRecyclerView;
    private StatusAdapter statusAdapter;
    private List<Status> statusList;
    private Handler handler = new Handler();
    private int currentStatusIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_list);

        statusRecyclerView = findViewById(R.id.statusRecyclerView);
        statusRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Инициализация списка статусов
        statusList = new ArrayList<>();
        statusList.add(new Status("Ожидание", R.drawable.air_soft_guns));
        statusList.add(new Status("В пути", R.drawable.air_soft_guns));
        statusList.add(new Status("В сортировочном центре", R.drawable.air_soft_guns));
        statusList.add(new Status("Доставлен", R.drawable.air_soft_guns));

        statusAdapter = new StatusAdapter(this, statusList);
        statusRecyclerView.setAdapter(statusAdapter);

        // Запуск таймера для обновления статусов каждые 30 секунд
        startStatusUpdate();
    }

    private void startStatusUpdate() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                updateStatus();
                handler.postDelayed(this, 30000); // Обновление каждые 30 секунд
            }
        }, 30000);
    }

    private void updateStatus() {
        // Логика смены статуса
        for (int i = 0; i < statusList.size(); i++) {
            if (i == currentStatusIndex) {
                switch (i) {
                    case 0:
                        statusList.get(i).setStatusText("Ожидание");
                        break;
                    case 1:
                        statusList.get(i).setStatusText("В пути");
                        break;
                    case 2:
                        statusList.get(i).setStatusText("В сортировочном центре");
                        break;
                    case 3:
                        statusList.get(i).setStatusText("Доставлен");
                        break;
                }
            }
        }
        currentStatusIndex = (currentStatusIndex + 1) % statusList.size();
        statusAdapter.updateStatusList(statusList);
    }
}
