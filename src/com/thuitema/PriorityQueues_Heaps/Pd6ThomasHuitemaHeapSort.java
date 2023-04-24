
/****************************************************************************
 Name: Thomas Huitema
 Lab Assignment: HeapSort program
 Purpose of the program: write methods to build and sort a max heap

 What I Learned (be as specific as possible):
    HeapSort only works on max/min heaps, not random arrays; you must make a random array a min/max heap to do HeapSort
    When creating a max heap from a random array, the middle index is the parent of the bottom right-most subtree; it is where you want to start heapDown()

    In the sort method, after swapping the first and last elements, the new last element is in sorted position, and not to be touched. So, we subtract 1
    from the "last" index to show what elements still need to be sorted

 How I feel about this lab:
    This lab was very fulfilling because this is the first sorting algorithm that I've written. It ois very nice to see a large array go from unsorted
    to sorted. I now feel confident in and am interested in writing other sorting algorithms.

 What I am wondering:
 HeapSort seems unique to me since an array must be a min/max heap before sorting. Do any other sorting algorithms require data to be in a certain
 structure/order before we can sort?

 The credits: who and/or what website(s) helped you (must state what information you got from the helper or website):
 Paris Phan showed me how to use the DecimalFormat to make double values 2 decimals long

 Students (names) you helped (to what extent, be specific): None
 ****************************************************************************/

package com.thuitema.PriorityQueues_Heaps;


import java.text.DecimalFormat;

public class Pd6ThomasHuitemaHeapSort {
    public static void main(String[] args) {
        //Part 1: Given a max heap, sort it. Do this part first.
        double[] heap = {-1, 99, 80, 85, 17, 30, 84, 2, 16, 1};
        System.out.println("Original: ");
        display(heap);
        sort(heap);
        System.out.println("Sorted: ");
        display(heap);
        System.out.println("Is sorted: " + isSorted(heap));

        //Part 2:  Generate 100 random numbers, make a heap, sort it.
        System.out.println("------------------");
        System.out.println("Part 2: random array of 100 numbers, from 1-100:");
        int SIZE = 100;
        double[] heap2 = new double[SIZE + 1];
        heap2 = createRandom(heap2);
        System.out.println("Original: ");
        display(heap2);
        makeHeap(heap2);
        System.out.println("Max-Heap: ");
        display(heap2);
        System.out.println("Sorted: ");
        sort(heap2);
        display(heap2);
        System.out.println("Is sorted: " + isSorted(heap2));
    }

    //******* Part 1 ******************************************
    // precondition: array parameter is not null
    // postcondition: prints each value of the heap
    public static void display(double[] array) {
        for (int k = 1; k < array.length; k++) {
            System.out.print(array[k] + " | ");
        }
        System.out.println("\n");
    }

    // precondition: array[] is a max-heap
    // postcondition: sorts array[] from least to greatest
    public static void sort(double[] array) {
        int last = array.length - 1;
        while (last > 1) {
            swap(array, 1, last); // swap first and last elements
            last--; // last element is in sorted position after swap(), don't want to move it
            heapDown(array, 1, last); // heapDown first element into correct position
        }

        if (array[1] > array[2])   // just an extra swap, if needed.
            swap(array, 1, 2);
    }

