package com.datastructures.graph;

import java.util.Set;

/**
 * @author spattabiraman 2021-12-20
 */
public interface Graph {

    enum GraphType{
        DIRECTED,
        UNDIRECTED;
    }

    void addEdge(int v1, int v2);

    Set<Integer> getAdjacentVertices(int v);
}
