// Name: Thomas Huitema

package com.thuitema.Graphs;

import java.util.*;

/* Resource classes and interfaces
 * for use with Graphs3: EdgeList/AdjacencyList,
 *              Graphs4: DFS-BFS
 *          and Graphs5: EdgeListCities
 */

/* Graphs 3: Adjacency List
 */


interface VertexInterface {
    String toString(); // Don't use commas in the list.  Example: "C [C D]"
    String getName();
    ArrayList<Vertex> getAdjacencies();
    void addAdjacent(Vertex v);
}


class Vertex implements VertexInterface {
    private final String name;
    private ArrayList<Vertex> adjacencies; // like a tree node

    public Vertex(String name) {
        this.name = name;
        adjacencies = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Vertex> getAdjacencies() {
        return adjacencies;
    }

    public void addAdjacent(Vertex v) {
        adjacencies.add(v);
    }

    // print each vertex and its adjacencies
    public String toString() {
        String out = name + " [";
        for (Vertex v : adjacencies) {
            out += " " + v.getName();
        }
        out += " ]";
        return out;
    }
}


// Graphs 4: DFS and BFS
interface DFS_BFS {
    List<Vertex> depthFirstSearch(String name);
    List<Vertex> depthFirstSearch(Vertex v);
    List<Vertex> breadthFirstSearch(String name);
    List<Vertex> breadthFirstSearch(Vertex v);
    /*  three extra credit methods */
    List<Vertex> depthFirstRecur(String name);
    List<Vertex> depthFirstRecur(Vertex v);
    void depthFirstRecurHelper(Vertex v, ArrayList<Vertex> reachable);
}

/* Graphs 5: Edgelist with Cities
 */
//interface EdgeListWithCities {
//    void graphFromEdgeListData(String fileName) throws FileNotFoundException;
//    int edgeCount();
//    int vertexCount(); //count the vertices in the object
//    boolean isReachable(String source, String target);
//    boolean isConnected();
//}

interface AdjListInterface {
    List<Vertex> getVertices();
    Vertex getVertex(int i);
    Vertex getVertex(String vertexName);
    Map<String, Integer> getVertexMap();
    void addVertex(String v);
    void addEdge(String source, String target);
    String toString(); // returns all vertices with their edges (omit commas)
}


class AdjList implements AdjListInterface, DFS_BFS //, EdgeListWithCities
{
    private ArrayList<Vertex> vertices = new ArrayList<Vertex>();
    private Map<String, Integer> nameToIndex = new TreeMap<String, Integer>(); // so we can give vertices a name instead of relying on index

    public void addVertex(String v) {
        if (!nameToIndex.containsKey(v)) { // prevent duplicates
            vertices.add(new Vertex(v));
            nameToIndex.put(v, vertices.size() - 1);
        }
    }

    public void addEdge(String source, String target) {
        if (nameToIndex.get(target) == null) { // create new vertex if target doesn't exist
            this.addVertex(target);
        }
        vertices.get(nameToIndex.get(source)).addAdjacent(vertices.get(nameToIndex.get(target)));
    }

    public Vertex getVertex(int i) {
        return vertices.get(i);
    }

    public Vertex getVertex(String vertexName) {
        return vertices.get(nameToIndex.get(vertexName));
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public Map<String, Integer> getVertexMap() {
        return nameToIndex;
    }

    public String toString() {
        String out = "";
        for (Vertex v : vertices) {
            out += v + "\n";
        }
        return out;
    }

    // DFS METHODS
    public List<Vertex> depthFirstSearch(String name) {
        Vertex v = vertices.get(nameToIndex.get(name));
        return depthFirstSearch(v);
    }

    public List<Vertex> depthFirstSearch(Vertex v) {
        ArrayList<Vertex> visited = new ArrayList<>();
        Stack<Vertex> stack = new Stack<>();

        stack.push(v);
        while (!stack.isEmpty()) {
            Vertex current = stack.pop();
            if (!visited.contains(current)) {
                visited.add(current);
                for (Vertex adj : current.getAdjacencies()) {
                    stack.push(adj);
                }
            }
        }
        return visited;
    }

    // BFS METHODS
    public List<Vertex> breadthFirstSearch(String name) {
        Vertex v = vertices.get(nameToIndex.get(name));
        return breadthFirstSearch(v);
    }

    public List<Vertex> breadthFirstSearch(Vertex v) {
        ArrayList<Vertex> visited = new ArrayList<>();
        Queue<Vertex> queue = new LinkedList<>();

        queue.add(v);
        while (!queue.isEmpty()) {
            Vertex current = queue.remove();
            if (!visited.contains(current)) {
                visited.add(current);
                for (Vertex adj : current.getAdjacencies()) {
                    queue.add(adj);
                }
            }
        }
        return visited;
    }

    // extra credit recursive methods
    public List<Vertex> depthFirstRecur(String name) {
        return depthFirstRecur(vertices.get(nameToIndex.get(name)));
    }

    public List<Vertex> depthFirstRecur(Vertex v) {
        ArrayList<Vertex> visited = new ArrayList<>();
        depthFirstRecurHelper(v, visited);
        return visited;
    }

    public void depthFirstRecurHelper(Vertex v, ArrayList<Vertex> reachable) {
        reachable.add(v);
        for (Vertex adj : v.getAdjacencies()) { // recur through its edges/adjacencies
            if(!reachable.contains(adj)) {
                depthFirstRecurHelper(adj, reachable);
            }
        }


    }
}


