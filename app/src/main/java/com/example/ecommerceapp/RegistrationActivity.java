package com.example.ecommerceapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class RegistrationActivity extends AppCompatActivity {
    private EditText customerName, customerEmail, customerPass, customerConfirmPass, customerCountry,
            customerCity, customerContact;
    private Button registerButton;
    private TextView signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // Привязка полей к переменным
        customerName = findViewById(R.id.customer_name);
        customerEmail = findViewById(R.id.customer_email);
        customerPass = findViewById(R.id.customer_pass);
        customerConfirmPass = findViewById(R.id.customer_confirm_pass);
        customerCountry = findViewById(R.id.customer_country);
        customerCity = findViewById(R.id.customer_city);
        customerContact = findViewById(R.id.customer_contact);
        registerButton = findViewById(R.id.registerButton);
        signUpBtn = findViewById(R.id.signUpBtn);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Переход на LoginActivity при нажатии на Sign up
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void registerUser() {
        String name = customerName.getText().toString().trim();
        String email = customerEmail.getText().toString().trim();
        String password = customerPass.getText().toString().trim();
        String confirmPassword = customerConfirmPass.getText().toString().trim();
        String country = customerCountry.getText().toString().trim();
        String city = customerCity.getText().toString().trim();
        String contact = customerContact.getText().toString().trim();

        // Проверка совпадения паролей
        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        // Запуск асинхронной задачи для отправки данных на сервер
        new RegisterUserTask(name, email, password, country, city, contact).execute();
    }

    private class RegisterUserTask extends AsyncTask<Void, Void, String> {
        private String name, email, password, country, city, contact;

        RegisterUserTask(String name, String email, String password, String country, String city, String contact) {
            this.name = name;
            this.email = email;
            this.password = password;
            this.country = country;
            this.city = city;
            this.contact = contact;
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL("http://10.0.2.2/military-shop/api/register.php");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                String postData = "customer_name=" + URLEncoder.encode(name, "UTF-8") +
                        "&customer_email=" + URLEncoder.encode(email, "UTF-8") +
                        "&customer_pass=" + URLEncoder.encode(password, "UTF-8") +
                        "&customer_country=" + URLEncoder.encode(country, "UTF-8") +
                        "&customer_city=" + URLEncoder.encode(city, "UTF-8") +
                        "&customer_contact=" + URLEncoder.encode(contact, "UTF-8") +
                        "&customer_address=&customer_image=&customer_ip=&customer_confirm_code=";

                writer.write(postData);
                writer.flush();
                writer.close();
                os.close();

                InputStream is = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                reader.close();
                is.close();

                conn.disconnect();
                return sb.toString();

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (result != null) {
                try {
                    JSONObject jsonResponse = new JSONObject(result);
                    String status = jsonResponse.getString("status");
                    String message = jsonResponse.getString("message");

                    if (status.equals("success")) {
                        Toast.makeText(RegistrationActivity.this, "Регестрация прошла успешно", Toast.LENGTH_SHORT).show();

                        // Переход на экран входа после успешной регистрации
                        Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish(); // Закрываем текущую активность, чтобы пользователь не мог вернуться назад
                    } else {
                        Toast.makeText(RegistrationActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(RegistrationActivity.this, "Ошибка отпарвки сообщения на сервер", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(RegistrationActivity.this, "Ошибка подключения к серверу", Toast.LENGTH_SHORT).show();
            }
        }
    }
}