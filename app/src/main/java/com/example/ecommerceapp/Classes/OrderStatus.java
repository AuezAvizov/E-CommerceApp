package com.example.ecommerceapp.Classes;
public class OrderStatus {
    private int statusIcon;
    private String statusText;

    public OrderStatus(int statusIcon, String statusText) {
        this.statusIcon = statusIcon;
        this.statusText = statusText;
    }

    public int getStatusIcon() {
        return statusIcon;
    }

    public String getStatusText() {
        return statusText;
    }
}
