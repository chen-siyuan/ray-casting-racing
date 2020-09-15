public class Main {

    public static void main(String[] args) {

        Point o = new Point(0, 0);
        Point p1 = new Point(-5.11, 9.28);
        Point p2 = new Point(15.96, -10.36);

        Edge e = new Edge(p1, p2);
        Angle a = new Angle(Math.PI * (0 / 180.));
        Ray r = new Ray(o, a);

        // System.out.println(r.distanceTo(e));

        Observer ob = new Observer(5, new Angle(Math.PI * (60. / 180.)));
        ob.setPosition(o);
        ob.setDirection(a);
        ob.constructRays();

        for(int i=0; i < 5; i++) System.out.println(ob.getRay(i).distanceTo(e));

    }

}
