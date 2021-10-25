public class Body {
  private final double length;
  private Point position;
  private Vector velocity;
  private Angle turn;
  private int gear;
  private double scale;
  
  public Body() {
    length = 5.;
    position = new Point(0., 0.);
    velocity = new Vector(0, new Angle(Math.PI * 0. / 180.));
    turn = new Angle(Math.PI * 0. / 180.);
    gear = 0;
    scale = 1.;
  }
  
  public Point getPosition() {
    return position;
  }
  
  public Vector getVelocity() {
    return velocity;
  }
  
  public void updateTurn(int level) {
    turn = new Angle(Math.PI * level * 30. / 180.);
  }
  
  public void updateGear(int _gear) {
    gear = _gear;
  }
  
  public void setScale(double ratio) {
    scale *= ratio;
    scale = Math.max(0.01, scale);
    scale = Math.min(10. , scale);
  }
  
  public void update() {
    double speed = (velocity.getLength() + gear * 0.1 * scale) * 0.9;
    Point headPosition = position.forward(velocity.getDirection(), length * 0.5).forward(velocity.getDirection().add(turn), speed);
    Point tailPosition = position.forward(velocity.getDirection(), length * -0.5).forward(velocity.getDirection(), speed);
    position = Point.weightedAverage(headPosition, tailPosition, 1., 1.);
    velocity = new Vector(speed, Point.angle(tailPosition, headPosition));
  }

  public void reset() {
    updateTurn(0);
    updateGear(0);
  }
  
  @Override
  public String toString() {
    return String.format("Body: %.2f at (%s) with (%s) and (%s)", length, position.toString(), velocity.toString(), turn.toString());
  }
}
