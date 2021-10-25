import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.*;

public class VisionPanel extends JPanel {

  private double focal;
  private double[] heights;

  public VisionPanel(int[] color) {
    setPreferredSize(new Dimension(Main.PANEL_WIDTH * 2, Main.PANEL_HEIGHT));
    setBackground(new Color(color[0], color[1], color[2]));
    focal = 1.;
  }

  public void setFocal(double ratio) {
    focal *= ratio;
  }

  private double[] transform(double[] distances) {
    return Arrays.stream(distances).map(distance -> Main.PANEL_HEIGHT * focal / (distance + focal)).toArray();
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

    for(double height: heights) {
      AffineTransform at = g2.getTransform();
      g2.setColor(calcColor(height));
      g2.fillRect((int)Math.round(x += width), (int)Math.round(0.5 * Main.PANEL_HEIGHT - 0.5 * height), (int)Math.round(width), (int)Math.round(height));
      g2.setTransform(at);
    }
  }

  private Color calcColor(double height) {
    Color from  = new Color(255, 0, 0);
    Color to = new Color(51, 0, 204);
    // TODO: 10/11/20 try sigmoid or sth else here
    return weightedAverage(from, to, height, Main.PANEL_HEIGHT - height);
  }

  private Color weightedAverage(Color from, Color to, double w1, double w2) {
    return new Color(
        weightedAverage(from.getRed(), to.getRed(), w1, w2),
        weightedAverage(from.getGreen(), to.getGreen(), w1, w2),
        weightedAverage(from.getBlue(), to.getBlue(), w1, w2));
  }

  private int weightedAverage(int from, int to, double w1, double w2) {
    return (int)Math.round((from * w1 + to * w2) / (w1 + w2));
  }

}
