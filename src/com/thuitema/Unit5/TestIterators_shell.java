package com.thuitema.Unit5;/*
Purpose: This program creates a "pseudo collection" class that implements Iterable. Through this program,
         we want to confirm the fact that once a class implements Iterable, we can traverse an object of
         that class by either using an iterator or a for-each loop
*/
import java.util.*;

// Step 1: have the class implements Iterable
public class TestIterators_shell <E> implements Iterable <E>   
{
   // Step 2: determine the internal representation of the class Test
   private E [] list ;      
   
   public TestIterators_shell ()
   {
      list = (E[]) new Object [10];   // Java does not allow us to do list = new E [10] directly
      for (int i = 0; i < list.length; i++)
      {
         list[i] = (E) new Integer (i);
      }
   }
   
   // Step 3: implement the abstract method declared in the interface Iterable
   public Iterator <E> iterator () 
   {
      return new MyIterator<>();
   }
   
   // Step 4: Define an Iterator class that implements Iterator
   //   MyIterator must implements the 3 methods declared in Iterator interface
   private class MyIterator <E> implements Iterator <E>
   {
      // Step 5: index or other instance variable that is used to keep track of the next element
      //             to be processed
      private int index = 0;
      private int cursor; // index of next element to return
      private int lastRet; // index of last element returned

      public boolean hasNext()
      {
         return list.length - 1 < index;
      }

      public E next()
      {
         return (E) list[++index];
      }


      public void remove()
      {
        /* to be implemented */
      }
   }  // MyIterator
   
   //  Iterate over the object that implements Iterable using an iterator object and for-each loop
   public static void main (String [] args)
   {
      TestIterators_shell <Integer> t = new TestIterators_shell <Integer>();
      
      Iterator <Integer> iter = t.iterator();
      System.out.println ("Traversing t using an iterator: ");
      while (iter.hasNext())
      {
         System.out.print (iter.next() + " ");
      }
      
      // Step 6: Once you have the class Test implements Iterable, you can go ahead to use
      //              for-each loop to process object of type Test!!!
      System.out.println ("\n\nTraversing t using a for-each loop: ");
      for (int p: t)
         System.out.print (p + " ");
      
   } // main
      
}  // Test

   /************************* outputs  ************************************************
    Traversing t using an iterator: 
 0 1 2 3 4 5 6 7 8 9 
 
 Traversing t using a for-each loop: 
 0 1 2 3 4 5 6 7 8 9 

   
   ***********************************************************************************/
   