    // precondition: 1 ≤ a & b < array.length
    // postcondition: swaps the values in indices a & b in array[]
    public static void swap(double[] array, int a, int b) {
        double temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    // precondition: array[] is not null
    // postcondition: heapDown element at index i to correct position
    public static void heapDown(double[] array, int k, int size) {
        if (k * 2 > size) { // no children exist
            return;
        }
        if (k * 2 + 1 > size) { // 1st child exists at 2k
            if (array[k * 2] > array[k]) { // swap if child is greater
                swap(array, k, k * 2);
            }
            // no more child node --> stop recursion
        } else { // both children exist
            if (array[k * 2] > array[k] || array[k * 2 + 1] > array[k]) { // one or both children are greater
                if (array[k * 2] > array[k * 2 + 1]) { // first child is greatest
                    swap(array, k, k * 2);
                    heapDown(array, k * 2, size);
                } else { // second child is greater
                    swap(array, k, k * 2 + 1);
                    heapDown(array, k * 2 + 1, size);
                }
            }
        }
    }

    // precondition: array parameter is provided
    // postcondition: returns whether the heap is sorted least to greatest
    public static boolean isSorted(double[] array) {
        for (int i = 1; i < array.length - 1; i++) {
            // sorted = nth element is less than nth + 1 element
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }

    // ****** Part 2 *******************************************

    //Generate 100 random numbers between 1 and 100, formatted to 2 decimal places
    //postcondition:  array[0] == -1, the rest of the array is random
    public static double[] createRandom(double[] array) {
        DecimalFormat df = new DecimalFormat("##.##");

        array[0] = -1.0; // first element is ignored
        for (int i = 1; i < array.length; i++) {
            double val = Math.random() * 100;
            array[i] = Double.parseDouble(df.format(val)); // format double to 2 decimals, add to array
        }
        return array;
    }

    // Turn the random array into a MAX heap
    // postcondition:  array[0] == -1, the rest of the array is in heap-order
    private static void makeHeap(double[] array) {
        int index = (array.length - 1) / 2; // start at middle node to go through each subtree
        while (index >= 1) {
            heapDown(array, index, array.length - 1);
            index--;
        }
    }

} // HeapSort_shell

/*
Original:
99.0 | 80.0 | 85.0 | 17.0 | 30.0 | 84.0 | 2.0 | 16.0 | 1.0 |

Sorted:
1.0 | 2.0 | 16.0 | 17.0 | 30.0 | 80.0 | 84.0 | 85.0 | 99.0 |

Is sorted: true
------------------
Part 2: random array of 100 numbers, from 1-100:
Original:
56.06 | 45.69 | 5.25 | 50.92 | 6.85 | 20.76 | 18.18 | 67.92 | 1.82 | 35.81 | 88.41 | 98.06 | 29.68 | 97.46 | 83.38 | 10.77 | 17.08 | 83.85 | 4.72
| 6.15 | 62.52 | 45.29 | 84.69 | 20.03 | 79.09 | 77.76 | 14.36 | 56.66 | 98.88 | 42.41 | 53.74 | 85.52 | 84.49 | 84.79 | 24.62 | 89.88 | 26.44 | 54.42
| 28.28 | 22.01 | 79.28 | 37.32 | 61.52 | 1.98 | 3.64 | 92.24 | 27.1 | 95.45 | 36.58 | 24.27 | 47.72 | 19.57 | 8.67 | 39.25 | 96.08 | 55.5 | 62.34 | 96.53
| 47.87 | 90.87 | 3.26 | 18.29 | 49.48 | 76.6 | 34.17 | 19.54 | 67.77 | 58.22 | 78.93 | 94.34 | 54.9 | 34.25 | 3.22 | 31.23 | 18.79 | 1.24 | 78.34 | 33.49
| 36.1 | 68.63 | 14.25 | 88.45 | 83.71 | 65.09 | 9.14 | 63.98 | 92.26 | 12.44 | 24.04 | 10.25 | 45.41 | 13.42 | 93.11 | 9.93 | 57.69 | 84.97 | 23.04
| 51.08 | 64.73 | 70.26 |

Max-Heap:
98.88 | 94.34 | 98.06 | 89.88 | 93.11 | 96.08 | 97.46 | 85.52 | 83.85 | 92.26 | 92.24 | 95.45 | 77.76 | 96.53 | 90.87 | 84.49 | 84.79 | 45.69 | 78.34
| 88.45 | 65.09 | 45.41 | 88.41 | 84.97 | 79.09 | 56.06 | 39.25 | 62.34 | 47.87 | 83.38 | 53.74 | 76.6 | 67.77 | 78.93 | 54.9 | 34.25 | 31.23 | 54.42
| 36.1 | 68.63 | 83.71 | 37.32 | 63.98 | 24.04 | 45.29 | 84.69 | 57.69 | 23.04 | 64.73 | 70.26 | 47.72 | 19.57 | 8.67 | 29.68 | 14.36 | 55.5 | 56.66
| 18.18 | 5.25 | 42.41 | 3.26 | 18.29 | 49.48 | 10.77 | 34.17 | 19.54 | 50.92 | 58.22 | 67.92 | 24.62 | 17.08 | 1.82 | 3.22 | 26.44 | 18.79 | 1.24 | 4.72
| 33.49 | 28.28 | 22.01 | 14.25 | 79.28 | 6.15 | 35.81 | 9.14 | 62.52 | 61.52 | 12.44 | 1.98 | 10.25 | 3.64 | 13.42 | 6.85 | 9.93 | 27.1 | 20.03 | 20.76
| 51.08 | 36.58 | 24.27 |

Sorted:
1.24 | 1.82 | 1.98 | 3.22 | 3.26 | 3.64 | 4.72 | 5.25 | 6.15 | 6.85 | 8.67 | 9.14 | 9.93 | 10.25 | 10.77 | 12.44 | 13.42 | 14.25 | 14.36 | 17.08 | 18.18
| 18.29 | 18.79 | 19.54 | 19.57 | 20.03 | 20.76 | 22.01 | 23.04 | 24.04 | 24.27 | 24.62 | 26.44 | 27.1 | 28.28 | 29.68 | 31.23 | 33.49 | 34.17 | 34.25
| 35.81 | 36.1 | 36.58 | 37.32 | 39.25 | 42.41 | 45.29 | 45.41 | 45.69 | 47.72 | 47.87 | 49.48 | 50.92 | 51.08 | 53.74 | 54.42 | 54.9 | 55.5 | 56.06
| 56.66 | 57.69 | 58.22 | 61.52 | 62.34 | 62.52 | 63.98 | 64.73 | 65.09 | 67.77 | 67.92 | 68.63 | 70.26 | 76.6 | 77.76 | 78.34 | 78.93 | 79.09 | 79.28
| 83.38 | 83.71 | 83.85 | 84.49 | 84.69 | 84.79 | 84.97 | 85.52 | 88.41 | 88.45 | 89.88 | 90.87 | 92.24 | 92.26 | 93.11 | 94.34 | 95.45 | 96.08 | 96.53
| 97.46 | 98.06 | 98.88 |

Is sorted: true

Process finished with exit code 0
 */

