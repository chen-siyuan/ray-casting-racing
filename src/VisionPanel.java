import javax.swing.*;
import java.awt.*;
import java.util.*;

public class VisionPanel extends JPanel {

    private double focal;
    private double[] heights;

    public VisionPanel() {
        setPreferredSize(new Dimension(Main.PANEL_WIDTH * 2, Main.PANEL_HEIGHT));
        setBackground(new Color(53, 69, 53));
        focal = 1.;
    }

    public void setFocal(double ratio) {
        focal *= ratio;
    }

    private double[] transform(double[] distances) {
        return Arrays.stream(distances).map(distance -> Main.VIEW_MAX * focal / (distance + focal)).toArray();
    }

    public void setHeightsFromDistances(double[] distances) {
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

    // TODO: 10/3/20 color based on distance? 

}
