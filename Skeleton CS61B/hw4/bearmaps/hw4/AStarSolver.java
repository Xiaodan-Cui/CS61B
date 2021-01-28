package bearmaps.hw4;

import bearmaps.proj2ab.ArrayHeapMinPQ;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex>{
    private ArrayHeapMinPQ<Vertex> pq;
    private HashMap<Vertex,Double> distToStart;
    private HashMap<Vertex,Vertex> edgeTo;
    private HashSet<Vertex> circlevetex;
    private double timeSpent;
    private SolverOutcome outcome;
    private double totalweight;
    private int numStatesExplored;
    private LinkedList<Vertex> Solution;

    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout){
        Stopwatch sw = new Stopwatch();
        pq=new ArrayHeapMinPQ<>();
        distToStart=new HashMap<>();
        Solution=new LinkedList<>();
        edgeTo=new HashMap<>();
        circlevetex=new HashSet<>();
        pq.add(start,0+input.estimatedDistanceToGoal(start, end));
        distToStart.put(start,0.0);
        while(pq.getSmallest()!=end && pq.size()!=0 && timeSpent<timeout){
            Vertex p=pq.removeSmallest();
            for (WeightedEdge e : input.neighbors(p)){
                relax(e,end,input);
            }
            numStatesExplored+=1;
            timeSpent = sw.elapsedTime();
        }
        if (pq.size()==0){
            outcome=SolverOutcome.UNSOLVABLE;
        }
        else if (timeSpent>=timeout){
            outcome=SolverOutcome.TIMEOUT;
        }
        else {
            outcome=SolverOutcome.SOLVED;
            totalweight=distToStart.get(end);
            Vertex v=pq.removeSmallest();
            while (v!=null){
                Solution.addFirst(v);
                v=edgeTo.get(v);
            }
        }
    }

    private void relax(WeightedEdge<Vertex> e, Vertex end,AStarGraph<Vertex> input){
        Vertex p= e.from();
        Vertex q= e.to();
        double w=e.weight();
        circlevetex.add(p);
        if (q!=end && input.neighbors(q).size()==0){
            return;
        }
        if (circlevetex.contains(q)){
            return;
        }
        if (!distToStart.containsKey(q) || distToStart.get(p)+w<distToStart.get(q)){
            distToStart.put(q,distToStart.get(p)+w);
            edgeTo.put(q,p);
            if (pq.contains(q)) {
                pq.changePriority(q, distToStart.get(q) + input.estimatedDistanceToGoal(q, end));
            }
            else {
                    pq.add(q, distToStart.get(q) + input.estimatedDistanceToGoal(q, end));
            }
        }
    }
    @Override
    public SolverOutcome outcome(){
        return outcome;
    }

    @Override
    public List<Vertex> solution() {

            return Solution;
    }
    @Override
    public double solutionWeight(){
        return totalweight;
    }

    @Override
    public int numStatesExplored() {
        return numStatesExplored;
    }
    @Override
    public double explorationTime(){
        return timeSpent;
    }




}
