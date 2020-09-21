public class Vector {

    private final double length;
    private final Angle direction;

    public Vector(double _length, Angle _direction) {
        length = _length;
        direction = _direction;
    }

    public double getLength() {
        return length;
    }

    public Angle getDirection() {
        return direction;
    }

    public double getX() {
        return length * direction.cos();
    }

    public double getY() {
        return length * direction.sin();
    }

    public Vector scale(double ratio) {
        return new Vector(length * ratio, direction);
    }

    public Vector add(Vector vector) {
        double _length = Math.sqrt(Math.pow(getX() + vector.getX(), 2) + Math.pow(getY() + vector.getY(), 2));
        Angle _direction = new Angle(Math.atan2(getY() + vector.getY(), getX() + vector.getX()));
        return new Vector(_length, _direction);
    }

    @Override
    public String toString() {
        return String.format("Vector: %.2f towards (%s)", length, direction.toString());
    }

}
