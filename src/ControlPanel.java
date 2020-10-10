import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {

    public ControlPanel(int[] color) {
        setPreferredSize(new Dimension(Main.PANEL_WIDTH, Main.PANEL_HEIGHT));
        setBackground(new Color(color[0], color[1], color[2]));
    }

    // TODO: 10/3/20 add wheel and speed animation
    // TODO: 10/3/20 add focus, map size, zoom info

}
