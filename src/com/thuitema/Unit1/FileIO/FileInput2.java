package com.thuitema.Unit1.FileIO;

import java.io.*;
import java.util.Scanner;

public class FileInput2 {
    public static void main(String[] args) throws FileNotFoundException{
        String fileName = "src/com/thuitema/Unit1/FileIO/file.txt";
        File f = new File(fileName);
        Scanner file = new Scanner(f);

        PrintStream out = new PrintStream(new File("newline.txt"));

        while(file.hasNextLine()) {
            String line = file.nextLine();
            Scanner scannerLine = new Scanner(line);

            while(scannerLine.hasNext()) {
                out.println(scannerLine.next());
                System.out.println(scannerLine.next());
            }
        }
    }
}
