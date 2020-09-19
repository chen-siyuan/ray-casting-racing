import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Set;

public class MapPanel extends JPanel {

    private Set<Edge> map;
    private Observer observer;

    // TODO: 9/18/20 add in different options like visible
    // TODO: 9/19/20 add zoom 

    public MapPanel() {
        setPreferredSize(new Dimension(Main.PANEL_WIDTH, Main.PANEL_HEIGHT));
        setBackground(new Color(224, 194, 163));
    }

    public void setMap(Set<Edge> _map) {
        map = _map;
    }

    public void setObserver(Observer _observer) {
        observer = _observer;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawObserver(g2);
        drawMap(g2);
    }

    private void drawObserver(Graphics2D g2) {

        Angle span = observer.getSpan();

        g2.setStroke(new BasicStroke(3));
        g2.drawLine((int)Math.round(0.5 * Main.PANEL_WIDTH), (int)Math.round(0.5 * Main.PANEL_HEIGHT),
                (int)Math.round(0.5 * Main.PANEL_WIDTH),
                (int)Math.round(0.5 * Main.PANEL_HEIGHT - span.scale(0.5).getValue() * Main.MAP_OBSERVER_LENGTH));
        g2.drawLine((int)Math.round(0.5 * Main.PANEL_WIDTH), (int)Math.round(0.5 * Main.PANEL_HEIGHT),
                (int)Math.round(0.5 * Main.PANEL_WIDTH - span.scale(0.5).sin() * Main.MAP_OBSERVER_LENGTH),
                (int)Math.round(0.5 * Main.PANEL_HEIGHT - span.scale(0.5).cos() * Main.MAP_OBSERVER_LENGTH));
        g2.drawLine((int)Math.round(0.5 * Main.PANEL_WIDTH), (int)Math.round(0.5 * Main.PANEL_HEIGHT),
                (int)Math.round(0.5 * Main.PANEL_WIDTH + span.scale(0.5).sin() * Main.MAP_OBSERVER_LENGTH),
                (int)Math.round(0.5 * Main.PANEL_HEIGHT - span.scale(0.5).cos() * Main.MAP_OBSERVER_LENGTH));

    }

    private void drawMap(Graphics2D g2) {

        AffineTransform at = g2.getTransform();

        Point position = observer.getPosition();
        Angle direction = observer.getDirection();

        g2.rotate(Math.PI * (270. / 180.), (int)Math.round(0.5 * Main.PANEL_WIDTH), (int)Math.round(0.5 * Main.PANEL_HEIGHT));

        for(Edge edge: map) {
            Point left = Point.coordinateTransform(position, direction, edge.getLeft());
            Point right = Point.coordinateTransform(position, direction, edge.getRight());
            g2.drawLine((int)Math.round(0.5 * Main.PANEL_WIDTH + left.getX() * Main.MAP_MAP_SCALE),
                    (int)Math.round(0.5 * Main.PANEL_HEIGHT + left.getY() * Main.MAP_MAP_SCALE),
                    (int)Math.round(0.5 * Main.PANEL_WIDTH + right.getX() * Main.MAP_MAP_SCALE),
                    (int)Math.round(0.5 * Main.PANEL_HEIGHT + right.getY() * Main.MAP_MAP_SCALE));
        }

        g2.setTransform(at);

    }

}
