package com.thuitema.Unit4;

import java.util.List;
import java.util.Scanner;

public class Pd6ThomasHuitemaListExploration {
    public static void main(String[] args) {
        ListNode<String> head = new ListNode<>("hello", null);

        head = new ListNode<>("foo", head);
        head = new ListNode<>("boo", head);
        head = new ListNode<>("nonsense", head);
        head = new ListNode<>("computer",
                new ListNode<>("science",
                        new ListNode<>("java",
                                new ListNode<>("coffee", head))));

        // The print(head) displays the same sequence.
        // So why do we call print two times here?
        // What do we want you to realize?
        print(head);
        //        print(head);

        /**** uncomment the code below for ListExploration extension  ****/

        System.out.println("First = " + first(head));
        System.out.println("Second = " + second(head));
        ListNode<String> p = pointerToLast(head);
        System.out.println("Pointer to Last = " + p.getValue() + " at " + p);
        ListNode<String> c = copyOfLast(head);
        System.out.println("Copy of Last = " + c.getValue() + " at " + c);

        Scanner in = new Scanner(System.in);
        System.out.print("Insert what? ");
        String x = in.next();
        head = insertFirst(head, x);
        head = insertLast(head, x);
        print(head);
    }

    public static void print(ListNode<String> head) {
        System.out.print("[");
        while (head != null) {
            System.out.print(head.getValue());
            head = head.getNext();
            if (head != null)
                System.out.print(", ");
        }
        System.out.println("]");
    }

    /* enter your code here */

    // Precondition: ListNode object given
    // Postcondition: returns the value of the first node in head
    public static String first(ListNode<String> head) {
        return head.getValue();
    }

    // Precondition: ListNode object given
    // Postcondition: returns value of second node in head
    public static String second(ListNode<String> head) {
        return rest(head).getValue();
    }

    // Precondition: ListNode object given
    // Postcondition: returns the last node of h
    public static ListNode<String> pointerToLast(ListNode<String> h) {
        if (h.getNext() == null) {
            return h;
        }
        return pointerToLast(h.getNext());
    }

    // Precondition: ListNode object given
    // Postcondition: returns a new ListNode object the last node
    public static ListNode<String> copyOfLast(ListNode<String> head) {
        return new ListNode<>(pointerToLast(head).getValue(), null);
    }

    // Precondition: ListNode object and String given
    // Postcondition: returns head with a node at the end, value is arg and points to null
    public static ListNode<String> insertFirst(ListNode<String> head, String arg) {
        return new ListNode<>(arg, head);
    }

    // Precondition:
    // Postcondition:
    public static ListNode<String> insertLast(ListNode<String> head, String arg) {
        if (head == null) {
            return new ListNode<>(arg, null);
        }
        ListNode<String> temp = head;
        while (temp.getNext() != null) {
            temp = temp.getNext(); // temp will be the last node of head, both share same address
        }

        temp.setNext(new ListNode<>(arg, null)); // will also setNext() for last node in head
        return head;
    }


    // HELPER METHODS
    public static ListNode<String> copyList(ListNode<String> arg) {
        if (arg == null) {
            return null;
        }
        return new ListNode<>(arg.getValue(), copyList(arg.getNext()));
    }

    public static ListNode<String> rest(ListNode<String> h) {
        if (h == null) {
            return null;
        }
        return copyList(h.getNext());
    }


} // ListExploration


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

/*
[computer, science, java, coffee, nonsense, boo, foo, hello]
First = computer
Second = science
Pointer to Last = hello at com.thuitema.Unit4.ListNode@1554909b
Copy of Last = hello at com.thuitema.Unit4.ListNode@6bf256fa
Insert what? what?
[what?, computer, science, java, coffee, nonsense, boo, foo, hello, what?]
 */