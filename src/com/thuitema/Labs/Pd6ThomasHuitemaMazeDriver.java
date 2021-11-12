package com.thuitema.Labs;

/*********************************************************************************
 NAME: Thomas Huitema
 PERIOD: 6
 DATE SUBMITTED: 10/13/2021
 DUE DATE: 10/16/2021
 ASSIGNMENT: Maze Lab
 PURPOSE OF THE LAB: Use recursive concepts to find solutions to mazes

 MISTAKES:
 I needed a temp. character for parts in the maze I've already covered, so I made the constant TEMP ('o')
 When changing chars in the correct path to STEP, I accidentally changed START as well. So, I added an if statement so I don't change START to STEP

 NEW CONCEPTS LEARNED:
 Calling recursive methods in an if-statement
 Using De Morgan's laws to simplify methods (specifically in if statements)

 HOW I FEEL ABOUT THIS LAB:
 Initially, using recursive concepts inside if-statements was confusing until I drew a tree on paper, then it made sense. It isn't a good idea to
 imagine a program like this run in your head because of how complex it is.

 CREDITS:
 AreaFill Lab driver class to help read characters from file --> toCharArray()

 STUDENTS WHOM I HELPED: none
 */

import java.util.*;
import java.io.*;

public class Pd6ThomasHuitemaMazeDriver {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        char[][] retArr;
        Maze m;

        System.out.println();

        while (true) {
            System.out.print("Enter the maze's filename (file extension not needed): ");
            retArr = buildCharArr(sc.next());
            m = new Maze(retArr);
            System.out.println("\nMaze: ");
            m.display();

            System.out.println("What do you want to do (choose 1, 2, or 3):");
            System.out.println("   1: Mark only the correct path.");
            System.out.println("   2: Mark only the correct path, and display the count of STEPs.");
            System.out.println("   3: Exit");

            m.solve(sc.nextInt());

            m.display();
        }
    } // main

    /**
     * precondition: takes in String representing a file name
     * postcondition: take in a filename (without .txt), and return a char[][]
     */
    public static char[][] buildCharArr(String fileName) throws FileNotFoundException {
        File f = new File(fileName + ".txt");
        Scanner input = new Scanner(f);

        // reading the tokens on first line to get number of rows & columns to create char array
        int numRows = Integer.parseInt(input.next());
        int numColumns = Integer.parseInt(input.next());

        char[][] maze = new char[numRows][numColumns];

        // enter maze characters into array
        for (int r = 0; r < numRows; r++) {
            String row = input.next();
            maze[r] = row.toCharArray();
        }

        return maze;
    }  // buildCharArr

    private static void transfer2DGridToFile(char[][] m) throws FileNotFoundException {
        System.out.print("Enter the name of the output file? \nUse 'txt' as the file extension: ");
        Scanner input = new Scanner(System.in);
        String name = input.next();
        File outputFile = new File(name);
        if (outputFile.exists()) {
            System.out.println(name + "already exists");
            System.exit(1);
        }

        PrintWriter pw = new PrintWriter(outputFile);

        System.out.println("Enter the dimensions (row and column) of the random maze (separated the numbers with a space): ");
        pw.println(input.next() + " " + input.next());

        // transfer the 2D grid to the .txt text file
        for (char[] row : m) {
            pw.println(row);
        }
        pw.close();
    } // transfer2DGridToFile

}  // MazeDriver


class Maze {
    // constants
    private final char WALL = 'W';
    private final char DOT = '.';
    private final char START = 'S';
    private final char EXIT = 'E';
    private final char STEP = '*';
    private final char TEMP = 'o';

    // fields
    private char[][] maze;
    private int startRow, startCol;  // store where the start location is
    private boolean S_Exists = false, E_Exists = false;

    /**
     * precondition: takes in char[][] array
     * postcondition: initializes maze, startRow, and startCol
     */
    public Maze(char[][] inCharArr) {
        this.maze = inCharArr;

        // find START in maze, initialize startRow & startCol
        for (int r = 0; r < maze.length; r++) {
            for (int c = 0; c < maze[0].length; c++) {
                if (maze[r][c] == START) {
                    this.startRow = r;
                    this.startCol = c;
                    return;
                }
            }
        }
    }  // Maze

