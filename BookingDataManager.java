package airline.utils;

import java.util.HashMap;

public class BookingDataManager {
    
    // Booking class to store ALL passenger information
    public static class Booking {
        public String bookingId;
        public String flightNo;
        public String from;
        public String to;
        public String passengerName;
        public String seatNo;
        public String passengerClass;
        public String status;
        public String bookingDate;
        public double price;
        public String email;
        public String phone;
        
        // Constructor with ALL fields
        public Booking(String bookingId, String flightNo, String from, String to,
                      String passengerName, String seatNo, String passengerClass,
                      String status, String bookingDate, double price,
                      String email, String phone) {
            this.bookingId = bookingId;
            this.flightNo = flightNo;
            this.from = from;
            this.to = to;
            this.passengerName = passengerName;
            this.seatNo = seatNo;
            this.passengerClass = passengerClass;
            this.status = status;
            this.bookingDate = bookingDate;
            this.price = price;
            this.email = email;
            this.phone = phone;
        }
    }
    
    // Database to store all bookings
    private static HashMap<String, Booking> bookingDatabase = new HashMap<>();
    
    // Save a new booking
    public static void saveBooking(Booking booking) {
        bookingDatabase.put(booking.bookingId, booking);
        System.out.println("Saved: " + booking.bookingId + " for " + booking.passengerName);
    }
    
    // Get booking by ID
    public static Booking getBooking(String bookingId) {
        return bookingDatabase.get(bookingId);
    }
    
    // Cancel a booking
    public static void cancelBooking(String bookingId) {
        Booking booking = bookingDatabase.get(bookingId);
        if (booking != null) {
            booking.status = "Cancelled";
            bookingDatabase.put(bookingId, booking);
        }
    }
    
    // Get all bookings
    public static HashMap<String, Booking> getAllBookings() {
        return bookingDatabase;
    }
}
