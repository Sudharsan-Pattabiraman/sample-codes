package com.datastructures.graph;

import java.util.*;

/**
 * @author spattabiraman 2021-12-20
 */
public class AdjacencySetGraph implements Graph {

    private final int vertices;
    private final Map<Integer, Node> adjacencySetGraph;
    private final GraphType graphType;

    public AdjacencySetGraph(final int vertices, final GraphType graphType) {
        this.vertices = vertices;
        this.graphType = graphType;
        this.adjacencySetGraph = new HashMap<>();

        for (int i = 0; i < vertices; i++) {
            adjacencySetGraph.put(i, new Node(i));
        }
    }

    @Override
    public void addEdge(final int v1, final int v2) {

        if (v1 >= vertices || v1 < 0 || v2 >= vertices || v2 < 0) {
            throw new IllegalArgumentException("Invalid vertices " + v1 + "and " + v2);
        }

        this.adjacencySetGraph.get(v1).adjacentVertices.add(v2);

        if (GraphType.UNDIRECTED == this.graphType) {
            this.adjacencySetGraph.get(v2).adjacentVertices.add(v1);
        }
    }

    @Override
    public Set<Integer> getAdjacentVertices(final int v) {
        return Collections.unmodifiableSet(this.adjacencySetGraph.get(v).adjacentVertices);
    }

    public static class Node {
        private final int vertexId;
        private final Set<Integer> adjacentVertices;

        public Node(final int vertexId) {
            this.vertexId = vertexId;
            this.adjacentVertices = new TreeSet<>();
        }

        public void addEdge(final int v) {
            this.adjacentVertices.add(v);
        }

        public List<Integer> getAdjacentVertices() {
            return new ArrayList<>(this.adjacentVertices);
        }

        @Override
        public String toString() {
            return "Node{" +
                "vertexId=" + vertexId +
                ", adjacentVertices=" + adjacentVertices +
                '}';
        }
    }

    public static void main(String[] args) {
        final AdjacencySetGraph adjacencySetGraph = new AdjacencySetGraph(5, GraphType.UNDIRECTED);

        adjacencySetGraph.addEdge(1, 4);
        adjacencySetGraph.addEdge(1, 2);
        adjacencySetGraph.addEdge(2, 4);
        adjacencySetGraph.addEdge(3, 1);
        adjacencySetGraph.addEdge(3, 4);
        adjacencySetGraph.addEdge(3, 2);
        adjacencySetGraph.addEdge(0, 4);

        adjacencySetGraph.adjacencySetGraph.entrySet()
            .forEach(graphyEntrySet -> System.out.println(graphyEntrySet.getKey() + "-->" + graphyEntrySet.getValue()));

        System.out.println(adjacencySetGraph.getAdjacentVertices(1));
        System.out.println(adjacencySetGraph.getAdjacentVertices(2));
        System.out.println(adjacencySetGraph.getAdjacentVertices(3));
        System.out.println(adjacencySetGraph.getAdjacentVertices(4));
    }
}
