import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    private final VisionPanel visionPanel;
    private final MapPanel mapPanel;
    private final ControlPanel controlPanel;

    public Frame() {
        visionPanel = new VisionPanel();
        mapPanel = new MapPanel();
        controlPanel = new ControlPanel();
    }

    public VisionPanel getViewPanel() {
        return visionPanel;
    }

    public MapPanel getMapPanel() {
        return mapPanel;
    }

    public ControlPanel getControlPanel() {
        return controlPanel;
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

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(visionPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(mapPanel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(controlPanel, gbc);

        pack();
        setLocationRelativeTo(null);

    }

}
