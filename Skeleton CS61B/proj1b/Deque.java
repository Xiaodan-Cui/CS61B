public interface Deque<T> {
    default boolean isEmpty(){
        return size()==0;}
    void addFirst(T item);
    void addLast(T item);
    void printDeque();
    int size();
    T removeFirst();
    T removeLast();
    T get(int i);
}