package bearmaps;

import java.util.List;

public class KDTree implements PointSet {
    private class KDNode {
        private Point Point;
        private KDNode LeftDown;
        private KDNode RightUp;
        private boolean Orientation;

        KDNode(Point point, boolean horizontal) {
            Point = point;
            Orientation = horizontal;
        }
    }

    private KDNode Root;
    private static final boolean Horizontal = false;
    private static final boolean Vertical = true;

    public KDTree(List<Point> points) {
        for (Point p : points) {
            Root = put(Root, p,Horizontal);
        }
    }

    private int comparePoint(Point a, Point b, boolean orientation) {
        if (orientation == Horizontal) {
            return Double.compare(a.getX(), b.getX());
        } else {
            return Double.compare(a.getY(), b.getY());
        }
    }

    private KDNode put(KDNode node, Point point, boolean orientation) {
        if (node == null) {
            return new KDNode(point, orientation);
        }
        int cmp = comparePoint(point, node.Point, orientation);
        if (cmp >= 0) {
            node.RightUp = put(node.RightUp, point, !orientation);
        } else {
            node.LeftDown = put(node.LeftDown, point, !orientation);
        }
        return node;
    }

    private Point nearest(KDNode node, Point target, Point best) {
        if (node == null) {
            return best;
        }
        double CurrBest = Point.distance(best, target);
        if (Point.distance(node.Point, target) < CurrBest) {
            best = node.Point;
        }
        KDNode goodside, badside;
        int cmp = comparePoint(target, node.Point, node.Orientation);
        if (cmp >= 0) {
            goodside = node.RightUp;
            badside = node.LeftDown;
        }
        else{
            badside = node.RightUp;
            goodside = node.LeftDown;
            }
        best = nearest(goodside, target, best);

        if (isWorthLooking(node, target, best)) {
            best = nearest(badside, target, best);
        }
        return best;
    }

    private boolean isWorthLooking(KDNode node, Point target, Point best) {
        double CurrBest = Point.distance(best, target);
        double dist;
        if (node.Orientation == Horizontal) {
            dist = Math.abs(node.Point.getX() - target.getX());
        } else {
            dist = Math.abs(node.Point.getY() - target.getY());
        }
        return dist < CurrBest;
    }

    @Override
    public Point nearest(double x, double y) {
        Point target = new Point(x, y);
        return nearest(Root, target, Root.Point);
    }

    public static void main(String[] args) {
    }
}
