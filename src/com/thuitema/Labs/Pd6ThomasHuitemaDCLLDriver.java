package com.thuitema.Labs;

/***********************************************************************************************************************************************
 * Name: Thomas Huitema
 * Period: 6
 * Name of the Lab: Doubly Circular Linked Lists (with a dummy header node)
 * Purpose of the Program: write methods for the DCLL class
 * Due Date: 11/16/2021
 * Date Submitted:
 *
 * What I learned:
 *  The dummy header node is like any other node, but its not considered as part of the actual list
 *  You get the first node by doing "head.getNext()" and the last node by doing "head.getPrev()"
 *  The dummy header node was helpful because you didn't have to check if the DCLL was null
 *  When dealing with an index parameter, it's important to check if it's out of bounds. If so, throw an IndexOutOfBoundsException
 *
 * How I feel about this lab:
 *  I really enjoyed this lab because there weren't any parts that were extremely easy to do, but nothing impossible either. It provided the right challenge for me.
 *  Each method presented a different challenge from the others, which I liked. I think the most important part of understanding and completing the lab
 *  was to draw out each problem. For every method I would write, I drew a DCLL and its pointers, then drew the new connections & nodes based on the method
 *  I was writing. Also, I believe the hardest part was in the beginning when I was trying to understand how a dummy header node worked with a DCLL. After I
 *  understood it, I was confident in completing the lab.
 *
 * What I wonder:
 *  Are there any situations where a singular, circular, or doubly-linked list is better than a doubly circular linked list?
 *  What are the real-life applications of linked lists?
 *  Are there any other types of linked lists besides singular, doubly, circular, and doubly circular?
 *
 * Student(s) who helped me (to what extent): 
 *************************************************************************************************************************************************/

/**
 * NOTE: in methods involving an index parameter, the "first" node (node after dummy header) is index 1. remove(1) would remove the node head is pointing to
 **/

/*
TODO:
  - add(int index, E obj)
  - remove(int index)
  - put output at bottom
*/

class Pd6ThomasHuitemaDCLL<E> {
    private int size;
    private DLNode<E> head = new DLNode<>(); // dummy node - very useful - simplifies the code

    /* pre: List is provided
       post: return size */
    public int size() {
        return size;
    }

    /* appends obj to end of list; increases size;
          @return true  */
    public boolean add(E obj) {
        // point last node to new node, set prev of head to new node
        DLNode<E> newLast = new DLNode<E>(obj, head.getPrev(), head);
        head.getPrev().setNext(newLast);
        head.setPrev(newLast);
        size++;
        return true;
    } // add


