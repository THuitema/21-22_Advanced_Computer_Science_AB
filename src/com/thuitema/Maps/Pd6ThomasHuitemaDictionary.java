/*********************************************************************************************************************************
 Name: Thomas Huitema
 Period: 6
 Date: 3/17/2022

 What I learned:
 Two ways to iterate through a map: (1) for each loop of the map's keySet(), (2) create iterator object of the map's keySet()
 You can change where you'd like System.out.println() to print using the System.setOut() method
 If the value of a key is a Set, you need to initialize it before adding elements (e.g. new Set<>())

 How I feel about this lab:
 I really liked this lab because it forced me to use some creativity to write complex methods such as add()
 when the values are sets which store multiple values. Additionally, I liked how part II was left all to me to write,
 no shell provided. I think it is good sometimes for me to write an entire program from scratch instead of being provided a
 rigid shell.

 What I wonder:
 How are character from other languages dealt with that aren't in the english alphabet? Such as Chinese characters, or
 accent marks on spanish characters
 ************************************************************************************************************************************/

package com.thuitema.Maps;

import java.io.*;
import java.util.*;

public class Pd6ThomasHuitemaDictionary {
    private static PrintStream ps;
    private static PrintStream sysOut = System.out;

    public static void main(String[] args) throws Exception {
        /***************************************************
         PART I
         **************************************************/
        try {
            // funnel all System.out.print() results to the output file "Pd6ThomasHuitemaDictionaryOutputI.txt");
            ps = new PrintStream(new FileOutputStream("Pd6ThomasHuitemaDictionaryOutputI.txt"));
            System.setOut(ps);
        } catch (Exception e) {
            System.out.println("Error:" + e);
        } // catch

        Map<String, Set<String>> eng2spn = new TreeMap<String, Set<String>>();
        Scanner infile = new Scanner(new File("src/com/thuitema/Maps/spanglish.txt"));

        while (infile.hasNext()) {
            add(eng2spn, infile.next(), infile.next());
        }
        infile.close();

        System.out.println("ENGLISH TO SPANISH");
        display(eng2spn);

        Map<String, Set<String>> spn2eng = reverse(eng2spn);
        System.out.println("SPANISH TO ENGLISH");
        display(spn2eng);

        ps.close();    // close the output file


        /***************************************************
         Part II
         **************************************************/

        // The two maps are still in the memory. Part II can interact with the user and add
        // new word(s) to both maps
        // For this part of the program, display all outputs onto the console. See sample outputs below.
        // After the user is done, write the two maps to a text file.

        // Write your Part II code here
        System.setOut(sysOut); // change where we print to console
        Scanner sc = new Scanner(System.in);
        int arg;
        while (true) {
            System.out.println("\nEnter a number to select option");
            System.out.println("\t(1) English to Spanish \n\t(2) Spanish to English \n\t(3) Add a new translation \n\t(4) Exit");
            arg = sc.nextInt();
            switch (arg) {
                case 1: // display english to spanish dictionary
                    System.out.println("ENGLISH TO SPANISH");
                    display(eng2spn);
                    break;

                case 2: // display spanish to english dictionary
                    System.out.println("SPANISH TO ENGLISH");
                    display(spn2eng);
                    break;

                case 3: // add translation
                    System.out.println("(a) from English -> Spanish (b) from Spanish -> English");
                    String transArg = sc.next();
                    String eng;
                    String span;
                    switch (transArg) {
                        case "a": // add english to spanish translation
                            System.out.print("Enter english: ");
                            eng = sc.next();
                            System.out.print("Enter translation: ");
                            span = sc.next();
                            add(eng2spn, eng, span); // add to english dict
                            add(spn2eng, span, eng); // add to spanish dict
                            System.out.println("Translation added!");
                            break;

                        case "b": // add spanish to english translation
                            System.out.print("Enter spanish: ");
                            span = sc.next();
                            System.out.print("Enter translation: ");
                            eng = sc.next();
                            add(spn2eng, span, eng); // add to spanish dict
                            add(eng2spn, eng, span); // add to english dict
                            System.out.println("Translation added!");
                            break;

                    }
                    break;

                case 4: // exit
                    // change where we print to new output file
                    PrintStream ps2 = new PrintStream(new FileOutputStream("Pd6ThomasHuitemaDictionaryOutputII.txt"));
                    System.setOut(ps2);

                    // write updated dictionaries to file Pd6ThomasHuitemaDictionaryOutputII.txt
                    System.out.println("ENGLISH TO SPANISH");
                    display(eng2spn);
                    System.out.println("SPANISH TO ENGLISH");
                    display(spn2eng);

                    ps2.close();
                    System.exit(0); // exit program

                default: // invalid argument
                    System.out.println("Enter a valid argument.");
                    break;
            }
        }
    } // main

    // Note: must explain how your method works
    // Postcondition: display the contents of  a dictionary on the screen
    public static void display(Map<String, Set<String>> m) {
        for (String s : m.keySet()) { // convert map to set in order to loop through its keys
            System.out.print("\t" + s); // print key

            // print value(s)
            Iterator<String> it = m.get(s).iterator(); // iterate through value set
            System.out.print(" [");
            while (it.hasNext()) {
                String val = it.next();
                if (it.hasNext()) { // more values in set --> add comma
                    System.out.print(val + ", ");
                }
                else { // last value in set --> add end bracket
                    System.out.print(val + "]");
                }
            }
            System.out.println();
        }
    } // display

    // Note: must explain how your method works
    // postcondition: insert a new pair to the English to Spanish Dictionary
    public static void add(Map<String, Set<String>> engToSpnDictionary, String word, String translation) {
        if (engToSpnDictionary.containsKey(word)) { // value exists --> add to set
            engToSpnDictionary.get(word).add(translation);
        }

        else { // value does NOT exist --> create new set and add first value
            engToSpnDictionary.put(word, new TreeSet<>());
            engToSpnDictionary.get(word).add(translation);
        }
    } // add

