package com.thuitema.Graphs;

import java.util.*;
import java.io.*;

public class Pd6ThomasHuitemaWarshallDriver {
    public static void main(String[] args) throws FileNotFoundException {

        Scanner kb = new Scanner(System.in);
        System.out.print("Warshall's Algorithm! Enter file of names: "); // cities
        String fileNames = kb.next() + ".txt";
        Scanner sc = new Scanner(new File(fileNames));
        int size = sc.nextInt();
        AdjMat g = new AdjMat(size);
        g.readNames(fileNames);

        System.out.print("Enter file of the matrix: "); // citymatrix
        String fileGrid = kb.next() + ".txt";
        g.readGrid(fileGrid);
        System.out.println("Adjacency Matrix");
        System.out.println(g);
        System.out.println("Number of Edges: " + g.edgeCount());
        System.out.println();

        g.allPairsReachability();    //runs Warshall's algorithm
        g.displayVertices();
        System.out.println("Reachability Matrix");
        System.out.println(g);
        System.out.println("Number of Edges: " + g.edgeCount());

        while (true) {
            System.out.print("\nIs it reachable?  Enter name of start city (-1 to exit): ");
            String from = kb.next().trim();
            if (from.equals("-1"))
                break;
            System.out.print("                Enter name of end city: ");
            String to = kb.next().trim();
            System.out.println(g.isEdge(from, to));
        }

        //Extension
        System.out.println("\n================== EXTENSION ==================");
        System.out.println("List of every city's reachable cities: ");
        for (String city : g.getVertices().keySet())
            System.out.println(city + "--> " + g.getReachables(city));

        while (true) {
            System.out.print("\nList the reachable cities from: ");
            String from = kb.next();
            if (from.equals("-1"))
                break;
            System.out.println(g.getReachables(from));
        }
    }
}

/*
Warshall's Algorithm! Enter file of names: cities
Enter file of the matrix: citymatrix
Adjacency Matrix
0 0 0 0 0 0 0 1
0 0 0 1 0 0 0 0
0 0 0 0 0 1 0 1
0 0 0 0 0 1 0 1
1 0 0 0 0 0 0 0
0 1 0 1 0 0 0 0
0 0 0 0 0 1 1 0
1 0 0 0 1 0 0 0

Number of Edges: 13

0-Pendleton
1-Pensacola
2-Peoria
3-Phoenix
4-Pierre
5-Pittsburgh
6-Princeton
7-Pueblo
Reachability Matrix
1 0 0 0 1 0 0 1
1 1 0 1 1 1 0 1
1 1 0 1 1 1 0 1
1 1 0 1 1 1 0 1
1 0 0 0 1 0 0 1
1 1 0 1 1 1 0 1
1 1 0 1 1 1 1 1
1 0 0 0 1 0 0 1

Number of Edges: 40

Is it reachable?  Enter name of start city (-1 to exit): Peoria
                Enter name of end city: Pittsburgh
true

Is it reachable?  Enter name of start city (-1 to exit): Princeton
                Enter name of end city: Phoenix
true

Is it reachable?  Enter name of start city (-1 to exit): -1

================== EXTENSION ==================
List of every city's reachable cities:
Pendleton--> [Pendleton, Pierre, Pueblo]
Pensacola--> [Pendleton, Pensacola, Phoenix, Pierre, Pittsburgh, Pueblo]
Peoria--> [Pendleton, Pensacola, Phoenix, Pierre, Pittsburgh, Pueblo]
Phoenix--> [Pendleton, Pensacola, Phoenix, Pierre, Pittsburgh, Pueblo]
Pierre--> [Pendleton, Pierre, Pueblo]
Pittsburgh--> [Pendleton, Pensacola, Phoenix, Pierre, Pittsburgh, Pueblo]
Princeton--> [Pendleton, Pensacola, Phoenix, Pierre, Pittsburgh, Princeton, Pueblo]
Pueblo--> [Pendleton, Pierre, Pueblo]

List the reachable cities from: Princeton
[Pendleton, Pensacola, Phoenix, Pierre, Pittsburgh, Princeton, Pueblo]

List the reachable cities from: -1

Process finished with exit code 0
 */
