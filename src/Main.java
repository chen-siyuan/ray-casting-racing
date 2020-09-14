public class Main {

    public static void main(String[] args) {

        Point o = new Point(0, 0);
        Point p1 = new Point(-10.36, -10.66);
        Point p2 = new Point(10.77, -0.27);

        Edge e = new Edge(p1, p2);
        Angle a = new Angle(Math.PI * (243.79 / 180.));
        Ray r = new Ray(o, a);

        System.out.println(r.distanceTo(e));

    }

}