    // Note: must explain how your method works
    // postcondition: returns a Spanish to English dictionary
    public static Map<String, Set<String>> reverse(Map<String, Set<String>> engToSpnDictionary) {
        Map<String, Set<String>> reversed = new TreeMap<>();
        // when reversing a map, the value becomes the key and key becomes the value
        // loop through each key in set
        for (String k : engToSpnDictionary.keySet()) {
            // loop through each value in set
            for (String v : engToSpnDictionary.get(k)) {
                if (reversed.containsKey(v)) { // value set exists
                    reversed.get(v).add(k); // add to existing set at key
                }
                else { // value set doesn't exist
                    reversed.put(v, new TreeSet<>()); // create new key and set
                    reversed.get(v).add(k); // add to set at key
                }
            }
        }
        return reversed;
    } // reverse

} // Dictionary2022

/*
INPUT:
holiday
fiesta
holiday
vacaciones
party
fiesta
celebration
fiesta
feast
fiesta
hand
mano
father
padre
priest
padre
sun
sol
son
hijo
sleep
dormir
vacation
vacaciones
plaza
plaza
banana
banana
hello
hola
good
bueno
double
doble
double
doblar
double
duplicar
computer
ordenador
computer
computadora
program
programa
program
programar
*************************************
OUTPUT:
ENGLISH TO SPANISH
	banana [banana]
	celebration [fiesta]
	computer [computadora, ordenador]
	double [doblar, doble, duplicar]
	father [padre]
	feast [fiesta]
	good [bueno]
	hand [mano]
	hello [hola]
	holiday [fiesta, vacaciones]
	party [fiesta]
	plaza [plaza]
	priest [padre]
	program [programa, programar]
	sleep [dormir]
	son [hijo]
	sun [sol]
	vacation [vacaciones]
SPANISH TO ENGLISH
	banana [banana]
	bueno [good]
	computadora [computer]
	doblar [double]
	doble [double]
	dormir [sleep]
	duplicar [double]
	fiesta [celebration, feast, holiday, party]
	hijo [son]
	hola [hello]
	mano [hand]
	ordenador [computer]
	padre [father, priest]
	plaza [plaza]
	programa [program]
	programar [program]
	sol [sun]
	vacaciones [holiday, vacation]
***********************************

CONSOLE OUTPUT FOR PART II:
Enter a number to select option
	(1) English to Spanish
	(2) Spanish to English
	(3) Add a new translation
	(4) Exit
5
Enter a valid argument.

Enter a number to select option
	(1) English to Spanish
	(2) Spanish to English
	(3) Add a new translation
	(4) Exit
3
(a) from English -> Spanish (b) from Spanish -> English
a
Enter english: baseball
Enter translation: beisbol
Translation added!

Enter a number to select option
	(1) English to Spanish
	(2) Spanish to English
	(3) Add a new translation
	(4) Exit
3
(a) from English -> Spanish (b) from Spanish -> English
b
Enter spanish: basura
Enter translation: trash
Translation added!

Enter a number to select option
	(1) English to Spanish
	(2) Spanish to English
	(3) Add a new translation
	(4) Exit
2
SPANISH TO ENGLISH
	banana [banana]
	basura [trash]
	beisbol [baseball]
	bueno [good]
	computadora [computer]
	doblar [double]
	doble [double]
	dormir [sleep]
	duplicar [double]
	fiesta [celebration, feast, holiday, party]
	hijo [son]
	hola [hello]
	mano [hand]
	ordenador [computer]
	padre [father, priest]
	plaza [plaza]
	programa [program]
	programar [program]
	sol [sun]
	vacaciones [holiday, vacation]

Enter a number to select option
	(1) English to Spanish
	(2) Spanish to English
	(3) Add a new translation
	(4) Exit
1
ENGLISH TO SPANISH
	banana [banana]
	baseball [beisbol]
	celebration [fiesta]
	computer [computadora, ordenador]
	double [doblar, doble, duplicar]
	father [padre]
	feast [fiesta]
	good [bueno]
	hand [mano]
	hello [hola]
	holiday [fiesta, vacaciones]
	party [fiesta]
	plaza [plaza]
	priest [padre]
	program [programa, programar]
	sleep [dormir]
	son [hijo]
	sun [sol]
	trash [basura]
	vacation [vacaciones]

Enter a number to select option
	(1) English to Spanish
	(2) Spanish to English
	(3) Add a new translation
	(4) Exit
4

Process finished with exit code 0
**********************************************
NEW DICTIONARY OUTPUT:
ENGLISH TO SPANISH
	banana [banana]
	baseball [beisbol]
	celebration [fiesta]
	computer [computadora, ordenador]
	double [doblar, doble, duplicar]
	father [padre]
	feast [fiesta]
	good [bueno]
	hand [mano]
	hello [hola]
	holiday [fiesta, vacaciones]
	party [fiesta]
	plaza [plaza]
	priest [padre]
	program [programa, programar]
	sleep [dormir]
	son [hijo]
	sun [sol]
	trash [basura]
	vacation [vacaciones]
SPANISH TO ENGLISH
	banana [banana]
	basura [trash]
	beisbol [baseball]
	bueno [good]
	computadora [computer]
	doblar [double]
	doble [double]
	dormir [sleep]
	duplicar [double]
	fiesta [celebration, feast, holiday, party]
	hijo [son]
	hola [hello]
	mano [hand]
	ordenador [computer]
	padre [father, priest]
	plaza [plaza]
	programa [program]
	programar [program]
	sol [sun]
	vacaciones [holiday, vacation]
 */