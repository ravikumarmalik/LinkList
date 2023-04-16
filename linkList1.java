import java.util.LinkedList;

public class linkList1 {
    public static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }


    public static Node head;
    public static Node tail;
    public static int size;//by-default java initialised as 0



    //Add first node in LL
    public void addFirst(int data){//O(1)
        //1. create new node
        Node newNode=new Node(data);
        size++;
        if (head == null){
            head=tail=newNode;
            return;
        }
        //2.newNode next->head
        newNode.next=head;//linking

        //3. head=newNode
        head=newNode;
    }

    public void addLast(int data){//O(1)
        Node newNode=new Node(data);
        size++;
        if (head==null){//by convension if head is null then empty linkList
            head=tail=newNode;
            return;
        }
        tail.next=newNode;
        tail=newNode;
    }

    public void print(){
        Node temp=head;
        while (temp !=null){
            System.out.print(temp.data+"-->");
            temp=temp.next;
        }
        System.out.println("null");
    }




    //addMiddle element
    public void addMiddle(int index,int data){
        if (index==0){
            addFirst(data);
            return;
        }
        Node newNode=new Node(data);
        size++;
        Node temp=head;
        int i=0;
        while (i<index-1){
            temp=temp.next;
            i++;
        }
        //i=index-1; temp-->prev
        newNode.next=temp.next;
        temp.next=newNode;
    }






    //remove first element
    public int removeFirst() {
        if (size==0){
            System.out.println("empty");
            return Integer.MIN_VALUE;
        }else if (size==1){
            int val=head.data;
            head=tail=null;
            size=0;

            return val;
        }
        int val=head.data;
        head=head.next;
        size--;
        return val;
    }



    //remove last element from LL
    public int removeLast(){
        if (size==0){
            System.out.println("LL is empty");
            return Integer.MIN_VALUE;
        } else if (size==1) {
            int value=head.data;
            head=tail=null;
            size=0;
            return value;
        }
        //prev : i=size-2
        Node prev=head;
        for (int i=0;i<size-2;i++){
            prev=prev.next;
        }
        int value=prev.next.data;//tail.data
        prev.next=null;
        tail=prev;
        size--;
        return value;
    }



    public int itrSearch(int key){
        Node temp=head;
        int i=0;
        while (temp != null){
            if (temp.data==key){
                return i;
            }
            temp=temp.next;
            i++;
        }
        return -1;

    }

    public int helperSearch(Node head, int key){
        if (head==null){
            return -1;
        }
        if (head.data==key){
            return 0;
        }
        int index=helperSearch(head.next,key);
        if (index==-1){
            return -1;
        }
        return index+1;
    }
    public int recSearch(int key){
        return helperSearch(head,key);

    }



    public void reverse(){
        Node prev=null;
        Node current=tail=head;
        Node next;
        while (current !=null){
            next=current.next;
            current.next=prev;
            prev=current;
            current=next;
        }
        head=prev;
    }



    public void deleteNthFromEnd(int n){
        //calculate size
        int size=0;
        Node temp=head;
        while (temp !=null){
            temp= temp.next;
            size++;
        }
        if (n==size){
            head=head.next;//remove 1st element
            return;
        }
        //size-1
        int i=1;
        int iToFind=size-n;
        Node prev=head;
        while (i<iToFind){
            prev=prev.next;
            i++;
        }
        prev.next=prev.next.next;
        return;

    }


    public Node findMiddle(Node head){//find middle Node
        Node slow =head;
        Node fast=head;

        while (fast !=null && fast.next !=null){
            slow=slow.next; //+1;
            fast=fast.next.next; //+2
        }
        return slow;//slow my middle
    }

    public boolean isPalindrome(){
        //base case
        if (head==null && head.next == null){
            return true;
        }
        //step 1-find mid
        Node midNode=findMiddle(head);

        //step2 reverse 2nd half
        Node prev=null;
        Node current=midNode;
        Node next;

        while (current !=null){
            next=current.next;
            current.next=prev;
            prev=current;
            current=next;
        }

        Node right =prev;///right ka head
        Node left=head;

        //step3 check left half and right half
        while (right != null){
            if (left.data !=right.data){
                return false;
            }
            left=left.next;
            right=right.next;
        }
        return true;
    }




    //to find cycle in LL
    public static boolean isCycle() {
        Node slow=head;
        Node fast=head;
        while (fast !=null && fast.next !=null) {
            slow=slow.next;//+1
            fast=fast.next.next;//+2

            if (slow==fast){
                return true; //cycle exist
            }
        }
        return false;//cycle does not exist.
    }




    //remove cycle
    public static void removeCycle() {
        //detect cycle
        Node slow =head;
        Node fast=head;
        boolean cycle=false;
        while (fast !=null && fast.next !=null){
            slow=slow.next;
            fast=fast.next.next;
            if (slow==fast){
                cycle=true;
                break;
            }
        }
        if (cycle==false){
            return;
        }
        //find meeting point
        slow=head;
        Node prev=null;//last node
        while (slow !=fast){
            prev=fast;
            slow=slow.next;
            fast=fast.next;
        }
        //remove cycle last.next=null
        prev.next=null;

    }






    //merge sort in LL
    private Node getMid(Node head) {
        Node slow = head;
        Node fast = head.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;//mid node
    }

    private Node merge(Node head1,Node head2) {
        Node mergedLL = new Node(-1);//dummy node
        Node temp = mergedLL;

        while (head1 != null && head2 != null) {
            if (head1.data <= head2.data) {
                temp.next = head1;
                head1= head1.next;
                temp = temp.next;
            } else {
                temp.next = head2;
                head2 = head2.next;
                temp = temp.next;
            }

        }

        while (head1 != null ){
            temp.next=head1;
            head1=head1.next;
            temp=temp.next;
        }

        while (head2 != null){
            temp.next=head2;
            head2=head2.next;
            temp=temp.next;
        }
        return mergedLL.next;
    }

    public Node mergeSort(Node head) {
        if (head==null || head.next ==null){
            return head;
        }
        //to find mid
        Node mid =getMid(head);

        //merge sort call for left and right sight
        Node rightHead= mid.next;
        mid.next=null;
        Node newLeft=mergeSort(head);
       Node newRight= mergeSort(rightHead);

        //merge
        return merge(newLeft,newRight);
    }




    public void zigZag(){
        //to find middle
        Node slow=head;
        Node fast=head.next;

        while (fast !=null && fast.next != null){
            slow=slow.next;
            fast=fast.next.next;
        }
        Node mid=slow;

        //reverse 2nd half
        Node current=mid.next;
        mid.next=null;
        Node pre=null;
        Node next;
        while (current != null){
            next=current.next;
            current.next=pre;
            pre=current;
            current=next;
        }

        Node left=head;
        Node right=pre;
        Node nextLeft,nextRight;

        //zig-zag merge
        while (left !=null && right !=null){
            nextLeft=left.next;
            left.next=right;
            nextRight=right.next;
            right.next=nextLeft;
            left=nextLeft;
            right=nextRight;
        }
    }




    public static void main(String[] args) {
       linkList1 ll = new linkList1();
//        ll.addLast(1);
//        ll.addLast(2);
//        ll.addLast(2);
//        ll.addLast(1);

//        ll.addMiddle(2,2);
//        ll.print();

//        ll.removeFirst();
//        ll.print();

//        ll.removeLast();
//        ll.print();

//        System.out.println("at index:"+ll.itrSearch(3));
//        System.out.println(ll.itrSearch(20));

//        System.out.println("at index:"+ll.recSearch(3));
//        System.out.println(ll.recSearch(19));

//        ll.reverse();
//        ll.print();

//        ll.deleteNthFromEnd(3);
//        ll.print();

//        System.out.println("size of LL:"+ll.size);


//        ll.print();
//        System.out.println(ll.isPalindrome());



//        head=new Node(1);
//        head.next=new Node(2);
//        head.next.next=new Node(3);
//        head.next.next.next=head;
       //1->2->3->1
//        System.out.println(isCycle());//true


//        head=new Node(1);
//        Node temp=new Node(2);
//        head.next=temp;
//        head.next.next=new Node(3);
//        head.next.next.next=temp;
//        //1->2->3->2;
//        System.out.println(isCycle());//true
//        removeCycle();
//        System.out.println(isCycle());//false



//        ll.addFirst(1);
//        ll.addFirst(2);
//        ll.addFirst(3);
//        ll.addFirst(4);
//        ll.addFirst(5);
//        //5->4->3->2->1
//        ll.print();
//        ll.head=ll.mergeSort(ll.head);
//        ll.print();


        ll.addLast(1);
        ll.addLast(2);
        ll.addLast(3);
        ll.addLast(4);
        ll.addLast(5);
        ll.addLast(6);
        //1->2->3->4->5->6

        ll.print();
        ll.zigZag();
        ll.print();









    }
}

/*

1-->2-->9-->3-->4-->null
2-->9-->3-->4-->null//head remove
2-->9-->3-->null//tail remove
at index:2
-1
at index:2
-1
3-->9-->2-->null
9-->2-->null
size of LL:3(size after remove head and tail element)

 */
