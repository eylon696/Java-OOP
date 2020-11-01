package graph;


import java.util.HashSet;
import java.util.Set;


public class ConnectionChecker<V> {
	private GraphInterface<V> g;

	public ConnectionChecker(GraphInterface<V> g) {
		this.g = g;
	}
	//checks if we can reach v2 from v1 using a private recursion method	
	public boolean check(V v1, V v2) 
	{
		Set<V> colored = new HashSet<V>(); 
		return checkrec(v1,v2,colored); 
	}
	private boolean checkrec(V v1, V v2, Set<V> colored) {
		boolean flag=false; 
		colored.add(v1); // coloring v1 to avoid double check
		if (v1.equals(v2)) //if v1 and v2 are the same vertex
			return true; 
		if (g.neighbours(v1).contains(v2)) return true; 
		for (V i : g.neighbours(v1)) // going over all the neighbors of v1
		{
			if (!(colored.contains(i)))
				flag= flag | checkrec(i,v2,colored); 
		}
		return flag;
	}
	
}
