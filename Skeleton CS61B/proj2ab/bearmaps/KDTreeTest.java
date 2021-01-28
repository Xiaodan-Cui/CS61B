package bearmaps;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class KDTreeTest {
    @Test
    public void sanityGeneralTest() {
        List points=new ArrayList();
        for (int i = 0; i < 505; i++) {
            Point point = new Point(Math.random()*100, Math.random()*100);
            points.add(point);
        }
        KDTree b = new KDTree(points);
        NaivePointSet a=new NaivePointSet(points);

        assertEquals(b.nearest(1, 2),a.nearest(1, 2));
        assertEquals(b.nearest(44, 33),a.nearest(44, 33));
        assertFalse(b.nearest(21,2) ==a.nearest(44, 33));
    }

   @Test
    public void runtimeTest() {
       List points=new ArrayList();
       for (int i = 0; i < 50500; i++) {
           Point point = new Point(Math.random()*100, Math.random()*100);
           points.add(point);
       }
       KDTree b = new KDTree(points);
       NaivePointSet a=new NaivePointSet(points);
        long start = System.currentTimeMillis();
       for (int i = 0; i < 50500; i++) {
           Point point = new Point(Math.random()*100, Math.random()*100);
           b.nearest(Math.random()*100,Math.random()*100);
       }
        long end = System.currentTimeMillis();

        System.out.println("KDTree Total time elapsed: " + (end - start)/1000.0 +  " seconds.");


        long start1 = System.currentTimeMillis();
       for (int i = 0; i < 505; i++) {
           Point point = new Point(Math.random()*100, Math.random()*100);
           a.nearest(Math.random()*100,Math.random()*100);
       }
        long end1 = System.currentTimeMillis();
        System.out.println("NPQ Total time elapsed: " + (end1 - start1)/1000.0 +  " seconds.");
    }

    public static void main(String[] args) {
        jh61b.junit.TestRunner.runTests(ArrayHeapMinPQTest.class);
    }


}
