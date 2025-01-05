package org.example.graphTravelers;

import java.util.Collection;

public interface GraphAdapter<V, E> {
    void addVertex(V vertex);
    void addEdge(E edge, V source, V target);
    Collection<V> getNeighbors(V vertex);
}
