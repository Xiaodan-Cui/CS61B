public class ArrayDeque<T> implements Deque<T> {
    private int size;
    private T[] items;
    private static int Init_Capacity=8;
    private static int Factor=2;
    private static double minRatio=0.25;
    private int nextFirst;
    private int nextLast;
    private int Capacity;
    public ArrayDeque(){
        items=(T[]) new Object[Init_Capacity];
        size=0;
        nextFirst=4;
        nextLast=5;
    }
    public ArrayDeque(ArrayDeque other){
        items=(T[]) new Object[other.items.length];
        size=0;
        nextFirst=4;
        nextLast=5;
        for (int i=0;i<other.size;i++){
            this.addLast((T) other.get(i));
        }
    }
    public int plusone(int index){
        return (index+1)% items.length;
    }
    public int minusone(int index){
        return (index-1+items.length)% items.length;
    }
    public void resize(int Capacity){
        T[] newarray=(T[]) new Object[Capacity];
        int curr=plusone(nextFirst) ;
        for (int i =0; i<size;i++){
            newarray[i]=items[curr];
            curr=plusone(curr);
        }
        items=newarray;
        nextFirst=Capacity-1;
        nextLast=size;
        }
    @Override
    public void addFirst(T item){
    if (size==items.length){
        resize(size*Factor);
    }
        items[nextFirst]=item;
        nextFirst=minusone(nextFirst);
        size+=1;
    }
    @Override
    public void addLast(T item){
        if (size==items.length){
            resize(size*Factor);
        }
        items[nextLast]=item;
        nextLast=plusone(nextLast);
        size+=1;
    }
    @Override
    public boolean isEmpty(){
        return size==0;
    }
    @Override
    public int size(){
        return size;
    }
    @Override
    public void printDeque(){
        int curr=plusone(nextFirst) ;
        for (int i =0; i<size;i++) {
            System.out.print(items[curr] + " ");
            curr = plusone(curr);
        }
        System.out.println();
    }
    @Override
    public T removeFirst(){
        if (size==0){return null;}
        int curr=plusone(nextFirst) ;
        T first = items[curr];
        nextFirst=curr;
        size-=1;
        if (size/items.length<minRatio){
            resize(items.length/Factor);
        }
        return first;
    }
    @Override
    public T removeLast(){
        if (size==0){return null;}
        int curr=minusone(nextLast) ;
        T last = items[curr];
        nextLast=curr;
        size-=1;
        if (size/items.length<minRatio){
            resize(items.length/Factor);
        }
        return last;
    }
    @Override
    public T get(int i){
        int curr=plusone(nextFirst);
        while(i>0){
            curr=plusone(curr);
            i-=1;
        }
        return items[curr];
    }

}
