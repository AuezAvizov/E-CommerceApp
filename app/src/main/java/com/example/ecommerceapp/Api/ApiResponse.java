package com.example.ecommerceapp.Api;

import com.example.ecommerceapp.Classes.Customer;

public class ApiResponse {
    private String status;
    private String message;
    private Customer customer;

    // Getters и Setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
