import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        Point a = new Point(3.66, 3.7);
        Point b = new Point(1.32, 5.57);
        Point c = new Point(-2.92, 3.75);
        Point d = new Point(-1.42, -2.9);

        Edge e1 = new Edge(a, b);
        Edge e2 = new Edge(a, c);
        Edge e3 = new Edge(a, d);
        Edge e4 = new Edge(b, c);
        Edge e5 = new Edge(b, d);
        Edge e6 = new Edge(c, d);

        Set<Edge> es = new HashSet<>();
        es.add(e1);
        es.add(e2);
        es.add(e3);
        es.add(e4);
        es.add(e5);
        es.add(e6);

        Observer ob = new Observer(31, new Angle(Math.PI * (60. / 180.)));
        ob.setPosition(new Point(1.41, 2.68));
        ob.setDirection(new Angle(Math.PI * (118.64 / 180.)));
        ob.construct();

        for(double distance: ob.detect(es)) System.out.println(String.format("%.3f", distance));

    }

}
