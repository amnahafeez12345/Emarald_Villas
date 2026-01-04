package com.example.emrald_villas.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.emrald_villas.models.Booking;
import com.example.emrald_villas.models.Room;
import com.example.emrald_villas.models.User;
import com.example.emrald_villas.models.Payment;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "emerald_villas.db";
    private static final int DB_VERSION = 3;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "fullName TEXT," +
                "email TEXT UNIQUE," +
                "passwordHash TEXT)");

        db.execSQL("CREATE TABLE rooms (" +
                "id INTEGER PRIMARY KEY," +
                "title TEXT," +
                "description TEXT," +
                "beds INTEGER," +
                "baths INTEGER," +
                "pricePerNight REAL," +
                "imageResourceName TEXT," +
                "available INTEGER)");

        db.execSQL("CREATE TABLE bookings (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "roomId INTEGER," +
                "userId INTEGER," +
                "checkIn TEXT," +
                "checkOut TEXT," +
                "nights INTEGER," +
                "totalPrice REAL," +
                "contactNumber TEXT)");

        // ✅ PAYMENT TABLE
        db.execSQL("CREATE TABLE payments (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "bookingId INTEGER," +
                "method TEXT," +
                "cardHolder TEXT," +
                "cardLast4 TEXT," +
                "successful INTEGER)");

        seedUsers(db);
        seedRooms(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 3) {
            db.execSQL("CREATE TABLE payments (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "bookingId INTEGER," +
                    "method TEXT," +
                    "cardHolder TEXT," +
                    "cardLast4 TEXT," +
                    "successful INTEGER)");
        }
    }

    public long insertUser(User user) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("fullName", user.getFullName());
        cv.put("email", user.getEmail());
        cv.put("passwordHash", user.getPasswordHash());
        return db.insert("users", null, cv);
    }
    public User findUserByEmail(String email) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM users WHERE email=?", new String[]{email});
        if (c.moveToFirst()) {
            User u = new User();
            u.setId(c.getLong(0));
            u.setFullName(c.getString(1));
            u.setEmail(c.getString(2));
            u.setPasswordHash(c.getString(3));
            c.close();
            return u;
        }
        c.close();
        return null;
    }

    // ---------------- ROOMS ----------------
    public List<Room> getAllRooms() {
        List<Room> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM rooms", null);
        while (c.moveToNext()) {
            Room r = new Room();
            r.setId(c.getLong(0));
            r.setTitle(c.getString(1));
            r.setDescription(c.getString(2));
            r.setBeds(c.getInt(3));
            r.setBaths(c.getInt(4));
            r.setPricePerNight(c.getDouble(5));
            r.setImageResourceName(c.getString(6));
            r.setAvailable(c.getInt(7) == 1);
            list.add(r);
        }
        c.close();
        return list;
    }

    // ---------------- BOOKINGS ----------------
    public long insertBooking(Booking booking) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("roomId", booking.getRoomId());
        cv.put("userId", booking.getUserId());
        cv.put("checkIn", booking.getCheckIn().toString());
        cv.put("checkOut", booking.getCheckOut().toString());
        cv.put("nights", booking.getNights());
        cv.put("totalPrice", booking.getTotalPrice());
        cv.put("contactNumber", booking.getContactNumber());
        return db.insert("bookings", null, cv);
    }

    // ---------------- PAYMENTS ----------------
    public long insertPayment(Payment p) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("bookingId", p.getBookingId());
        cv.put("method", p.getMethod().name());
        cv.put("cardHolder", p.getCardHolder());
        cv.put("cardLast4", p.getCardLast4());
        cv.put("successful", p.isSuccessful() ? 1 : 0);
        return db.insert("payments", null, cv);
    }

    // ---------------- DUMMY DATA ----------------
    private void seedUsers(SQLiteDatabase db) {
        insertUserRaw(db, "Amna Hafeez", "amna@gmail.com", "12345");
        insertUserRaw(db, "Ali Khan", "ali@gmail.com", "12345");
    }

    private void insertUserRaw(SQLiteDatabase db, String name, String email, String pass) {
        ContentValues cv = new ContentValues();
        cv.put("fullName", name);
        cv.put("email", email);
        cv.put("passwordHash", pass);
        db.insert("users", null, cv);
    }

    private void seedRooms(SQLiteDatabase db) {
        insertRoom(db, 1, "Deluxe Room", "City View", 1, 1, 6000, "room1", 1);
    }

    private void insertRoom(SQLiteDatabase db, int id, String title, String desc,
                            int beds, int baths, double price, String img, int avail) {
        ContentValues cv = new ContentValues();
        cv.put("id", id);
        cv.put("title", title);
        cv.put("description", desc);
        cv.put("beds", beds);
        cv.put("baths", baths);
        cv.put("pricePerNight", price);
        cv.put("imageResourceName", img);
        cv.put("available", avail);
        db.insert("rooms", null, cv);
    }
}
