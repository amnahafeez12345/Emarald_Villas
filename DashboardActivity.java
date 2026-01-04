package com.example.emrald_villas.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import com.example.emrald_villas.R;
import com.example.emrald_villas.utils.Navigator;

public class DashboardActivity extends AppCompatActivity {

    private ImageButton btnDeluxe, btnExecutive, btnLuxury;
    private Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Bind buttons
        btnDeluxe = findViewById(R.id.btn_deluxe);
        btnExecutive = findViewById(R.id.btn_executive);
        btnLuxury = findViewById(R.id.btn_luxury);
        btnSearch = findViewById(R.id.btn_dashboard_search);

        // Click events → BookingActivity
        btnDeluxe.setOnClickListener(v ->
                Navigator.goTo(DashboardActivity.this, BookingActivity.class)
        );

        btnExecutive.setOnClickListener(v ->
                Navigator.goTo(DashboardActivity.this, BookingActivity.class)
        );

        btnLuxury.setOnClickListener(v ->
                Navigator.goTo(DashboardActivity.this, BookingActivity.class)
        );

        // Search button → SearchActivity
        btnSearch.setOnClickListener(v ->
                Navigator.goTo(DashboardActivity.this, SearchActivity.class)
        );
    }
}