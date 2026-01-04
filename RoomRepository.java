package com.example.emrald_villas.data;

import android.content.Context;
import com.example.emrald_villas.models.Room;
import java.util.List;

public class RoomRepository {

    private DBHelper db;

    public RoomRepository(Context context) {
        db = new DBHelper(context);
    }

    public List<Room> getAllRooms() {
        return db.getAllRooms();
    }
}