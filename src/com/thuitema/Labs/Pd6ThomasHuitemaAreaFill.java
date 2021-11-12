package com.thuitema.Labs;

/***********************************************************************************************************************************************
 * Name: Thomas Huitema
 * Period: 6
 * Name of the Lab: Area Fill
 * Purpose of the Program: use recursive techniques
 * Due Date: 9/26/2021
 * Date Submitted: 9/26/2021
 *
 * What I learned:
 * 1. I can have a base case without explicitly writing it - my fill() method just does nothing if one of base cases are met
 * 2. I originally had an if statement to check each boundary of the grid, I combined them into one large if statement, making
 *    the program more efficient and easier to read
 *
 * How I feel about this lab:
 * I feel powerful in my abilities to write methods recursively while thinking about base and recursive cases
 *
 * What I wonder:
 * Is there a checklist I can use to determine whether my recursive method is efficient enough?
 *
 * Student(s) who helped me (to what extent): none
 * Student(s) whom I helped (to what extent): none
 *************************************************************************************************************************************************/


import java.util.Scanner;
import java.io.*;

public class Pd6ThomasHuitemaAreaFill {
    public static char[][] grid = null;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Filename: ");
        String filename = sc.next();
        grid = read(filename + ".txt");
        display(grid);
        System.out.print("\nEnter ROW COL to fill from: ");
        int row = sc.nextInt();
        int col = sc.nextInt();
        fill(grid, row, col, grid[row][col]);
        display(grid);
        sc.close();
    }

    public static char[][] read(String filename) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(filename));
        char[][] board = new char[sc.nextInt()][sc.nextInt()];
        for (int i = 0; i < board.length; i++) {
            String row = sc.next();
            board[i] = row.toCharArray();
        }
        return board;
    }

    public static void display(char[][] g) {
        for (int i = 0; i < g.length; i++) {
            for (int j = 0; j < g[i].length; j++) {
                System.out.print(g[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * pre: method called in main method
     * post: modifies char[][] g and replaces characters of the
     * index indicated on the grid with a *
     *
     * @param g grid
     * @param r row
     * @param c column
     * @param ch target character
     */

    public static void fill(char[][] g, int r, int c, char ch) // recursive method
    {
        // base cases: (1) exceeding grid boundaries, (2) current position != to target char
        // method does nothing if the row or col exceeds grid boundaries or if current char != target char

        if(r >= 0 && r <= g.length - 1 && c >= 0 && c <= g[0].length - 1) {
            if(g[r][c] == ch) {
                g[r][c] = '*';
                fill(g, r - 1, c, ch); // check position above
                fill(g, r + 1, c, ch); // check position below
                fill(g, r, c - 1, ch); // check position left
                fill(g, r, c + 1, ch); // check position right
            }
        }
    } // fill
}

/**
 TEST OUTPUT:
 Filename: area2
 ..........00
 ...0....0000
 ...000000000
 0000.....000
 ............
 ..#########.
 ..#...#####.
 ......#####.
 ...00000....

 Enter ROW COL to fill from: 8 11
 ..........00
 ...0....0000
 ...000000000
 0000*****000
 ************
 **#########*
 **#***#####*
 ******#####*
 ***00000****

 */
