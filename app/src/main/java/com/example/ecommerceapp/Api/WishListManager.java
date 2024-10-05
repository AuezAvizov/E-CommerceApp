package com.example.ecommerceapp.Api;

import com.example.ecommerceapp.Classes.Product;

import java.util.ArrayList;
import java.util.List;

public class WishListManager {
    private static WishListManager instance;
    private List<Product> wishListItems;

    private WishListManager() {
        wishListItems = new ArrayList<>();
    }

    public static WishListManager getInstance() {
        if (instance == null) {
            instance = new WishListManager();
        }
        return instance;
    }

    public List<Product> getWishListItems() {
        return wishListItems;
    }

    public void addProductToWishList(Product product) {
        wishListItems.add(product);
    }

    public boolean isProductInWishList(Product product) {
        return wishListItems.contains(product);
    }
}
