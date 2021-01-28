package es.datastructur.synthesizer;

import java.util.Iterator;
import java.util.Objects;

public interface BoundedQueue<T> extends Iterable<T>{
    int capacity ();
    int fillCount ();
    void enqueue(T x);
    T dequeue();
    T peek();
    boolean equals(Object other);
    Iterator<T> iterator();
    default boolean isEmpty(){
        return Objects.equals(fillCount(), 0);
    }
    default boolean isFull(){
        return Objects.equals(fillCount(), capacity());
    }

}
