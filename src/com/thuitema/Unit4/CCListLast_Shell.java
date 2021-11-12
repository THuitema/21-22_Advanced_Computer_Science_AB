package com.thuitema.Unit4;

/*
   starts at lastNode, which is the LAST node

   a --> b --> c --> lastNode -->
*/

public class CCListLast_Shell<E> {
    private ListNode<E> lastNode;

    public void addFirst(E v) {
        if (lastNode == null) { // adding node to empty list
            lastNode = new ListNode<E>(v, null);
            lastNode.setNext(lastNode);
        } else {
            lastNode.setNext(new ListNode<>(v, lastNode.getNext()));
        }
    } // addFirst

    public void addLast(E v) {
        if (lastNode == null) {
            lastNode = new ListNode<E>(v, null);
            lastNode.setNext(lastNode);
        } else {
            lastNode.setNext(new ListNode<>(v, lastNode.getNext()));
            lastNode = lastNode.getNext();
        }
    } // addLast

    public int size() {
        if(lastNode == null) {
            return 0;
        }
        int count = 1; // if the list isn't empty, there's at least 1 node
        ListNode<E> temp = lastNode;
        while(temp.getNext() != lastNode) {
            count++;
            temp = temp.getNext();
        }
        return count;
    }

    // post: returns the removed node
    public E removeFirst() {
        E value;
        if(lastNode == null) {
            return null;
        }
        else if (lastNode.getNext() == lastNode) {
            value = lastNode.getValue();
            lastNode = null;
            return value;
        }
        value = lastNode.getValue();
        lastNode.setNext(lastNode.getNext().getNext());
        return value;
    } // removeFirst

    // post: returns the removed node
    public E removeLast() {
        if(lastNode == null) {
            return null;
        }
        else if(lastNode.getNext() == lastNode) {
            E value = lastNode.getValue();
            lastNode = null;
            return value;
        }
        E value = lastNode.getValue();
        ListNode<E> temp = lastNode;
        while(temp.getNext() != lastNode) {
            temp = temp.getNext();
        }
        temp.setNext(lastNode.getNext());
        lastNode = temp;
        return value;
    }

    // post: returns the string representation of the circular list
    //       (from head to tail)
    public String toString() {
        String out = "[";
        if (lastNode != null) {
            ListNode<E> temp = lastNode.getNext();
            do {
                out += temp.getValue();
                if (temp.getNext() != lastNode.getNext()) {
                    out += ", ";
                }
                temp = temp.getNext();
            } while (temp != lastNode.getNext());
        }
        out += "]";
        return out;
    }

    public static void main(String[] args) {
        CCListLast_Shell<Integer> myList = new CCListLast_Shell<>();

        myList.addFirst(9);
        myList.addFirst(10);
        myList.addFirst(11);
        myList.addFirst(1);
        myList.addLast(99);
        System.out.println("The CCList: \n" + myList);
        myList.removeLast();

        System.out.println("The CCList: \n" + myList);
        System.out.println("List Size: " + myList.size());

        myList.removeFirst();
        System.out.println("The CCList: \n" + myList);
        System.out.println("List Size: " + myList.size());


    } // main

} // CCList

//class ListNode <E>
//{
//   private E value;
//   private ListNode <E>  next;
//   public ListNode  (E  initValue, ListNode <E> initNext)
//   {
//      value = initValue;
//      next = initNext;
//   }
//   public E getValue()
//   {
//      return value;
//   }
//
//   public ListNode <E> getNext()
//   {
//      return next;
//   }
//
//   public void setValue(E theNewValue)
//   {
//      value = theNewValue;
//   }
//
//   public void setNext(ListNode  <E> theNewNext)
//   {
//      next = theNewNext;
//   }
//
//}  // end of ListNode