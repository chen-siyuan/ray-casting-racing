public class Velocity {

    private Vector value;
    private boolean boost;
    private boolean brake;
    private boolean left;
    private boolean right;

    public Velocity() {
        value = new Vector(0, new Angle(0));
    }

    private void add(Vector other) {
        value = value.add(other);
    }

    public Vector getValue() {
        return value;
    }

    public void standardize() {
        if(value.getLength() > Main.VELOCITY_SPEED)
            value = value.scale(Main.VELOCITY_SPEED / value.getLength());
    }

    public void forward(Angle direction) {
        add(new Vector(0.1, direction));
    }

    public void backward(Angle direction) {
        add(new Vector(0.02, direction.add(new Angle(Math.PI * (180. / 180.)))));
    }

    public void counterclockwise(Angle direction) {
        add(new Vector(0.05, direction.add(new Angle(Math.PI * (270. / 180.)))));
    }

    public void clockwise(Angle direction) {
        add(new Vector(0.05, direction.add(new Angle(Math.PI * (90. / 180.)))));
    }

    public void boost() {

    }

    public void brake() {

    }

    public void left() {

    }

    public void right() {

    }

    @Override
    public String toString() {
        return value.toString();
    }
}
