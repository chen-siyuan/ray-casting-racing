public class Point {

    public static double distance(Point from, Point to) {
        return Math.sqrt(Math.pow(from.getX() - to.getX(), 2) + Math.pow(from.getY() - to.getY(), 2));
    }

    public static double angle(Point from, Point to) {
        return Math.atan2(to.getY() - from.getY(), to.getX() - from.getX());
    }

    private final double x;
    private final double y;

    public Point(double _x, double _y) {
        x = _x;
        y = _y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

}
