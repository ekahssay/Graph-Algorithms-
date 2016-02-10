import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * Representation of a Graph as a list, where each vertex has a list of its edges.
 */
public class AdjacentGraph {
    ArrayList<Vertex> vertices;

    /**
     * Creates new AdjacentGraph.
     */
    public AdjacentGraph(){
        vertices = new ArrayList<Vertex>();
    }

    /**
     * Finds all vertices reachable from starting vertices by exploring nodes in "layers" which correspond to nodes nth away
     * from starting vertex.
     * @param starting - starting vertex
     * @param reset - whether to reset the vertices that were discovered to not discovered
     * @return vertices that reachable form starting vertex sorted by when they were found by the breath first search (corresponds
     * to what layer they lay in)
     */
    public ArrayList<Integer> breadthFirstSearch(Vertex starting, boolean reset){
        //used First In First Out property to explore all nodes in a given layer
        // since each
        PriorityQueue<Vertex> vertices = new PriorityQueue<>();
        ArrayList<Integer> order = new ArrayList<Integer>();
        vertices.add(starting);
        //set the first vertex to have been discovered
        starting.setDiscovered(true);
        // while all possible vertices reachable from starting vertex are exhusted
        while(vertices.size() != 0){
            Vertex current = vertices.poll();
           order.add(current.getIndex());
            HashSet<Edge> edges = current.getEdges();
            for(Edge edge : edges){
                Vertex next = edge.getEnd();
                if(!next.isDiscovered()){
                    vertices.add(next);
                    next.setDiscovered(true);
                }
            }
        }
        if(reset){
            resetDiscovered(order);
        }

        return order;
    }

    private void resetDiscovered(ArrayList<Integer> order) {
        for(int i = 0; i<order.size(); i++){
            vertices.get(order.get(i)).setDiscovered(false);
        }
    }

    public ArrayList<Integer> DepthFirstSearch(Vertex starting, boolean reset){
        ArrayList<Integer> order = new ArrayList<Integer>();
        DepthFirstSearchHelper(starting,order);
        if(reset){
            resetDiscovered(order);
        }

        return order;
    }
    private  void DepthFirstSearchHelper(Vertex current, ArrayList<Integer> order){
        HashSet<Edge> edges = current.getEdges();
        order.add(current.getIndex());
        for(Edge edge : edges){
            Vertex next = edge.getEnd();
            if(!next.isDiscovered())
            {
                DepthFirstSearchHelper(next,order);
                next.setDiscovered(true);
            }
        }
    }

    public ArrayList<ArrayList<Integer>>findConnectedComponents(){
        ArrayList<ArrayList<Integer>> components = new  ArrayList<ArrayList<Integer>> ();
        for(int i = 0; i<vertices.size(); i++){
            Vertex current = vertices.get(i);
            if(!current.isDiscovered()){
                ArrayList<Integer> component = new ArrayList<Integer>();
                components.add(breadthFirstSearch(current,false));
            }
        }
        resetVerticesDiscovered(vertices);
        return components;
    }
    public void topologicalSort(){
        int currentLabel = vertices.size();
        for(int i = 0; i<vertices.size(); i++){
            Vertex current = vertices.get(i);
            if(!current.isDiscovered()){
                DepthFirstSearch(current, false);
            }
            current.setTopology(currentLabel--);
        }
        resetVerticesDiscovered(vertices);
    }

    private void resetVerticesDiscovered(ArrayList<Vertex> vertices) {
        for(int i = 0; i <vertices.size(); i++){
            vertices.get(i).setDiscovered(false);
        }
    }
}
