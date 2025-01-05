package org.example.graphTravelers;

import edu.uci.ics.jung.graph.SparseMultigraph;

import java.util.Collection;

public class JungGraphAdapter<V, E> implements GraphAdapter<V, E> {
    private final SparseMultigraph<V, E> graph;

    public JungGraphAdapter() {
        this.graph = new SparseMultigraph<>();
    }

    @Override
    public void addVertex(V vertex) {
        graph.addVertex(vertex);
    }

    @Override
    public void addEdge(E edge, V source, V target) {
        graph.addEdge(edge, source, target);
    }

    @Override
    public Collection<V> getNeighbors(V vertex) {
        return graph.getNeighbors(vertex);
    }

    public SparseMultigraph<V, E> getGraph() {
        return graph;
    }
}
