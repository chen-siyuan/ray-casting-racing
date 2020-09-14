public class Main {

    public static void main(String[] args) {

        Point o = new Point(0, 0);
        Point p1 = new Point(-1, 1);
        Point p2 = new Point(1, 1);

        Edge e = new Edge(p1, p2);
        Angle a = new Angle(Math.PI * 1 / 2);
        Ray r = new Ray(o, a);

        System.out.println(r.intersects(e));

    }

}
