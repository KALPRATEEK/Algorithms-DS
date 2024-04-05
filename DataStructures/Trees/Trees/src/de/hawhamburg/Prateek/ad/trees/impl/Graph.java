package de.hawhamburg.hamann.ad.trees.impl;

import java.util.*;

/*
Copyright This class is Created and Programmed by:KALPRATEEK
*/
class Graph<T> {
    private Map<T, List<T>> adjacencyList;  // we store all the elements  T as key and their list of all neighbours are given by the corresponding value
    private Map<T, Map<T, Integer>> weights;

    public Graph() {
        this.adjacencyList = new HashMap<>();
        this.weights = new HashMap<>();
    }

    public void addVertex(T vertex) {
        if (!adjacencyList.containsKey(vertex))// cant add duplicate vertex , so should be a unique key
        {
            adjacencyList.put(vertex, new ArrayList<>()); //initialise as no neighbours
        }
        if (!weights.containsKey(vertex))// cant add duplicate vertex , so should be a unique key
        {
            weights.put(vertex, new HashMap<>()); //initialise as no neighbours
        }
    }

    public void addEdge(T source, T destination) {
        addEdge(source, destination, 1);  // Default weight is 1
    }

    public void addEdge(T source, T destination, int weight) {
        addVertex(source);
        addVertex(destination);

        adjacencyList.get(source).add(destination);
        adjacencyList.get(destination).add(source);

        weights.get(source).put(destination, weight);
        weights.get(destination).put(source, weight);
    }

    public Map<T, Map<T, Integer>> getWeights() {
        return weights;
    }

    public void printGraph() {
        for (Map.Entry<T, List<T>> entry : adjacencyList.entrySet()) {
            System.out.print(entry.getKey() + ": ");
            List<T> neighbors = entry.getValue(); // list of all neighbours
            for (int i = 0; i < neighbors.size(); i++) {
                System.out.print(neighbors.get(i));
                if (i < neighbors.size() - 1) {
                    System.out.print(",");
                }
            }
            System.out.println();
        }
    }

    // dry run
    public static void main(String[] args) {
  // beisipiel von Vorlesung
        Graph<String> stringGraph2 = new Graph<>();
        // Add vertices
        stringGraph2.addVertex("A");
        stringGraph2.addVertex("B");
        stringGraph2.addVertex("C");
        stringGraph2.addVertex("D");
        stringGraph2.addVertex("E");
        stringGraph2.addVertex("F");
        stringGraph2.addVertex("G");


        // Add weighted edges
        stringGraph2.addEdge("A", "B", 4);
        stringGraph2.addEdge("A", "F", 10);
        stringGraph2.addEdge("A", "G", 5);
        stringGraph2.addEdge("B", "G", 2);
        stringGraph2.addEdge("B", "C", 7);
        stringGraph2.addEdge("G", "F", 4);
        stringGraph2.addEdge("G", "C", 1);
        stringGraph2.addEdge("C", "D", 12);
        stringGraph2.addEdge("E", "D", 4);
        stringGraph2.addEdge("F", "E", 3);
        stringGraph2.addEdge("E", "G", 8);
        stringGraph2.printGraph();
        // Record start time
        long startTime = System.currentTimeMillis();

        // Run Dijkstra's algorithm from vertex "A"
        Map<String, Integer> distancesFromA = stringGraph2.dijkstra("A");

        // Record end time
        long endTime = System.currentTimeMillis();

        // Print the distances
        for (Map.Entry<String, Integer> entry : distancesFromA.entrySet()) {
            System.out.println("Shortest Distance from A to " + entry.getKey() + ": " + entry.getValue());
        }

        // Print the time taken
        long totalTime = endTime - startTime;
        System.out.println("Time taken: " + totalTime + " milliseconds");


    }


    // Getter method for testing purposes
    public Map<T, List<T>> getAdjacencyList() {
        return adjacencyList;
    }


    public T bfs(T startVertex, T targetElement) {
        if (!adjacencyList.containsKey(startVertex)) {
            System.out.println("Start vertex not found in the graph.");
            return null;
        }

        Set<T> visited = new HashSet<>(); // made a set of already visited "Knoten" vertices
        Queue<T> queue = new LinkedList<>();  // make a queue put all the vertices one be one
/*
why Queue??
FIFO (First-In-First-Out): The queue follows the FIFO principle, where the first element added to the queue is
the first one to be removed. In BFS, this ensures that nodes are processed in the order they are discovered,
exploring all neighbors at the current level before moving to the next level.
 */
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            T currentVertex = queue.poll(); // give the top or head of queue.

            if (currentVertex.equals(targetElement)) {
                // Search successful, return the target element
                return targetElement;
            }

            visited.add(currentVertex); // current vertext is visited hence add in the set.

            List<T> neighbors = adjacencyList.get(currentVertex); //getting and checkig all the neighbours of current vertex.
            for (T neighbor : neighbors) // adding all the neighbours of current in the queue
            {
                if (!visited.contains(neighbor))  // if neighbour already visited skip if not ,add and continue while loop.
                {
                    queue.add(neighbor);
                }
            }
        }
        return null;

    }

    public T dfs(T startVertex, T targetElement) {
        if (!adjacencyList.containsKey(startVertex)) {
            System.out.println("Start vertex not found in the graph.");
            return null;
        }

        Set<T> visited = new HashSet<>();
        Deque<T> stack = new LinkedList<>();
        /*
        The use of a stack (either an explicit stack or the call stack in the case of recursive DFS) is natural
         for this process. When backtracking is needed, the algorithm can pop the most
        recently visited node off the stack and continue exploring from the previous decision point.
        Using a queue in DFS would fundamentally alter the nature of the algorithm.
         A queue follows the FIFO (First-In-First-Out) principle, which is not suitable
          for the depth-first exploration strategy. If DFS were implemented with a queue,
           it would no longer explore as deeply as possible along each branch before backtracking.
           Instead,
        it would behave more like BFS, which is not the intended behavior of DFS.
         */
        stack.push(startVertex);

        while (!stack.isEmpty()) {
            T currentVertex = stack.pop();

            if (currentVertex.equals(targetElement)) {
                // Search successful, return the target element
                return targetElement;
            }

            visited.add(currentVertex);

            List<T> neighbors = adjacencyList.get(currentVertex);
            for (T neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    stack.push(neighbor);
                }
            }
        }

        // Search unsuccessful, target element not found in the graph
        return null;
    }

    public Map<T, Integer> dijkstra(T startVertex) {
        Map<T, Integer> distances = new HashMap<>();
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(node -> node.distance));

        distances.put(startVertex, 0);
        priorityQueue.add(new Node(startVertex, 0));

        while (!priorityQueue.isEmpty()) {
            Node current = priorityQueue.poll();
            T currentVertex = current.vertex;

            for (Map.Entry<T, Integer> neighborEntry : weights.get(currentVertex).entrySet()) {
                T neighbor = neighborEntry.getKey();
                int newDistance = distances.get(currentVertex) + neighborEntry.getValue();

                if (!distances.containsKey(neighbor) || newDistance < distances.get(neighbor)) {
                    distances.put(neighbor, newDistance);
                    priorityQueue.add(new Node(neighbor, newDistance));
                }
            }
        }

        return distances;
    }

    private class Node {
        T vertex;
        int distance;

        Node(T vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }


}






