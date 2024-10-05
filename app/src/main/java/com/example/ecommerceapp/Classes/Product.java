package com.example.ecommerceapp.Classes;

public class Product {
    private String product_id;
    private String product_title;
    private String product_img1;
    private String product_price;
    private String product_desc;
    private String product_features;
    private String date;
    private String cat_id; // Добавляем cat_id

    // Конструктор
    public Product(String product_id, String product_title, String product_img1, String product_price, String product_desc, String product_features, String date, String cat_id) {
        this.product_id = product_id;
        this.product_title = product_title;
        this.product_img1 = product_img1;
        this.product_price = product_price;
        this.product_desc = product_desc;
        this.product_features = product_features;
        this.date = date;
        this.cat_id = cat_id; // Инициализируем cat_id
    }

    // Геттеры
    public String getProductId() {
        return product_id;
    }

    public String getProductTitle() {
        return product_title;
    }

    public String getProductImg1() {
        return product_img1;
    }

    public String getProductPrice() {
        return product_price;
    }

    public String getProductDesc() {
        return product_desc;
    }

    public String getProductFeatures() {
        return product_features;
    }

    public String getDate() {
        return date;
    }

    public String getCatId() {
        return cat_id; // Геттер для cat_id
    }
}
