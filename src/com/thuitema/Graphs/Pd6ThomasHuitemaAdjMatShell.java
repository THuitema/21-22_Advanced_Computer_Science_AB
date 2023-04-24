
// Name: Thomas Huitema

package com.thuitema.Graphs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/* Resource classes and interfaces
 * for use with Graph0 AdjMat_0_Driver,
 *              Graph1 WarshallDriver,
 *          and Graph2 FloydDriver
 */


interface AdjacencyMatrix {
    void addEdge(int source, int target);
    void removeEdge(int source, int target);
    boolean isEdge(int from, int to);
    String toString(); // returns the grid as a String
    int edgeCount();
    List<Integer> getNeighbors(int source);
    List<String> getReachables(String from); // Warshall extension
}

interface Warshall // User-friendly functionality
{
    boolean isEdge(String from, String to);
    Map<String, Integer> getVertices();
    void readNames(String fileName) throws FileNotFoundException;
    void readGrid(String fileName) throws FileNotFoundException;
    void displayVertices();
    void allPairsReachability();  // Warshall's Algorithm
}

interface Floyd {
    int getCost(int from, int to);
    int getCost(String from, String to);
    void allPairsWeighted();
    int floydEdgeCount();
}

class AdjMat implements AdjacencyMatrix, Warshall,Floyd
{
    private int[][] grid; // adjacency matrix representation
    private Map<String, Integer> vertices; // name maps to index (for Warshall & Floyd)
    ArrayList<String> nameList; // for Warshall's Extension - reverses the map, index-->name

    public AdjMat(int size) {
        grid = new int[size][size];
        vertices = new TreeMap<>();
        nameList = new ArrayList<>();
    }

    // Floyd methods
    public int getCost(int from, int to) {
        return grid[from][to];
    }

    public int getCost(String from, String to) {
        return grid[vertices.get(from)][vertices.get(to)];
    }

    public void allPairsWeighted() {
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid.length; j++) {
                for(int k = 0; k < grid.length; k++) {
                    if(grid[j][i] + grid[i][k] < grid[j][k]) { // replace current cost if new path has lower cost
                        grid[j][k] = grid[j][i] + grid[i][k];
                    }
                }
            }
        }
    }

    // edge count method for floyd; different from edgeCount() because it considers cost rather than 0s & 1s
    public int floydEdgeCount() {
        int count = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid.length; c++) {
                if (grid[r][c] != 9999 && grid[r][c] != 0) {
                    count++;
                }
            }
        }
        return count;
    }

    // Warshall methods
    public void readNames(String fileName) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(fileName));
        sc.next(); // skip first line b/c city names start on second line
        while (sc.hasNextLine()) {
            String cityName = sc.next();
            nameList.add(cityName);
            vertices.put(cityName, nameList.size() - 1);
        }
    }

    public void readGrid(String fileName) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(fileName));
        sc.nextLine(); // skip first line

        int row = 0;
        while (sc.hasNextLine()) { // loop through rows
            Scanner tokens = new Scanner(sc.nextLine());
            int col = 0;
            while (tokens.hasNext()) { // loop through columns
                grid[row][col] = Integer.parseInt(tokens.next());
                col++;
            }
            row++;
        }

    }

    // returns whether a path exists from "from" to "to"
    public boolean isEdge(String from, String to) {
        int fromIndex = vertices.get(from); // convert string to index
        int toIndex = vertices.get(to);

        return grid[fromIndex][toIndex] == 1;
    }

    // precondition: allPairsReachability() has been called
    public List<String> getReachables(String from) {
        ArrayList<String> reachables = new ArrayList<>();

        int fromIndex = vertices.get(from);
        for (int c = 0; c < grid.length; c++) { // loop through columns in "from" row
            if (grid[fromIndex][c] == 1) {
                reachables.add(nameList.get(c)); // add reachable city name
            }
        }
        return reachables;
    }


    public Map<String, Integer> getVertices() {
        return vertices;
    }


    public void displayVertices() {
        for (String name : vertices.keySet()) {
            System.out.println(vertices.get(name) + "-" + name);
        }
    }

    public void allPairsReachability() {
        int n = grid.length;
        for (int v = 0; v < n; v++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][v] + grid[v][j] == 2) {
                        grid[i][j] = 1;
                    }
                }
            }
        }
    }

    public void addEdge(int source, int target) {
        grid[source][target] = 1;
    }

    public void removeEdge(int source, int target) {
        grid[source][target] = 0;
    }

    public boolean isEdge(int from, int to) {
        return grid[from][to] == 1;
    }

    //returns the grid as a String
    public String toString() {
        String out = "";
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid.length; c++) {
                out += grid[r][c] + " ";
            }
            out += "\n";
        }
        return out;
    }

    // loop through 2D array, count indices where value is 1
    public int edgeCount() {
        int count = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid.length; c++) {
                if (grid[r][c] == 1) {
                    count++;
                }
            }
        }
        return count;
    }

    public List<Integer> getNeighbors(int source) {
        List<Integer> neighbors = new ArrayList<>();

        // loop through the source's row in matrix
        for (int c = 0; c < grid.length; c++) {
            if (grid[source][c] == 1) {
                neighbors.add(c); // index is the node number
            }
        }
        return neighbors;
    }



} // AdjMat

//public class AdjMat_shell {
//    public static void main(String[] args) {
//
//    }

/******************   sample run
 Enter size of adjacency matrix: 4
 Adjacency Matrix
 0  0  0  0
 0  0  0  0
 0  0  0  0
 0  0  0  0
 Add edges, source<space>target<enter>.  Enter -1 to end.
 1 3
 0  0  0  0
 0  0  0  1
 0  0  0  0
 0  0  0  0
 0 2
 0  0  1  0
 0  0  0  1
 0  0  0  0
 0  0  0  0
 2 2
 0  0  1  0
 0  0  0  1
 0  0  1  0
 0  0  0  0
 -1
 0  0  1  0
 0  0  0  1
 0  0  1  0
 0  0  0  0
 Remove an edge? Y/N y
 Remove which edge?  0 2
 0  0  0  0
 0  0  0  1
 0  0  1  0
 0  0  0  0
 Remove which edge?  0 0
 That's not an edge
 0  0  0  0
 0  0  0  1
 0  0  1  0
 0  0  0  0
 Remove which edge?  -1
 Number of edges: 2
 The neighbors of each vertex:
 0: []
 1: [3]
 2: [2]
 3: []
 ************************************/
