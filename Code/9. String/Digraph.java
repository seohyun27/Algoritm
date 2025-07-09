package String;

import java.util.LinkedList;

public class Digraph {
	private static final String NEWLINE = System.getProperty("line.separator");
	private int V;           				// number of vertices in this digraph
    private int E;                 			// number of edges in this digraph
    private LinkedList<Integer>[] adj;    	// adj[v] = adjacency list for vertex v
    private int[] indegree;        			// indegree[v] = indegree of vertex v
    
    @SuppressWarnings("unchecked")
	public Digraph(int V) {
        if (V < 0) 
        	throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
        this.V = V;
        this.E = 0;
        indegree = new int[V];
        adj = (LinkedList<Integer>[]) new LinkedList[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new LinkedList<Integer>();
        }
    }
    
    public int V() {
        return V;
    }
    
    public int E() {
        return E;
    }
    
    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IndexOutOfBoundsException("vertex " + v + " is not between 0 and " + (V-1));
    }
    
    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        adj[v].add(w);
        indegree[w]++;
        E++;
    }
    
    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }
    
    public int outdegree(int v) {
        validateVertex(v);
        return adj[v].size();
    }
    
    public int indegree(int v) {
        validateVertex(v);
        return indegree[v];
    }
    
    public Digraph reverse() {
        Digraph R = new Digraph(V);
        for (int v = 0; v < V; v++) {
            for (int w : adj(v)) {
                R.addEdge(w, v);
            }
        }
        return R;
    }
    
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(String.format("%d: ", v));
            for (int w : adj[v]) {
                s.append(String.format("%d ", w));
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
}
