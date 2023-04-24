package com.thuitema.Unit5; /*****************************************************************************************************************
 NAME: Thomas Huitema
 PERIOD: 6
 DUE DATE: 12/2
 ASSIGNMENT: Iterator Lab

 PURPOSE: Learn concepts of the Iterator class

 WHAT I LEARNED:
 it.next() both iterates to the next value and returns the value
 store it.next() in a variable to use its value more than once
 you must reset an iterator object to use it multiple times --> e.g. bIt = b.listIterator();
 in a for-each loop, you can only iterate through elements, no editing/adding/deleting values

 CREDITS: none
 ****************************************************************************************************************/

// NOTE:  Use only for-each loops or iterators, not regular for-loops
//        Points will be taken off if regular for loops are used.

import java.util.*;

public class IteratorLab {
    public static void main(String[] args) {
        System.out.println("Iterator Lab\n");
        int[] rawNumbers = {-9, 4, 2, 5, -10, 6, -4, 24, 20, -28};

        for (int n : rawNumbers)
            System.out.print(n + " ");

        ArrayList<Integer> numbers = createNumbers(rawNumbers);

        System.out.println("\nArrayList: " + numbers);      // Implicit Iterator!
        System.out.println("Count negative numbers: " + countNeg(numbers));
        System.out.println("Average: " + average(numbers));
        System.out.println("Replace negative numbers: " + replaceNeg(numbers));
        System.out.println("Delete zeros: " + deleteZero(numbers));

        String[] rawMovies = {"High_Noon", "High_Noon", "Star_Wars", "Tron", "Mary_Poppins",
                "Dr_No", "Dr_No", "Mary_Poppins", "High_Noon", "Tron"};

        ArrayList<String> movies = createMovies(rawMovies);

        System.out.println("Movies: " + movies);
        System.out.println("Movies: " + removeDupes(movies));
    }

    // pre: an array of just int values
    // post: return an ArrayList containing all the values
    public static ArrayList<Integer> createNumbers(int[] rawNumbers) {
        ArrayList<Integer> nums = new ArrayList<>();
        for (int num : rawNumbers) {
            nums.add(num);
        }
        return nums;
    }

    // pre: an array of just Strings
    // post: return an ArrayList containing all the Strings
    public static ArrayList<String> createMovies(String[] rawWords) {
        ArrayList<String> movies = new ArrayList<>();
        for (String movie : rawWords) {
            movies.add(movie);
        }
        return movies;
    }

    // pre: ArrayList a is not empty and contains only Integer objects
    // post: return the number of negative values in the ArrayList a
    public static int countNeg(ArrayList<Integer> a) {
        int c = 0;
        for (int num : a) {
            if (num < 0) {
                c++;
            }
        }
        return c;
    }

    // pre: ArrayList a is not empty and contains only Integer objects
    // post: return the average of all values in the ArrayList a
    public static double average(ArrayList<Integer> a) {
        int size = 0;
        int total = 0;
        for (int n : a) {
            size++;
            total += n;
        }
        return (double) total / size;
    }

    // NOTE: in this method, you must use an iterator, NO for-each loop
    // pre: ArrayList a is not empty and contains only Integer objects
    // post: replaces all negative values with 0
    public static ArrayList<Integer> replaceNeg(ArrayList<Integer> a) {
        ListIterator<Integer> it = a.listIterator();
        while (it.hasNext()) {
            if (it.next() < 0) { // doesn't skip first object since the iterator pointer starts before 1st object
                it.set(0);
            }
        }
        return a;
    }

    // NOTE: in this method, you must use an iterator, NO for-each loop
    // pre: ArrayList a is not empty and contains only Integer objects
    // post: deletes all zeros in the ArrayList a
    public static ArrayList<Integer> deleteZero(ArrayList<Integer> a) {
        Iterator<Integer> it = a.iterator();
        while (it.hasNext()) {
            if (it.next() == 0) {
                it.remove();
            }
        }
        return a;
    }

    // pre: ArrayList a is not empty and contains only String objects
    // post: return ArrayList without duplicate movie titles
    // strategy: start with an empty array and add movies as needed
    public static ArrayList<String> removeDupes(ArrayList<String> a) {
        ArrayList<String> b = new ArrayList<>(); // array without duplicates
        ListIterator<String> aIt = a.listIterator();
        ListIterator<String> bIt = b.listIterator();

        while (aIt.hasNext()) { // loop through original array
            String aVal = aIt.next();
            if (b.size() == 0) {
                bIt.add(aVal);
            }
            while (bIt.hasNext()) { // loop through without duplicates array
                String bVal = bIt.next();
                if (aVal.equals(bVal)) { // if both arrays have the same element, there is a duplicate
                    break;
                }
                if (!bIt.hasNext()) { // looped through and no duplicate - add element to new array
                    bIt.add(aVal);
                }
            }
            bIt = b.listIterator(); // reset iterator for b
        }
        return b;
    }
}

/*
Iterator Lab

-9 4 2 5 -10 6 -4 24 20 -28
ArrayList: [-9, 4, 2, 5, -10, 6, -4, 24, 20, -28]
Count negative numbers: 4
Average: 1.0
Replace negative numbers: [0, 4, 2, 5, 0, 6, 0, 24, 20, 0]
Delete zeros: [4, 2, 5, 6, 24, 20]
Movies: [High_Noon, High_Noon, Star_Wars, Tron, Mary_Poppins, Dr_No, Dr_No, Mary_Poppins, High_Noon, Tron]
Movies: [High_Noon, Star_Wars, Tron, Mary_Poppins, Dr_No]
 */
