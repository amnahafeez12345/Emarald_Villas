package com.example.emrald_villas.controllers;

import android.content.Context;
import com.example.emrald_villas.data.DBHelper;
import com.example.emrald_villas.models.User;

public class AuthController {

    private DBHelper db;

    public AuthController(Context ctx) {
        db = new DBHelper(ctx);
    }

    public long register(User user) {
        if (!user.isValidForSignup()) return -1;
        user.setPasswordHash(simpleHash(user.getPasswordHash()));
        return db.insertUser(user);
    }

    public User login(String email, String password) {
        User u = db.findUserByEmail(email);
        if (u == null) return null;

        String hashed = simpleHash(password);
        return hashed.equals(u.getPasswordHash()) ? u : null;
    }

    private String simpleHash(String input) {
        return new StringBuilder(input).reverse().toString() + input.length();
    }
}