package com.example.emrald_villas.utils;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.emrald_villas.R;
import com.example.emrald_villas.models.Room;
import com.example.emrald_villas.ui.BookingActivity;

import java.util.List;

public class roomCardInflater {

    public interface OnRoomSelectedListener {
        void onRoomSelected(Room room);
    }

    /**
     * Dynamically inflates room cards into a container.
     * Each card shows image, title, details, and has a clickable button.
     */
    public static void populateRooms(Activity activity, LinearLayout container, List<Room> rooms, OnRoomSelectedListener listener) {
        container.removeAllViews();
        LayoutInflater inflater = activity.getLayoutInflater();

        for (final Room r : rooms) {
            // Inflate the room card layout
            View item = inflater.inflate(R.layout.item_room_card, container, false);

            // Find views
            TextView title = item.findViewById(R.id.room_title);
            TextView subtitle = item.findViewById(R.id.room_sub);
            TextView details = item.findViewById(R.id.room_details);
            ImageView img = item.findViewById(R.id.room_image);
            Button btn = item.findViewById(R.id.btn_select_room);

            // Set room data
            title.setText(r.getTitle());
            subtitle.setText("Available Today");
            details.setText(r.getSummary() + " • PKR " + (int) r.getPricePerNight() + "/night");

            // Set image dynamically by resource name
            int resId = activity.getResources().getIdentifier(r.getImageResourceName(), "drawable", activity.getPackageName());
            if (resId != 0) {
                img.setImageResource(resId);
            }

            // Make the whole card clickable too (not just button)
            item.setOnClickListener(v -> {
                // Navigate to BookingActivity
                Intent intent = new Intent(activity, BookingActivity.class);
                intent.putExtra("roomId", r.getId()); // send room ID to booking page
                activity.startActivity(intent);

                // Callback if needed
                if (listener != null) listener.onRoomSelected(r);
            });

            // Button click also does the same
            btn.setOnClickListener(v -> {
                Intent intent = new Intent(activity, BookingActivity.class);
                intent.putExtra("roomId", r.getId());
                activity.startActivity(intent);

                if (listener != null) listener.onRoomSelected(r);
            });

            // Add the card to container
            container.addView(item);
        }
    }
}