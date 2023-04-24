//lesson: Graphs4: DFS_BFS
// uses AdjList

package com.thuitema.Graphs;

import java.io.*;

public class Pd6ThomasHuitemaDFS_BFS_Driver {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Edge List Representation! ");
        AdjList g = new AdjList();
        g.addVertex("A"); //if it's not there, add it.
        g.addVertex("B");

        g.addEdge("A", "C"); // <-- warning!  Be sure to add all the Vertices first,
        // or else deal with it.
        g.addVertex("C");
        g.addVertex("D");
        g.addEdge("B", "A");
        g.addEdge("C", "C");
        g.addEdge("C", "D");
        g.addEdge("D", "C");
        g.addEdge("D", "A");
        System.out.println(g); // let's look at it first

        // DFS & BFS methods
        System.out.println("Depth First Search");
        for (String name : g.getVertexMap().keySet())
            System.out.println(name + " ---> " + g.depthFirstSearch(name));

        System.out.println("\nBreadth First Search");
        for (String name : g.getVertexMap().keySet())
            System.out.println(name + " ---> " + g.breadthFirstSearch(name));

        //use the debugger to see the difference:
        System.out.println();
        System.out.println("D" + " ---> " + g.depthFirstSearch("D"));
        System.out.println("D" + " ---> " + g.breadthFirstSearch("D"));

        /*  Extension  */
         System.out.println("\nDepth First Search (Recursive)");
         for (String name : g.getVertexMap().keySet() )
         System.out.println ( name + " ---> " + g.depthFirstRecur(name) );

        // using a different graph - from #4 on Graphs 4 Packet
        AdjList g2 = new AdjList();
        g2.addVertex("1");
        g2.addVertex("2");
        g2.addVertex("3");
        g2.addVertex("4");
        g2.addVertex("5");
        g2.addVertex("6");
        g2.addVertex("7");
        g2.addVertex("8");
        g2.addVertex("9");
        g2.addVertex("10");

        g2.addEdge("1", "2");
        g2.addEdge("1", "3");
        g2.addEdge("1", "5");
        g2.addEdge("2", "3");
        g2.addEdge("3", "4");
        g2.addEdge("4", "2");
        g2.addEdge("5", "6");
        g2.addEdge("5", "9");
        g2.addEdge("5", "10");
        g2.addEdge("6", "7");
        g2.addEdge("6", "8");
        g2.addEdge("7", "1");
        g2.addEdge("7", "4");
        g2.addEdge("8", "9");
        g2.addEdge("10", "8");

        System.out.println("\nGraph 2");
        System.out.println(g2);

        System.out.println("DFS at vertex 7: " + g2.depthFirstSearch("7"));
        System.out.println("BFS at vertex 7: " + g2.breadthFirstSearch("7"));

    }
}
/********************************

 Edge List Representation! 
 A [C]
 B [A]
 C [C D]
 D [C A]

 Depth First Search
 Enter source: A
 [A [C], C [C D], D [C A]]
 Enter source: D
 [D [C A], A [C], C [C D]]
 Enter source: -1

 Breadth First Search
 Enter source: A
 [A [C], C [C D], D [C A]]
 Enter source: D
 [D [C A], C [C D], A [C]]
 Enter source: -1
 ******************************/