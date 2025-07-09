package String;

import java.util.ArrayDeque;
import java.util.LinkedList;

public class NFA {
	private Digraph G;		// digraph of epsilon transitions
	private char[] re;		// regular expression
	private int M;			// number of characters in regular expression
	
	public NFA(String regexp) {
		re = regexp.toCharArray();
		M = regexp.length();
		ArrayDeque<Integer> ops = new ArrayDeque<Integer>();	// stack
		G = new Digraph(M+1);
		
		for (int i = 0; i < M; i++) {
			int lp = i;
			if (re[i] == '(' || re[i] == '|')
				ops.push(i);
			else if (re[i] == ')') {
				int or = ops.pop();
				if (re[or] == '|') {
					lp = ops.pop();
					G.addEdge(lp, or+1);
					G.addEdge(or,  i);
				}
				else lp = or;
			}
			if (i < M-1 && re[i+1] == '*') {
				G.addEdge(lp,  i+1);
				G.addEdge(i+1, lp);
			}
			if (re[i] == '(' || re[i] == '*' || re[i] == ')')
				G.addEdge(i, i+1);
		}
	}
	
	public boolean recognize(String txt) {
		LinkedList<Integer> pc = new LinkedList<Integer>();
		DirectedDFS dfs = new DirectedDFS(G, 0);
		for (int v = 0; v < G.V(); v++)
			if (dfs.marked(v))
				pc.add(v);
		
		LinkedList<Integer> match = new LinkedList<Integer>();
		for (int i = 0; i < txt.length(); i++) {
			match.clear();
			for (int v : pc)
				if (v < M)
					if (re[v] == txt.charAt(i) || re[v] == '.')
						match.add(v+1);
			pc.clear();
			dfs = new DirectedDFS(G, match);
			for (int v = 0; v < G.V(); v++)
				if (dfs.marked(v))
					pc.add(v);
		}
		
		for (int v: pc)
			if (v == M)
				return true;
		return false;
	}
}
