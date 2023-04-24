package com.thuitema.Unit4;

public class Review<E> {
    public static void main(String[] args) {
        ListNode<Integer> nums = new ListNode<>(1, null);
        nums = insertFirst(nums, 1);
        nums = insertFirst(nums, 3);
        nums = insertFirst(nums, 2);
        //        nums = insertLast(nums, 4);
//        nums = deleteFirst(nums);
//        nums = deleteLast(nums);
        nums = deleteOccur(nums, 1);
        nums.print();

    }

    public static ListNode<Integer> insertFirst(ListNode<Integer> h, Integer obj) {
        return new ListNode<>(obj, h);
    }

    public static ListNode<Integer> insertLast(ListNode<Integer> h, Integer obj) {
        if(h == null) {
            return new ListNode<>(obj, null);
        }
        else {
            h.setNext(insertLast(h.getNext(), obj));
            return h;
        }
    }

    public static ListNode<Integer> deleteFirst(ListNode<Integer> h) {
        if(h == null || h.getNext() == null) {
            return null;
        }
        return h.getNext();
    }

    public static ListNode<Integer> deleteLast(ListNode<Integer> h) {
        if(h == null || h.getNext() == null) {
            return null;
        }
        h.setNext(deleteLast(h.getNext()));
        return h;
    }

    public static ListNode<Integer> deleteOccur(ListNode<Integer> h, Integer val) {
        if(h == null) {
            return null;
        }
        if(h.getValue().equals(val)) {
            return h.getNext();
        }
        h.setNext(deleteOccur(h.getNext(), val));
        return h;
    }
}
