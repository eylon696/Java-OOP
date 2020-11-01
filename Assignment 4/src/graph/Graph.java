package graph;

import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Graph<V> {
	private Set<V> vertices = new HashSet<>();
	private Map<V, Set<V>> edges = new HashMap<>();

//Adding a new vertex to the vertices and inserting it with a new empty hashset to the map 
	public void addVertex(V v) throws GraphException {
		if (vertices.contains(v))
			throw new GraphException("The vertex is already in the set");
		vertices.add(v);
		edges.put(v, new HashSet<>());
	}

//Adding a new edge to the map
	public void addEdge(V v1, V v2) throws GraphException {
		if (!vertices.contains(v1) || !vertices.contains(v2))
			throw new GraphException("The vertex does not exist");
		if (hasEdge(v1, v2) || hasEdge(v2, v1))
			throw new GraphException("The edge already exist");
		edges.get(v1).add(v2);
		edges.get(v2).add(v1);
	}
//Check if the edge exists
	public boolean hasEdge(V v1, V v2) {
		if (!edges.containsKey(v1) || !edges.containsKey(v2))
			return false;
		if (edges.get(v1).contains(v2))
			return true;
		return false;
	}
//checks if we can reach v2 from v1 using a private recursion method
	public boolean connected(V v1, V v2) throws GraphException {
		Set<V> colored = new HashSet<V>();
		if (!vertices.contains(v1) || !vertices.contains(v2))
			throw new GraphException("The vertex does not exist");
		return connectedrec(v1, v2, colored);
	}

	private boolean connectedrec(V v1, V v2, Set<V> colored) {
		boolean flag = false;
		colored.add(v1);// coloring v1 to avoid double check
		if (v1.equals(v2)) //if v1 and v2 are the same vertex
			return true;
		for (V i : edges.get(v1)) {// going over all the neighbors of v1
			if (!colored.contains(i))
				flag = flag | connectedrec(i, v2, colored);
		}
		return flag;
	}
}
