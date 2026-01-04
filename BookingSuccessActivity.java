package com.example.emrald_villas.ui;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.emrald_villas.R;
import com.example.emrald_villas.utils.Navigator;

public class BookingSuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_success);

        Button btnHome = findViewById(R.id.btn_home);

        // 🔹 Home → Dashboard
        btnHome.setOnClickListener(v ->
                Navigator.goAndFinish(this, DashboardActivity.class)
        );
    }
}