    /**
     * precondition: maze is not null
     * postcondition: prints chars from maze array to console
     */
    public void display() {
        if (maze != null) {
            for (int a = 0; a < maze.length; a++) {
                for (int b = 0; b < maze[0].length; b++) {
                    System.out.print(maze[a][b]);
                }
                System.out.println();
            }
            System.out.println();
        }
    }  // display

    /**
     * precondition: takes an int representing an option
     * postcondition: runs method based on user input
     */
    public void solve(int n) {
        final int FIND_PATH = 1;
        final int FIND_PATH_AND_COUNT_PATH_LENGTH = 2;
        final int QUIT = 3;

        System.out.println();

        if (n == FIND_PATH) {
            if (markTheCorrectPath(startRow, startCol)) {
                System.out.println("Path found!");
            } else {
                System.out.println("No Path found!");
            }
        } else if (n == FIND_PATH_AND_COUNT_PATH_LENGTH) {
            if (markCorrectPathAndCountStars(startRow, startCol, 0)) {
                System.out.println("Path found!");
            } else {
                System.out.println("No Path found!");
            }
        } else if (n == QUIT) {
            System.out.println("Goodbye!");
            System.exit(0);
        } else {
            System.out.println("Invalid choice\n");
        }
    }  // solve

    /**
     * precondition: takes in integers for row and column that correspond with coordinates in maze[][]
     * postcondition: returns a boolean whether there is a path of DOT chars from START to EXIT, marks correct path with '*'
     */
    /*  Recur until you find E, then mark the True path */
    private boolean markTheCorrectPath(int r, int c) {
        // base cases: (1) exceeding array boundaries, (2) current position is WALL (i.e not on path) or TEMP (i.e part of maze it's already passed)
        if (r >= 0 && r <= maze.length - 1 && c >= 0 && c <= maze[0].length - 1 && maze[r][c] != WALL && maze[r][c] != TEMP) {
            if (maze[r][c] == EXIT) { // base case: character is EXIT ('E')
                return true;
            }

            if (maze[r][c] != START) {  // we don't want to change the START char to TEMP
                maze[r][c] = TEMP;
            }

            // base case: if all 4 directions are false, then it hit a dead end
            if (!markTheCorrectPath(r + 1, c) && !markTheCorrectPath(r - 1, c) && !markTheCorrectPath(r, c - 1) && !markTheCorrectPath(r, c + 1)) {
                if (maze[r][c] == TEMP) {
                    maze[r][c] = DOT; // change TEMP to DOT since the path doesn't work
                }
                return false;
            }
            else { // base case: if one or more directions are true, a path exists
                if (maze[r][c] != START) {
                    maze[r][c] = STEP; // change path from DOT to STEP unless the char is START
                }
                return true;
            }
        }
        return false;
    } // markTheCorrectPath


    /**
     * precondition: r and c correspond with row and column in maze[][], count ≥ 0
     * postcondition: returns a boolean whether or not a path exists; if one does, it returns the number of steps to the endpoint, marks correct path with '*'
     */
    private boolean markCorrectPathAndCountStars(int r, int c, int count) {
        // base cases: (1) exceeding array boundaries, (2) current position is WALL (i.e not on path) or TEMP (i.e part of maze it's already passed)
        if (r >= 0 && r <= maze.length - 1 && c >= 0 && c <= maze[0].length - 1 && maze[r][c] != WALL && maze[r][c] != TEMP) {

            if (maze[r][c] == EXIT) { // base case: character is EXIT ('E')
                System.out.println("Number of steps: " + count);
                return true;
            }

            if (maze[r][c] != START) {
                maze[r][c] = TEMP;
            }

            // if all 4 directions are false, then it hit a dead end
            if (!markCorrectPathAndCountStars(r + 1, c, count + 1) && !markCorrectPathAndCountStars(r - 1, c, count + 1) &&
                    !markCorrectPathAndCountStars(r, c - 1, count + 1) && !markCorrectPathAndCountStars(r, c + 1, count + 1)) {
                if (maze[r][c] == TEMP) {
                    maze[r][c] = DOT; // change TEMP to DOT since the path doesn't work
                }
                return false;
            }
            else { // if one or more directions are true, a path exists
                if (maze[r][c] != START) {
                    maze[r][c] = STEP; // change path from DOT to STEP unless the char is START
                }
                return true;
            }
        }
        return false;
    } // markCorrectPathAndCountStars
}

