package com.thuitema.Unit1.FileIO;

import java.io.*;
import java.util.Scanner;

public class FileInput {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner console = new Scanner(System.in);
        System.out.print("Enter file name: ");
        String fileName = console.next();

        File f = new File("src/com/thuitema/Unit1/FileIO/" + fileName);
        Scanner input = new Scanner(f);

        System.out.println("Information about " + fileName + ":");

        int numWords = 0;
        int numLines = 0;
        int numChars = 0;

        while(input.hasNextLine())  {
            String line = input.nextLine();
            Scanner scannerLine = new Scanner(line);

            while(scannerLine.hasNext()) {
                numWords++;
                scannerLine.next();
            }
            numChars += line.length();
            numLines++;

        }
        System.out.println(numChars + " characters");
        System.out.println(numWords + " words");
        System.out.println(numLines + " lines");
    }
}

/*
Enter file name: file.txt
Information about file.txt:
950 characters
160 words
20 lines
*/