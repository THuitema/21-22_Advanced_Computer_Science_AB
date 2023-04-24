/*
Name: Thomas Huitema
Period: 6
Name of the Lab: Mini Facebook Part III
Purpose of the Program: observe the time it takes to complete various methods in the MicroFB lab

Note: on the assignment submission, I included a graph of different number of names generated (250, 500, 750, 1000), and
compared the runtimes of reading each file with lines of best fit

Observations:
    • I noticed that reading the unfriend file took about twice as long as reading the friend file
    • Reading namesOnly was nearly constant no matter the number of names. It always took between 1-3 milliseconds to read
    • My laptop ran the methods significantly faster than my peers with non-mac laptops (~10x faster, especially when reading namesOnly.txt).
        Is it due to a better processor, RAM, or just because I have macOS?
 */

package com.thuitema.FinalProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Scanner;

public class Pd6ThomasHuitemaMicroFBPartIII {
    public static void main(String[] args) throws FileNotFoundException {
        MiniFB fb = new MiniFB();

        // create People file with 500 random names
        createPeopleFile();

        // create file to make every person in nameOnly.txt friends with each other
        createFriendFile();

        // create file to unfriend every friendship
        createUnfriendFile();

        // read in nameOnly.txt with timer
        long startTime = System.currentTimeMillis(); // returns type "long"
        fb.readPeopleFile("nameOnly.txt");
        long stopTime = System.currentTimeMillis();

        System.out.println("Time to read people file: " + (stopTime - startTime) + " milliseconds");

        // read in friendOnly.txt with timer
        long startTime2 = System.currentTimeMillis();
        fb.readFriendsFile("friendOnly.txt");
        long stopTime2 = System.currentTimeMillis();

        System.out.println("Time to read friends file: " + (stopTime2 - startTime2) + " milliseconds");

        // read in unfriend.txt with timer
        long startTime3 = System.currentTimeMillis();
        fb.readUnfriendFile("unfriend.txt");
        long stopTime3 = System.currentTimeMillis();

        System.out.println("Time to read unfriend file: " + (stopTime3 - startTime3) + " milliseconds");

        // run original program
        // Welcome message & instructions
        System.out.println("\nWelcome to Mini-Facebook!!");
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
                    fb.createPerson(s.next());
                }

                // friend two person objects
                else if (command.equals("F")) {
                    fb.createFriends(s.next(), s.next());
                }

                // unfriend two person objects
                else if (command.equals("U")) {
                    fb.unfriend(s.next(), s.next());
                }

                // print friends of person
                else if (command.equals("L")) {
                    fb.listFriends(s.next());
                }

                // check if two people are friends
                else if (command.equals("Q")) {
                    fb.checkFriends(s.next(), s.next());
                }

            } catch (Exception e) {
                System.out.println("Invalid command " + e);
            }
        }
    }

    // pre-condition: none
    // post-condition: creates a text file with 500 random names
    public static void createPeopleFile() throws FileNotFoundException {
        String fileName = "nameOnly.txt";
        // create new text file w/ 500 random names
        PrintStream out = new PrintStream(fileName);
        int numPeople = 500;
        for (int i = 0; i < numPeople; i++) {
            generateName(out);
        }
    }

    // pre-condition: PrintStream out is initialized
    // post-condition: generates one random name (5 letters) & writes it to the text file
    public static void generateName(PrintStream out) {
        String chars = "abcdefghijklmnopqrstuvwxyz";
        String line = "P ";

        for (int i = 0; i < 5; i++) {
            int randIndex = (int) (Math.random() * chars.length());
            line += chars.substring(randIndex, randIndex + 1);
        }
        out.println(line);
    }

    // pre-condition: nameOnly.txt has been created & contains names in the format "P <name>"
    // post-condition: creates a text file to friend every person with each other
    public static void createFriendFile() throws FileNotFoundException {
        PrintStream friends = new PrintStream("friendOnly.txt");
        File people = new File("nameOnly.txt");
        Scanner s1 = new Scanner(people);

        int name1Index = 0;

        while (s1.hasNext()) {
            if (s1.next().equals("P")) {
                String name1 = s1.next();
                name1Index++;
                Scanner s2 = new Scanner(people); // second scanner to get second person

                int name2Index = 0;
                while (s2.hasNext()) {
                    if (s2.next().equals("P")) {
                        String name2 = s2.next();
                        if (name2Index > name1Index) { // already covered the previous indices, so only look at ones ahead
                            friends.println("F " + name1 + " " + name2);
                        }
                        name2Index++;
                    }
                }
            }
        }
    }

    // pre-condition: nameOnly.txt has been created & contains names in the format "P <name>"
    // post-condition: creates a text file to unfriend every person with each other
    public static void createUnfriendFile() throws FileNotFoundException {
        PrintStream unfriends = new PrintStream("unfriend.txt");
        File people = new File("nameOnly.txt");
        Scanner s1 = new Scanner(people);

        int name1Index = 0;

        while (s1.hasNext()) {
            if (s1.next().equals("P")) {
                String name1 = s1.next();
                name1Index++;
                Scanner s2 = new Scanner(people); // second scanner to get second person

                int name2Index = 0;
                while (s2.hasNext()) {
                    if (s2.next().equals("P")) {
                        String name2 = s2.next();
                        if (name2Index > name1Index) { // already covered the previous indices, so only look at ones ahead
                            unfriends.println("U " + name1 + " " + name2);
                        }
                        name2Index++;
                    }
                }
            }
        }
    }
}


