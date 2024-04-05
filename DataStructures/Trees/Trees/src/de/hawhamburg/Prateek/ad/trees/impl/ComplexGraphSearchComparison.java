package de.hawhamburg.hamann.ad.trees.impl;
/*
Copyright This class is Created and Programmed by:KALPRATEEK
*/
import java.util.*;

public class ComplexGraphSearchComparison {
    public static void main(String[] args) {
        // Create a more complex graph
        Graph<Integer> graph = createComplexGraph();
        graph.printGraph();
        // Number of iterations
        int iterations = 10;

        // Random element to search for
        Integer targetElement = 75;

        // Perform the search process multiple times
        for (int i = 1; i <= iterations; i++) {
            System.out.println("Iteration " + i);

            // Measure DFS time
            long dfsStartTime = System.nanoTime();
            graph.dfs(1, targetElement);
            long dfsEndTime = System.nanoTime();
            long dfsTime = dfsEndTime - dfsStartTime;
            System.out.println("DFS Time: " + dfsTime + " nanoseconds");

            // Measure BFS time
            long bfsStartTime = System.nanoTime();
            graph.bfs(1, targetElement);
            long bfsEndTime = System.nanoTime();
            long bfsTime = bfsEndTime - bfsStartTime;
            System.out.println("BFS Time: " + bfsTime + " nanoseconds");

            System.out.println(); // Add a newline for clarity
        }
    }

    private static Graph<Integer> createComplexGraph() {
        Graph<Integer> graph = new Graph<>();
        Random random = new Random();

        // Adding vertices
        for (int i = 1; i <= 100; i++) {
            graph.addVertex(i);
        }

        // Adding random edges
        for (int i = 1; i <= 200; i++) {
            int source = random.nextInt(100) + 1;
            int destination = random.nextInt(100) + 1;
            graph.addEdge(source, destination);
        }

        return graph;
    }
}