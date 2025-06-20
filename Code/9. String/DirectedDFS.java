package alg.chap6.base;

public class DirectedDFS {
	private boolean[] marked;	// marked[v] = true if v is reachable from source 
	private int count;			// number of vertices reachable from s
	
	public DirectedDFS(Digraph G, int s) {
		marked = new boolean[G.V()];
		dfs(G, s);
	}
	
	public DirectedDFS(Digraph G, Iterable<Integer> sources) {
        marked = new boolean[G.V()];
        for (int v : sources) {
            if (!marked[v]) dfs(G, v);
        }
    }
	
	private void dfs(Digraph G, int v) {
		count++;
		marked[v] = true;
		for (int w : G.adj(v))
			if (marked[w] == false)
				dfs(G, w);
	}
	
	public boolean marked(int v) {
		return marked[v];
	}
	
	public int count() {
		return count;
	}
}

