package com.thuitema.Unit4;

import java.util.List;

public class CLLReview {
    public static void main(String[] args) {
        ListNode<Integer> head = new ListNode<>(1, null);
        head.setNext(new ListNode<>(2, head));

        head = insertFirst(head, 3);
    }

    public static ListNode<Integer> insertFirst(ListNode<Integer> h, Integer val) {
        ListNode<Integer> first = new ListNode<>(val, null);
        if(h == null) {
            first.setNext(first);
            return first;
        }
        ListNode<Integer> temp = h;
        while(temp.getNext() != h) {
            temp = temp.getNext();
        }
        temp.setNext(first);
        first.setNext(h);
        return first;
    }

}