/*
Enter the maze's filename (file extension not needed): maze1

Maze:
WWWWWWWW
W....W.W
WW.WW..W
W....W.W
W.W.WW.E
S.W.WW.W
WW.....W
WWWWWWWW

What do you want to do (choose 1, 2, or 3):
   1: Mark only the correct path.
   2: Mark only the correct path, and display the count of STEPs.
   3: Exit
1

Path found!
WWWWWWWW
W....W.W
WW.WW..W
W***.W.W
W*W*WW*E
S*W*WW*W
WW.****W
WWWWWWWW

Enter the maze's filename (file extension not needed): maze1

Maze:
WWWWWWWW
W....W.W
WW.WW..W
W....W.W
W.W.WW.E
S.W.WW.W
WW.....W
WWWWWWWW

What do you want to do (choose 1, 2, or 3):
   1: Mark only the correct path.
   2: Mark only the correct path, and display the count of STEPs.
   3: Exit
2

Number of steps: 14
Path found!
WWWWWWWW
W....W.W
WW.WW..W
W***.W.W
W*W*WW*E
S*W*WW*W
WW.****W
WWWWWWWW

Enter the maze's filename (file extension not needed): maze2

Maze:
WWWSWWWWWW
W....W.W.W
WWWW.....W
W...W.WW.W
W.W....W.W
WEWWWWWWWW

What do you want to do (choose 1, 2, or 3):
   1: Mark only the correct path.
   2: Mark only the correct path, and display the count of STEPs.
   3: Exit
1

Path found!
WWWSWWWWWW
W..**W.W.W
WWWW**...W
W***W*WW.W
W*W***.W.W
WEWWWWWWWW

Enter the maze's filename (file extension not needed): maze2

Maze:
WWWSWWWWWW
W....W.W.W
WWWW.....W
W...W.WW.W
W.W....W.W
WEWWWWWWWW

What do you want to do (choose 1, 2, or 3):
   1: Mark only the correct path.
   2: Mark only the correct path, and display the count of STEPs.
   3: Exit
2

Number of steps: 13
Path found!
WWWSWWWWWW
W..**W.W.W
WWWW**...W
W***W*WW.W
W*W***.W.W
WEWWWWWWWW

Enter the maze's filename (file extension not needed): maze3

Maze:
..WW
W..S
E.WW

What do you want to do (choose 1, 2, or 3):
   1: Mark only the correct path.
   2: Mark only the correct path, and display the count of STEPs.
   3: Exit
1

Path found!
..WW
W**S
E*WW

Enter the maze's filename (file extension not needed): maze3

Maze:
..WW
W..S
E.WW

What do you want to do (choose 1, 2, or 3):
   1: Mark only the correct path.
   2: Mark only the correct path, and display the count of STEPs.
   3: Exit
2

Number of steps: 4
Path found!
..WW
W**S
E*WW

Enter the maze's filename (file extension not needed): maze5NoPath

Maze:
WWEWW
W...W
W.W.X
WWWWW

What do you want to do (choose 1, 2, or 3):
   1: Mark only the correct path.
   2: Mark only the correct path, and display the count of STEPs.
   3: Exit
1

No Path found!
WWEWW
W...W
W.W.X
WWWWW

Enter the maze's filename (file extension not needed): maze5NoPath

Maze:
WWEWW
W...W
W.W.X
WWWWW

What do you want to do (choose 1, 2, or 3):
   1: Mark only the correct path.
   2: Mark only the correct path, and display the count of STEPs.
   3: Exit
2

No Path found!
WWEWW
W...W
W.W.X
WWWWW

Enter the maze's filename (file extension not needed): maze6NoPath

Maze:
WWWWW
W...W
W.W.W
S.WWE
WWWWW

What do you want to do (choose 1, 2, or 3):
   1: Mark only the correct path.
   2: Mark only the correct path, and display the count of STEPs.
   3: Exit
1

No Path found!
WWWWW
W...W
W.W.W
S.WWE
WWWWW

Enter the maze's filename (file extension not needed): maze6NoPath

Maze:
WWWWW
W...W
W.W.W
S.WWE
WWWWW

What do you want to do (choose 1, 2, or 3):
   1: Mark only the correct path.
   2: Mark only the correct path, and display the count of STEPs.
   3: Exit
2

No Path found!
WWWWW
W...W
W.W.W
S.WWE
WWWWW

*/


