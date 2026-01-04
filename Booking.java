package com.example.emrald_villas.models;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Booking {

    private long id;
    private long userId;
    private long roomId;

    private LocalDate checkIn;
    private LocalDate checkOut;

    private int nights;
    private double totalPrice;
    private String contactNumber;

    public Booking() {}

    // ---------------- GETTERS & SETTERS ----------------

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
        calculateNights();
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
        calculateNights();
    }

    public int getNights() {
        return nights;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    // ---------------- BUSINESS LOGIC ----------------

    // ✅ CALLED FROM BookingController
    public void calculateTotals(Room room) {
        calculateNights();

        if (room != null) {
            totalPrice = nights * room.getPricePerNight();
        } else {
            totalPrice = 0;
        }
    }

    // ---------------- HELPER ----------------

    private void calculateNights() {
        if (checkIn != null && checkOut != null) {
            nights = (int) ChronoUnit.DAYS.between(checkIn, checkOut);
            if (nights <= 0) nights = 1; // minimum 1 night
        } else {
            nights = 1;
        }
    }
}
