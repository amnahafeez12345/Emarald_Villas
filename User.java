package com.example.emrald_villas.models;

public class User {
    private long id;
    private String fullName;
    private String email;
    private String passwordHash;

    public User() {}

    public User(String fullName, String email, String passwordHash) {
        this.fullName = fullName;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    // Information expert: validate basic user data
    public boolean isValidForSignup() {
        return fullName != null && fullName.length() >= 3
                && email != null && email.contains("@")
                && passwordHash != null && passwordHash.length() >= 6;
    }

    // getters & setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
}