class MiniFB {
    private HashMap<String, Person> people;
    private HashMap<String, Boolean> friends;

    public MiniFB() {
        people = new HashMap<>();
        friends = new HashMap<>();
    }

    // pre-condition: people is initialized
    // post-condition: creates a new Person object and adds it to the people map
    public void createPerson(String name) {
        people.put(name, new Person(name));
    }

    // pre-condition: f1 & f2 exist, friends is initialized
    // post-condition: adds each to each other's friend list & adds the pair to the friends map
    public void createFriends(String name1, String name2) {
        Person f1 = people.get(name1);
        Person f2 = people.get(name2);

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
    public void unfriend(String name1, String name2) {
        Person f1 = people.get(name1);
        Person f2 = people.get(name2);

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
    public void listFriends(String name) {
        Person p = people.get(name);

        System.out.print("\tFriend(s): ");
        for (Person f : p.getFriends()) {
            System.out.print(f.getName() + " ");
        }
        System.out.println();
    }

    // pre-condition: name1 & name2 correspond to Person objects
    // post-condition: prints whether p1 and p2 are friends, determined by the friends map
    public void checkFriends(String name1, String name2) {
        Person p1 = people.get(name1);
        Person p2 = people.get(name2);

        if (friends.containsKey(p1.getName() + "*" + p2.getName()) || friends.containsKey(p2.getName() + "*" + p1.getName())) {
            System.out.println("\tYes, they are friends");
        }
        else {
            System.out.println("\tNo, they aren't friends");
        }
    }

    // pre-condition: none
    // post-condition: creates Person object for each name in file
    public void readPeopleFile(String fileName) throws FileNotFoundException {
        File f = new File(fileName);
        Scanner s = new Scanner(f);

        while (s.hasNext()) {
            if (s.next().equals("P")) {
                String name = s.next();
                createPerson(name);
            }
        }
    }

    // pre-condition: none
    // post-condition: friends each pair of Person objects in friend file
    public void readFriendsFile(String fileName) throws FileNotFoundException {
        File f = new File(fileName);
        Scanner s = new Scanner(f);

        while (s.hasNext()) {
            if (s.next().equals("F")) {
                createFriends(s.next(), s.next());
            }
        }
    }

    // pre-condition: none
    // post-condition: unfriends each pair of Person objects in unfriend file
    public void readUnfriendFile(String fileName) throws FileNotFoundException {
        File f = new File(fileName);
        Scanner s = new Scanner(f);

        while (s.hasNext()) {
            if (s.next().equals("U")) {
                unfriend(s.next(), s.next());
            }
        }
    }
}

/*
Time to read people file: 2 milliseconds
Time to read friends file: 310 milliseconds
Time to read unfriend file: 838 milliseconds

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
F thomas evan
F evan paris
Q thomas paris
	No, they aren't friends
U paris evan
F thomas paris
L paris
	Friend(s): thomas
Q evan thomas
	Yes, they are friends
X

Process finished with exit code 0
 */