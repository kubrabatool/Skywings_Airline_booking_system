package airline.ui;

import airline.utils.BookingDataManager;
import airline.utils.BookingDataManager.Booking;
import javax.swing.*;
import java.awt.*;

public class CancelPanel extends JPanel {
    private MainFrame parent;
    private JTextField bookingIdField;
    private JTextArea displayArea;
    
    public CancelPanel(MainFrame parent) {
        this.parent = parent;
        setLayout(new BorderLayout());
        setBackground(new Color(240, 242, 245));
        
        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(Color.WHITE);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        inputPanel.add(new JLabel("Booking ID:"));
        bookingIdField = new JTextField(15);
        inputPanel.add(bookingIdField);
        
        JButton viewButton = new JButton("View Booking");
        viewButton.addActionListener(e -> viewBooking());
        inputPanel.add(viewButton);
        
        JButton cancelButton = new JButton("Cancel Booking");
        cancelButton.addActionListener(e -> cancelBooking());
        inputPanel.add(cancelButton);
        
        add(inputPanel, BorderLayout.NORTH);
        
        displayArea = new JTextArea(15, 50);
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        add(scrollPane, BorderLayout.CENTER);
    }
    
    private void viewBooking() {
        String bookingId = bookingIdField.getText().toUpperCase();
        
        if (bookingId.equals("")) { JOptionPane.showMessageDialog(this, "Please enter Booking ID!"); return; }
        
        Booking booking = BookingDataManager.getBooking(bookingId);
        
        if (booking == null) {
            displayArea.setText("❌ BOOKING NOT FOUND!\n\nNo booking found with ID: " + bookingId);
            JOptionPane.showMessageDialog(this, "Booking ID not found!");
            return;
        }
        
        String statusSymbol = booking.status.equals("Confirmed") ? "✓" : "✗";
        
        displayArea.setText(
            "╔══════════════════════════════════════════════════════════╗\n" +
            "║                 📋 BOOKING DETAILS                       ║\n" +
            "╠══════════════════════════════════════════════════════════╣\n" +
            "║                                                          ║\n" +
            "║   Booking ID:     " + booking.bookingId + "\n" +
            "║                                                          ║\n" +
            "║   ✈ FLIGHT INFORMATION                                   ║\n" +
            "║   ───────────────────────────────────────────────────────║\n" +
            "║   Flight Number:  " + booking.flightNo + "\n" +
            "║   Route:          " + booking.from + " → " + booking.to + "\n" +
            "║                                                          ║\n" +
            "║   👤 PASSENGER INFORMATION                               ║\n" +
            "║   ───────────────────────────────────────────────────────║\n" +
            "║   Passenger Name: " + booking.passengerName + "\n" +
            "║   Seat Number:    " + booking.seatNo + "\n" +
            "║   Class:          " + booking.passengerClass + "\n" +
            "║   Email:          " + booking.email + "\n" +
            "║   Phone:          " + booking.phone + "\n" +
            "║                                                          ║\n" +
            "║   📅 BOOKING INFORMATION                                 ║\n" +
            "║   ───────────────────────────────────────────────────────║\n" +
            "║   Booking Date:   " + booking.bookingDate + "\n" +
            "║   Status:         " + statusSymbol + " " + booking.status + "\n" +
            "║   Total Paid:     $" + String.format("%.2f", booking.price) + "\n" +
            "║                                                          ║\n" +
            "╚══════════════════════════════════════════════════════════╝"
        );
    }
    
    private void cancelBooking() {
        String bookingId = bookingIdField.getText().toUpperCase();
        
        if (bookingId.equals("")) { JOptionPane.showMessageDialog(this, "Please enter Booking ID!"); return; }
        
        Booking booking = BookingDataManager.getBooking(bookingId);
        
        if (booking == null) { JOptionPane.showMessageDialog(this, "Booking ID not found!"); return; }
        
        int confirm = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to cancel booking for:\n\n" +
            "Passenger: " + booking.passengerName + "\n" +
            "Flight: " + booking.flightNo + " (" + booking.from + " → " + booking.to + ")\n" +
            "Seat: " + booking.seatNo + "\n\n" +
            "This action cannot be undone!",
            "Confirm Cancellation", JOptionPane.YES_NO_OPTION);
            
        if (confirm == JOptionPane.YES_OPTION) {
            BookingDataManager.cancelBooking(bookingId);
            JOptionPane.showMessageDialog(this,
                "✅ Booking cancelled successfully!\n\n" +
                "Passenger: " + booking.passengerName + "\n" +
                "Flight: " + booking.flightNo);
            bookingIdField.setText("");
            displayArea.setText("");
        }
    }
}
