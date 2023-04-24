/*****************************************************************************************************************
 NAME: Thomas Huitema
 PERIOD: 6
 DUE DATE: 2/27/2022

 PURPOSE: Write methods to implement different strategies for collisions: Linear Probing, Rehash, and Chaining

 WHAT I LEARNED:
 Load factor is number of elements divided by the length of the array
 Load factor determines when the array should be resized, since insert() in a full array changes to O(n) from O(1)
 Euclid's algorithm is a very quick and recursive method to find the greatest common factor of 2 numbers
 Since LinkedList implements the List class, we can use get() to search a linked list in the HashtableChaining class
 add() and indexOf() are written very similarly, since we use a hash function to find the index where an element already is or should be inserted

 CREDITS (BE SPECIFIC: FRIENDS, PEERS, ONLINE WEBSITE):
 Evan Ho told me that he used Euclid's algorithm to find the greatest common factor between 2 numbers, so I read about
 the algorithm here: https://www.khanacademy.org/computing/computer-science/cryptography/modarithmetic/a/the-euclidean-algorithm

 Assignment:  This hashing program results in collisions.
 You are to implement three different collision schemes:
 linear probing, relative prime probing (use the first relatively prime
 number of the length of the hash table as the step increase), and
 chaining.  Then implement a search algorithm that is appropriate
 for each collision scheme.
 ***********************************************************************************/

package com.thuitema.Hashing;

import java.util.*;
import javax.swing.*;

public class Pd6ThomasHuitemaHashingShell {
    public static void main(String[] args) {
        int arrayLength = Integer.parseInt(JOptionPane.showInputDialog("Hashing!\n" + "Enter the size of the array:  ")); // enter 20

        int numItems = Integer.parseInt(JOptionPane.showInputDialog("Add n items:  ")); // enter 15

        int scheme = Integer.parseInt(JOptionPane.showInputDialog("The Load Factor is " + (double) numItems / arrayLength + "\nWhich collision scheme?\n" + "1. Linear Probing\n" + "2. Relatively Prime Probing\n" + "3. Chaining"));

        Hashtable table = null;
        switch (scheme) {
            case 1:
                table = new HashtableLinearProbe(arrayLength);
                break;
            case 2: // rehash using the first relatively prime or arrayLength
                table = new HashtableRehash(arrayLength);
                break;
            case 3:
                table = new HashtableChaining(arrayLength);
                break;
            default:
                System.exit(0);
        }

        for (int i = 0; i < numItems; i++)
            table.add("Item" + i);

        int itemNumber = Integer.parseInt(JOptionPane.showInputDialog("Search for:  Item 0" + " to " + "Item " + (numItems - 1)));

        while (itemNumber != -1) {
            String key = "Item" + itemNumber;
            int index = table.indexOf(key);
            if (index >= 0)    //found it
                System.out.println(key + " found at index " + index);
            else System.out.println(key + " not found!");

            itemNumber = Integer.parseInt(JOptionPane.showInputDialog("Search for:  Item 0" + " to " + "Item " + (numItems - 1)));
        }
        System.exit(0);
    }
}


interface Hashtable {
    void add(Object obj);

    int indexOf(Object obj);
}


class HashtableLinearProbe implements Hashtable {
    private Object[] array;

    // pre-condition: array[] is declared, size parameter is provided
    // post-condition: array is initialized with a size determined by the size parameter
    public HashtableLinearProbe(int size) {
        array = new Object[size];
    }

    // pre-condition: obj parameter is not null
    // post-condition: obj is hashed, then inserted into the array based on the linear probing hash function
    public void add(Object obj) {
        int code = obj.hashCode();
        int index = Math.abs(code % array.length);
        if (array[index] == null) { // empty
            // insert it
            array[index] = obj;
            System.out.println(obj + "\t" + code + "\t" + index);
        }
        else { // collision
            System.out.println(obj + "\t" + code + "\tCollision at " + index);
            index = linearProbe(index); // returns next index that is null
            array[index] = obj;
            System.out.println(obj + "\t" + code + "\t" + index);
        }
    }

    // pre-condition: index parameter is provided
    // post-condition: returns the next available index for insertion
    public int linearProbe(int index) {
        while (array[index] != null) { // loop to find next open array index
            index++;
            if (index == array.length) { // wrap around array
                index = 0;
            }
        }

        return index;
    }

    // pre-condition: obj parameter is not null
    // post-condition: returns the index of the object if it is in the array, otherwise returns -1
    public int indexOf(Object obj) {
        int index = Math.abs(obj.hashCode() % array.length);
        while (array[index] != null) {
            if (array[index].equals(obj)) // found it
            {
                return index;
            }
            else // use linear probe to search
            {
                index++;
                System.out.println("Looking at index " + index);
            }
        } // while

        // not found
        return -1;
    } // indexOf
} // HashtableLinearProbe


class HashtableRehash implements Hashtable {
    private Object[] array;
    private int constant = 2;

