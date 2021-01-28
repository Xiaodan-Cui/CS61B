package es.datastructur.synthesizer;
import org.junit.Test;

import java.util.Iterator;
import java.util.Objects;

import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {

    @Test
    public void testbasics() {
        ArrayRingBuffer arb = new ArrayRingBuffer(4);
        assertTrue(arb.isEmpty());
        arb.enqueue(3);
        assertEquals(1,arb.fillCount());
        arb.enqueue(4);
        assertEquals(2,arb.fillCount());
        arb.enqueue(5);
        assertEquals(3,arb.fillCount());
        arb.enqueue(10);
        assertEquals(4,arb.fillCount());
        assertTrue(arb.isFull());
        arb.dequeue();
        assertEquals(3,arb.fillCount());
        arb.dequeue();
        assertEquals(2,arb.fillCount());
        assertEquals(5,arb.peek());
        ArrayRingBuffer arb1 = new ArrayRingBuffer(4);
        arb1.enqueue(5);
        arb1.enqueue(10);
        ArrayRingBuffer arb2 = new ArrayRingBuffer(4);
        arb2.enqueue(1);
        arb2.enqueue(2);
        arb2.enqueue(5);
        arb2.enqueue(10);
        arb2.dequeue();
        arb2.dequeue();
        assertTrue(arb2.equals(arb1));
        assertTrue(arb.equals(arb1));


    }

}

