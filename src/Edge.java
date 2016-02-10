/**
 * Created by anjeo on 2/10/2016.
 */
public class Edge {
    private Vertex end;
    private double weight;
    private static final int UNIT_LENGTH = 1;

    public Vertex getEnd() {
        return end;
    }

    public void Edge(Vertex end){
        Edge(end,UNIT_LENGTH);

    }
    public void Edge( Vertex end, double weight){
        this.end = end;
        this.weight = weight;
    }
}
