public class Angle {

    public static double standardize(double value) {
        return ((value % (2 * Math.PI)) + 2 * Math.PI) % (2 * Math.PI);
    }

    private final double value;

    public Angle(double _value) {
        value = standardize(_value);
    }

    public double getValue() {
        return value;
    }

    private double distanceTo(Angle other) {
        return standardize(other.getValue() - value);
    }

    public boolean equals(Angle other) {
        return distanceTo(other) == 0;
    }

    public boolean opposes(Angle other) {
        return distanceTo(other) == Math.PI;
    }

    public int compareTo(Angle other) {
        if(equals(other) || opposes(other)) return 0;
        return (distanceTo(other) < Math.PI) ? -1 : 1;
    }

    public Angle differenceWith(Angle other) {
        return new Angle(Math.min(distanceTo(other), other.distanceTo(this)));
    }

}
