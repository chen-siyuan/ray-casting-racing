public class Velocity {

    private Vector value;
    private boolean boost;
    private boolean brake;
    private boolean left;
    private boolean right;

    public Velocity() {
        value = new Vector(0, new Angle(0));
        boost = false;
        left = false;
        right = false;
    }

    private void add(Vector other) {

    }

    private void standardize() {

    }

    public Vector getValue() {
        return value;
    }

    public void forward(Angle direction) {

    }

    public void backward(Angle direction) {

    }

    public void counterclockwise(Angle direction) {

    }

    public void clockwise(Angle direction) {

    }

    public void boost() {

    }

    public void brake() {

    }

    public void left() {

    }

    public void right() {

    }

}