    // pre-condition: array and constant are declared
    // post-condition: array is initialized with size given by parameter, constant set to the lowest number
    // relatively prime to array.length
    public HashtableRehash(int size) {
        //constructor
        array = new Object[size];

        // find a constant that is relatively prime to the size of the array

        if(size == 1 || size == 2) {
            constant = 1;
        }

        // the lowest relatively prime number to any odd number is 2, so we just need to check even numbers
        else if(size % 2 == 0) {
            for(int i = 3; i < size; i++) { // already checked if size is 1 or 2, so start at 3
                if(gcf(size, i) == 1) { // relatively prime is gcf is 1
                    constant = i;
                    return;
                }
            }
        }
    }


    // pre-condition: a and b are provided
    // post-condition: returns the greatest common factor of a & b using Euclid's algorithm
    private int gcf(int a, int b) {
        if(b == 0) {
            return a;
        }
        return gcf(b, a % b); // b is now the remainder of a/b
    }


    // pre-condition:obj parameter is not null
    // post-condition: obj is hashed, then inserted into array based on rehash function
    public void add(Object obj) {
        int code = obj.hashCode();
        int index = Math.abs(code % array.length);

        if (array[index] == null) // empty
        {
            // insert it
            array[index] = obj;
            System.out.println(obj + "\t" + code + "\t" + index);
        }
        else // collision
        {
            System.out.println(obj + "\t" + code + "\tCollision at " + index);
            index = rehash(index); // computes next available index
            array[index] = obj;
            System.out.println(obj + "\t" + code + "\t" + index);
        }
    }

    // pre-condition: index parameter is provided
    // post-condition: returns the next available index determined by rehash function
    public int rehash(int index) {
        int i = 1;
        while (array[index] != null) {
            index = (index + i * constant) % array.length;
            i++;
        }
        return index;
    }

    // pre-condition: obj is not null
    // post-condition: returns the index of the object if it exists within the array, otherwise returns -1
    public int indexOf(Object obj) {
        int index = Math.abs(obj.hashCode() % array.length);
        int i = 1;
        while (array[index] != null) {
            if (array[index].equals(obj)) // found it
            {
                return index;
            }
            else // search for it in a rehashing manner
            {
                index = (index + i * constant) % array.length;
                i++;
                System.out.println("Looking at index " + index);
            }
        }

        //not found
        return -1;
    }
} // HashTableRehash


class HashtableChaining implements Hashtable {
    private LinkedList[] array;

    // pre-condition: array is declared
    // post-condition: initialized array with size determined by parameter, initializes each index as a Linked List object
    public HashtableChaining(int size) {
        // instantiate the array
        array = new LinkedList[size];

        // instantiate the LinkedLists
        for (int i = 0; i < array.length; i++) {
            array[i] = new LinkedList<>();
        }

    }

    // pre-condition: obj parameter is not null
    // post-condition: obj is hashed, then inserted to the front of the linked list at its array index
    public void add(Object obj) {
        int code = obj.hashCode();
        int index = Math.abs(code % array.length);
        array[index].addFirst(obj);
        System.out.println(obj + "\t" + code + " " + " at " + index + ": " + array[index]);
    }

    // pre-condition: obj parameter is not null
    // post-condition: returns the index of the object if it is in the array, otherwise returns -1
    public int indexOf(Object obj) {
        int index = Math.abs(obj.hashCode() % array.length);

        if (!array[index].isEmpty()) { // linked list at index contains value(s)
            if (array[index].getFirst().equals(obj)) { // at front of linked list
                return index;
            }

            else // search for it in a chaining manner
            {
                for (int i = 1; i < array[index].size(); i++) { // start at 1 because we've already checked the first element
                    if (array[index].get(i).equals(obj)) {
                        return index;
                    }
                }
            }
        }
        // not found
        return -1;
    } // indexOf
} // HashtableChaining

/*
Linear Probing (size = 10, insert 8 items)
Item0	70973277	7
Item1	70973278	8
Item2	70973279	9
Item3	70973280	0
Item4	70973281	1
Item5	70973282	2
Item6	70973283	3
Item7	70973284	4
Item3 found at index 0
Item0 found at index 7

Rehash (size = 10, insert 8 items)
Item0	70973277	7
Item1	70973278	8
Item2	70973279	9
Item3	70973280	0
Item4	70973281	1
Item5	70973282	2
Item6	70973283	3
Item7	70973284	4
Item7 found at index 4
Item2 found at index 9

Chaining (size = 20, insert 16 items)
Item0	70973277  at 17: [Item0]
Item1	70973278  at 18: [Item1]
Item2	70973279  at 19: [Item2]
Item3	70973280  at 0: [Item3]
Item4	70973281  at 1: [Item4]
Item5	70973282  at 2: [Item5]
Item6	70973283  at 3: [Item6]
Item7	70973284  at 4: [Item7]
Item8	70973285  at 5: [Item8]
Item9	70973286  at 6: [Item9]
Item10	-2094795630  at 10: [Item10]
Item11	-2094795629  at 9: [Item11]
Item12	-2094795628  at 8: [Item12]
Item13	-2094795627  at 7: [Item13]
Item14	-2094795626  at 6: [Item14, Item9]
Item15	-2094795625  at 5: [Item15, Item8]
Item9 found at index 6
Item15 found at index 5
Item3 found at index 0
 */
