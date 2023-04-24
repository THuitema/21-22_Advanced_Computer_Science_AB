/*
Name: Thomas Huitema
Date: 3/8/2022
 */

package com.thuitema.Sets;

import java.util.*;
import java.io.*;

public class Pd6ThomasHuitemaSetsOfLetters {
    public static void main(String[] args) throws FileNotFoundException {
        String fileName = "src/com/thuitema/Sets/declarationLast.txt";
        fillTheSets(fileName);
    }

    public static void fillTheSets(String fn) throws FileNotFoundException {
        Scanner infile = new Scanner(new File(fn));

        // sets to hold characters that are in each line
        Set<Character> commonLowerCase = new HashSet<>();
        Set<Character> commonUpperCase = new HashSet<>();
        Set<Character> commonOther = new HashSet<>();

        int j = 0; // counter to check if while loop is on first run
        // if while loop is on first loop, add every character from the line to corresponding common set
        while (infile.hasNextLine()) {
            String line = infile.nextLine();

            // sets for current line, used to compare against common sets
            Set<Character> lowerCase = new HashSet<>();
            Set<Character> upperCase = new HashSet<>();
            Set<Character> other = new HashSet<>();

            // check each character in line, add to correct set
            for (int i = 0; i < line.length(); i++) {
                Character c = line.charAt(i);

                if (c != ' ') { // don't care about spaces
                    // lower case
                    if (Character.isLowerCase(c)) {
                        lowerCase.add(c);

                        if (j == 0) { // add to common set if checking first line
                            commonLowerCase.add(c);
                        }
                    }

                    // upper case
                    else if (Character.isUpperCase(c)) {
                        upperCase.add(c);

                        if (j == 0) {
                            commonUpperCase.add(c);
                        }
                    }

                    // other
                    else {
                        other.add(c);

                        if (j == 0) {
                            commonOther.add(c);
                        }
                    }
                }
            } // for

            // update common character sets
            commonLowerCase = intersect(lowerCase, commonLowerCase);
            commonUpperCase = intersect(upperCase, commonUpperCase);
            commonOther = intersect(other, commonOther);

            // print attributes about current line
            System.out.println(line);
            System.out.println("Lower Case: " + lowerCase);
            System.out.println("Upper Case: " + upperCase);
            System.out.println("Other: " + other);
            System.out.println();

            j++;
        } // while

        // print attributes about common sets
        System.out.println("Common Lower Case: " + commonLowerCase);
        System.out.println("Common Upper Case: " + commonUpperCase);
        System.out.println("Common Other: " + commonOther);


    }

    // helper method to find the intersection of two sets
    public static Set<Character> intersect(Set<Character> s1, Set<Character> commonSet) {
        Set<Character> intersectSet = new HashSet<>();

        for (Character c : commonSet) {
            if (s1.contains(c)) {
                intersectSet.add(c);
            }
        }

        return intersectSet;
    }
}


/***********************************
 We, therefore, the Representatives of the United States of America, in General Congress, Assembled,
 Lower Case: [a, b, c, d, e, f, g, h, i, l, m, n, o, p, r, s, t, v]
 Upper Case: [A, R, S, C, U, W, G]
 Other: [,]

 appealing to the Supreme Judge of the world for the rectitude of our intentions, do,
 Lower Case: [a, c, d, e, f, g, h, i, l, m, n, o, p, r, s, t, u, w]
 Upper Case: [S, J]
 Other: [,]

 in the Name, and by the Authority of the good People of these Colonies, solemnly publish and declare,
 Lower Case: [a, b, c, d, e, f, g, h, i, l, m, n, o, p, r, s, t, u, y]
 Upper Case: [P, A, C, N]
 Other: [,]

 That these United Colonies are, and of Right ought to be Free and Independent States;
 Lower Case: [a, b, d, e, f, g, h, i, l, n, o, p, r, s, t, u]
 Upper Case: [R, C, S, T, U, F, I]
 Other: [;, ,]

 and that they are Absolved from all Allegiance to the British Crown,
 Lower Case: [a, b, c, d, e, f, g, h, i, l, m, n, o, r, s, t, v, w, y]
 Upper Case: [A, B, C]
 Other: [,]

 and that all political connection between them and the State of Great Britain, is and ought to be totally dissolved;
 Lower Case: [a, b, c, d, e, f, g, h, i, l, m, n, o, p, r, s, t, u, v, w, y]
 Upper Case: [B, S, G]
 Other: [;, ,]

 and that as Free and Independent States, they have full Power to levy War, conclude Peace, contract Alliances,
 Lower Case: [a, c, d, e, f, h, i, l, n, o, p, r, s, t, u, v, w, y]
 Upper Case: [P, A, S, F, W, I]
 Other: [,]

 establish Commerce, and to do all other Acts and Things which Independent States may of right do.
 Lower Case: [a, b, c, d, e, f, g, h, i, l, m, n, o, p, r, s, t, w, y]
 Upper Case: [A, C, S, T, I]
 Other: [,, .]

 And for the support of this Declaration, with a firm reliance on the protection of divine Providence, we mutually
 Lower Case: [a, c, d, e, f, h, i, l, m, n, o, p, r, s, t, u, v, w, y]
 Upper Case: [P, A, D]
 Other: [,]

 pledge to each other our Lives, our Fortunes and our sacred Honor.
 Lower Case: [a, c, d, e, g, h, i, l, n, o, p, r, s, t, u, v]
 Upper Case: [F, H, L]
 Other: [,, .]

 Common Lower Case: [a, r, s, d, t, e, h, i, l, n, o]
 Common Upper Case: []
 Common Other: [,]

 Process finished with exit code 0
 ************************************/