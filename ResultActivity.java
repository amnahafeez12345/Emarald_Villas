package com.example.emrald_villas.ui;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.emrald_villas.R;
import com.example.emrald_villas.models.Room;
import com.example.emrald_villas.utils.roomCardInflater;

import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity {

    private LinearLayout container;

    // ✅ Static list to receive data from SearchActivity
    private static List<Room> roomList = new ArrayList<>();

    // ✅ Setter (called from SearchActivity)
    public static void setRoomList(List<Room> rooms) {
        roomList = rooms;
    }

    // ✅ Getter (optional, good practice)
    public static List<Room> getRoomList() {
        return roomList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        container = findViewById(R.id.results_container);

        // Safety check
        if (roomList == null || roomList.isEmpty()) {
            Toast.makeText(this, "No rooms to display", Toast.LENGTH_SHORT).show();
            return;
        }

        // Populate UI cards
        roomCardInflater.populateRooms(
                this,
                container,
                roomList,
                room -> {
                    // Click handling already managed inside inflater
                }
        );
    }
}
