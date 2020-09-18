import javax.swing.*;
import java.awt.*;
import java.util.*;

public class ViewPanel extends JPanel {

    public static double[] transform(double[] distances) {
        return Arrays.stream(distances).map(distance -> Main.VIEW_MAX * Main.VIEW_FOCAL / (distance + Main.VIEW_FOCAL)).toArray();
    }

    private double[] heights;

    public ViewPanel() {
        setPreferredSize(new Dimension(Main.PANEL_WIDTH, Main.PANEL_HEIGHT));
        setBackground(new Color(53, 69, 53));
    }

    public void setHeights(double[] distances) {
        heights = transform(distances);
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        drawBars(g2);

    }

    private void drawBars(Graphics2D g2) {

        g2.fillRect(1, 2, 3, 4);

        double width = 1. * Main.PANEL_WIDTH / heights.length;
        double x = -width;

        for(double height: heights)
            g2.fillRect((int)Math.round(x += width), (Main.PANEL_HEIGHT - (int)Math.round(height)) / 2, (int)Math.round(width), (int)Math.round(height));

    }

    // TODO: 9/17/20 Edit drawbars(), looks kinda ugly.

}
