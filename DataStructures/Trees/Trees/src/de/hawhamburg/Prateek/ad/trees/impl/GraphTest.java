package de.hawhamburg.hamann.ad.trees.impl;
/*
Copyright This class is Created and Programmed by:KALPRATEEK
*/
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    @Test
    void testAddVertex() {
        Graph<Integer> graph = new Graph<>();
        graph.addVertex(1);
        assertTrue(graph.getAdjacencyList().containsKey(1));
    }

    @Test
    void testAddEdge() {
        Graph<Integer> graph = new Graph<>();
        graph.addEdge(1, 2);
        assertTrue(graph.getAdjacencyList().containsKey(1));
        assertTrue(graph.getAdjacencyList().containsKey(2));
        assertTrue(graph.getAdjacencyList().get(1).contains(2));
        assertTrue(graph.getAdjacencyList().get(2).contains(1));
    }

    @Test
    void testPrintGraph() {
        // Since printGraph method just prints to console, we can't easily test its output
        // So, we'll just call it to make sure it doesn't throw exceptions
        Graph<Integer> graph = new Graph<>();
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.printGraph(); // This should not throw any exceptions
    }

    // Add more test methods as needed
    @Test
    public void testDFS() {
        // Create a graph and add vertices and edges
        Graph<Integer> graph = new Graph<>();
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(2, 5);
        graph.addEdge(3, 6);
        graph.addEdge(3, 7);

        // Test DFS
        assertEquals(Integer.valueOf(4), graph.dfs(1, 4));
        assertEquals(Integer.valueOf(6), graph.dfs(1, 6));
        assertNull(graph.dfs(1, 8));
    }

    @Test
    public void testBFS() {
        // Create a graph and add vertices and edges
        Graph<Integer> graph = new Graph<>();
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(2, 5);
        graph.addEdge(3, 6);
        graph.addEdge(3, 7);

        // Test BFS
        assertEquals(Integer.valueOf(4), graph.bfs(1, 4));
        assertEquals(Integer.valueOf(6), graph.bfs(1, 6));
        assertNull(graph.bfs(1, 8));
    }

    @Test
    public void testWeightedGraph() {
        Graph<Integer> weightedGraph = new Graph<>();

        // Add vertices
        weightedGraph.addVertex(1);
        weightedGraph.addVertex(2);
        weightedGraph.addVertex(3);

        // Add weighted edges
        weightedGraph.addEdge(1, 2, 3);
        weightedGraph.addEdge(2, 3, 2);

        // Check weights
        assertEquals(weightedGraph.getWeights().toString(),
                "{1={2=3}, 2={1=3, 3=2}, 3={2=2}}");

    }

    @Test
    public void testWeightedGraphWithDefaultWeights() {
        Graph<String> weightedGraph = new Graph<>();

        // Add vertices
        weightedGraph.addVertex("A");
        weightedGraph.addVertex("B");
        weightedGraph.addVertex("C");

        // Add edges without specifying weights (default weight is 1)
        weightedGraph.addEdge("A", "B");
        weightedGraph.addEdge("B", "C");

        // Check weights with default weights
        assertEquals(weightedGraph.getWeights().toString(),
                "{A={B=1}, B={A=1, C=1}, C={B=1}}");

    }

    @Test
    public void testWeightedGraphEdgeNegative() {
        Graph<String> weightedGraph = new Graph<>();

        // Add vertices
        weightedGraph.addVertex("X");
        weightedGraph.addVertex("Y");

        // Add edge with a custom weight
        weightedGraph.addEdge("X", "Y", 5);

        // Check weights
        assertNotEquals(weightedGraph.getWeights().toString(),
                "{X={Y=7}, Y={X=5}}");
    }

}
