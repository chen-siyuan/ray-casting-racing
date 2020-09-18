import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    private final MapPanel mapPanel;
    private final ViewPanel viewPanel;

    public Frame() {
        mapPanel = new MapPanel();
        viewPanel = new ViewPanel();
    }

    public void initUI() {

        setTitle("ray-tracing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;

        gbc.gridy = 0;

        gbc.gridx = 0;
        add(mapPanel);
        gbc.gridx = 1;
        add(viewPanel);

        pack();

    }

}
