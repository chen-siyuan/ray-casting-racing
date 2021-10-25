import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
  public ControlPanel(int[] color) {
    setPreferredSize(new Dimension(Main.PANEL_WIDTH, Main.PANEL_HEIGHT));
    setBackground(new Color(color[0], color[1], color[2]));
  }
}
