package com.example.emrald_villas.controllers;

import android.content.Context;
import com.example.emrald_villas.data.RoomRepository;
import com.example.emrald_villas.models.Room;
import java.util.List;

public class RoomController {

    private RoomRepository repo;

    public RoomController(Context context) {
        repo = new RoomRepository(context);
    }

    public List<Room> getAllRooms() {
        return repo.getAllRooms();
    }

    public Room getRoomById(long roomId) {
        for (Room r : repo.getAllRooms()) {
            if (r.getId() == roomId) {
                return r;
            }
        }
        return null;
    }
}
