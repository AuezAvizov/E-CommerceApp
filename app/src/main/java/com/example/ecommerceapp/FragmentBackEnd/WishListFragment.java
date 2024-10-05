package com.example.ecommerceapp.FragmentBackEnd;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerceapp.Adapters.WishListAdapter;
import com.example.ecommerceapp.Classes.Product;
import com.example.ecommerceapp.Api.WishListManager;
import com.example.ecommerceapp.R;

import java.util.List;

public class WishListFragment extends Fragment {

    private RecyclerView recyclerViewWishList;
    private WishListAdapter wishListAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wish_list, container, false);

        recyclerViewWishList = view.findViewById(R.id.recyclerViewWishList);
        recyclerViewWishList.setLayoutManager(new LinearLayoutManager(getContext()));

        // Получаем данные из WishListManager
        updateWishList();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateWishList(); // Обновляем список при возврате к фрагменту
    }

    private void updateWishList() {
        List<Product> wishListItems = WishListManager.getInstance().getWishListItems();
        wishListAdapter = new WishListAdapter(wishListItems);
        recyclerViewWishList.setAdapter(wishListAdapter);
    }
}
