package airline.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SearchPanel extends JPanel {
    private MainFrame parent;
    private JTable flightsTable;
    
    public SearchPanel(MainFrame parent) {
        this.parent = parent;
        setLayout(new BorderLayout());
        setBackground(new Color(240, 242, 245));
        
        JLabel title = new JLabel("Search Flights");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        add(title, BorderLayout.NORTH);
        
        String[] columns = {"Flight No", "From", "To", "Departure", "Price", "Seats"};
        Object[][] data = {
            {"SK101", "New York",  "London",    "10:00 AM", "$450", "120"},
            {"SK102", "New York",  "Paris",     "11:30 AM", "$480", "95"},
            {"SK103", "London",    "Paris",     "02:00 PM", "$200", "85"},
            {"SK104", "London",    "Dubai",     "04:00 PM", "$550", "110"},
            {"SK105", "Paris",     "Dubai",     "06:00 PM", "$520", "70"},
            {"SK106", "Paris",     "Tokyo",     "08:00 PM", "$680", "60"},
            {"SK107", "Dubai",     "Tokyo",     "09:00 PM", "$650", "55"},
            {"SK108", "Dubai",     "Sydney",    "10:00 PM", "$720", "45"},
            {"SK109", "Tokyo",     "Sydney",    "11:00 PM", "$580", "80"},
            {"SK110", "Tokyo",     "New York",  "12:00 AM", "$750", "50"},
            {"SK111", "London",    "Edinburgh", "08:00 AM", "$150", "60"},
            {"SK112", "Edinburgh", "London",    "10:00 AM", "$150", "55"},
            {"SK113", "Dubai",     "Pakistan",  "02:00 PM", "$400", "100"},
            {"SK114", "Pakistan",  "Dubai",     "08:00 PM", "$400", "95"},
            {"SK115", "London",    "Pakistan",  "09:00 PM", "$550", "85"},
            {"SK116", "Pakistan",  "London",    "10:00 PM", "$550", "80"},
            {"SK117", "New York",  "Edinburgh", "07:00 PM", "$620", "70"},
            {"SK118", "Edinburgh", "Paris",     "11:00 AM", "$280", "65"},
            {"SK119", "Pakistan",  "Tokyo",     "03:00 AM", "$700", "50"},
            {"SK120", "Tokyo",     "Pakistan",  "01:00 PM", "$700", "45"},
        };
        
        DefaultTableModel model = new DefaultTableModel(data, columns);
        flightsTable = new JTable(model);
        flightsTable.setRowHeight(30);
        flightsTable.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        flightsTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        flightsTable.getTableHeader().setBackground(new Color(33, 45, 85));
        flightsTable.getTableHeader().setForeground(Color.WHITE);
        
        flightsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int row = flightsTable.getSelectedRow();
                    if (row >= 0) {
                        String flightNo = (String) flightsTable.getValueAt(row, 0);
                        String from     = (String) flightsTable.getValueAt(row, 1);
                        String to       = (String) flightsTable.getValueAt(row, 2);
                        
                        int confirm = JOptionPane.showConfirmDialog(SearchPanel.this,
                            "Book flight " + flightNo + " from " + from + " to " + to + "?",
                            "Confirm Booking", JOptionPane.YES_NO_OPTION);
                        
                        if (confirm == JOptionPane.YES_OPTION) parent.showBookingFor(flightNo);
                    }
                }
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(flightsTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(scrollPane, BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(240, 242, 245));
        
        JButton bookButton = new JButton("Book Selected Flight");
        bookButton.setBackground(new Color(0, 150, 200));
        bookButton.setForeground(Color.WHITE);
        bookButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        bookButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bookButton.addActionListener(e -> bookSelectedFlight());
        buttonPanel.add(bookButton);
        
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void bookSelectedFlight() {
        int row = flightsTable.getSelectedRow();
        if (row >= 0) {
            String flightNo = (String) flightsTable.getValueAt(row, 0);
            String from     = (String) flightsTable.getValueAt(row, 1);
            String to       = (String) flightsTable.getValueAt(row, 2);
            
            int confirm = JOptionPane.showConfirmDialog(this,
                "Book flight " + flightNo + " from " + from + " to " + to + "?",
                "Confirm Booking", JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) parent.showBookingFor(flightNo);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a flight first");
        }
    }
}
