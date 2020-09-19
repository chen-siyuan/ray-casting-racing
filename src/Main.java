import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static final int FRAME_RATE = 10;
    public static final int PANEL_WIDTH = 600;
    public static final int PANEL_HEIGHT = 400;
    public static final int OBSERVER_RAYS = 241;
    public static final Angle OBSERVER_SPAN = new Angle(Math.PI * (120. / 180.));
    public static final double OBSERVER_MOVING_SPEED = 1.;
    public static final double OBSERVER_TURNING_SPEED = Math.PI * (30. / 180.);
    public static final double VIEW_MAX = 400.;
    public static final double VIEW_FOCAL = 0.5;
    public static final double MAP_OBSERVER_LENGTH = 25.;
    public static final double MAP_MAP_SCALE = 25.;

    // TODO: 9/17/20 explore changing focal length 
    // TODO: 9/18/20 create more maps

    public static Set<Edge> drawPolygon(Point... points) {

        Set<Edge> res = new HashSet<>();
        Point prev = null;
        Point head = null;

        for(Point point: points) {
            if(prev == null) head = point;
            else res.add(new Edge(prev, point));
            prev = point;
        }

        res.add(new Edge(prev, head));
        return res;
    }

    public static Set<Edge> map1() {

        Point a = new Point(3.66, 3.7);
        Point b = new Point(1.32, 5.57);
        Point c = new Point(-2.92, 3.75);
        Point d = new Point(-1.42, -2.9);

        Set<Edge> res = drawPolygon(a, b, c, d);
        res.add(new Edge(a, c));
        res.add(new Edge(b, d));

        return res;
    }

    public static Set<Edge> map2() {

        Set<Edge> res = drawPolygon(new Point(10, 10), new Point(-10, 10), new Point(-10, -10), new Point(10, -10));

        res.addAll(drawPolygon(new Point(2.5, 2.5), new Point(2.5, 7.5), new Point(7.5, 7.5), new Point(7.5, 2.5)));
        res.addAll(drawPolygon(new Point(-2.5, 2.5), new Point(-2.5, 7.5), new Point(-7.5, 7.5), new Point(-7.5, 2.5)));
        res.addAll(drawPolygon(new Point(-2.5, -2.5), new Point(-2.5, -7.5), new Point(-7.5, -7.5), new Point(-7.5, -2.5)));
        res.addAll(drawPolygon(new Point(2.5, -2.5), new Point(2.5, -7.5), new Point(7.5, -7.5), new Point(7.5, -2.5)));

        return res;
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            Frame frame = new Frame();

            frame.getMap().addAll(map1());
            frame.getObserver().setPosition(new Point(0, 0));
            frame.getObserver().setDirection(new Angle(Math.PI * (0. / 180.)));

            frame.initUI();
            frame.setVisible(true);

        });

    }

}
