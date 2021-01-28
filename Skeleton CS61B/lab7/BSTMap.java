import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>,V> implements Map61B<K,V>{

    private int size=0;
    private Node KNode;

    private class Node {
        K Key;
        V Val;
        Node Left;
        Node Right;

        Node(K key, V value) {
            Key = key;
            Val = value;
        }
    }
    private V get(Node x, K key){
        if (x==null){
            return null;
        }
        int cmp=key.compareTo(x.Key);
        if (cmp==0){
            return x.Val;
        }
        else if (cmp>0){
            return get(x.Right,key);
        }
        else{
            return get(x.Left,key);
        }
    }
    private Node put(Node x, K key, V value){
        if (x==null) {
            return new Node (key,value);}
        int cmp=key.compareTo(x.Key);
        if (cmp==0){
            x.Val=value;
        }
        else if (cmp>0){
            x.Right=put(x.Right,key,value);
        }
        else{
            x.Left=put(x.Left,key,value);;
        }
        return x;
    }
   

    @Override
    public void clear() {
        size=0;
        KNode=null;
    }
    @Override
    public boolean containsKey(K key) {
        if (get(key)==null){
            return false;
        }
        return true;
    }

    @Override
    public V get(K key) {
        return get(KNode,key);
    }
    @Override
    public int size(){
        return size;}
    @Override
    public void put(K key, V value){
        KNode=put(KNode,key,value);
        size+=1;
    }
    public void printInOrder(){
        System.out.print("{");
        printInOrder(KNode);
        System.out.print("}");
    }
    private void printInOrder(Node x){
        if (x.Left==null && x.Right==null){
            System.out.print("{"+x.Key+","+x.Val+"}");
        }

        else if (x.Left==null && x.Right!=null){
            System.out.print("{"+x.Key+","+x.Val+"}"+", ");
            printInOrder(x.Right);
        }
        else if (x.Left!=null && x.Right==null){
            printInOrder(x.Left);
            System.out.print("{"+x.Key+","+x.Val+"}");
        }
        else {
            printInOrder(x.Left);
            System.out.print("{"+x.Key+","+x.Val+"}"+", ");
            printInOrder(x.Right);
        }

    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public V remove(K key, V value) {
        return null;
    }


    @Override
    public Iterator<K> iterator() {
        return null;
    }
    public static void main (String[] args){
        BSTMap M=new BSTMap();
        M.put(1,2);
        M.put(3,4);
        M.put(-2,1);
        M.put(7,8);
        M.printInOrder();
    }
}
