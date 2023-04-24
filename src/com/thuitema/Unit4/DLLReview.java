package com.thuitema.Unit4;

public class DLLReview {
    public static void main(String[] args) {
        DLNode<Integer> head = new DLNode<>(1, null, null);
        head.setNext(new DLNode<>(2, head, null));


        head = insertLast(head, 3);
        head = insertLast(head, 4);
        head = insertLast(head, 5);

        head = removeNode(head, 5);

//        head = deleteFirst(head);
//        head = deleteLast(head);

    }

    public static DLNode<Integer> removeNode(DLNode<Integer> head, Integer v) {
        if(head == null) { // list is empty
            return null;
        }
        DLNode<Integer> prev = null;
        DLNode<Integer> curr = head;

        while(curr.getNext() != null && !curr.getValue().equals(v)) { // loop until last node or value
            prev = curr;
            curr = curr.getNext();
        }

        if(prev == null) { // remove first node
            curr.getNext().setPrev(null);
            head = head.getNext();
        }
        else if(curr.getNext() == null) { // remove last node
            prev.setNext(null);
        }
        else { // remove node in middle
            curr.getNext().setPrev(prev);
            prev.setNext(curr.getNext());
        }

        return head;
    }

    public static DLNode<Integer> insertLast(DLNode<Integer> h, Integer val) {
        if(h == null) {
            return new DLNode<>(val, null, null);
        }
        if(h.getNext() == null) {
            h.setNext(new DLNode<>(val, h, null));
            return h;
        }
        h.setNext(insertLast(h.getNext(), val));
        return h;
    }

    public static DLNode<Integer> deleteFirst(DLNode<Integer> h) {
        if(h == null || h.getNext() == null) {
            return null;
        }
        h.getNext().setPrev(null);
        return h.getNext();
    }

    public static DLNode<Integer> deleteLast(DLNode<Integer> h) {
        if(h == null || h.getNext() == null) {
            return null;
        }
        h.setNext(deleteLast(h.getNext()));
        return h;
    }
}



class DLNode<E> {
    private E value;
    private DLNode<E> prev;
    private DLNode<E> next;

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
