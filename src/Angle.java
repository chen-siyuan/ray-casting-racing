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

    public boolean equals(Angle other) {
        return subtract(other).getValue() == 0;
    }

    public boolean opposes(Angle other) {
        return subtract(other).getValue() == Math.PI;
    }

    public int compareTo(Angle other) {
        if(equals(other) || opposes(other)) return 0;
        return (subtract(other).getValue() > Math.PI) ? -1 : 1;
    }

    public Angle difference(Angle other) {
        return new Angle(Math.min(subtract(other).getValue(), other.subtract(this).getValue()));
    }

    public Angle add(Angle other) {
        return new Angle(value + other.getValue());
    }

    public Angle subtract(Angle other) {
        return new Angle(value - other.getValue());
    }

    public Angle scale(double ratio) {
        return new Angle(value * ratio);
    }

}
