package com.example.emrald_villas.controllers;

import android.content.Context;
import com.example.emrald_villas.data.DBHelper;
import com.example.emrald_villas.models.Booking;
import com.example.emrald_villas.models.Room;

public class BookingController {

    private DBHelper dbHelper;

    public BookingController(Context context) {
        dbHelper = new DBHelper(context);
    }

    // Prepare totals
    public void prepareBooking(Booking booking, Room room) {
        booking.calculateTotals(room);
    }

    // Save booking in DB
    public boolean confirmBooking(Booking booking) {
        if (booking != null && booking.getRoomId() > 0 && booking.getNights() > 0) {
            dbHelper.insertBooking(booking);
            return true;
        }
        return false;
    }
}