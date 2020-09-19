public class Point {

    public static double distance(Point from, Point to) {
        return Math.sqrt(Math.pow(from.getX() - to.getX(), 2) + Math.pow(from.getY() - to.getY(), 2));
    }

    public static Angle angle(Point from, Point to) {
        return new Angle(Math.atan2(to.getY() - from.getY(), to.getX() - from.getX()));
    }

    public static Point weightedAverage(Point from, Point to, double w1, double w2) {
        return new Point((from.getX() * w1 + to.getX() * w2) / (w1 + w2),
                (from.getY() * w1 + to.getY() * w2) / (w1 + w2));
    }

    public static Point coordinateTransform(Point origin, Angle direction, Point point) {
        return new Point(direction.cos() * (point.getX() - origin.getX()) + direction.sin() * (point.getY() - origin.getY()),
                - direction.sin() * (point.getX() - origin.getX()) + direction.cos() * (point.getY() - origin.getY()));
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

    @Override
    public boolean equals(Object object) {
        if(!(object instanceof Point)) return false;
        Point point = (Point) object;
        return x == point.getX() && y == point.getY();
    }

    @Override
    public String toString() {
        return String.format("%.2f, %.2f", x, y);
    }
}
