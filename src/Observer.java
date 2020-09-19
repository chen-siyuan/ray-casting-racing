import java.util.Set;

public class Observer {

    private final Ray[] rays;
    private final Angle span;

    private Point position;
    private Angle direction;

    public Observer(int _numRays, Angle _span) {
        rays = new Ray[_numRays];
        span = _span;
    }

    public Angle getSpan() {
        return span;
    }

    public Point getPosition() {
        return position;
    }

    public Angle getDirection() {
        return direction;
    }

    public void setPosition(Point _position) {
        position = _position;
    }

    public void setDirection(Angle _direction) {
        direction = _direction;
    }

    private void construct() {
        for(int i=0; i < rays.length; i++) rays[i] =
                new Ray(position, direction.subtract(span.scale(0.5)).add(span.scale(1. * i / (rays.length - 1))));
    }

    public double[] detect(Set<Edge> edges) {

        double[] res = new double[rays.length];
        for(int i=0; i < rays.length; i++) res[i] = Double.MAX_VALUE;

        construct();
        for(Edge edge: edges) for(int i=0; i < rays.length; i++) res[i] = Math.min(res[i], rays[i].distanceTo(edge));

        return res;
    }

    // Test method
    public Ray getRay(int index) {
        return rays[index];
    }

}
