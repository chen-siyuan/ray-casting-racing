import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Set;

public class MapPanel extends JPanel {

    private Set<Edge> map;
    private Observer observer;
    private Point cameraPosition;
    private Angle cameraDirection;
    private double scale;
    private boolean mode;

    // TODO: 9/18/20 add in different options like visible
    // TODO: 9/19/20 add zoom 

    public MapPanel() {
        setPreferredSize(new Dimension(Main.PANEL_WIDTH, Main.PANEL_HEIGHT));
        setBackground(new Color(224, 194, 163));
        cameraPosition = new Point(0., 0.);
        cameraDirection = new Angle(Math.PI * (0. / 180.));
        scale = 25.;
        mode = false;
    }

    public void setMap(Set<Edge> _map) {
        map = _map;
    }

    public void setObserver(Observer _observer) {
        observer = _observer;
    }

    public void setZoom(double ratio) {
        scale *= ratio;
    }

    public void toggleMode() {
        mode = !mode;
    }

    public void setCamera() {
        cameraPosition = observer.getPosition();
        cameraDirection = observer.getDirection();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawObserver(g2);
        drawMap(g2);
    }

    private void drawLine(Graphics2D g2, Point left, Point right) {
        g2.drawLine(
                (int) Math.round(0.5 * Main.PANEL_WIDTH + left.getX() * scale), (int) Math.round(0.5 * Main.PANEL_HEIGHT + left.getY() * scale),
                (int) Math.round(0.5 * Main.PANEL_WIDTH + right.getX() * scale), (int) Math.round(0.5 * Main.PANEL_HEIGHT + right.getY() * scale));
    }

    private void drawObserver(Graphics2D g2) {

        AffineTransform at = g2.getTransform();

        g2.rotate(Math.PI * (270. / 180.), (int)Math.round(0.5 * Main.PANEL_WIDTH), (int)Math.round(0.5 * Main.PANEL_HEIGHT));
        g2.setStroke(new BasicStroke(3));

        Angle span = observer.getSpan();
        Point position = mode ? cameraPosition : observer.getPosition();
        Angle direction = mode ? cameraDirection: observer.getDirection();
        Point center = Point.coordinateTransform(position, direction, observer.getPosition());

        drawLine(g2, center, Point.coordinateTransform(position, direction,
                observer.getPosition().forward(observer.getDirection(), Main.MAP_OBSERVER_LENGTH / scale)));
        drawLine(g2, center, Point.coordinateTransform(position, direction,
                observer.getPosition().forward(observer.getDirection().subtract(span.scale(0.5)), Main.MAP_OBSERVER_LENGTH / scale)));
        drawLine(g2, center, Point.coordinateTransform(position, direction,
                observer.getPosition().forward(observer.getDirection().add(span.scale(0.5)), Main.MAP_OBSERVER_LENGTH / scale)));

        g2.setTransform(at);

    }

    private void drawMap(Graphics2D g2) {

        AffineTransform at = g2.getTransform();

        Point position = mode ? cameraPosition : observer.getPosition();
        Angle direction = mode ? cameraDirection : observer.getDirection();

        g2.rotate(Math.PI * (270. / 180.), (int)Math.round(0.5 * Main.PANEL_WIDTH), (int)Math.round(0.5 * Main.PANEL_HEIGHT));

        for(Edge edge: map) drawLine(g2,
                Point.coordinateTransform(position, direction, edge.getLeft()),
                Point.coordinateTransform(position, direction, edge.getRight()));

        g2.setTransform(at);

    }

}
