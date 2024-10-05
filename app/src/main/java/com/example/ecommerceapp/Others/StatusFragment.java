package com.example.ecommerceapp.Others;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Handler;

import com.example.ecommerceapp.Adapters.StatusAdapter;
import com.example.ecommerceapp.Classes.Status;
import com.example.ecommerceapp.DetailsFragment;
import com.example.ecommerceapp.R;

import java.util.ArrayList;
import java.util.List;

public class StatusFragment extends Fragment {

    private RecyclerView statusRecyclerView;
    private StatusAdapter statusAdapter;
    private List<Status> statusList;
    private Handler handler = new Handler();
    private int currentStatusIndex = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // "Раздуваем" макет фрагмента
        View view = inflater.inflate(R.layout.fragment_status, container, false);

        // Инициализация RecyclerView
        statusRecyclerView = view.findViewById(R.id.statusRecyclerView);
        statusRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Инициализация списка статусов
        statusList = new ArrayList<>();
        statusList.add(new Status("Ожидание", R.drawable.air_soft_guns));
        statusList.add(new Status("В пути", R.drawable.air_soft_guns));
        statusList.add(new Status("В сортировочном центре", R.drawable.air_soft_guns));
        statusList.add(new Status("Доставлен", R.drawable.air_soft_guns));

        statusAdapter = new StatusAdapter(getContext(), statusList);
        statusRecyclerView.setAdapter(statusAdapter);

        // Запуск обновления статусов каждые 30 секунд
        startStatusUpdate();

        // Вставляем DetailsFragment в контейнер внутри StatusFragment
        if (savedInstanceState == null) {
            getChildFragmentManager().beginTransaction()
                    .replace(R.id.details_fragment_container, new DetailsFragment()) // Вставляем DetailsFragment
                    .commit();
        }

        return view;
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
        currentStatusIndex = (currentStatusIndex + 1) % statusList.size();
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
        statusAdapter.updateStatusList(statusList);
    }
}
