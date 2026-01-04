package com.example.emrald_villas.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.emrald_villas.R;
import com.example.emrald_villas.controllers.AuthController;
import com.example.emrald_villas.models.User;
import com.example.emrald_villas.utils.Navigator;
import com.google.android.material.textfield.TextInputEditText;

public class SignupActivity extends AppCompatActivity {

    private AuthController authController;
    private TextInputEditText etName, etEmail, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        authController = new AuthController(this);

        etName = findViewById(R.id.et_name);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
    }

    // ✅ Called from XML: android:onClick="onSignUpClicked"
    public void onSignUpClicked(View view) {

        String name = etName.getText() == null ? "" : etName.getText().toString().trim();
        String email = etEmail.getText() == null ? "" : etEmail.getText().toString().trim();
        String pass = etPassword.getText() == null ? "" : etPassword.getText().toString();

        User user = new User(name, email, pass);
        long userId = authController.register(user);

        if (userId > 0) {
            Toast.makeText(this, "Account created successfully", Toast.LENGTH_SHORT).show();
            Navigator.goAndFinish(this, DashboardActivity.class);
        } else {
            Toast.makeText(this, "Invalid data or email already exists", Toast.LENGTH_SHORT).show();
        }
    }

    // ✅ Called from XML: android:onClick="onLoginRedirectClicked"
    public void onLoginRedirectClicked(View view) {
        Navigator.go(this, LoginActivity.class);
    }
}