public class Ray {

    private final Point origin;
    private final Angle angle;

    public Ray(Point _origin, Angle _angle) {
        origin = _origin;
        angle = _angle;
    }

    public boolean intersects(Edge edge) {

        Angle alpha = new Angle(Point.angle(origin, edge.getLeft()));
        Angle beta = new Angle(Point.angle(origin, edge.getRight()));

        if(alpha.opposes(beta)) return true;
        if(alpha.equals(beta)) return angle.equals(beta);
        if(angle.equals(alpha) || angle.equals(beta)) return true;

        return alpha.compareTo(beta) < 0
                ? alpha.compareTo(angle) < 0 && angle.compareTo(beta) < 0
                : alpha.compareTo(angle) > 0 && angle.compareTo(beta) > 0;
    }

    public double distanceTo(Edge edge) {
        return 0.;
    }

}
