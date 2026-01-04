package com.example.emrald_villas.models;

public class Payment {
    public enum Method { CASH, ONLINE }
    private long id;
    private long bookingId;
    private Method method;
    private boolean successful;
    private String cardHolder;
    private String cardLast4;

    public Payment() {}

    // Simple verification expert
    public boolean isCardValid(String cardNumber) {
        if (cardNumber == null) return false;
        String trimmed = cardNumber.replaceAll("\\s+",""); // basic check
        return trimmed.length() >= 12 && trimmed.length() <= 19;
    }

    // getters & setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public long getBookingId() { return bookingId; }
    public void setBookingId(long bookingId) { this.bookingId = bookingId; }
    public Method getMethod() { return method; }
    public void setMethod(Method method) { this.method = method; }
    public boolean isSuccessful() { return successful; }
    public void setSuccessful(boolean successful) { this.successful = successful; }
    public String getCardHolder() { return cardHolder; }
    public void setCardHolder(String cardHolder) { this.cardHolder = cardHolder; }
    public String getCardLast4() { return cardLast4; }
    public void setCardLast4(String cardLast4) { this.cardLast4 = cardLast4; }
}