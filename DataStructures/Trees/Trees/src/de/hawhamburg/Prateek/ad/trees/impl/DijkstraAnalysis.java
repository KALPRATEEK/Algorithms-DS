package de.hawhamburg.hamann.ad.trees.impl;
/*
Copyright This class is Created and Programmed by:KALPRATEEK
*/
import java.util.Map;
import java.util.Random;

public class DijkstraAnalysis {

    public static void main(String[] args) {
        analyzeDijkstraPerformance();
    }

    private static void analyzeDijkstraPerformance() {
        for (int graphSize : new int[]{100, 500, 1000, 5000, 10000,20000,30000,40000,50000,60000,70000,80000,90000,100000}) {
            Graph<Integer> graph = createRandomGraphOfSize(graphSize);

            long startTime = System.currentTimeMillis();
            Map<Integer, Integer> distances = graph.dijkstra(1);
            long endTime = System.currentTimeMillis();

            long executionTime = endTime - startTime;
            System.out.println("Graph Size: " + graphSize + ", Execution Time: " + executionTime + " milliseconds");
        }
    }

    private static Graph<Integer> createRandomGraphOfSize(int size) {
        Graph<Integer> graph = new Graph<>();
        Random random = new Random();

        // Add vertices
        for (int i = 1; i <= size; i++) {
            graph.addVertex(i);
        }

        // Add weighted edges randomly
        for (int i = 1; i <= size - 1; i++) {
            int source = i;
            int destination = i + 1;
            int weight = random.nextInt(10) + 1;  // Random weight between 1 and 10
            graph.addEdge(source, destination, weight);
        }

        return graph;
    }
}
