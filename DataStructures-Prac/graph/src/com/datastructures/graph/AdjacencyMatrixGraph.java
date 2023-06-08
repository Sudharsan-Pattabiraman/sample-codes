package com.datastructures.graph;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author spattabiraman 2021-12-20
 */
public class AdjacencyMatrixGraph implements Graph {

    private final int[][] adjacencyMatrix;
    private final GraphType graphType;
    private final int vertices;

    public AdjacencyMatrixGraph(final int vertices, final GraphType graphType) {

        this.graphType = graphType;
        this.vertices = vertices;
        this.adjacencyMatrix = new int[vertices][vertices];

        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                adjacencyMatrix[i][j] = 0;
            }
        }
    }

    @Override
    public void addEdge(final int v1, final int v2) {

        if (v1 >= vertices || v1 < 0 || v2 >= vertices || v2 < 0) {
            throw new IllegalArgumentException("Invalid vertices " + v1 + "and " + v2);
        }

        adjacencyMatrix[v1][v2] = 1;
        if (GraphType.UNDIRECTED == graphType) {
            adjacencyMatrix[v2][v1] = 1;
        }
    }

    @Override
    public Set<Integer> getAdjacentVertices(final int v) {

        return IntStream.range(0, vertices)
            .filter(i -> adjacencyMatrix[v][i] == 1)
            .boxed()
            .collect(Collectors.toCollection(TreeSet::new));
    }

    public static void main(final String[] args) {

        final AdjacencyMatrixGraph adjacencyMatrixGraph = new AdjacencyMatrixGraph(5, GraphType.UNDIRECTED);

        adjacencyMatrixGraph.addEdge(1, 4);
        adjacencyMatrixGraph.addEdge(1, 2);
        adjacencyMatrixGraph.addEdge(2, 4);
        adjacencyMatrixGraph.addEdge(3, 1);
        adjacencyMatrixGraph.addEdge(3, 4);
        adjacencyMatrixGraph.addEdge(3, 2);
        adjacencyMatrixGraph.addEdge(0, 4);

        System.out.println(adjacencyMatrixGraph.getAdjacentVertices(1));
        System.out.println(adjacencyMatrixGraph.getAdjacentVertices(4));
        System.out.println(adjacencyMatrixGraph.getAdjacentVertices(2));
        System.out.println(adjacencyMatrixGraph.getAdjacentVertices(3));
    }
}
