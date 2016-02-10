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
        PriorityQueue<Vertex> vertices = new PriorityQueue<>();
        ArrayList<Integer> order = new ArrayList<Integer>();
        vertices.add(starting);
        //set the first vertex to have been discovered
        starting.setDiscovered(true);
        // while all possible vertices reachable from starting vertex are not exhausted
        while(vertices.size() != 0){
            Vertex current = vertices.poll();
            //this will guarantee order to be sorted by what layers the nodes are found in.
           order.add(current.getIndex());
            HashSet<Edge> edges = current.getEdges();
            for(Edge edge : edges){
                Vertex next = edge.getEnd();
                // if it has been discovered, all the edges to it will have evaluted in a previous
                //iterations
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

    /**
     * Resets discovery of vertices whose index is in the array order
     * @param order - index of vertices to reset to being undiscovered in the vertices array.
     */
    private void resetDiscovered(ArrayList<Integer> order) {
        for(int i = 0; i<order.size(); i++){
            vertices.get(order.get(i)).setDiscovered(false);
        }
    }

    /**
     * Finds all vertices reachable from starting vertex by aggressively following a path until the path comes to an end or back it where
     * it started, and then backtracking to where the path originated from, and following another path from that node until all possible paths are
     * exhusted.
     * @param starting - starting vertex
     * @param reset - whether to reset verices that were reachable from starting vertex
     * @return
     */
    public ArrayList<Integer> DepthFirstSearch(Vertex starting, boolean reset){
        ArrayList<Integer> order = new ArrayList<Integer>();
        DepthFirstSearchHelper(starting,order);
        if(reset){
            resetDiscovered(order);
        }

        return order;
    }

    /* Helper method for Depth First Search
     *
     * @param current - current vertex
     * @param order - array to put the order in which the vertices are found
     */
    private  void DepthFirstSearchHelper(Vertex current, ArrayList<Integer> order){
        HashSet<Edge> edges = current.getEdges();
        order.add(current.getIndex());
        for(Edge edge : edges){
            Vertex next = edge.getEnd();
            // if it was already discovered, then DepthFirstSearch has already computed or is in the process of
            // computing all possible paths originating from it.
            if(!next.isDiscovered())
            {
                DepthFirstSearchHelper(next,order);
                next.setDiscovered(true);
            }
        }
    }

    /**
     * Finds connected Components in an undirected Graph, where the components refer to parts of the graph where there exists
     * a path from every vertex to another vertex.
     * @return - All the components, with the vertices they refer to from vertex array
     */
    public ArrayList<ArrayList<Integer>>findConnectedComponents(){
        // not storing the actual vertices because the index can be used to find them from the vertices array.
        ArrayList<ArrayList<Integer>> components = new  ArrayList<ArrayList<Integer>> ();
        for(int i = 0; i<vertices.size(); i++){
            Vertex current = vertices.get(i);
            if(!current.isDiscovered()){
                //  Breath first search returns all the vertices reachable from
                // a given vertex, and since this is undirected graph, there exists the trivial
                // path in the opposite direction of vertices to the starting vertex to all the other vertices, peeling each component one by one.
                // passing in false for the reset parameter because the loop needs to remember vertices that have already been
                // discovered by previous iterations.
                components.add(breadthFirstSearch(current,false));
            }
        }
        //all vertices should have been discovered, so make them all undiscovered
        resetVerticesDiscovered(vertices);
        return components;
    }

    /**
     * Finds Topological Sorting of a directed graph that is not cyclic. The topological ordering of a graph is
     * a such that for any U,V vertices with an edge from U to V, U has a smaller "topological number" than V.
     */
    public void topologicalSort(){
        // maximum value for topology
        int currentLabel = vertices.size();
        for(int i = 0; i<vertices.size(); i++){
            Vertex current = vertices.get(i);
            if(!current.isDiscovered()){
                DepthFirstSearch(current, false);
            }
            //guaranteed that all vertices reachable from current vertex are assigned a bigger topological number since
            // depth first search exhausts all possible paths before returning to the vertex that is the "source", and
            //currentLabel is being reduced by one each time.
            current.setTopology(currentLabel--);
        }
        resetVerticesDiscovered(vertices);
    }

    /**
     * Resets vertices to be undiscovered.
     * @param vertices - vertices to reset
     */
    private void resetVerticesDiscovered(ArrayList<Vertex> vertices) {
        for(int i = 0; i <vertices.size(); i++){
            vertices.get(i).setDiscovered(false);
        }
    }
}