    // inserts obj at position index.  increments size.
    public void add(int index, E obj) {
        if (index < 1 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        size++;
    } // add

    // inserts obj at front of list; increases size;
    public void addFirst(E obj) {
        DLNode<E> newFirst = new DLNode<>(obj, head, head.getNext());
        head.getNext().setPrev(newFirst);
        head.setNext(newFirst);
        size++;
    } // addFirst


    // appends obj to end of list; increases size;
    public void addLast(E obj) {
        // point last node to new node, set prev of head to new node
        DLNode<E> newLast = new DLNode<E>(obj, head.getPrev(), head);
        head.getPrev().setNext(newLast);
        head.setPrev(newLast);
        size++;
    } // addLast

    /* removes the node from position index, decrements size.
          @return the object at position index.
        */
    public E remove(int index) {
        if (index < 1 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        size--;
        return null;
    } // remove

    /* pre: List is provided
       post: remove last value and return that value*/
    public E removeLast() {
        if (head.getNext() == head) { // don't want to delete dummy header if the list is empty
            return null;
        }
        E value = head.getPrev().getValue();
        head.setPrev(head.getPrev().getPrev());
        head.getPrev().setNext(head);
        size--;
        return value;
    } // removeLast

    /* pre: List is provided
       post: remove first value and return that value */
    public E removeFirst() {
        if (head.getNext() == head) {
            return null;
        }
        E value = head.getNext().getValue();
        head.setNext(head.getNext().getNext());
        head.getNext().setPrev(head);
        size--;
        return value;
    } // removeFirst

    // replaces obj at position index.
    public void set(int index, E obj) {
        if (index < 1 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        DLNode<E> temp = head;
        for (int c = 1; c <= index; c++) {
            temp = temp.getNext();
        }
        temp.setValue(obj);


    } // set

    public E get(int index) {
        if (index < 1 || index > size) {
            //            return null;
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        DLNode<E> temp = head;
        for (int c = 1; c <= index; c++) {
            temp = temp.getNext();
        }
        return temp.getValue();
    } // get

    /* pre: List is provided
       post: return first value */
    public E getFirst() {
        return head.getNext().getValue(); // list is empty
    } // getFirst


    /* pre: List is provided
       post: return last value */
    public E getLast() {
        return head.getPrev().getValue(); // list is empty
    } // getLast


    /* pre: List is provided
       post: print out the list provided*/
    public String toString() {
        String out = "[";
        DLNode<E> temp = head.getNext();

        do {
            out += temp.getValue();
            temp = temp.getNext();
            if (temp != head) {
                out += ", ";
            }
        } while (temp != head);

        out += "]";
        return out;
    } // toString
}  // DCLL

public class Pd6ThomasHuitemaDCLLDriver {
    public static void main(String[] args) {
        Pd6ThomasHuitemaDCLL<String> list = new Pd6ThomasHuitemaDCLL<>();

        list.addLast("Apple");
        list.addLast("Banana");
        list.addLast("Cucumber");
        list.add("Dumpling");
        list.add("Escargot");
        System.out.println(list);
        System.out.println("Size: " + list.size());

        //        Object obj = list.remove(3);
        //        System.out.println(list);

        //        System.out.println("Size: " + list.size());
        //        System.out.println("Removed " + obj);
        //
        //        System.out.print("Add at 3:   ");
        //        list.add(3, "Cheese");
        //        System.out.println(list);
        //
        //        System.out.println("Get values at 1 and first: " + list.get(1) + " and " + list.getFirst());
        //        System.out.println("No change: " + list);
        //
        //        System.out.println(list.getFirst() + " " + list.getLast());
        //        System.out.println(list.removeLast() + " is now removed!");
        //        System.out.println(list);
        //
        //        System.out.print("Add first: ");
        //        list.addFirst("Anchovie");
        //        System.out.println(list);
        //        System.out.println("Size: " + list.size());
        //
        //                System.out.print("Set the second:  ");
        //                list.set(2, "Bread");
        //                System.out.println(list);
        System.out.println(list.get(10));
    } // main
}

class DLNode<E> {
    private E value;
    private DLNode prev;
    private DLNode next;

    public DLNode(E arg, DLNode<E> prev, DLNode<E> next) {
        value = arg;
        this.prev = prev;
        this.next = next;
    }

    public DLNode() {
        value = null;
        next = this;
        prev = this;
    }

    public void setValue(E arg) {
        value = arg;
    }

    public void setNext(DLNode<E> arg) {
        next = arg;
    }

    public void setPrev(DLNode<E> arg) {
        prev = arg;
    }

    public DLNode<E> getNext() {
        return next;
    }

    public DLNode<E> getPrev() {
        return prev;
    }

    public E getValue() {
        return value;
    }
}  // DLNode
   


/*
  ----jGRASP exec: java Pd2MinJiKangDLL
  Apple Banana Cucumber Dumpling Escargot
 Size: 5
  Apple Banana Cucumber Escargot
 Size: 4
 Removed Dumpling
 Add at 3:    Apple Banana Cucumber Cheese Escargot
 Get values at 1 and first: Banana and Apple
 No change:  Apple Banana Cucumber Cheese Escargot
 Escargot is now removed!
  Apple Banana Cucumber Cheese
 Add first:   Anchovie Apple Banana Cucumber Cheese
 Size: 5
 Set the second:   Anchovie Bread Banana Cucumber Cheese
 
  ----jGRASP: operation complete.
 
*/