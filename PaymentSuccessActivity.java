package com.example.emrald_villas.ui;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.emrald_villas.R;
import com.example.emrald_villas.utils.Navigator;

public class PaymentSuccessActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_access); // your success XML

        Button btnHome = findViewById(R.id.btn_home);
        btnHome.setOnClickListener(v -> {
            Navigator.goTo(this, WelcomeActivity.class);
            finishAffinity();
        });
    }
}