import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Solver for the Flight problem (#9) from CS 61B Spring 2018 Midterm 2.
 * Assumes valid input, i.e. all Flight start times are >= end times.
 * If a flight starts at the same time as a flight's end time, they are
 * considered to be in the air at the same time.
 */
public class FlightSolver {
    int maxpeople=0;
    public FlightSolver(ArrayList<Flight> flights) {
        PriorityQueue<Flight> StartMinTime= new PriorityQueue<>(new Comparator<Flight>() {
            @Override
            public int compare(Flight o1,Flight o2){
                return Integer.compare(o1.startTime(), o2.startTime());
            }
        });
        PriorityQueue<Flight> EndMinTime= new PriorityQueue<>(new Comparator<Flight>() {
            @Override
            public int compare(Flight o1,Flight o2){
                return Integer.compare(o1.endTime(), o2.endTime());
            }
        });
        StartMinTime.addAll(flights);
        EndMinTime.addAll(flights);
        int tolly=0;

        while (StartMinTime.size()!=0){
            if (StartMinTime.peek().startTime()<=EndMinTime.peek().endTime()){
                tolly+=StartMinTime.poll().passengers();
                maxpeople=Math.max(tolly,maxpeople);}
            else{
                tolly-=EndMinTime.poll().passengers();
            }

        }
    }
    public int solve() {

        return maxpeople;
    }

}
