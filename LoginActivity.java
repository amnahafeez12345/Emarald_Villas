package com.example.emrald_villas.ui;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.emrald_villas.R;
import com.example.emrald_villas.controllers.AuthController;
import com.example.emrald_villas.models.User;
import com.example.emrald_villas.utils.Navigator;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    private AuthController authController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        authController = new AuthController(this);

        TextInputEditText etEmail = findViewById(R.id.et_login_email);
        TextInputEditText etPassword = findViewById(R.id.et_login_password);

        // ✅ LOGIN BUTTON
        findViewById(R.id.btn_login).setOnClickListener(v -> {

            String email = etEmail.getText() == null
                    ? ""
                    : etEmail.getText().toString().trim();

            String password = etPassword.getText() == null
                    ? ""
                    : etPassword.getText().toString();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this,
                        "Please enter email & password",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            User user = authController.login(email, password);

            if (user != null) {

                Toast.makeText(
                        this,
                        "Welcome " + user.getFullName(),
                        Toast.LENGTH_SHORT
                ).show();

                // ✅ SUCCESS → DASHBOARD
                Navigator.goTo(this, DashboardActivity.class);
                finish();

            } else {
                Toast.makeText(
                        this,
                        "Invalid email or password",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

        // ✅ FORGOT PASSWORD → SIGNUP
        findViewById(R.id.tv_forgot)
                .setOnClickListener(v ->
                        Navigator.goTo(this, SignupActivity.class)
                );
    }
}