package byow.Core;

import byow.Core.Point;

import java.util.List;
import java.util.Queue;

public class KDTree{
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

    public KDTree(Queue<Point> points) {
        for (Point p : points) {
            Root = put(Root, p,Horizontal);
        }
    }

    private int comparePoint(Point a, Point b, boolean orientation) {
        if (orientation == Horizontal) {
            return Double.compare(a.pos.x, b.pos.x);
        } else {
            return Double.compare(a.pos.y, b.pos.y);
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
        double CurrBest = distance(best, target);
        if (distance(node.Point, target)!=0 && distance(node.Point, target) < CurrBest && node.Point.isConnected==false) {
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


    private double distance(Point a,Point b){
        return Math.sqrt(Math.pow((a.pos.x-b.pos.x),2)+Math.pow((a.pos.y-b.pos.y),2));
    }

    private boolean isWorthLooking(KDNode node, Point target, Point best) {
        double CurrBest = distance(best, target);
        double dist;
        if (node.Orientation == Horizontal) {
            dist = Math.abs(node.Point.pos.x - target.pos.x);
        } else {
            dist = Math.abs(node.Point.pos.y - target.pos.y);
        }
        return dist < CurrBest;
    }



    public Point nearest(Point p) {
    return nearest(Root, p, Root.Point);
    }

    public static void main(String[] args) {
    }
}
