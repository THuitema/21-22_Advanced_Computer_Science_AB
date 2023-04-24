/*
Name: Thomas Huitema
Period: 6
Name of the Lab: Mini Facebook Part II
Purpose: generate and read in text file with specified number of names

 */

package com.thuitema.FinalProject;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class Pd6ThomasHuitemaMicroFacebookDBExtension {
    public static void main(String[] args) throws FileNotFoundException {
        HashMap<String, Person> people = new HashMap<>();
        HashMap<String, Boolean> friends = new HashMap<>();

        Scanner s = new Scanner(System.in);

        // get number of names from user
        System.out.print("How many names would you like to generate: ");
        int n = s.nextInt();
        System.out.println("Creating " + n + " names...");

        String fileName = "Pd6ThomasHuitema_persons.txt";
        // create new text file w/ random names
        PrintStream out = new PrintStream(fileName);
        for (int i = 0; i < n; i++) {
            generateName(out);
        }

        // read in names
        readNames(fileName, people);

        // run program

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

    // pre-condition: people HashMap is initialized
    // post-condition: prints each name in file & creates Person objects
    public static void readNames(String fileName, HashMap<String, Person> people) throws FileNotFoundException {
        File f = new File(fileName);
        Scanner s = new Scanner(f);

        System.out.println("Names read from file:");
        while (s.hasNext()) {
            if (s.next().equals("P")) {
                String name = s.next();
                createPerson(name, people);
                System.out.println("\t" + name);
            }
        }
        System.out.println();
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

/*
How many names would you like to generate: 100
Creating 100 names...
Names read from file:
	idtwc
	wlmpv
	ldlip
	xcnbb
	fjtax
	qqsfy
	yweji
	bvvml
	dsqlh
	kioac
	ekepl
	fmkze
	dcobr
	bzocl
	ejloo
	juqxd
	jcuer
	cnixu
	vkkmt
	aioff
	qndvs
	zcbsl
	zcxtw
	hqryz
	mvufa
	atbsj
	zpeli
	kfprv
	wffhh
	uebvt
	rjgun
	uyrst
	vmxit
	hfblm
	enjtw
	zxwrp
	qshuh
	yeanc
	vpwnb
	oxjfo
	bhczj
	oyufu
	nghki
	hupif
	xzhqb
	sxstr
	nllmg
	zmrlg
	eoytw
	uepto
	acgxp
	ovihn
	jatzk
	mdiyq
	rdgbq
	ecefg
	vmeam
	olsae
	chnyn
	ifrgy
	vofpz
	odjci
	ycjvz
	hwzhu
	wppho
	gmjqc
	cumtv
	avawo
	gbtvt
	weboe
	smopq
	trthe
	otrii
	krqda
	ukuzo
	zzxpb
	lxqlp
	cppey
	ngpaj
	xtkyv
	ilcuc
	nzdma
	yyiwc
	mbqzz
	ggsuz
	iqwmu
	kgpgm
	ophpf
	gsdre
	wzeie
	xnwmw
	ezzmf
	yyqrl
	hyzxl
	qgbai
	ftsgk
	xiaha
	lfudp
	ujamb
	szqyo

Welcome to Mini-Facebook!!
Commands:
	P (name) – Create a person record of the specified name
	F (name1) (name2) — Record that the two specified people are friends
	U (name1) (name2) — Record that the two specified people are no longer friends
	L (name) — Print out the friends of the specified person
	Q (name1) (name2) — Check whether the two people are friends
	X – Terminate the program

Enter Command:
F yyqrl ujamb
F ujamb xiaha
L ujamb
	Friend(s): xiaha yyqrl
F yyqrl xiaha
U ujamb yyqrl
L yyqrl
	Friend(s): xiaha
Q xiaha ujamb
	Yes, they are friends
P thomas
F thomas xiaha
L xiaha
	Friend(s): thomas yyqrl ujamb
Q xiaha thomas
	Yes, they are friends
X

Process finished with exit code 0
 */
