import java.util.*;

public class DijkstraAlgorithm {
    public static void main(String[] args) {
        // Create a graph (adjacency list representation)
        int V = 6; // Number of vertices
        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        // Add edges to the graph
        addEdge(graph, 0, 1, 2);
        addEdge(graph, 0, 2, 4);
        addEdge(graph, 1, 2, 1);
        addEdge(graph, 1, 3, 7);
        addEdge(graph, 2, 4, 3);
        addEdge(graph, 3, 4, 2);
        addEdge(graph, 4, 5, 5);

        int source = 0; // Source vertex
        dijkstra(graph, source, V);
    }

    // Class to represent a node and its weight
    static class Node {
        int vertex;
        int weight;

        Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }

    // Add an edge to the graph
    static void addEdge(List<List<Node>> graph, int src, int dest, int weight) {
        graph.get(src).add(new Node(dest, weight));
        graph.get(dest).add(new Node(src, weight)); // for undirected graph
    }

    // Dijkstra's algorithm to find the shortest path
    static void dijkstra(List<List<Node>> graph, int source, int V) {
        int[] dist = new int[V]; // To store the shortest distances
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[source] = 0; // Distance from source to itself is 0

        PriorityQueue<Node> minHeap = new PriorityQueue<>(Comparator.comparingInt(node -> node.weight));
        minHeap.offer(new Node(source, 0));

        while (!minHeap.isEmpty()) {
            Node currentNode = minHeap.poll();

            for (Node neighbor : graph.get(currentNode.vertex)) {
                int newDistance = dist[currentNode.vertex] + neighbor.weight;
                if (newDistance < dist[neighbor.vertex]) {
                    dist[neighbor.vertex] = newDistance;
                    minHeap.offer(new Node(neighbor.vertex, newDistance));
                }
            }
        }

        // Print the shortest distances from the source
        System.out.println("Shortest distances from the source vertex " + source + ":");
        for (int i = 0; i < V; i++) {
            System.out.println("Vertex " + i + ": " + dist[i]);
        }
    }
}
