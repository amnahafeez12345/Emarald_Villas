package com.example.emrald_villas.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.emrald_villas.R;
import com.example.emrald_villas.controllers.PaymentController;
import com.example.emrald_villas.data.DBHelper;
import com.example.emrald_villas.models.Booking;
import com.example.emrald_villas.models.Payment;
import com.example.emrald_villas.utils.Navigator;
import com.google.android.material.textfield.TextInputEditText;

public class OnlinePaymentActivity extends AppCompatActivity {

    private TextInputEditText etCardName, etCardNumber, etExpiry, etCVV;
    private DBHelper db;
    private PaymentController paymentController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_payment);

        db = new DBHelper(this);
        paymentController = new PaymentController(this);

        etCardName = findViewById(R.id.et_card_name);
        etCardNumber = findViewById(R.id.et_card_number);
        etExpiry = findViewById(R.id.et_card_expiry);
        etCVV = findViewById(R.id.et_card_cvv);
        Button btnPay = findViewById(R.id.btn_pay);

        btnPay.setOnClickListener(v -> processPayment());
    }

    private void processPayment() {

        String holder = etCardName.getText().toString().trim();
        String number = etCardNumber.getText().toString().trim();

        if (holder.isEmpty() || number.isEmpty()) {
            Toast.makeText(this, "All fields required", Toast.LENGTH_SHORT).show();
            return;
        }

        // Demo booking object
        Booking booking = new Booking();
        booking.setId(1);

        Payment payment = paymentController.processOnlinePayment(
                booking,
                holder,
                number
        );

        if (!payment.isSuccessful()) {
            Toast.makeText(this, "Invalid Card Details", Toast.LENGTH_SHORT).show();
            return;
        }

        // Save payment to DB
        db.insertPayment(payment);

        Toast.makeText(this, "Payment Successful", Toast.LENGTH_SHORT).show();

        // ✅ Navigate to success screen
        Navigator.goAndFinish(this, BookingSuccessActivity.class);
    }
}
