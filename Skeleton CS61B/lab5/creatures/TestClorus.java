package ceatures;

import creatures.*;
import huglife.*;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Random;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class TestClorus {

/* @author Xiaodan */
    @Test
    public void testBasics() {
        Clorus c = new Clorus(2);
        assertEquals(2, c.energy(), 0.01);
        assertEquals(new Color(34, 0, 231), c.color());
        c.move();
        assertEquals(1.97, c.energy(), 0.01);
        c.move();
        assertEquals(1.94, c.energy(), 0.01);
        c.stay();
        assertEquals(1.95, c.energy(), 0.01);
        c.stay();
        assertEquals(1.96, c.energy(), 0.01);
    }

    @Test
    public void testReplicate() {
        Clorus c = new Clorus(2);
        Clorus o = c.replicate();
        assertEquals(1, c.energy(), 0.01);
        assertEquals(1, o.energy(), 0.01);
        assertFalse(c.equals(o));
    }

    @Test
    public void testChoose() {
        /*noempty*/
        Clorus c = new Clorus(1.2);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());

        Action actual = c.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);


        // one plip seen.
        c = new Clorus(1.2);
        HashMap<Direction, Occupant> onePlip = new HashMap<Direction, Occupant>();
        onePlip.put(Direction.TOP, new Empty());
        onePlip.put(Direction.BOTTOM, new Plip(2));
        onePlip.put(Direction.LEFT, new Empty());
        onePlip.put(Direction.RIGHT, new Empty());
        Deque<Direction> PlipNeighbors = new ArrayDeque<>();
        actual = c.chooseAction(onePlip);
        expected = new Action(Action.ActionType.ATTACK, Direction.BOTTOM);
        assertEquals(expected, actual);


        // Energy >= 1; replicate towards an empty space.
        c = new Clorus(1.2);
        HashMap<Direction, Occupant> TopEmpty = new HashMap<Direction, Occupant>();
        TopEmpty.put(Direction.TOP, new Empty());
        TopEmpty.put(Direction.BOTTOM, new Impassible());
        TopEmpty.put(Direction.LEFT, new Impassible());
        TopEmpty.put(Direction.RIGHT, new Impassible());

        actual = c.chooseAction(TopEmpty);
        expected = new Action(Action.ActionType.REPLICATE, Direction.TOP);

        assertEquals(expected, actual);



        c = new Clorus(.99);

        actual = c.chooseAction(TopEmpty);
        expected = new Action(Action.ActionType.MOVE, Direction.TOP);

        assertEquals(expected, actual);


    }
}

