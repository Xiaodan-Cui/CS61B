package creatures;

import huglife.Action;
import huglife.Creature;
import huglife.Direction;
import huglife.Occupant;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.Random;

public class Clorus extends Creature{

    private int r;
    private int g;
    private int b;
    private double moveProbability = 0.5;


    public Clorus(double e) {
        super("clorus");
        r = 0;
        g = 0;
        b = 0;
        energy = e;
    }


    public Clorus() {
        this(1);
    }


    public Color color() {
        g = 0;
        r=34;
        b=231;
        return color(r, g, b);
    }


    public void attack(Creature c) {
        energy+=c.energy();

    }


    public void move() {
        energy-=0.03;
        if (energy<0){energy=0;}
    }


    /**
     * Plips gain 0.2 energy when staying due to photosynthesis.
     */
    public void stay() {
        energy+=0.01;

    }

    /**
     * Plips and their offspring each get 50% of the energy, with none
     * lost to the process. Now that's efficiency! Returns a baby
     * Plip.
     */
    public Clorus replicate() {
        energy*=0.5;
        return new Clorus(energy);
    }


    public Action chooseAction(Map<Direction, Occupant> neighbors) {

        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> PlipNeighbors = new ArrayDeque<>();
        for (Direction i: neighbors.keySet()) {
            // (Google: Enhanced for-loop over keys of NEIGHBORS?)
            // for () {...}
            if (neighbors.get(i).name().equals("empty")) {
                emptyNeighbors.addLast(i); }
            else if (neighbors.get(i).name().equals("plip")) {
                PlipNeighbors.addLast(i); }
        }
        // Rule 1
        if (emptyNeighbors.size()==0){
            return new Action(Action.ActionType.STAY);}

        // Rule 2
        else if (PlipNeighbors.size()!=0){
            int k = new Random().nextInt(PlipNeighbors.size());
            for (int i=0; i<k-1;i++){
                PlipNeighbors.removeFirst();}
            Direction dir=PlipNeighbors.removeFirst();
            return new Action(Action.ActionType.ATTACK,dir);
        }
        // Rule 3
        // HINT: randomEntry(emptyNeighbors)
        else if (energy>=1.0){
            int k = new Random().nextInt(emptyNeighbors.size());
            for (int i=0; i<k-1;i++){
                emptyNeighbors.removeFirst();
            }
            Direction dir=emptyNeighbors.removeFirst();
            return new Action(Action.ActionType.REPLICATE,dir);
        }

        // Rule 4
        else {
            int k = new Random().nextInt(emptyNeighbors.size());
            for (int i=0; i<k-1;i++){
                emptyNeighbors.removeFirst();
            }
            Direction dir=emptyNeighbors.removeFirst();
            return new Action(Action.ActionType.MOVE,dir);
        }
    }
}
