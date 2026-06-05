package airline.ui;

import airline.utils.BookingDataManager;
import airline.utils.BookingDataManager.Booking;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class BookingPanel extends JPanel {
    private MainFrame parent;
    private JTextField flightNumberField, passengerNameField, seatField;
    private JComboBox<String> classCombo;
    private JTextField emailField, phoneField;
    
    // Flight data
    private String[][] flights = {
        {"SK101", "New York", "London", "450"},
        {"SK102", "New York", "Paris", "480"},
        {"SK103", "London", "Paris", "200"},
        {"SK104", "London", "Dubai", "550"},
        {"SK105", "Paris", "Dubai", "520"},
        {"SK106", "Paris", "Tokyo", "680"},
        {"SK107", "Dubai", "Tokyo", "650"},
        {"SK108", "Dubai", "Sydney", "720"},
        {"SK109", "Tokyo", "Sydney", "580"},
        {"SK110", "Tokyo", "New York", "750"},
        {"SK111", "London", "Edinburgh", "150"},
        {"SK112", "Edinburgh", "London", "150"},
        {"SK113", "Dubai", "Pakistan", "400"},
        {"SK114", "Pakistan", "Dubai", "400"},
        {"SK115", "London", "Pakistan", "550"},
        {"SK116", "Pakistan", "London", "550"},
        {"SK117", "New York", "Edinburgh", "620"},
        {"SK118", "Edinburgh", "Paris", "280"},
        {"SK119", "Pakistan", "Tokyo", "700"},
        {"SK120", "Tokyo", "Pakistan", "700"},
    };
    
    public BookingPanel(MainFrame parent) {
        this.parent = parent;
        setLayout(new BorderLayout());
        setBackground(new Color(240, 242, 245));
        
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        JLabel title = new JLabel("Book a Flight");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        formPanel.add(title, gbc);
        
        gbc.gridwidth = 1;
        gbc.gridy = 1; gbc.gridx = 0;
        formPanel.add(new JLabel("Flight Number:"), gbc);
        flightNumberField = new JTextField(15);
        gbc.gridx = 1;
        formPanel.add(flightNumberField, gbc);
        
        gbc.gridy = 2; gbc.gridx = 0;
        formPanel.add(new JLabel("Passenger Name:"), gbc);
        passengerNameField = new JTextField(15);
        gbc.gridx = 1;
        formPanel.add(passengerNameField, gbc);
        
        gbc.gridy = 3; gbc.gridx = 0;
        formPanel.add(new JLabel("Email:"), gbc);
        emailField = new JTextField(15);
        gbc.gridx = 1;
        formPanel.add(emailField, gbc);
        
        gbc.gridy = 4; gbc.gridx = 0;
        formPanel.add(new JLabel("Phone:"), gbc);
        phoneField = new JTextField(15);
        gbc.gridx = 1;
        formPanel.add(phoneField, gbc);
        
        gbc.gridy = 5; gbc.gridx = 0;
        formPanel.add(new JLabel("Class:"), gbc);
        classCombo = new JComboBox<>(new String[]{"Economy", "Business", "First Class"});
        gbc.gridx = 1;
        formPanel.add(classCombo, gbc);
        
        gbc.gridy = 6; gbc.gridx = 0;
        formPanel.add(new JLabel("Seat Number:"), gbc);
        seatField = new JTextField(15);
        gbc.gridx = 1;
        formPanel.add(seatField, gbc);
        
        JPanel buttonPanel = new JPanel();
        JButton bookButton = new JButton("Book Flight");
        bookButton.setBackground(new Color(0, 150, 200));
        bookButton.setForeground(Color.WHITE);
        bookButton.addActionListener(e -> bookFlight());
        
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> clearForm());
        
        buttonPanel.add(bookButton);
        buttonPanel.add(clearButton);
        
        gbc.gridy = 7; gbc.gridx = 0; gbc.gridwidth = 2;
        formPanel.add(buttonPanel, gbc);
        
        add(formPanel, BorderLayout.NORTH);
    }
    
    public void setFlight(String flightNumber) {
        flightNumberField.setText(flightNumber);
        String[] seats = {"12A", "12B", "12C", "14A", "14B", "15A"};
        int randomIndex = (int)(Math.random() * seats.length);
        seatField.setText(seats[randomIndex]);
    }
    
    private void bookFlight() {
        String flightNo = flightNumberField.getText().toUpperCase();
        String passengerName = passengerNameField.getText();
        String seatNo = seatField.getText().toUpperCase();
        String passengerClass = (String) classCombo.getSelectedItem();
        String email = emailField.getText();
        String phone = phoneField.getText();
        
        if (flightNo.equals("")) { JOptionPane.showMessageDialog(this, "Please enter Flight Number!"); return; }
        if (passengerName.equals("")) { JOptionPane.showMessageDialog(this, "Please enter Passenger Name!"); return; }
        if (email.equals("") || !email.contains("@")) { JOptionPane.showMessageDialog(this, "Please enter valid Email!"); return; }
        if (phone.equals("") || phone.length() < 10) { JOptionPane.showMessageDialog(this, "Please enter valid Phone Number!"); return; }
        if (seatNo.equals("")) { JOptionPane.showMessageDialog(this, "Please enter Seat Number!"); return; }
        
        String from = "", to = "", priceStr = "";
        for (int i = 0; i < flights.length; i++) {
            if (flights[i][0].equals(flightNo)) {
                from = flights[i][1]; to = flights[i][2]; priceStr = flights[i][3]; break;
            }
        }
        
        if (from.equals("")) { JOptionPane.showMessageDialog(this, "Invalid Flight Number! Use SK101 to SK120"); return; }
        
        double price = Double.parseDouble(priceStr);
        if (passengerClass.equals("Business")) price = price * 2;
        else if (passengerClass.equals("First Class")) price = price * 3;
        
        String bookingId = "BKG" + System.currentTimeMillis();
        String bookingDate = LocalDate.now().toString();
        
        Booking booking = new Booking(bookingId, flightNo, from, to,
                                      passengerName, seatNo, passengerClass,
                                      "Confirmed", bookingDate, price, email, phone);
        BookingDataManager.saveBooking(booking);
        
        JOptionPane.showMessageDialog(this,
            "✅ BOOKING CONFIRMED!\n\n" +
            "Booking ID: " + bookingId + "\n" +
            "Passenger: " + passengerName + "\n" +
            "Flight: " + flightNo + " (" + from + " → " + to + ")\n" +
            "Seat: " + seatNo + "\n" +
            "Class: " + passengerClass + "\n" +
            "Email: " + email + "\n" +
            "Phone: " + phone + "\n" +
            "Total: $" + price + "\n\n" +
            "⚠️ SAVE THIS BOOKING ID TO VIEW LATER!");
        
        clearForm();
    }
    
    private void clearForm() {
        flightNumberField.setText(""); passengerNameField.setText("");
        seatField.setText(""); emailField.setText(""); phoneField.setText("");
        classCombo.setSelectedIndex(0);
    }
}
