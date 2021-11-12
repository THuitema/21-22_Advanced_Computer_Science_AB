package com.thuitema.Unit4;

public class ListNode <E> {
    private E value;
    private ListNode <E> next;

    public ListNode(E value, ListNode<E> next) {
        this.value = value;
        this.next = next;
    }

    public E getValue() {
        return value;
    }

    public ListNode<E> getNext() {
        return next;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public void setNext(ListNode<E> next) {
        this.next = next;
    }

    public void print() {
        System.out.print("[");
        ListNode<E> temp = this;

        while(temp != null) {
            System.out.print(temp.getValue());
            temp  = temp.getNext();
            if(temp != null) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}
