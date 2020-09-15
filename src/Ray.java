public class Ray {

    private final Point position;
    private final Angle direction;

    public Ray(Point _position, Angle _direction) {
        position = _position;
        direction = _direction;
    }

    public boolean intersects(Edge edge) {

        Angle alpha = Point.angle(position, edge.getLeft());
        Angle beta = Point.angle(position, edge.getRight());

        if(alpha.opposes(beta)) return true;
        if(alpha.equals(beta)) return direction.equals(beta);
        if(direction.equals(alpha) || direction.equals(beta)) return true;

        return alpha.compareTo(beta) < 0
                ? alpha.compareTo(direction) < 0 && direction.compareTo(beta) < 0
                : alpha.compareTo(direction) > 0 && direction.compareTo(beta) > 0;
    }

    public double distanceTo(Edge edge) {

        if(!intersects(edge)) return Double.MAX_VALUE;

        double x = Point.distance(position, edge.getLeft());
        double y = Point.distance(position, edge.getRight());

        Angle alpha = direction.difference(Point.angle(position, edge.getLeft()));
        Angle beta = direction.difference(Point.angle(position, edge.getRight()));

        Point d = Point.weightedAverage(edge.getLeft(), edge.getRight(),
                y * Math.sin(beta.getValue()), x * Math.sin(alpha.getValue()));

        return Point.distance(position, d);
    }

}
