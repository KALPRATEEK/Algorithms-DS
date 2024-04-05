package de.hawhamburg.hamann.ad.trees.impl;

import java.util.List;
import java.util.Map;
//main for our graph class.
public class main {
    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<>();

        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);

        graph.printGraph();

        System.out.println("--------------------------------------------------------------------------");
        Graph<String> stringGraph = new Graph<>();

        stringGraph.addEdge("A", "B");
        stringGraph.addEdge("A", "C");
        stringGraph.addEdge("B", "D");
        stringGraph.addEdge("C", "D");
        stringGraph.addEdge("D", "E");

        stringGraph.printGraph();

        System.out.println("BFS starting from vertex 1:");
        Integer targetElement = 90;
        Integer result = graph.bfs(1, targetElement);

        if (result != null) {
            System.out.println("Search successful. Target element " + targetElement + " found.");
        } else {
            System.out.println("Search unsuccessful. Target element " + targetElement + " not found in the graph.");
        }

        System.out.println("DFS starting from vertex 1:");
        Integer target = 5;
        Integer res = graph.dfs(1, target);

        if (res != null) {
            System.out.println("Search successful. Target element " + target + " found.");
        } else {
            System.out.println("Search unsuccessful. Target element " + target + " not found in the graph.");
        }
        Graph<Integer> weightedGraph = new Graph<>();
        weightedGraph.addEdge(1, 2, 3);  // Edge from 1 to 2 with weight 3
        weightedGraph.addEdge(2, 3, 2);  // Edge from 2 to 3 with weight 2
        weightedGraph.addEdge(3, 4); // to check one for default value , should give 1.
        Map<Integer, List<Integer>> adjacencyList = weightedGraph.getAdjacencyList();
        Map<Integer, Map<Integer, Integer>> weights = weightedGraph.getWeights();

        System.out.println("Adjacency List: " + adjacencyList);
        System.out.println("Weights: " + weights);
    }
}


