//uses AdjList

package com.thuitema.Graphs;

import java.io.*;

public class Pd6ThomasHuitemaAdjList_3_Driver {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Edge List Representation! ");
        System.out.println("Test the Vertex class");
        Vertex a = new Vertex("alpha");
        Vertex b = new Vertex("beta");
        a.addAdjacent(b);
        b.addAdjacent(a);
        System.out.println("get the names:\n  " + a.getName() + "\n  " + b.getName());
        System.out.println("get the list of adjacencies: \n  " + a.getAdjacencies() + "\n  " + b.getAdjacencies());
        System.out.println("toString() shows the names plus the name of the neighbor(s): \n  " + a.toString() + "\n  " + b.toString());

        System.out.println("\nTest the AdjList class");
        AdjList g = new AdjList();
        g.addVertex("A"); // if it's not in the graph, add it.
        g.addVertex("B");
        System.out.println("list of vertices in the graph:  " + g.getVertices());
        System.out.println("  map string to index:  " + g.getVertexMap());
        System.out.println("  get vertex by index 1:  " + g.getVertex(1).toString());
        System.out.println("  get vertex by name \"B\":  " + g.getVertex("B").toString());
        System.out.println("the whole graph:\n" + g.toString());

        g.addVertex("C");
        g.addVertex("D");
        g.addEdge("A", "C"); // <-- warning!  Be sure to add all the Vertices first;
        // or else deal with it.
        g.addEdge("B", "A");  // <-- warning! Do not add a new Vertex("A").  You must get
        // the old Vertex B and addAdjacent() the old Vertex A.
        g.addEdge("C", "C");
        g.addEdge("C", "D");
        g.addEdge("D", "C");
        g.addEdge("D", "A");
        System.out.println("\nlist of vertices in the graph:  " + g.getVertices());
        System.out.println("  map string to index:  " + g.getVertexMap());
        System.out.println("  get vertex by index:  " + g.getVertex(1));
        System.out.println("  get vertex by name:  " + g.getVertex("B").toString());
        System.out.println("the whole graph:\n" + g.toString());

        // DEPTH/BREADTH FIRST SEARCH OPTIONAL METHODS

        AdjList g2 = new AdjList();
        g2.addVertex("A");
        g2.addVertex("B");
        g2.addVertex("C");
        g2.addVertex("D");

        g2.addEdge("A", "C");
        g2.addEdge("A", "B");
        g2.addEdge("B", "D");
        g2.addEdge("B", "C");
        g2.addEdge("C", "A");
        g2.addEdge("C", "D");
        g2.addEdge("D", "D");


        System.out.println("Depth first search of C: " + g2.depthFirstRecur("C"));
        System.out.println("Breadth first traversal of C: " + g2.breadthFirstSearch("C"));
    }
}

/***************************
 MY OUTPUT:

 Edge List Representation! 
 Test the Vertex class
 get the names:
 alpha
 beta
 get the list of adjacencies:
 [beta [ alpha ]]
 [alpha [ beta ]]
 toString() shows the names plus the name of the neighbor(s):
 alpha [ beta ]
 beta [ alpha ]

 Test the AdjList class
 list of vertices in the graph:  [A [ ], B [ ]]
 map string to index:  {A=0, B=1}
 get vertex by index 1:  B [ ]
 get vertex by name "B":  B [ ]
 the whole graph:
 A [ ]
 B [ ]


 list of vertices in the graph:  [A [ C ], B [ A ], C [ C D ], D [ C A ]]
 map string to index:  {A=0, B=1, C=2, D=3}
 get vertex by index:  B [ A ]
 get vertex by name:  B [ A ]
 the whole graph:
 A [ C ]
 B [ A ]
 C [ C D ]
 D [ C A ]

 Depth first search of C: [C [ A D ], A [ C B ], B [ D C ], D [ D ]]
 Breadth first traversal of C: [C [ A D ], A [ C B ], D [ D ], B [ D C ]]

 Process finished with exit code 0
 ************************/
