package bearmaps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;


public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    private class PriorityNode {
        T item;
        double priority;

        PriorityNode(T e, double p) {
            this.item = e;
            this.priority = p;
        }

    }
    private ArrayList<PriorityNode> items;
    private int size;
    private HashMap<T, Integer> keyset;

    public ArrayHeapMinPQ (){
        items=new ArrayList<>();
        size=0;
        keyset=new HashMap<>();
    }
    public int parent (int i){
        return (i-1)/2;
    }
    public int leftChild(int i){
        return 2*i+1;
    }
    public int rightChild(int i){
        return 2*(i+1);
    }
    public void swim(int i){
        if (items.get(parent(i)).priority>items.get(i).priority){
            swap(i,parent(i));
            swim(parent(i));
        }
    }
    public void sink(int i){
        int s;
        if (i==size-1 ||leftChild(i)>=size-1){
            return;
        }
        if (rightChild(i)<=size-1) {
            if (items.get(leftChild(i)).priority <= items.get(rightChild(i)).priority) {
                s = leftChild(i);
            } else {
                s = rightChild(i);
            }
        }
        else {s = leftChild(i);}
        if (items.get(i).priority<items.get(s).priority){
            return;
        }
            swap(i,s);
            sink(s);
    }
    public void swap(int i,int j){
        PriorityNode temp=items.get(i);
        items.set(i,items.get(j));
        items.set(j,temp);;
        keyset.put(items.get(i).item, i);
        keyset.put(items.get(j).item, j);;
    }
    @Override
    public void add(T item, double priority){
        PriorityNode newnode = new PriorityNode(item, priority);
        items.add(size,newnode);
        swim(size);
        keyset.put(item,items.indexOf(newnode));
        size+=1;
    }
    /* Returns true if the PQ contains the given item. */
    @Override
    public boolean contains(T item){
        return keyset.containsKey(item);
    }
        /* Returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    @Override
    public T getSmallest(){
        if (items==null){
            throw new NoSuchElementException();}
        return items.get(0).item;
    }
    /* Removes and returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    @Override
    public T removeSmallest(){
        if (items==null){
            throw new NoSuchElementException();}
        PriorityNode temp=items.get(0);
        items.get(0).priority=1000000;
        sink(0);
        int i=items.indexOf(temp);
        if (i!=size-1){swap(i, size-1);}
        swim(i);
        items.remove(size-1);
        size-=1;
        return temp.item;
    }
    /* Returns the number of items in the PQ. */
    @Override
    public int size(){
        return size;
    }
    /* Changes the priority of the given item. Throws NoSuchElementException if the item
     * doesn't exist. */
    @Override
    public void changePriority(T item, double priority){
        if (!contains(item)){
            throw new NoSuchElementException();
        }
        int i=keyset.get(item);
        double oldpriority=items.get(i).priority;
        items.get(i).priority=priority;
        if (oldpriority<priority){
            sink(i);
        }
        if (oldpriority>priority){
            swim(i);
        }
    }

}
