package bearmaps;

import java.util.ArrayList;
import java.util.List;


public class NaivePointSet implements PointSet{
    private ArrayList<Point> Points;

    public NaivePointSet(List<Point> points) {

        Points = (ArrayList<Point>) points;
    }

    @Override
    public  Point nearest (double x,double y){
        Point NewPoint=new Point(x,y);
        Point NearestPoint = Points.get(0);
        double Currdist =Points.get(0).distance(Points.get(0),NewPoint);
        for (Point point : Points){
            double Dist=Point.distance(point,NewPoint);
            if (Dist<Currdist){
                Currdist=Dist;
                NearestPoint=point;
            }
        }
        return NearestPoint;
    }

}
