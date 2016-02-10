import java.util.HashSet;

/**
 *
 */
public class Vertex {
    private HashSet<Edge> edges;
    private int index;
    private boolean discovered;

    public int getTopology() {
        return topology;
    }

    private int topology;

    public Vertex(){
        edges = new HashSet<Edge>();
    }

    public int getIndex() {
        return index;
    }

    public void setTopology(int topology) {
        this.topology = topology;
    }

    public boolean isDiscovered() {

        return discovered;
    }

    public void setDiscovered(boolean discovered) {
        this.discovered = discovered;
    }

    public HashSet<Edge> getEdges(){

        return edges;

    }
    public void addEdge(Edge edge){
        edges.add(edge);
    }

}
