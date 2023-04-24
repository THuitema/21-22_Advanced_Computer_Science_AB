package com.thuitema.Graphs;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Pd6ThomasHuitemaAdjMat0Driver {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner kb = new Scanner(System.in);
        System.out.print("Enter size of adjacency matrix: ");
        int size = kb.nextInt();
        AdjMat g = new AdjMat(size);
        System.out.println("Adjacency Matrix");
        System.out.println(g.toString());
        System.out.println("Add edges, source<space>target<enter>.  Enter -1 to stop.");

        while (true) {
            int source = kb.nextInt();
            if (source == -1)
                break;
            int target = kb.nextInt();
            if (!g.isEdge(source, target))
                g.addEdge(source, target);
            System.out.println(g.toString());
        }

        System.out.println(g.toString());
        System.out.print("Remove an edge? Y/N");

        if (kb.next().equalsIgnoreCase("Y")) {
            while (true) {
                System.out.print("Remove which edge?  ");
                int source = kb.nextInt();
                if (source == -1)
                    break;
                int target = kb.nextInt();
                if (g.isEdge(source, target))
                    g.removeEdge(source, target);
                else
                    System.out.println("That's not an edge");
                System.out.println(g.toString());
            }
        }

        System.out.println("Number of edges: " + g.edgeCount());
        System.out.println("The neighbors of each vertex: ");
        for (int i = 0; i < size; i++) {
            System.out.println(i + ": " + g.getNeighbors(i));
        }
    }
}

/*
Enter size of adjacency matrix: 4
Adjacency Matrix
0 0 0 0
0 0 0 0
0 0 0 0
0 0 0 0

Add edges, source<space>target<enter>.  Enter -1 to stop.
2 1
0 0 0 0
0 0 0 0
0 1 0 0
0 0 0 0

3 0
0 0 0 0
0 0 0 0
0 1 0 0
1 0 0 0

0 3
0 0 0 1
0 0 0 0
0 1 0 0
1 0 0 0

-1
0 0 0 1
0 0 0 0
0 1 0 0
1 0 0 0

Remove an edge? Y/N Y
Remove which edge?  2 1
0 0 0 1
0 0 0 0
0 0 0 0
1 0 0 0

Remove which edge?  0 1
That's not an edge
0 0 0 1
0 0 0 0
0 0 0 0
1 0 0 0

Remove which edge?  -1
Number of edges: 2
The neighbors of each vertex:
0: [3]
1: []
2: []
3: [0]

Process finished with exit code 0
 */
