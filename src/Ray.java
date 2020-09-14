public class Ray {

    private final Point origin;
    private final double angle;

    public Ray(Point _origin, double _angle) {
        origin = _origin;
        angle = _angle;
    }

    public boolean intersects(Edge edge) {

        double alpha = Point.angle(origin, edge.getLeft());
        double beta = Point.angle(origin, edge.getRight());

        if((alpha - beta) % Math.PI == 0) return true;

        return false;
    }

    public double distanceTo(Edge edge) {
        return 0.;
    }

}
