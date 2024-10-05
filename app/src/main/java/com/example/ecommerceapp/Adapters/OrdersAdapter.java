package com.example.ecommerceapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerceapp.Classes.OrderStatus;
import com.example.ecommerceapp.R;

import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.ViewHolder> {

    private List<OrderStatus> orderStatusList;
    private Context context;

    public OrdersAdapter(Context context, List<OrderStatus> orderStatusList) {
        this.context = context;
        this.orderStatusList = orderStatusList;
    }

    // Создание нового элемента (вызывается LayoutManager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_status, parent, false);
        return new ViewHolder(view);
    }

    // Привязка данных к View элементам
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderStatus currentOrder = orderStatusList.get(position);
        holder.statusText.setText(currentOrder.getStatusText());
        holder.statusIcon.setImageResource(currentOrder.getStatusIcon());
    }

    @Override
    public int getItemCount() {
        return orderStatusList.size();
    }

    // Класс ViewHolder, который держит ссылки на элементы интерфейса
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView statusIcon;
        TextView statusText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            statusIcon = itemView.findViewById(R.id.statusIcon);
            statusText = itemView.findViewById(R.id.statusText);
        }
    }
}
