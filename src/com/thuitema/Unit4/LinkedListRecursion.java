package com.thuitema.Unit4;

import java.util.List;

public class LinkedListRecursion
{
   public static void main(String[] args) {
      ListNode<Integer> head = new ListNode<>(7, null);
      head = new ListNode<>(5, head);
      head = new ListNode<>(3, head);
      head = new ListNode<>(1, head);

      System.out.println("Original List: ");
      head.print();

//      System.out.println("Delete specified value");
//      ListNode<Integer> deleteValue = delete(head, 2);
//      deleteValue.print(deleteValue);

//      System.out.println("Delete last element: ");
//      ListNode<Integer> deleted = deleteLast(head);
//      deleted.print(deleted);

//      ListNode<Integer> list2 = new ListNode<>(8, null);
//      list2 = new ListNode<>(7, list2);
//      list2 = new ListNode<>(6, list2);
//      list2 = new ListNode<>(5, list2);
//
//      ListNode<Integer> appended = append(head, list2);
//      appended.print(appended);

//      ListNode<Integer> inserted = insertInOrder(head, 6);
//      inserted.print();
   }

   public static <E> ListNode<E> deleteLast(ListNode<E> h) {
      if(h == null) {
         return null;
      }
      if(h.getNext() == null) {
         return null;
      }
      h.setNext(deleteLast(h.getNext()));
      return h;
   }

   public static <E> ListNode<E> delete(ListNode<E> h, E value) {
      if(h == null) {
         return null;
      }

      if(h.getValue() == value) {
         return h.getNext();
      }

      h.setNext(delete(h.getNext(), value));
      return h;
   }

   public static <E> ListNode<E> append(ListNode<E> list1, ListNode<E> list2) {
      // loop through list1 until null, then loop through list2 until null
      if(list1 == null) {
         if(list2 == null) {
            return null;
         }
         list2.setNext(append(null, list2.getNext()));
         return list2;
      }
      list1.setNext(append(list1.getNext(), list2));
      return list1;

   }

   public static ListNode<Integer> insertInOrder(ListNode<Integer> h, Integer value) {
      if(h == null) { // if it reaches the end of the list w/o inserting, value must be the greatest element
         return new ListNode<>(value, null);
      }
      if(h.getNext() == null) {
         h.setNext(new ListNode<>(value, null));
         return h;
      }
      if(value >= h.getValue() && value <= h.getNext().getValue()) {
         h.setNext(new ListNode<>(value, h.getNext()));
         return h;
      }
      h.setNext(insertInOrder(h.getNext(), value));
      return h;
   }
}
