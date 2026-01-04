package com.example.emrald_villas.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.emrald_villas.R;
import com.example.emrald_villas.utils.Navigator;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // Get Started → Signup
        findViewById(R.id.btn_get_started).setOnClickListener(v ->
                Navigator.go(this, SignupActivity.class)
        );

        // Bottom navigation
        findViewById(R.id.nav_home).setOnClickListener(v ->
                Navigator.go(this, WelcomeActivity.class)
        );

        findViewById(R.id.nav_profile).setOnClickListener(v ->
                Navigator.go(this, LoginActivity.class)
        );

        findViewById(R.id.nav_contact).setOnClickListener(v ->
                Navigator.go(this, LoginActivity.class)
        );

        findViewById(R.id.nav_social).setOnClickListener(v ->
                Navigator.go(this, LoginActivity.class)
        );
    }
}