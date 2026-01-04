package com.example.emrald_villas.controllers;

import android.content.Context;
import com.example.emrald_villas.models.Payment;
import com.example.emrald_villas.models.Booking;

public class PaymentController {
    private Context context;

    public PaymentController(Context ctx) { this.context = ctx; }

    public Payment processOnlinePayment(Booking booking, String cardHolder, String cardNumber) {
        Payment p = new Payment();
        p.setBookingId(booking.getId());
        p.setMethod(Payment.Method.ONLINE);
        p.setCardHolder(cardHolder);

        if (p.isCardValid(cardNumber)) {
            p.setSuccessful(true);
            if (cardNumber.length() >= 4) {
                p.setCardLast4(cardNumber.substring(cardNumber.length() - 4));
            }
        } else {
            p.setSuccessful(false);
        }
        return p;
    }
}
