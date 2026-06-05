package airline.ui;

import airline.utils.BookingDataManager;
import airline.utils.BookingDataManager.Booking;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;

public class PassengerPanel extends JPanel {
    private MainFrame parent;
    private JTable passengerTable;
    private DefaultTableModel tableModel;
    
    public PassengerPanel(MainFrame parent) {
        this.parent = parent;
        setLayout(new BorderLayout());
        setBackground(new Color(240, 242, 245));
        
        JLabel title = new JLabel("👤 Passenger List");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        add(title, BorderLayout.NORTH);
        
        String[] columns = {"Booking ID", "Passenger Name", "Flight", "Route", "Seat", "Class", "Status"};
        tableModel = new DefaultTableModel(columns, 0);
        passengerTable = new JTable(tableModel);
        passengerTable.setRowHeight(25);
        
        JScrollPane scrollPane = new JScrollPane(passengerTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(scrollPane, BorderLayout.CENTER);
        
        JButton refreshButton = new JButton("🔄 Refresh");
        refreshButton.addActionListener(e -> loadPassengerData());
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(refreshButton);
        add(buttonPanel, BorderLayout.SOUTH);
        
        loadPassengerData();
        
        Timer timer = new Timer(5000, e -> loadPassengerData());
        timer.start();
    }
    
    private void loadPassengerData() {
        tableModel.setRowCount(0);
        HashMap<String, Booking> bookings = BookingDataManager.getAllBookings();
        
        for (Booking booking : bookings.values()) {
            tableModel.addRow(new Object[]{
                booking.bookingId,
                booking.passengerName,
                booking.flightNo,
                booking.from + " → " + booking.to,
                booking.seatNo,
                booking.passengerClass,
                booking.status
            });
        }
    }
}
