package graph;

import java.util.Collection;

public interface GraphInterface<V> {
	public Collection<V> neighbours(V v);
}
