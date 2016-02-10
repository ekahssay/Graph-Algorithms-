/**
 * Representation of an Edge in Graph.
 */
public class Edge {
    //starting point will be whatever vertex has this edge
    private Vertex end;
    private double weight;
    private static final int UNIT_LENGTH = 1;

    /**
     * Initializes a edge with the start vertex being the Vertex that is initialising this, and the weight being 1.
     * @param end- end of vertex
     */
    public void Edge(Vertex end){
        Edge(end,UNIT_LENGTH);

    }

    /**
     * Initializes a edge with the start vertex being the Vertex that is initialising this, and passed in weight.
     * @param end- end of vertex
     * @param weight- weight
     */
    public void Edge( Vertex end, double weight){
        this.end = end;
        this.weight = weight;
    }
    /**
     * Get the ending vertex.
     * @return end
     */
    public Vertex getEnd() {
        return end;
    }


}
