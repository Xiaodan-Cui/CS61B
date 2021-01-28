package es.datastructur.synthesizer;
import java.util.Iterator;

//TODO: Make sure to that this class and all of its methods are public
//TODO: Make sure to add the override tag for all overridden methods
//TODO: Make sure to make this class implement BoundedQueue<T>

public class ArrayRingBuffer<T> implements BoundedQueue<T>{
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;
    private int capacity;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int c) {
        capacity=c;
        rb=(T[]) new Object[capacity];
        first=0;
        last=0;
        fillCount=0;
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    public int plusone(int index){
        return (index+1)%capacity;
    }
    public int minusone(int index){
        return (index-1+capacity)%capacity;
    }
    @Override
    public int capacity(){
        return capacity;
    }
    @Override
    public int fillCount(){
        return fillCount;
    }
    @Override
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update
        //       last.
        if (isFull()){
            throw new RuntimeException("Ring Buffer overflow");}
        rb[last]=x;
        this.last =plusone(this.last);
        fillCount+=1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and
        //       update first.
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer underflow");
        }
        T temp = rb[first];
        first = plusone(first);
        fillCount -= 1;
        return temp;

    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        // TODO: Return the first item. None of your instance variables should
        //       change.
        return rb[first];
    }
    @Override
    public Iterator<T> iterator() {
        return new ArrayRingBufferIterator();
    }
    @Override
    public boolean equals(Object other){
        if (other==null){
            return false;
        }
        if (this.getClass()!=other.getClass()){
            return false;
        }
        ArrayRingBuffer<T> o = (ArrayRingBuffer<T>) other;
        if (this.fillCount()!= o.fillCount()){
            return false;
        }
        Iterator<T> thisI = this.iterator();
        Iterator<T> otherI = o.iterator();
        while(thisI.hasNext()&&otherI.hasNext()){
            if (thisI.next()!= otherI.next()){
                return false;
            }
        }
        return true;

    }

    // TODO: When you get to part 4, implement the needed code to support
    //       iteration and equals.
    private class ArrayRingBufferIterator implements Iterator<T>{
        private int wizPos;
        private int count;
        public ArrayRingBufferIterator(){
            wizPos=first;
            count=0;
        }
        public boolean hasNext() {
            return count < fillCount;
        }
        public T next(){
            T returnItem=rb[wizPos];
            count+=1;
            wizPos=plusone(wizPos);
            return returnItem;
        }

    }
}
    // TODO: Remove all comments that say TODO when you're done.
