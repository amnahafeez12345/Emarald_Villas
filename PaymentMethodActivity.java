package com.example.emrald_villas.ui;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.emrald_villas.R;
import com.example.emrald_villas.utils.Navigator;

public class PaymentMethodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);

        Button btnCash = findViewById(R.id.btn_cash);
        Button btnOnline = findViewById(R.id.btn_online);

        // 🔹 Cash on Arrival → Booking Success Screen
        btnCash.setOnClickListener(v ->
                Navigator.goTo(this, BookingSuccessActivity.class)
        );

        // 🔹 Online Payment → Online Payment Screen
        btnOnline.setOnClickListener(v ->
                Navigator.goTo(this, OnlinePaymentActivity.class)
        );
    }
}
