/*
Name: Thomas Huitema
Period: 6
Due Date: 3/1/2022

What I learned:
Sets automatically remove duplicates when inserting, so merging two sets without having duplicates is very simple
If a set is a subset, all of its values are contained in the comparing set
If a set is a superset, it contains all the values of the comparing set
You can use Iterable or a for-each loop to traverse through a set
Set.contains() is a very simple way to check if a value exists in a set
*/

package com.thuitema.Sets;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class Pd6ThomasHuitemaMySetDriver {
    public static void main(String[] args) {
        // TEST 1
        Object[] list1 = {-1, 99, 80, 85, 17, 30, 84, 2, 16, 1};
        Object[] list2 = {-1, 17, 10, 63, 2};
        Object[] list3 = {-1, 80, 1, 84, 30};
        Object[] list4 = {-1, 99, 80, 85};

        // TEST 2
//        Object[] list1 = {'a', 'd', 'z', 'f', 't', 'k', 'l', 'm'};
//        Object[] list2 = {'e', 'z', 'l', 'p', 'o'};
//        Object[] list3 = {'d', 'd', 'p', 'v'};
//        Object[] list4 = {'a', 'd', 'z', 'f'};

        // use the above lists to create four MySet objects: s1, s2, s3, s4
        // Note: MySet extends HashSet which implements Set
        MySet<Object> s1 = new MySet<>();
        MySet<Object> s2 = new MySet<>();
        MySet<Object> s3 = new MySet<>();
        MySet<Object> s4 = new MySet<>();

        for (Object x : list1) {
            s1.add(x);    // calling add method in Set
        }
        for (Object x : list2) {
            s2.add(x);
        }
        for (Object x : list3) {
            s3.add(x);
        }
        for (Object x : list4) {
            s4.add(x);
        }

        System.out.println("s1: " + s1 + "\ns2: " + s2 + "\ns3: " + s3 + "\ns4:" + s4);

        //should print -1, 99, 80, 85, 17, 30, 84, 2, 16, 1, 10, 63
        System.out.println("\nUnion of s and s2: " + s1.union(s2));

        //should print -1, 17, 2
        System.out.println("\nIntersection of s and s2: " + s1.intersect(s2));

        //should print 99, 80, 85, 30, 84,16,1
        System.out.println("\nDifference between s and s2: " + s1.difference(s2));

        //should print false
        System.out.println("\nIs s a subset of s3? " + s1.subset(s3));

        //should print true
        System.out.println("\nIs s a superset of s4? " + s1.superset(s4));
    } // main
}  // MySetDriver


// MySet is an extension of Set. It has union, intersect, difference, subset, superset
// methods defined in it.
class MySet<E> extends HashSet<E> {
    public MySet() {
        // leave empty
    }

    public Set<E> union(Set<E> s) {
        Set<E> unionSet = new HashSet<>();

        // loop through both sets, add values to union set
        Iterator<E> it1 = super.iterator();
        while (it1.hasNext()) {
            unionSet.add(it1.next());
        }

        for (E x : s) {
            unionSet.add(x);
        }

        return unionSet;
    }

    public Set<E> intersect(Set<E> s) {
        Set<E> intersectSet = new HashSet<>();

        // add to set if values are contained in both sets
        for (E x : s) {
            if (super.contains(x)) {
                intersectSet.add(x);
            }
        }

        return intersectSet;
    }

    public Set<E> difference(Set<E> s) {
        Set<E> differenceSet = new HashSet<>();

        // add to set if value isn't in s
        Iterator<E> it = super.iterator();
        while (it.hasNext()) {
            E val = it.next();

            if (!s.contains(val)) {
                differenceSet.add(val);
            }
        }

        return differenceSet;
    }

    public boolean subset(Set<E> s) {
        // a set isn't a subset of itself
        if (!super.equals(s)) {
            // check if each value in set is contained in s
            Iterator<E> it = super.iterator();
            while (it.hasNext()) {
                if (!s.contains(it.next())) {
                    return false; // not in s --> not a subset
                }
            }

            return true; // all values are in s --> is a subset
        }
        return false;
    }

    public boolean superset(Set<E> s) {
        // a set isn't a superset of itself
        if (!super.equals(s)) {
            // check if each value in s is contained in set
            // if s is a subset, this set is a superset of s
            for (E x : s) {
                if (!super.contains(x)) {
                    return false; // not in set --> not a superset
                }
            }
            return true; // all values are in this set --> is a superset
        }

        return false;
    }
} // MySet


/*
TEST 1: INTEGERS

s1: [-1, 80, 16, 17, 1, 2, 99, 84, 85, 30]
s2: [-1, 17, 2, 10, 63]
s3: [-1, 80, 1, 84, 30]
s4:[-1, 80, 99, 85]

Union of s and s2: [-1, 80, 16, 17, 1, 2, 99, 84, 85, 10, 30, 63]

Intersection of s and s2: [-1, 17, 2]

Difference between s and s2: [80, 16, 1, 99, 84, 85, 30]

Is s a subset of s3? false

Is s a superset of s4? true

Process finished with exit code 0


TEST 2: CHARS
s1: [a, d, t, f, z, k, l, m]
s2: [p, e, z, l, o]
s3: [p, d, v]
s4:[a, d, f, z]

Union of s and s2: [p, a, d, t, e, f, z, k, l, m, o]

Intersection of s and s2: [z, l]

Difference between s and s2: [a, d, t, f, k, m]

Is s a subset of s3? false

Is s a superset of s4? true

Process finished with exit code 0
*/