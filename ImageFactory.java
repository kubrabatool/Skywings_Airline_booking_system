package airline.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageFactory {

    public static ImageIcon makeAirplaneLogo(int size) {
        BufferedImage img = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(new Color(255, 255, 255));
        int[] xPoints = {size/2, size-5, size-8, size-10, size/2, 10, 8, 5};
        int[] yPoints = {5, size/3, size/3, size/2, size-5, size/2, size/3, size/3};
        g2d.fillPolygon(xPoints, yPoints, 8);

        g2d.dispose();
        return new ImageIcon(img);
    }

    public static ImageIcon makeSeatIcon(Color color, int size) {
        BufferedImage img = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(color);
        g2d.fillRect(2, 2, size-4, size-4);
        g2d.dispose();
        return new ImageIcon(img);
    }

    public static ImageIcon makeSideIcon(Color color, String type, int size) {
        BufferedImage img = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(color);
        g2d.setFont(new Font("Segoe UI", Font.PLAIN, size-4));

        String symbol = "•";
        switch(type) {
            case "home": symbol = "🏠"; break;
            case "plane": symbol = "✈"; break;
            case "search": symbol = "🔍"; break;
            case "person": symbol = "👤"; break;
            case "ticket": symbol = "📋"; break;
            case "cancel": symbol = "✖"; break;
        }

        g2d.drawString(symbol, 2, size-4);
        g2d.dispose();
        return new ImageIcon(img);
    }
}
