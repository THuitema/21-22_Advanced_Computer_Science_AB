/**************************************************************************************
 Name: Thomas Huitema
 Date: 2/8/22
 What I learned:
 Inserted objects are initially placed at the end of the heap, then reheaped up to the correct position
 The root of the heap is removed when deleting
 The rightmost leaf of a heap is the last index of its array representation
 Divide heap child index by 2 to get parent index
 Creating a swap() helper method removed redundancy from my code in reheapUp() and reheapDown()
 In reheapUp() we must stop heaping up when we reach the first index (root), or we will get a NullPointerException

 How I feel about this lab:
 I think most of the lab was pretty simple, but the reheapDown() method proved most challenging to write. This is due to the fact that you have to
 compare 3 different values, which can make the method complex rather quickly. However, after trial and error, and many drawings on my whiteboard,
 I was able to successfully write reheapDown() and all other methods in the lab. The difficulty in this lab wasn't so much writing the code itself,
 but just understanding the concepts of a priority queue implemented in a heap, especially heaping up and down to perform insertion & deletion.
 I think by now having a solid understanding of heaps, heapsort will be easier to understand as well.

 I am wondering (the what-if moment):
 Why are heaps implemented in arrays when they are visualized as tree structures? Because of time complexity?


 Credits: NONE
 ***************************************************************************************/

package com.thuitema.PriorityQueues_Heaps;



public class Pd6ThomasHuitemaHeapPriorityQueue<E extends Comparable<E>> {
    private static final int DEFAULT_CAPACITY = 1024;
    private Comparable[] items; // use a 1-D array instead of ArrayList
    private int numItems; // number of elements in items


    public static void main(String[] args) {
        // Create a HeapPriorQueue_shell object to test all the methods in this class
        Pd6ThomasHuitemaHeapPriorityQueue<Integer> pq = new Pd6ThomasHuitemaHeapPriorityQueue<>();
        System.out.println("Peek: " + pq.peek());
        System.out.println("Add 10, 2, 33");
        pq.add(10);
        pq.add(2);
        pq.add(33);
        System.out.println(pq);
        System.out.println("Peek: " + pq.peek());
        System.out.println("Remove twice");
        pq.remove();
        pq.remove();
        System.out.println(pq);
        System.out.println("Is empty: " + pq.isEmpty());
    }

    public Pd6ThomasHuitemaHeapPriorityQueue() {
        // your code goes here
        items = new Comparable[]{0, 5, 12, 20, 32, 52};  // a min-heap
        numItems = 5;
        for (Comparable v : items)
            System.out.print(v + " ");
        System.out.println();
    }


    public Pd6ThomasHuitemaHeapPriorityQueue(int initialCapacity) {
        items = new Comparable[initialCapacity];
    }

    // precondition: items array is initialized
    // postcondition: returns if the queue contains elements
    // O(1)
    public boolean isEmpty() {
        return numItems == 0;
    }

    // precondition: items array is initialized
    // postcondition: returns the first element of items array, or null if the heap is empty
    // O(1)
    public E peek() {
        if (numItems > 0) {
            return (E) items[1]; // index 0 is empty
        }
        return null; // for empty heap
    }

    // precondition: items array is initialized, obj parameter provided
    // postcondition: inserts obj at end of items array, then heaps up to correct position
    public boolean add(E obj) {
        // double size of items[] if array is full
        if (numItems + 1 == items.length) {
            doubleCapacity();
        }
        numItems++;
        items[numItems] = obj; // set next index to obj (becomes rightmost leaf)

        reheapUp(); // reheapUp obj to correct position in items[]
        return true;

    } // add

    // precondition: array is initialized
    // postcondition: heaps element up array to correct position
    private void reheapUp() {
        int currentIndex = numItems;
        int parentIndex = currentIndex / 2; // parent index of any node (besides root) is child index / 2 (int division)

        // heap up until inserted obj is greater than parent
        while (currentIndex != 1 && items[parentIndex].compareTo(items[currentIndex]) > 0) {
            swap(parentIndex, currentIndex); // swap current and parent positions in items[]
            currentIndex = parentIndex; // inserted obj is now at parent index, so we need to check from the new index now
            parentIndex /= 2; // move parent index up level
        }
    }

    // precondition: items array is initialized
    // postcondition: removes first element from items array, reheapDown() to maintain heap order and completeness
    public E remove() {
        if (numItems == 0) {
            return null;
        }

        Comparable removeValue = items[1]; // return first element
        items[1] = items[numItems]; // moving the last element to the root
        items[numItems] = null; // deleting last element
        numItems--;

        reheapDown(1); // heap down root to correct position

        return (E) removeValue;
    }

    // precondition: array and index are provided
    // postcondition: heaps element down array to correct position
    private void reheapDown(int index) {
        int childIndex = index * 2;

        while (childIndex <= numItems && items[childIndex] != null) {
            Comparable parent = items[index]; // for readability
            Comparable child1 = items[childIndex];

            // for reheapdown, we swap parent with smaller child

            // case 1: 1 child
            if (items[childIndex + 1] == null) {
                if (parent.compareTo(child1) > 0) {
                    swap(index, childIndex);
                }
                break; // always break - if there is only 1 child, we are at the end of the array
            }

            // case 2: 2 children
            else {
                Comparable child2 = items[childIndex + 1];
                if (child1.compareTo(child2) < 0) { // child1 is smaller
                    if (parent.compareTo(child1) > 0) { // parent is greater - swap with child1
                        swap(index, childIndex);
                        index = childIndex;
                        childIndex *= 2;
                    }
                } else { // child2 is smaller or equal
                    if (parent.compareTo(child2) > 0) { // parent is greater - swap with child2
                        swap(index, childIndex + 1);
                        index = childIndex + 1;
                        childIndex = (childIndex + 1) * 2;
                    }
                }
            }
        }
    }


    // helper method for reheapUp() and reheapDown()
    // precondition: both parameters are indices in items[] and not null
    // postcondition: swaps the elements in the 2 indices provided
    // O(1)
    private void swap(int index1, int index2) {
        Comparable temp = items[index1];
        items[index1] = items[index2];
        items[index2] = temp;
    }

    // precondition: items is initialized
    // postcondition: returns a string representation of priority queue
    // O(n)
    public String toString() {
        String out = "";
        for (int i = 1; i <= numItems; i++) {
            out += items[i] + " | ";
        }
        return out;
    }

    // precondition: items array is initialized
    // postcondition: creates a new items array with double size
    // O(n)
    private void doubleCapacity() {
        Comparable[] itemsCopy = new Comparable[items.length * 2]; // new array with double the size of items[]

        // copy elements from items[] into itemsCopy[]
        for (int i = 1; i < items.length; i++) {
            itemsCopy[i] = items[i];
        }
        items = itemsCopy; // replace items[] with itemsCopy[]
    }

}  // Pd6ThomasHuitemaHeapPriorityQueue

/*
5 12 20 32 52
Peek: 5
Add 10, 2, 33
2 | 12 | 5 | 32 | 52 | 20 | 10 | 33 |
Peek: 2
Remove twice
10 | 12 | 20 | 32 | 52 | 33 |
Is empty: false
 */
