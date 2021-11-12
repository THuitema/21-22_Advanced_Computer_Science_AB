package com.thuitema.Unit1.FileIO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class BraceStyle {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner console = new Scanner(System.in);
        System.out.print("Enter java file name: ");
        String fileName = console.next();
        File f = new File(fileName);
        Scanner input = new Scanner(f);

        PrintStream out = new PrintStream(new File("out.java"));

        while(input.hasNextLine()) {
            String line = input.nextLine();
            if(line.indexOf('{') != -1) {
                out.print(" {");
            }
            else {
                out.print("\n" + line);
            }
        }
    }
}

/*
output file (out.java):
public class Test {
    public static void main(String[] args) {
        // some statements
    }
}
*/
