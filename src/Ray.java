public class Ray {

    private final Point origin;
    private final Angle angle;

    public Ray(Point _origin, Angle _angle) {
        origin = _origin;
        angle = _angle;
    }

    public boolean intersects(Edge edge) {

        Angle alpha = Point.angle(origin, edge.getLeft());
        Angle beta = Point.angle(origin, edge.getRight());

        if(alpha.opposes(beta)) return true;
        if(alpha.equals(beta)) return angle.equals(beta);
        if(angle.equals(alpha) || angle.equals(beta)) return true;

        return alpha.compareTo(beta) < 0
                ? alpha.compareTo(angle) < 0 && angle.compareTo(beta) < 0
                : alpha.compareTo(angle) > 0 && angle.compareTo(beta) > 0;
    }

    public double distanceTo(Edge edge) {

        if(!intersects(edge)) return -1.;

        double x = Point.distance(origin, edge.getLeft());
        double y = Point.distance(origin, edge.getRight());

        Angle alpha = angle.differenceWith(Point.angle(origin, edge.getLeft()));
        Angle beta = angle.differenceWith(Point.angle(origin, edge.getRight()));

        Point d = Point.weightedAverage(edge.getLeft(), edge.getRight(),
                y * Math.sin(beta.getValue()), x * Math.sin(alpha.getValue()));

        System.out.println(d.getX());
        System.out.println(d.getY());

        return Point.distance(origin, d);
    }

}
