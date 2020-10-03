public class Edge {

    private final Point left;
    private final Point right;

    public Edge(Point _left, Point _right) {
        left = _left;
        right = _right;
    }

    public Point getLeft() {
        return left;
    }

    public Point getRight() {
        return right;
    }

    @Override
    public boolean equals(Object object) {
        if(!(object instanceof Edge)) return false;
        Edge edge = (Edge) object;
        return (left.equals(edge.getLeft()) && right.equals(edge.getRight()))
                || (left.equals(edge.getRight()) && right.equals(edge.getLeft()));
    }

    @Override
    public String toString() {
        return String.format("Edge: (%s) - (%s)", left.toString(), right.toString());
    }
}
