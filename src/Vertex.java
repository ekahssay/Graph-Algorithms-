import java.util.HashSet;

/**
 * Represents a vertex in a Graph with edges, index in AdjacentGraph representation, and
 * whether it has been discovered or not.
 */
public class Vertex {
    //using a hashset because there are no duplicate edges and allows lookup of edges in O(1)
    // so that randomized min cut algorithm can be implemented in O(vertices + edges).
    private HashSet<Edge> edges;
    //position in AdjacentGraph
    private int index;
    private boolean discovered;
    private int topology;
    /**
     * Gets topology number.
     * @return topology number
     */
    public int getTopology() {
        return topology;
    }

    /**
     * Creates a new Vertex.
     */
    public Vertex(){
        edges = new HashSet<Edge>();
    }
    /**
     * Gets Index in AdjacentGraph.
     * @return index
     */
    public int getIndex() {
        return index;
    }
    /**
     * Sets topology number.
     */
    public void setTopology(int topology) {
        this.topology = topology;
    }
    /**
     * Gets whether a node has been discovered.
     * @return discovery
     */
    public boolean isDiscovered() {
        return discovered;
    }

    public void setDiscovered(boolean discovered) {
        this.discovered = discovered;
    }

    /**
     * Gets Edges
     * @return Edges
     */
    public HashSet<Edge> getEdges(){
        return edges;

    }

    /**
     * Adds an edge.
     * @param edge - edge to add
     */
    public void addEdge(Edge edge){
        edges.add(edge);
    }

}
