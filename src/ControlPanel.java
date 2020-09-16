import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ControlPanel extends JPanel {

    private final Set<Edge> map;
    private final Observer ob;

    public ControlPanel() {
        setPreferredSize(new Dimension(Main.PANEL_WIDTH, Main.PANEL_HEIGHT));
        setBackground(new Color(224, 194, 163));

        map = new HashSet<>();
        ob = new Observer(Main.OBSERVER_NUMRAYS, Main.OBSERVER_SPAN);
    }

    public void addEdge(Edge edge) {
        map.add(edge);
    }

    public void addEdges(Edge... edges) {
        map.addAll(Arrays.asList(edges));
    }

    public void addEdges(Set<Edge> edges) {
        map.addAll(edges);
    }

    // TODO: 9/16/20 observer control 
    // TODO: 9/16/20 map display 
    // TODO: 9/16/20 map control??? 

}
