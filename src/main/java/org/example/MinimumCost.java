package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Scanner;

public class MinimumCost {
    /**
     * The main method for the program. It handles user input for test cases and manages
     * the reading of city connections and query processing.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the number of test cases");
        int testCases = scanner.nextInt();

        while (testCases-- > 0) {
            System.out.println("Please enter the number of cities");
            int numberOfCities = scanner.nextInt();
            scanner.nextLine();
            // Map to store city names and their corresponding indices
            Map<String, Integer> cityIndexMap = new HashMap<>();
            // Graph representation as an adjacency list
            List<List<Node>> graph = new ArrayList<>();

            // Read cities and their connections
            readCityConnections(numberOfCities, scanner, cityIndexMap, graph);
            // Read paths to find
            readQueries(scanner, cityIndexMap, graph, numberOfCities);
        }
        scanner.close();
    }

    /**
     * Reads city connections and builds the graph as an adjacency list.
     *
     * @param numberOfCities the number of cities in the test case
     * @param scanner the scanner object for input
     * @param cityIndexMap a map storing city names and their indices
     * @param graph the adjacency list representing the graph
     */
    private static void readCityConnections(int numberOfCities, Scanner scanner, Map<String, Integer> cityIndexMap, List<List<Node>> graph) {
        for (int i = 0; i < numberOfCities; i++) {
            System.out.println("Please enter the name of city " + (i + 1));
            String cityName = scanner.nextLine();
            cityIndexMap.put(cityName, i);

            System.out.println("Please enter the number of neighbors for " + cityName);
            int neighbors = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            List<Node> adjacencyList = new ArrayList<>();
            for (int j = 0; j < neighbors; j++) {
                System.out.println("Enter neighbor index and cost (space-separated)");
                int neighborIndex = scanner.nextInt() - 1;
                System.out.println("neighborIndex = " + neighborIndex);
                int cost = scanner.nextInt();
                System.out.println("cost = " + cost);
                scanner.nextLine();
                adjacencyList.add(new Node(neighborIndex, cost));
            }
            graph.add(adjacencyList);
        }
    }

    /**
     * Reads queries for paths between cities and computes the minimum cost using Dijkstra's algorithm.
     *
     * @param scanner the scanner object for input
     * @param cityIndexMap a map storing city names and their indices
     * @param graph the adjacency list representing the graph
     * @param numberOfCities the number of cities in the test case
     */
    private static void readQueries(Scanner scanner, Map<String, Integer> cityIndexMap, List<List<Node>> graph, int numberOfCities) {
        System.out.println("Please enter the number of paths");
        int numberOfPaths = scanner.nextInt();
        scanner.nextLine();

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < numberOfPaths; i++) {
            System.out.println("Enter the source and destination cities (space-separated)");
            String[] cities = scanner.nextLine().split(" ");
            String startCity = cities[0];
            String destinationCity = cities[1];

            int sourceIndex = cityIndexMap.get(startCity);
            int destinationIndex = cityIndexMap.get(destinationCity);

            int cost = dijkstra(graph, sourceIndex, destinationIndex, numberOfCities);

            output.append(cost).append("\n");
        }
        System.out.println("Results:");
        System.out.println(output);
    }

    /**
     * Implements Dijkstra's algorithm to find the minimum cost between two nodes in a graph.
     *
     * @param graph the adjacency list representing the graph
     * @param startIndex the index of the starting city
     * @param destinationIndex the index of the destination city
     * @param numberOfCities the number of cities in the test case
     * @return the minimum cost to reach the destination city from the starting city
     */
    private static int dijkstra(List<List<Node>> graph, int startIndex, int destinationIndex, int numberOfCities) {
        int n = graph.size();

        int[] distances = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[startIndex] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(startIndex, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int currentIndex = current.neighborIndex;

            for (Node neighbor : graph.get(currentIndex)) {
                int newDist = distances[currentIndex] + neighbor.cost;
                if (newDist < distances[neighbor.neighborIndex]) {
                    distances[neighbor.neighborIndex] = newDist;
                    pq.add(new Node(neighbor.neighborIndex, newDist));
                }
            }
        }
        return distances[destinationIndex];
    }

    /**
     * Represents a node in the graph with its index and cost.
     */
    static class Node implements Comparable<Node>{
        int neighborIndex;
        int cost;

        public Node(int neighborIndex, int cost) {
            this.neighborIndex = neighborIndex;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.cost, other.cost);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Node n)) return false;
            return this.neighborIndex == n.neighborIndex && this.cost == n.cost;
        }

        @Override
        public int hashCode() {
            return Objects.hash(neighborIndex, cost);
        }
    }
}
