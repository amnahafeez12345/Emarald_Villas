package com.example.emrald_villas.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.emrald_villas.R;
import com.example.emrald_villas.controllers.BookingController;
import com.example.emrald_villas.controllers.RoomController;
import com.example.emrald_villas.models.Booking;
import com.example.emrald_villas.models.Room;

import java.time.LocalDate;

public class BookingActivity extends AppCompatActivity {

    private RoomController roomController;
    private BookingController bookingController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        roomController = new RoomController(this);
        bookingController = new BookingController(this);

        TextView tvSelectedRoom = findViewById(R.id.tv_selected_room_type);
        TextView tvPriceSummary = findViewById(R.id.tv_total_price_summary);
        EditText etContact = findViewById(R.id.et_customer_contact);
        Button btnConfirm = findViewById(R.id.btn_confirm_reservation);

        long roomId = getIntent().getLongExtra("room_id", -1);
        Room room = roomController.getRoomById(roomId);

        if (room != null) {
            tvSelectedRoom.setText("Selected Room: " + room.getTitle());
        }

        Booking booking = new Booking();
        booking.setRoomId(roomId);

        // Example check-in/out dates; you can replace with actual user input later
        booking.setCheckIn(LocalDate.now());
        booking.setCheckOut(LocalDate.now().plusDays(2));

        bookingController.prepareBooking(booking, room);

        tvPriceSummary.setText("Total Price (" + booking.getNights() + " Nights): PKR " +
                (int) booking.getTotalPrice());

        btnConfirm.setOnClickListener(v -> {
            booking.setContactNumber(etContact.getText().toString().trim());

            if (bookingController.confirmBooking(booking)) {
                Toast.makeText(this, "Booking Confirmed!", Toast.LENGTH_SHORT).show();

                // Move to PaymentMethodActivity
                android.content.Intent intent = new android.content.Intent(this, PaymentMethodActivity.class);
                intent.putExtra("booking_nights", booking.getNights());
                intent.putExtra("booking_total", booking.getTotalPrice());
                intent.putExtra("room_id", booking.getRoomId());
                startActivity(intent);
            } else {
                Toast.makeText(this, "Booking failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}