package com.example.kalendar_viewer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    EditText inputUsername, inputPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputUsername = findViewById(R.id.inputUsername);
        inputPassword = findViewById(R.id.inputPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> {
            String username = inputUsername.getText().toString().trim();
            String password = inputPassword.getText().toString().trim();

            if (username.isEmpty()) {
                Toast.makeText(this, "Unesi korisničko ime", Toast.LENGTH_SHORT).show();
                return;
            }

            if (password.isEmpty()) {
                Toast.makeText(this, "Unesi lozinku", Toast.LENGTH_SHORT).show();
                return;
            }

            loginUser(username, password);
        });
    }

    private void loginUser(String username, String password) {
        new Thread(() -> {
            try {
                URL url = new URL("http://10.0.2.2/kalendar/api_login.php");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);

                JSONObject json = new JSONObject();
                json.put("username", username);
                json.put("password", password);

                OutputStream os = conn.getOutputStream();
                os.write(json.toString().getBytes());
                os.flush();
                os.close();

                Scanner sc = new Scanner(conn.getInputStream());
                StringBuilder response = new StringBuilder();
                while (sc.hasNext()) response.append(sc.nextLine());
                sc.close();

                JSONObject result = new JSONObject(response.toString());

                if (result.getString("status").equals("success")) {

                    runOnUiThread(() -> {
                        Toast.makeText(this, "Prijava uspješna!", Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(MainActivity.this, SecondActivity.class);
                        i.putExtra("username", username);
                        startActivity(i);
                        finish();
                    });

                } else {
                    runOnUiThread(() ->
                            Toast.makeText(this, "Pogrešno korisničko ime ili lozinka", Toast.LENGTH_SHORT).show()
                    );
                }

            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(() ->
                        Toast.makeText(this, "Greška u povezivanju", Toast.LENGTH_SHORT).show()
                );
            }
        }).start();
    }
}
