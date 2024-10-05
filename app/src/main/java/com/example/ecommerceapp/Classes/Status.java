package com.example.ecommerceapp.Classes;

public class Status {
    private String statusText;
    private int iconResId;

    public Status(String statusText, int iconResId) {
        this.statusText = statusText;
        this.iconResId = iconResId;
    }

    public String getStatusText() {
        return statusText;
    }

    public int getIconResId() {
        return iconResId;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }
}
