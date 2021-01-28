import java.security.Key;
import java.util.Random;

/**
 * Created by hug.
 */
public class Experiments {
    public static void experiment1(BST bst) {
        for (int i=0; i<5000;i++){
            Key r = Math.random();
            bst.add(r);
        }


    }

    public static void experiment2() {
    }

    public static void experiment3() {
    }

    public static void main(String[] args) {
    }
}
