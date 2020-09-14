public class Angle {

    private final double value;

    public Angle(double _value) {
        value = ((_value % (2 * Math.PI)) + 2 * Math.PI) % (2 * Math.PI);
    }

    public double getValue() {
        return value;
    }

}
