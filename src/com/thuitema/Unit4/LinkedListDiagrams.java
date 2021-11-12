package com.thuitema.Unit4;

public class LinkedListDiagrams {
    public static void main(String[] args) {
        Q1();
        Q2();
        Q3();
        Q4();
        Q5();
    }

    public static void Q1() {
        ListNode<String> head = null;
        head = new ListNode<>("4", head);
        head = new ListNode<>("2", head);
        head = new ListNode<>("6", head);

        ListNode<String> current = head.getNext();

        current.setNext(new ListNode<>("1", current.getNext()));

        head.print();
    }

    public static void Q2() {
        ListNode<String> head = null;
        head = new ListNode<>("4", head);
        head = new ListNode<>("2", head);
        head = new ListNode<>("6", head);

        ListNode<String> current = head;
        for(int i = 0; i < 1; i++) {
            current = current.getNext();
        }

        current.setNext(new ListNode<>("1", null));

        head.print();
    }

    public static void Q3() {
        ListNode<String> head = null;
        head = new ListNode<>("4", head);
        head = new ListNode<>("2", head);
        head = new ListNode<>("6", head);
        ListNode<String> current = head;

        while(current.getNext() != null) {
            current = current.getNext();
        }
        current.setNext(new ListNode<>("1", null));
        head.print();
    }

    public static void Q4() {
        ListNode<String> head = null;
        head = new ListNode<>("4", head);
        head = new ListNode<>("2", head);
        head = new ListNode<>("6", head);
        head = head.getNext().getNext();

        head.print();
    }

    public static void Q5() {
        ListNode<String> head = null;
        head = new ListNode<>("1", null);
        head = new ListNode<>("4", head);
        head = new ListNode<>("2", head);
        head = new ListNode<>("6", head);
        ListNode<String> current = head;

        while(current.getNext() != null) {
            current = current.getNext();
        }
        current.setNext(null);

        head.print();
    }
}
