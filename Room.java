package com.example.emrald_villas.models;

public class Room {

    private long id;
    private String title;
    private String description;
    private int beds;
    private int baths;
    private double pricePerNight;
    private String imageResourceName;
    private boolean available;

    public Room() {}

    public Room(long id, String title, String description,
                int beds, int baths,
                double pricePerNight,
                String imageResourceName,
                boolean available) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.beds = beds;
        this.baths = baths;
        this.pricePerNight = pricePerNight;
        this.imageResourceName = imageResourceName;
        this.available = available;
    }

    // ✅ REQUIRED METHOD (ERROR FIX)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getBeds() {
        return beds;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }

    public int getBaths() {
        return baths;
    }

    public void setBaths(int baths) {
        this.baths = baths;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public String getImageResourceName() {
        return imageResourceName;
    }

    public void setImageResourceName(String imageResourceName) {
        this.imageResourceName = imageResourceName;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    // 👌 Helper method (used in UI)
    public String getSummary() {
        return beds + " Bed • " + baths + " Bath • " + description;
    }
}
