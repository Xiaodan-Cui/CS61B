public class SLList{
    public class IntNode {
        public int item;
        public IntNode next;

        public IntNode(int item, IntNode next) {
            this.item = item;
            this.next = next;
        }
    }
    private IntNode first;
    public SLList(int x){
            first = new IntNode(x,null);
    }
    public void addFirst(int x){
        first = new IntNode(x,first);
    }

    public void insert(int item,int position) {
        if (position==0 || first==null){
            addFirst(item);
            return;
        }
        IntNode curr= first;
        while (position > 1  && curr!= null) {
            position-=1;
            curr=curr.next;
        }
        IntNode newnode=new IntNode(item,curr.next);
        curr.next=newnode;
    }
    public void reverse() {
        if (first == null || first.next == null) {
            return;
        }
        IntNode copy = first;
        first = null;
        while (copy != null) {
            this.addFirst(copy.item);
            copy = copy.next;
        }

    }


    public static void main(String[] args){
        SLList L = new SLList(2);
        L.addFirst(6);
        L.addFirst(5);
        L.reverse();
        System.out.println(L.first.item);
        System.out.println(L.first.next.item);
        System.out.println(L.first.next.next.item);
    }
}