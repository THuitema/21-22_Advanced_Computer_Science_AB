package com.thuitema.Unit1.FileIO;

import java.io.*;
import java.util.Scanner;

public class Election {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner console = new Scanner(System.in);
        System.out.print("Input file? ");
        String fileName = console.next();

        File f = new File("src/com/thuitema/Unit1/FileIO/" + fileName);
        Scanner input = new Scanner(f);

        int candidate1Votes = 0;
        int candidate2Votes = 0;

        while(input.hasNextLine()) {
            Scanner tokens = new Scanner(input.nextLine());

            Integer votes1 = 0;
            Integer votes2 = 0;
            Integer numVotes = 0;

            int i = 0;
            while(tokens.hasNext()) {
                String token = tokens.next();
                if(i == 1) {
                    votes1 = Integer.valueOf(token);
                }
                else if(i == 2) {
                    votes2 = Integer.valueOf(token);
                }
                else if(i == 3) {
                    numVotes = Integer.valueOf(token);
                }

                i++;
            }

            if(votes1 > votes2) {
                candidate1Votes += numVotes;
            }
            else if(votes1 < votes2) {
                candidate2Votes += numVotes;
            }
        }

        System.out.println("\nCandidate 1: " + candidate1Votes + " votes");
        System.out.println("Candidate 2: " + candidate2Votes + " votes");
    }
}

/*
Input file? polls.txt

Candidate 1: 325 votes
Candidate 2: 183 votes
*/
