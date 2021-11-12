package com.thuitema.Unit4;

import java.util.List;

public class LinkedListIterative<E> {
    public static void main(String[] args) {
        ListNode<Integer> head = new ListNode<>(7, null);
        head = new ListNode<>(5, head);
        head = new ListNode<>(3, head);
        head = new ListNode<>(1, head);

        ListNode<Integer> head1 = new ListNode<>(13, null);
        head1 = new ListNode<>(12, head1);
        head1 = new ListNode<>(11, head1);
        head1 = new ListNode<>(10, head1);

        swap(head, head1);

        System.out.println("Head (swapped): ");
        head.print();

        System.out.println("Head1 (swapped): ");
        head1.print();
//        System.out.println("Original List: ");
//        head.print(head);
//
//        ListNode<Integer> c = copy(head);
//        System.out.println("Copied: ");
//        c.print(c);
    }

    public static <E> ListNode<E> copy(ListNode<E> h) {
        if(h == null) {
            return null;
        }
        ListNode<E> c = new ListNode<>(h.getValue(), null);
        ListNode<E> temp = c;

        for(h = h.getNext(); h!= null; h = h.getNext()) {
            temp.setNext(new ListNode<>(h.getValue(), null));
            temp = temp.getNext();

        }
        return c;
    }

    public static <E> void swap(ListNode<E> a, ListNode<E> b) {
        while(a != null && b != null) {
            E temp = a.getValue();
            a.setValue(b.getValue());
            b.setValue(temp);
            a = a.getNext();
            b = b.getNext();
        }
    }
}
