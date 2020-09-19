import javax.swing.*;
import java.awt.*;
import java.util.*;

public class VisionPanel extends JPanel {

    public static double[] transform(double[] distances) {
        return Arrays.stream(distances).map(distance -> Main.VIEW_MAX * Main.VIEW_FOCAL / (distance + Main.VIEW_FOCAL)).toArray();
    }

    private double[] heights;

    public VisionPanel() {
        setPreferredSize(new Dimension(Main.PANEL_WIDTH * 2, Main.PANEL_HEIGHT));
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

        if(heights == null) return;

        double width = 2. * Main.PANEL_WIDTH / heights.length;
        double x = -width;

        for(double height: heights)
            g2.fillRect((int)Math.round(x += width), (int)Math.round(0.5 * Main.PANEL_HEIGHT - 0.5 * height), (int)Math.round(width), (int)Math.round(height));

    }

    // TODO: 9/17/20 edit drawbars(), looks kinda ugly

}
