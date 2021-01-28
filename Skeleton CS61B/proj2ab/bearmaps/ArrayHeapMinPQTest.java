package bearmaps;

import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class ArrayHeapMinPQTest {
    @Test
    public void sanityGeneralTest() {
        ArrayHeapMinPQ<String> b = new ArrayHeapMinPQ<>();
        NaiveMinPQ<String> a=new NaiveMinPQ<>();
        for (int i = 0; i < 45; i++) {
            b.add("hi" + i, i);
            a.add("hi" + i, i);
            //make sure put is working via containsKey and get
            assertTrue(null != b.getSmallest()
                    && b.contains("hi" + i));
            assertEquals(a.size(),b.size());

        }
        assertTrue(b.contains("hi3"));
        assertEquals("hi0",b.removeSmallest());
        assertEquals("hi1",b.removeSmallest());
        assertEquals(43, b.size());
        b.changePriority("hi30", 3);
        assertEquals("hi2",b.removeSmallest());
        assertEquals("hi3",b.removeSmallest());
        assertEquals("hi30",b.removeSmallest());
        assertEquals("hi4",b.removeSmallest());
        b.changePriority("hi5", 8);
        assertEquals("hi6",b.removeSmallest());
        assertEquals("hi7",b.removeSmallest());
        assertEquals("hi8",b.removeSmallest());
        assertEquals("hi5",b.removeSmallest());
        assertEquals("hi9",b.getSmallest());


    }

    @Test
    public void runtimeTest() {

        ArrayHeapMinPQ<String> b = new ArrayHeapMinPQ<>();
        for (int i = 0; i < 455; i++) {
            b.add("hi" + i, Math.random());
            //make sure put is working via containsKey and get
        }
        long start = System.currentTimeMillis();
        while (b.size()>10){
            b.removeSmallest();
        }
        long end = System.currentTimeMillis();

        System.out.println("HeapPQ Total time elapsed: " + (end - start)/1000.0 +  " seconds.");

        NaiveMinPQ<String> a=new NaiveMinPQ<>();
        for (int i = 0; i < 455; i++) {
            a.add("hi" + i, Math.random());
            //make sure put is working via containsKey and get
        }
        long start1 = System.currentTimeMillis();
        while (a.size()>10){
            a.removeSmallest();
        }
        long end1 = System.currentTimeMillis();
        System.out.println("NPQ Total time elapsed: " + (end1 - start1)/1000.0 +  " seconds.");


    }

    public static void main(String[] args) {
        jh61b.junit.TestRunner.runTests(ArrayHeapMinPQTest.class);
    }
}
