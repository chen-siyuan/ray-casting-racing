import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static final int PANEL_WIDTH = 600;
    public static final int PANEL_HEIGHT = 500;
    public static final int OBSERVER_NUMRAYS = 31;
    public static final Angle OBSERVER_SPAN = new Angle(Math.PI * (60. / 180.));

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

        Set<Edge> map = map2();

        Observer ob = new Observer(31, new Angle(Math.PI * (60. / 180.)));
        ob.setPosition(new Point(1.41, 2.68));
        ob.setDirection(new Angle(Math.PI * (118.64 / 180.)));
        ob.construct();

        for(double distance: ob.detect(map)) System.out.println(String.format("%.3f", distance));

        EventQueue.invokeLater(() -> {
            Frame frame = new Frame();
            frame.initUI();
            frame.setVisible(true);
        });

    }

}
