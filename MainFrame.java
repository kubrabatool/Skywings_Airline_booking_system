package airline.ui;

import airline.utils.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

public class MainFrame extends JFrame {

    private CardLayout cardLayout = new CardLayout();
    private JPanel contentArea;
    private Map<String, JButton> navButtons = new LinkedHashMap<>();

    private DashboardPanel dashPanel;
    private BookingPanel   bookPanel;
    private FlightsPanel   flightsPanel;
    private SearchPanel    searchPanel;
    private CancelPanel    cancelPanel;
    private PassengerPanel passengerPanel;

    public MainFrame() {
        super("Sky Wings Airline Booking System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 720);
        setMinimumSize(new Dimension(900, 600));
        setLocationRelativeTo(null);
        setIconImage(ImageFactory.makeAirplaneLogo(32).getImage());
        buildUI();
        setVisible(true);
    }

    private void buildUI() {
        JPanel root = new JPanel(new BorderLayout(0, 0));
        root.setBackground(UIConstants.COLOR_BG);
        setContentPane(root);
        root.add(buildSidebar(), BorderLayout.WEST);
        root.add(buildHeader(),  BorderLayout.NORTH);
        root.add(buildContent(), BorderLayout.CENTER);
    }

    private JPanel buildHeader() {
        JPanel header = new JPanel(null);
        header.setBackground(UIConstants.COLOR_PRIMARY_DARK);
        header.setPreferredSize(new Dimension(0, 52));

        JLabel logo = new JLabel(ImageFactory.makeAirplaneLogo(36));
        logo.setBounds(8, 8, 36, 36);
        header.add(logo);

        JLabel title = new JLabel("SKY WINGS AIRLINE — Booking System");
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setForeground(Color.WHITE);
        title.setBounds(54, 12, 500, 28);
        header.add(title);

        JLabel dateLbl = new JLabel(LocalDate.now()
                .format(DateTimeFormatter.ofPattern("EEEE, MMM d yyyy")));
        dateLbl.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        dateLbl.setForeground(new Color(160, 200, 255));
        dateLbl.setBounds(700, 18, 280, 18);
        header.add(dateLbl);

        for (int i = 0; i < 5; i++) {
            JLabel s = new JLabel(ImageFactory.makeSeatIcon(new Color(255, 255, 255, 80), 16));
            s.setBounds(990 - i * 20, 18, 16, 16);
            header.add(s);
        }
        return header;
    }

    private JPanel buildSidebar() {
        JPanel sidebar = new JPanel(null);
        sidebar.setBackground(UIConstants.COLOR_PRIMARY);
        sidebar.setPreferredSize(new Dimension(190, 0));

        JLabel planeImg = new JLabel(ImageFactory.makeAirplaneLogo(60));
        planeImg.setBounds(65, 18, 60, 60);
        sidebar.add(planeImg);

        JLabel brand = new JLabel("Sky Wings", SwingConstants.CENTER);
        brand.setFont(new Font("Segoe UI", Font.BOLD, 14));
        brand.setForeground(Color.WHITE);
        brand.setBounds(0, 82, 190, 22);
        sidebar.add(brand);

        JSeparator sep = new JSeparator();
        sep.setForeground(new Color(255, 255, 255, 60));
        sep.setBounds(16, 112, 158, 2);
        sidebar.add(sep);

        Object[][] navItems = {
            {"dashboard", "🏠 Dashboard",     "home"},
            {"flights",   "✈ All Flights",    "plane"},
            {"search",    "🔍 Search Flights", "search"},
            {"passenger", "👤 Passengers",    "person"},
            {"book",      "📋 Book Flight",   "ticket"},
            {"cancel",    "✖ Cancel / View",  "cancel"},
        };

        int y = 122;
        for (Object[] item : navItems) {
            String key   = (String) item[0];
            String label = (String) item[1];
            String icon  = (String) item[2];

            JButton btn = makeSidebarBtn(label,
                    ImageFactory.makeSideIcon(new Color(200, 230, 255), icon, 18));
            btn.setBounds(0, y, 190, 46);
            y += 48;
            btn.addActionListener(e -> showPanel(key));
            sidebar.add(btn);
            navButtons.put(key, btn);
        }

        JLabel ver = new JLabel("v2.0 — Sky Wings", SwingConstants.CENTER);
        ver.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        ver.setForeground(new Color(160, 190, 230));
        ver.setBounds(0, 600, 190, 18);
        sidebar.add(ver);

        return sidebar;
    }

    private JButton makeSidebarBtn(String label, ImageIcon icon) {
        JButton btn = new JButton(label) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                boolean active = Boolean.TRUE.equals(getClientProperty("active"));
                Color bg = active ? UIConstants.COLOR_ACCENT
                        : getModel().isRollover() ? new Color(255, 255, 255, 30)
                        : new Color(0, 0, 0, 0);
                if (active || getModel().isRollover()) {
                    g2.setColor(bg);
                    g2.fillRect(0, 0, getWidth(), getHeight());
                }
                if (active) {
                    g2.setColor(UIConstants.COLOR_ACCENT);
                    g2.fillRect(0, 0, 4, getHeight());
                }
                g2.dispose();
                super.paintComponent(g);
            }
            @Override protected void paintBorder(Graphics g) {}
        };
        btn.setIcon(icon);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btn.setForeground(new Color(220, 235, 255));
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setIconTextGap(10);
        btn.setBorder(BorderFactory.createEmptyBorder(0, 16, 0, 0));
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private JPanel buildContent() {
        contentArea = new JPanel(cardLayout);
        contentArea.setBackground(UIConstants.COLOR_BG);

        dashPanel      = new DashboardPanel(this);
        flightsPanel   = new FlightsPanel(this);
        searchPanel    = new SearchPanel(this);
        passengerPanel = new PassengerPanel(this);
        bookPanel      = new BookingPanel(this);
        cancelPanel    = new CancelPanel(this);

        contentArea.add(dashPanel,      "dashboard");
        contentArea.add(flightsPanel,   "flights");
        contentArea.add(searchPanel,    "search");
        contentArea.add(passengerPanel, "passenger");
        contentArea.add(bookPanel,      "book");
        contentArea.add(cancelPanel,    "cancel");

        showPanel("dashboard");
        return contentArea;
    }

    public void showPanel(String key) {
        cardLayout.show(contentArea, key);
        navButtons.forEach((k, btn) -> {
            btn.putClientProperty("active", k.equals(key));
            btn.repaint();
        });
    }

    public void showBookingFor(String flightNumber) {
        bookPanel.setFlight(flightNumber);
        showPanel("book");
    }
}
