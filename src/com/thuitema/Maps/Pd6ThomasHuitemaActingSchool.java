/*
Name: Thomas Huitema
Date: 3/12/2022

What I learned:
You can convert map to ses using Map.keySet()
You iterate through maps by converting to a set, then using an iterator object
Map.put() will overwrite an existing key
TreeMap maintains order (slower), while HashMap doesn't maintain order (faster)

How I feel about this lab:
I feel very comfortable using the methods of a Map object such as get(), put(), keySet(), and containsKey()
Also, my experience in python with its dictionary data structure has helped me understand the reasoning behind
Map rather quickly

What I wonder:
Is there any situation where a TreeMap is more efficient (smaller big-O) than a HashMap?

Credits: none
 */


package com.thuitema.Maps;

import java.util.*;

public class Pd6ThomasHuitemaActingSchool {
    public static void main(String[] args) {
        // Map initializer. Does it look like the one for 1D array? 2D array? NOT FOR LAB, JUST REFERENCE
        Map<String, String> doubleBraceMap =
                new HashMap<String, String>() {
                    {
                        put("key1", "value1");
                        put("key1", "value2");
                    }
                };

        System.out.println(doubleBraceMap);


        Map<String, String> sGrades = new HashMap<String, String>(); // HashMap
        sGrades.put("Jack Nicholson", "A-");
        sGrades.put("Humphrey Bogart", "A+");
        sGrades.put("Audrey Hepburn", "A");
        sGrades.put("Meryl Streep", "A-");
        sGrades.put("Jimmy Stewart", "A");

        // What you need to do:
        // 1. display initial data. Use an iterator instead of using the built-in toString method of HashMap
        display(sGrades);

        // 2. reverse the map--use TreeMap
        Map<String, ArrayList<String>> reverseMap = reverseMap(sGrades);

        // 3. display the reversed map
        displayReverse(reverseMap);

    } // main

    public static void display(Map<String, String> map) {
        Set<String> keySet = map.keySet(); // set of the keys from sGrades
        Iterator<String> it = keySet.iterator();
        while (it.hasNext()) { // iterate through the key, print key & value
            String key = it.next();
            System.out.println(key + " (" + map.get(key) + ")");
        }
    }

    public static Map<String, ArrayList<String>> reverseMap(Map<String, String> map) {
        Map<String, ArrayList<String>> reverse = new TreeMap<>(); // TreeMap object, key is a String, value is an ArrayList
        Set<String> keySet = map.keySet(); // set of the keys
        Iterator<String> it = keySet.iterator();

        while(it.hasNext()) {
            String key = it.next();
            String value = map.get(key);

            // contains key --> add value to arraylist
            if(reverse.containsKey(value)) {
                reverse.get(value).add(key); // add to arraylist
            }

            // doesn't contain key --> put key and create new arraylist with first value
            else {
                reverse.put(value, new ArrayList<>());
                reverse.get(value).add(key);
            }
        }
        return reverse;
    }

    public static void displayReverse(Map<String, ArrayList<String>> map) {
        Set<String> keySet = map.keySet(); // set of the keys
        Iterator<String> it = keySet.iterator();

        while (it.hasNext()) { // iterate through the key, print key & value
            String key = it.next();
            String out = key + ": [";

            for(String s : map.get(key)) { // iterate through arraylist values
                out += s + ", ";
            }
            out += "]";
            System.out.println(out);
        }
    }
} // ActingSchool_shell

/*
{key1=value2}
Audrey Hepburn (A)
Meryl Streep (A-)
Jimmy Stewart (A)
Humphrey Bogart (A+)
Jack Nicholson (A-)
A: [Audrey Hepburn, Jimmy Stewart, ]
A+: [Humphrey Bogart, ]
A-: [Meryl Streep, Jack Nicholson, ]
 */