public class LinkedListDeque<T>{
    private Node sentinel;
    private int size;
    private class Node{
        Node prev;
        T item;
        Node next;
        public Node(Node p,T i,Node n){
            prev = p;
            item = i;
            next = n;
        }
    }
    public LinkedListDeque(){
        sentinel = new Node(null,null,null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size=0;
    }
    public LinkedListDeque(LinkedListDeque other){
        sentinel = new Node(null,null,null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size=0;
        int i = 0;
        while (i<other.size){
            this.addLast((T) other.get(i));
            i+=1;
        }
    }
    public void addFirst(T item){
        Node node=new Node(sentinel,item,sentinel.next);
        sentinel.next.prev=node;
        sentinel.next=node;
        size+=1;
    }
    public void addLast(T item){
        Node node=new Node(sentinel.prev,item,sentinel);
        sentinel.prev.next=node;
        sentinel.prev=node;
        size+=1;
    }
    public boolean isEmpty(){
        return size==0;
    }
    public void printDeque(){
        Node curr= sentinel.next;
        while (curr!=sentinel){
            System.out.println(curr.item+" ");
            curr=curr.next;
        }
    }
    public int size(){
        return size;
    }
    public T removeFirst(){
        if (isEmpty()){return null;}
        T first=sentinel.next.item;
        sentinel.next=sentinel.next.next;
        sentinel.next.prev=sentinel;
        size-=1;
        return first;
    }
    public T removeLast(){
        if (isEmpty()){return null;}
        T last=sentinel.prev.item;
        sentinel.prev=sentinel.prev.prev;
        sentinel.prev.next=sentinel;
        size-=1;
        return last;
    }
    public T get(int i){
        if (i>size){return null;}
        Node curr=sentinel.next;
        while (i!=0){
            curr=curr.next;
            i-=1;
        }
        return curr.item;
    }
    public T getRecursive(int i) {
        return getRecursiveHelper(sentinel.next,i);}

        private T getRecursiveHelper (Node curr, int i){
        if (i == 0) {
                return curr.item;
            }
        else {
            return getRecursiveHelper(curr.next,i - 1);
            }
        }


}