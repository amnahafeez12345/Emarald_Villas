package com.example.emrald_villas.ui;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.emrald_villas.R;
import com.example.emrald_villas.data.DBHelper;
import com.example.emrald_villas.data.RoomRepository;
import com.example.emrald_villas.models.Room;
import com.example.emrald_villas.utils.Navigator;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private Spinner spinnerRoomType;
    private Button btnSearch;
    private RoomRepository roomRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        spinnerRoomType = findViewById(R.id.spinner_room_type);
        btnSearch = findViewById(R.id.btn_search_rooms);

        roomRepo = new RoomRepository(this);

        // Populate spinner with room types from DB
        List<Room> rooms = roomRepo.getAllRooms();
        List<String> roomTypes = new ArrayList<>();
        for (Room r : rooms) {
            if (!roomTypes.contains(r.getTitle())) {
                roomTypes.add(r.getTitle());
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, roomTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRoomType.setAdapter(adapter);

        btnSearch.setOnClickListener(v -> {
            String selectedType = spinnerRoomType.getSelectedItem().toString();

            List<Room> filteredRooms = new ArrayList<>();
            for (Room r : rooms) {
                if (r.getTitle().equalsIgnoreCase(selectedType) && r.isAvailable()) {
                    filteredRooms.add(r);
                }
            }

            if (filteredRooms.isEmpty()) {
                Toast.makeText(this, "No available rooms found!", Toast.LENGTH_SHORT).show();
            } else {
                // Pass filtered rooms to ResultActivity via singleton / static list for simplicity
                ResultActivity.setRoomList(filteredRooms);
                Navigator.goTo(this, ResultActivity.class);
            }
        });
    }
}