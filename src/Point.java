public class Point {

    public static double distance(Point from, Point to) {
        return Math.sqrt(Math.pow(from.getX() - to.getX(), 2) + Math.pow(from.getY() - to.getY(), 2));
    }

    public static double angle(Point from, Point to) {
        return Math.atan2(to.getY() - from.getY(), to.getX() - from.getX());
    }

    public static Point weightedAverage(Point from, Point to, double w1, double w2) {
        return new Point((from.getX() * w1 + to.getX() * w2) / (w1 + w2),
                (from.getY() * w1 + to.getY() * w2) / (w1 + w2));
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
