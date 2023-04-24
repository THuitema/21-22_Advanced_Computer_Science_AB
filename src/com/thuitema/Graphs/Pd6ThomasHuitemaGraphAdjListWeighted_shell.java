/*
 * Name: Thomas Huitema
 * Period: 6

 * What I learned:
    We override the compareTo method in wVertex class to correctly insert into the MPQ
    When performing Dijkstra's Algorithm, you examine neighboring vertices from least to greatest distance
    The Double class has a value POSITIVE_INFINITY which is useful in this case
    By default, Priority Queues are sorted least to greatest (MPQ). I wasn't sure what their default ordering is.
 */

package com.thuitema.Graphs;

import java.io.*;
import java.util.*;

//*************************** Edge Class ********************************
// need to have this class for adjacency list representation of a weighted graph
class Edge {
    private final wVertex target;
    private final double weight;

    public Edge(wVertex argTarget, double argWeight) {
        target = argTarget;
        weight = argWeight;
    }

    // add accessors and modifiers below

    public wVertex getTarget() {
        return target;
    }

    public double getWeight() {
        return weight;
    }
} // end of Edge


// each wVertex has a name, a list of adjacent weighted edges
class wVertex implements Comparable<wVertex> {
    private final String name;
    private ArrayList<Edge> adjacencies;
    private double minDistance = Double.POSITIVE_INFINITY; // this field is needed in order to implement Dijkstra's algorithm
    private wVertex previous; // for building the actual shortest path resulted from Dijkstra's algorithm

    public wVertex(String argName) {
        name = argName;
        adjacencies = new ArrayList<>();
    }

    // Accessors and modifiers of wVertex
    public double getMinDistance() {
        return minDistance;
    }

    public void setMinDistance(double m) {
        minDistance = m;
    }

    public wVertex getPrevious() {
        return previous;
    }

    public void setPrevious(wVertex v) {
        previous = v;
    }

    public ArrayList<Edge> getAdjacencies() {
        return adjacencies;
    }

    // implement this method
    public int compareTo(wVertex other) {
        // compare vertices based on their minDistance value
        if(this.minDistance < other.getMinDistance()) {
            return -1; // i.e. this < other
        }
        else if(this.minDistance > other.getMinDistance()) {
            return 1; // i.e. this > other
        }
        return 0; // distances are same, i.e. this == other
    }

    // toString returns the name of the wVertex, not all the attributes of a wVertex; same as getName() ... Sorry.
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }
/*****************/
}  // end of wVertex

public class Pd6ThomasHuitemaGraphAdjListWeighted_shell {
    private ArrayList<wVertex> vertices = new ArrayList<wVertex>();
    private Map<String, Integer> nameToIndex = new HashMap<String, Integer>();


    public List<wVertex> getVertices() {
        return vertices;
    }

    public wVertex getVertex(int i) {
        return vertices.get(i);
    }

    public wVertex getVertex(String vertexName) {
        return vertices.get(nameToIndex.get(vertexName));
    }

    public void addVertex(String v) {
        if (!nameToIndex.containsKey(v)) // don't add the same vertex twice
        {
            vertices.add(new wVertex(v));
            nameToIndex.put(v, vertices.size() - 1);
        }
    }

    public void addEdge(String source, String target, double weight) {
        getVertex(source).getAdjacencies().add(new Edge(getVertex(target), weight));
    }

    public void minimumWeightPath(String vertexName) {
        minimumWeightPath(getVertex(vertexName));
    }

    // implement Dijkstra's algorithm here
    private void minimumWeightPath(wVertex source) {
        // your code goes here
        // Step 1: set the min distance from source to infinity
        for (wVertex v : vertices) {
            v.setMinDistance(Double.POSITIVE_INFINITY);
        }

        // set the min distance to itself to 0
        source.setMinDistance(0);

        // create a priority queue
        PriorityQueue<wVertex> vertexQueue = new PriorityQueue<>();

        // enqueue the source wVertex
        vertexQueue.add(source);

        // start processing the priority queue.
        while (!vertexQueue.isEmpty()) {
            wVertex u = vertexQueue.remove();

            for (Edge e : u.getAdjacencies()) {
                wVertex v = e.getTarget();
                double oldDist = v.getMinDistance();
                double newDist = u.getMinDistance() + e.getWeight();

                if (newDist < oldDist) { // new path that's more efficient for Edge e
                    v.setMinDistance(newDist);
                    v.setPrevious(u);

                    if (oldDist == Double.POSITIVE_INFINITY) {
                        vertexQueue.add(v);
                    }
                    else {
                        // update MPQ by removing and adding back vertex
                        vertexQueue.remove(v);
                        vertexQueue.add(v);
                    }
                }
            } // for
        } // while
    }  // end of private minimumWeightPath

