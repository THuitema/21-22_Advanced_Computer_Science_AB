/*
Name: Thomas Huitema
Period: 6
Name of the Lab: Mini Facebook
Purpose of the Program: Use concepts learned over the course of the year in a final project

The program works/partially work/does not compile/has run time errors and where ...

What I learned (in bullet form):
    • Override equals and compareTo to correctly use a HashMap
    • compareTo() for strings returns < 0 if first string is higher in alphabetical order (e.g. A < D)
    • It's more effective to use HashMap than TreeMap because a HashMap takes O(1) time to add, search for,
        and delete elements

How I feel about this lab:
    • I feel really satisfied after completing the lab because I was able to effectively implement different data
        structures that I learned over the year
    • It provided a good review for the courses' earliest topics (e.g. try-catch exceptions, file I/O, etc.)
    • It was interesting to see how we can apply data structures to the real world

What I wonder:
    • Is there a limit (in terms of computer memory) of how many objects we can create without the program
       crashing/not being able to handle the size of data created (i.e. can we create too many Person objects?)
 */

package com.thuitema.FinalProject;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;


public class Pd6ThomasHuitemaMicroFB {
    public static void main(String[] args) {
        HashMap<String, Person> people = new HashMap<>();
        HashMap<String, Boolean> friends = new HashMap<>();

        // Welcome message & instructions
        System.out.println("Welcome to Mini-Facebook!!");
        System.out.println("Commands:");
        System.out.println("\tP (name) – Create a person record of the specified name");
        System.out.println("\tF (name1) (name2) — Record that the two specified people are friends");
        System.out.println("\tU (name1) (name2) — Record that the two specified people are no longer friends");
        System.out.println("\tL (name) — Print out the friends of the specified person");
        System.out.println("\tQ (name1) (name2) — Check whether the two people are friends");
        System.out.println("\tX – Terminate the program");

        System.out.println("\nEnter Command:");

        // Enter commands
        Scanner s = new Scanner(System.in);

        while (true) {

            // try-catch block to catch invalid inputs (e.g. non-string values)
            try {
                String command = s.next();

                // terminate program
                if (command.equals("X")) {
                    System.exit(0);
                }

                // create person object
                else if (command.equals("P")) {
                    createPerson(s.next(), people);
                }

                // friend two person objects
                else if (command.equals("F")) {
                    Person f1 = people.get(s.next());
                    Person f2 = people.get(s.next());
                    createFriends(f1, f2, friends);
                }

                // unfriend two person objects
                else if (command.equals("U")) {
                    Person f1 = people.get(s.next());
                    Person f2 = people.get(s.next());
                    unfriend(f1, f2, friends);
                }

                // print friends of person
                else if (command.equals("L")) {
                    Person p = people.get(s.next());
                    listFriends(p);
                }

                // check if two people are friends
                else if (command.equals("Q")) {
                    Person p1 = people.get(s.next());
                    Person p2 = people.get(s.next());
                    checkFriends(p1, p2, friends);
                }

            } catch (Exception e) {
                System.out.println("Invalid command " + e);
            }
        }
    }

    // pre-condition: people is initialized
    // post-condition: creates a new Person object and adds it to the people map
    public static void createPerson(String name, HashMap<String, Person> people) {
        people.put(name, new Person(name));
    }

    // pre-condition: f1 & f2 exist, friends is initialized
    // post-condition: adds each to each other's friend list & adds the pair to the friends map
    public static void createFriends(Person f1, Person f2, HashMap<String, Boolean> friends) {
        if (!f1.equals(f2)) {
            // add each to each other's friend list
            f1.addFriend(f2);
            f2.addFriend(f1);

            // construct key & add to friend HashMap
            if (f1.getName().compareTo(f2.getName()) < 0) {
                friends.put(f1.getName() + "*" + f2.getName(), true);
            }
            else {
                friends.put(f2.getName() + "*" + f1.getName(), true);
            }
        }
    }

    // pre-condition: f1 & f2 exist, friends is initialized
    // post-condition: removes each from each other's friend list & removes the pair from friends map
    public static void unfriend(Person f1, Person f2, HashMap<String, Boolean> friends) {
        if (!f1.equals(f2) && f1.getFriends().contains(f2)) { // make sure they are different people and friends of each other
            // delete friends from each other's friend list
            f1.removeFriend(f2);
            f2.removeFriend(f1);

            // construct key & delete from friend HashMap
            if (f1.getName().compareTo(f2.getName()) < 0) {
                friends.remove(f1.getName() + "*" + f2.getName());
            }
            else {
                friends.remove(f2.getName() + "*" + f1.getName());
            }
        }
    }

    // pre-condition: p exists
    // post-condition: prints each friend of p
    public static void listFriends(Person p) {
        System.out.print("\tFriend(s): ");
        for (Person f : p.getFriends()) {
            System.out.print(f.getName() + " ");
        }
        System.out.println();
    }

    // pre-condition: p1 & p2 exist, friends is initialized
    // post-condition: prints whether p1 and p2 are friends, determined by the friends map
    public static void checkFriends(Person p1, Person p2, HashMap<String, Boolean> friends) {
        if (friends.containsKey(p1.getName() + "*" + p2.getName()) || friends.containsKey(p2.getName() + "*" + p1.getName())) {
            System.out.println("\tYes, they are friends");
        }
        else {
            System.out.println("\tNo, they aren't friends");
        }
    }
}

class Person {
    private String name;
    private LinkedList<Person> friends;

    public Person(String name) {
        this.name = name;
        friends = new LinkedList<>();
    }

    public Person(String name, LinkedList<Person> friends) {
        this.name = name;
        this.friends = friends;
    }

    public Person() {
        this.name = "";
        this.friends = new LinkedList<>();
    }


    // pre-condition: other exists
    // post-condition: overrides equals, returns true if names are equal
    public boolean equals(Person other) {
        return this.name.equals(other.getName()) && this.friends.equals(other.getFriends());
    }

    // pre-condition: other exists
    // post-condition: overrides compareTo(), returns compareTo() of the objects' names
    public int compareTo(Person other) {
        return this.getName().compareTo(other.getName());
    }

    // pre-condition: none
    // post-condition: returns LinkedList of friends
    public LinkedList<Person> getFriends() {
        return friends;
    }

    // pre-condition: f exists
    // post-condition: adds f to front of friends list
    public void addFriend(Person f) {
        friends.addFirst(f);
    }

    // pre-condition: f exists
    // post-condition: removes f from friends list
    public void removeFriend(Person f) {
        friends.remove(f);
    }

    // pre-condition: none
    // post-condition: returns name
    public String getName() {
        return name;
    }

    // pre-condition: name exists
    // post-condition: sets object name to argument name
    public void setName(String name) {
        this.name = name;
    }
}

/*
Welcome to Mini-Facebook!!
Commands:
	P (name) – Create a person record of the specified name
	F (name1) (name2) — Record that the two specified people are friends
	U (name1) (name2) — Record that the two specified people are no longer friends
	L (name) — Print out the friends of the specified person
	Q (name1) (name2) — Check whether the two people are friends
	X – Terminate the program

Enter Command:
P thomas
P evan
P paris
F evan paris
F paris thomas
F evan thomas
L thomas
	Friend(s): evan paris
U evan paris
L paris
	Friend(s): thomas
P rick
F rick paris
Q evan rick
	No, they aren't friends
Q paris rick
	Yes, they are friends
X

Process finished with exit code 0
 */
