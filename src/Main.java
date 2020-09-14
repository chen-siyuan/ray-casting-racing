public class Main {

    public static void main(String[] args) {

        Point p1 = new Point(-3, 2);
        Point p2 = new Point(3, -2);

        System.out.println(Point.distance(p1, p2));
        System.out.println(Point.angle(p1, p2));

        Edge edge = new Edge(p1, p2);
        Ray ray = new Ray(new Point(1, 0), 0.3);

        System.out.println(Boolean.toString(ray.intersects(edge)));

        Angle angle = new Angle(-0.01);

        System.out.println(angle.getValue());

    }

}
