package airline.ui;

import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel {
    private MainFrame parent;
    
    public DashboardPanel(MainFrame parent) {
        this.parent = parent;
        setLayout(new BorderLayout());
        setBackground(new Color(240, 242, 245));
        
        JPanel welcomePanel = new JPanel();
        welcomePanel.setBackground(Color.WHITE);
        welcomePanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        JLabel welcomeLabel = new JLabel("Welcome to Sky Wings Airline Booking System");
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        welcomePanel.add(welcomeLabel);
        
        add(welcomePanel, BorderLayout.NORTH);
        
        JPanel statsPanel = new JPanel(new GridLayout(2, 3, 20, 20));
        statsPanel.setBackground(new Color(240, 242, 245));
        statsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        statsPanel.add(createStatCard("Total Flights",    "20",    new Color(33, 45, 85)));
        statsPanel.add(createStatCard("Available Seats",  "1,520", new Color(0, 150, 200)));
        statsPanel.add(createStatCard("Today's Bookings", "189",   new Color(46, 204, 113)));
        statsPanel.add(createStatCard("Passengers",       "2,845", new Color(155, 89, 182)));
        statsPanel.add(createStatCard("Cancellations",    "31",    new Color(231, 76, 60)));
        statsPanel.add(createStatCard("Revenue",          "$289K", new Color(241, 196, 15)));
        
        add(statsPanel, BorderLayout.CENTER);
        
        JPanel infoPanel = new JPanel();
        infoPanel.setBackground(new Color(255, 255, 200));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        JLabel infoLabel = new JLabel("✈ New Destinations Added: Edinburgh (UK) & Pakistan ✈");
        infoLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        infoLabel.setForeground(new Color(33, 45, 85));
        infoPanel.add(infoLabel);
        
        add(infoPanel, BorderLayout.SOUTH);
    }
    
    private JPanel createStatCard(String title, String value, Color color) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)));
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        titleLabel.setForeground(Color.GRAY);
        
        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        valueLabel.setForeground(color);
        
        card.add(titleLabel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);
        return card;
    }
}