    // returns the shortest path from source to vertexName
    public List<wVertex> getShortestPathTo(String vertexName) {
        return getShortestPathTo(getVertex(vertexName));
    }

    public List<wVertex> getShortestPathTo(wVertex v) {
        ArrayList<wVertex> path = new ArrayList<>();
        wVertex current = v;
        while (current != null) {
            path.add(0, current); // add to front b/c we are working backwards to get to source
            current = current.getPrevious();
        }

        return path;
    }

    //**************************************   main   *********************************************/
    public static void main(String[] args) throws FileNotFoundException {
        /* four vertex graph  	*/
        Pd6ThomasHuitemaGraphAdjListWeighted_shell graph = new Pd6ThomasHuitemaGraphAdjListWeighted_shell();

        // Do we really need the next four addVertex() statements to create the graph?
        // Think ...
        System.out.println("\n****GRAPH 1 - 4 VERTICES****");

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");

        // sketch the DAG on a piece of paper first so that you know what kind of graph you are
        // dealing with
        graph.addEdge("A", "B", 9);
        graph.addEdge("A", "C", 3);
        graph.addEdge("C", "B", 5);
        graph.addEdge("C", "D", 2);
        graph.addEdge("D", "B", 1);

        Scanner key = new Scanner(System.in);


        System.out.print("Enter start: ");
        String source = key.next();

        // find the minimum path from "source"

        graph.minimumWeightPath(source);

        // print out the minimum path cost from "source" to other vertices
        for (wVertex v : graph.getVertices()) {
            System.out.println("\nDistance to " + v + ": " + v.getMinDistance());
            System.out.println("Shortest Path from " + source + " to " + v + ": " + graph.getShortestPathTo(v));
        }


        // five vertex graph - from Q1 of Dijkstra's Algorithm Homework WS
        Pd6ThomasHuitemaGraphAdjListWeighted_shell graph2 = new Pd6ThomasHuitemaGraphAdjListWeighted_shell();

        System.out.println("\n*****GRAPH 2 - 5 VERTICES*****");
        graph2.addVertex("0");
        graph2.addVertex("1");
        graph2.addVertex("2");
        graph2.addVertex("3");
        graph2.addVertex("4");

        graph2.addEdge("0", "1", 10);
        graph2.addEdge("0", "3", 30);
        graph2.addEdge("0", "4", 100);
        graph2.addEdge("1", "2", 50);
        graph2.addEdge("2", "4", 10);
        graph2.addEdge("3", "2", 20);
        graph2.addEdge("3", "4", 60);

        Scanner key2 = new Scanner(System.in);


        System.out.print("Enter start: ");
        String source2 = key2.next();

        // find the minimum path from "source"
        graph2.minimumWeightPath(source2);

        // print out the minimum path cost from "source" to other vertices
        for (wVertex v : graph2.getVertices()) {
            System.out.println("\nDistance to " + v + ": " + v.getMinDistance());
            System.out.println("Shortest Path from " + source2 + " to " + v + ": " + graph2.getShortestPathTo(v));
        }

    }  // end of main
} // end of GraphAdjListWeighted_shell

/*******************************************  Sample Run
 ****GRAPH 1 - 4 VERTICES****
 Enter start: A

 Distance to A: 0.0
 Shortest Path from A to A: [A]

 Distance to B: 6.0
 Shortest Path from A to B: [A, C, D, B]

 Distance to C: 3.0
 Shortest Path from A to C: [A, C]

 Distance to D: 5.0
 Shortest Path from A to D: [A, C, D]

 *****GRAPH 2 - 5 VERTICES*****
 Enter start: 0

 Distance to 0: 0.0
 Shortest Path from 0 to 0: [0]

 Distance to 1: 10.0
 Shortest Path from 0 to 1: [0, 1]

 Distance to 2: 50.0
 Shortest Path from 0 to 2: [0, 3, 2]

 Distance to 3: 30.0
 Shortest Path from 0 to 3: [0, 3]

 Distance to 4: 60.0
 Shortest Path from 0 to 4: [0, 3, 2, 4]

 Process finished with exit code 0
 **********************************************************/

