/*
Name: Thomas Huitema
Period: 6
 */

package com.thuitema.Sets;

import java.util.*;

public class Pd6ThomasHuitemaHashingAWidget {
    public static void main(String[] args) {
        Set<Widget> tSet = new TreeSet<Widget>();
        Set<Widget> hSet = new HashSet<Widget>();

        Widget a = new Widget(2, 3);   //same or different?
        Widget b = new Widget(2, 3);
        Widget c = new Widget(2, 3);
        //c = b;

        tSet.add(a);
        tSet.add(b);
        tSet.add(c);

        hSet.add(a);
        hSet.add(b);
        hSet.add(c);

        System.out.println(a.hashCode() + " " + b.hashCode() + " " + c.hashCode());

        System.out.println("TreeSet:  " + tSet);
        System.out.println("HashSet:  " + hSet);
    }
}


/*************************************** 
 Modify the Widget class so that it hashes on its
 values, not on its address.   Be sure that compareTo(),
 equals(Object) and hashCode() agree with each other.
 ****************************************/
class Widget implements Comparable<Widget> {
    private int myPounds, myOunces;

    public Widget() {
        myPounds = myOunces = 0;
    }

    public Widget(int x) {
        myPounds = x;
        myOunces = 0;
    }

    public Widget(int x, int y) {
        myPounds = x;
        myOunces = y;
    }

    public Widget(Widget arg) {
        myPounds = arg.getPounds();
        myOunces = arg.getOunces();
    }

    public int getPounds() {
        return myPounds;
    }

    public int getOunces() {
        return myOunces;
    }

    public void setPounds(int x) {
        myPounds = x;
    }

    public void setOunces(int x) {
        myOunces = x;
    }

    public int compareTo(Widget w) {
        // pounds have greater priority than ounces
        if(myPounds > w.getPounds()) {
            return 1;
        }
        if(myPounds < w.getPounds()) {
            return -1;
        }
        if(myOunces > w.getOunces()) {
            return 1;
        }
        if(myOunces < w.getOunces()) {
            return -1;
        }

        return 0; // pounds & ounces are equal
    }

    public String toString() {
        return myPounds + " lbs. " + myOunces + " oz.";
    }

    // hashCode() and equals()
    public int hashCode() {
        return this.toString().hashCode(); // hash code of toString of Widget object
    }

    public boolean equals(Object obj) {
        if(obj.getClass() == this.getClass()) {
            Widget other = (Widget) obj;
            if(myOunces == other.getOunces() && myPounds == other.getPounds()) {
                return true;
            }
        }
        return false;
    }
}

/*
CASE 1: hashCode() & equals() are default
901506536 747464370 1513712028
TreeSet:  [2 lbs. 3 oz.]
HashSet:  [2 lbs. 3 oz., 2 lbs. 3 oz., 2 lbs. 3 oz.]

CASE 2: hashCode() is overridden, equals() is default
1400285205 1400285205 1400285205
TreeSet:  [2 lbs. 3 oz.]
HashSet:  [2 lbs. 3 oz., 2 lbs. 3 oz., 2 lbs. 3 oz.]

CASE 3: hashCode() is default, equals() is overridden
1513712028 1018547642 1456208737
TreeSet:  [2 lbs. 3 oz.]
HashSet:  [2 lbs. 3 oz., 2 lbs. 3 oz., 2 lbs. 3 oz.]

CASE 4: hashCode() & equals() are overridden
1400285205 1400285205 1400285205
TreeSet:  [2 lbs. 3 oz.]
HashSet:  [2 lbs. 3 oz.]
 */