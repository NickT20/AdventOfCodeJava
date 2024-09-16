package com.AdventOfCode2023;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.*;

public class Day17 {
    public long ExecutePart1(String file) throws IOException {
        var graph = new Graph();
        Node17 start = null;
        Node17 target = null;
        try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + file))) {
            var y = 0;
            while (br.ready()) {
                var line = br.readLine();
                for (var x = 0; x < line.length(); x++) {
                    var character = line.charAt(x);
                    var node = new Node17(getKey(x, y));
                    if (x == 0 && y == 0) { start = node; }
                    graph.addNode(node, character - '0');
                    if (x == line.length() - 1 && !br.ready()) {
                        target = node;
                    }
                }
                y++;
            }
        }
        calculateShortestPathFromSource(graph, start);
        return target.getDistance();
    }

    public static String getKey(int x, int y) {
        return MessageFormat.format("{0}-{1}", x, y);
    }

    public static void dijkstra(HashMap<String, Integer> graph, int start) {
        int n = graph.size();
        int[] dist = new int[n];
        boolean[] visited = new boolean[n];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        for (int count = 0; count < n - 1; count++) {
            int u = minDistance(dist, visited);
            visited[u] = true;

            for (int v = 0; v < n; v++) {
                if (!visited[v] && graph.get(getKey(v, u)) != 0 && dist[u] != Integer.MAX_VALUE &&
                        dist[u] + graph.get(getKey(v, u)) < dist[v]) {
                    dist[v] = dist[u] + graph.get(getKey(v, u));
                }
            }
        }
    }

    // public static Graph calculateShortestPathFromSource(Graph graph, Node17 source) {
    //     var direction = new Direction();
    //     direction.setDirection('d');
    //     source.setDistance(0);

    //     Set<Node17> settledNodes = new HashSet<>();
    //     Set<Node17> unsettledNodes = new HashSet<>();
    
    //     unsettledNodes.add(source);
    
    //     while (unsettledNodes.size() != 0) {
    //         Node17 currentNode = getLowestDistanceNode(unsettledNodes);
    //         unsettledNodes.remove(currentNode);
    //         for (var adjacencyPair:currentNode.getAdjacentNodes(graph).entrySet()) {
    //             var parts = currentNode.getName().split("-");
    //             var x = Integer.parseInt(parts[0]);
    //             var y = Integer.parseInt(parts[1]);
    //             var parts2 = adjacencyPair.getKey().getName().split("-");
    //             var x2 = Integer.parseInt(parts2[0]);
    //             var y2 = Integer.parseInt(parts2[1]);

    //             var nD = 'u';
    //             if (x == x2 && y == y2 - 1) { nD = 'd'; }
    //             if (x == x2 + 1 && y == y2) { nD = 'r'; }
    //             if (x == x2 - 1 && y == y2) { nD = 'l'; }

    //             switch (direction.getDirection()) {
    //                 case 'u':
    //                     if (nD == 'd')
    //                         continue;
    //                     break;
    //                 case 'd':
    //                     if (nD == 'u')
    //                         continue;
    //                     break;
    //                 case 'l':
    //                     if (nD == 'r')
    //                         continue;
    //                     break;
    //                 case 'r':
    //                     if (nD == 'l')
    //                         continue;
    //                     break;
    //             }

    //             Node17 adjacentNode = adjacencyPair.getKey();
    //             Integer edgeWeight = adjacencyPair.getValue();
    //             if (!settledNodes.contains(adjacentNode)) {
    //                 calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
    //                 unsettledNodes.add(adjacentNode);
    //             }
    //         }
    //         settledNodes.add(currentNode);
    //     }
    //     return graph;
    // }
    public static Graph calculateShortestPathFromSource(Graph graph, Node17 source) {
        var direction = new Direction();
        source.setDistance(0);
    
        Set<Node17> settledNodes = new HashSet<>();
        Set<Node17> unsettledNodes = new HashSet<>();
        unsettledNodes.add(source);
    
        while (unsettledNodes.size() != 0) {
            Node17 currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
    
            String[] parts = currentNode.getName().split("-");
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);
    
            // Get the current direction
            char currentDirection = direction.getDirection();
    
            for (var adjacencyPair : currentNode.getAdjacentNodes(graph).entrySet()) {
                Node17 adjacentNode = adjacencyPair.getKey();
                Integer edgeWeight = adjacencyPair.getValue();
    
                String[] adjParts = adjacentNode.getName().split("-");
                int adjX = Integer.parseInt(adjParts[0]);
                int adjY = Integer.parseInt(adjParts[1]);
    
                // Determine the direction you're moving in
                char nextDirection = 'u'; // Default value
    
                if (adjX == x && adjY == y + 1) {
                    nextDirection = 'd'; // Moving down
                } else if (adjX == x && adjY == y - 1) {
                    nextDirection = 'u'; // Moving up
                } else if (adjX == x + 1 && adjY == y) {
                    nextDirection = 'r'; // Moving right
                } else if (adjX == x - 1 && adjY == y) {
                    nextDirection = 'l'; // Moving left
                }
    
                // Ensure you don't return to the node you just came from (by avoiding the reverse direction)
                if (isReverseDirection(currentDirection, nextDirection)) {
                    continue; // Skip this adjacent node
                }
    
                // Only update the node's shortest path if it's not settled
                if (!settledNodes.contains(adjacentNode)) {
                    calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
    
                // Update direction to the new direction you're moving in
                direction.setDirection(nextDirection);
            }
    
            settledNodes.add(currentNode);
        }
    
        return graph;
    }
    
    // Helper function to determine if we're moving in the reverse direction
    private static boolean isReverseDirection(char current, char next) {
        if (current == 'u' && next == 'd') return true;
        if (current == 'd' && next == 'u') return true;
        if (current == 'l' && next == 'r') return true;
        if (current == 'r' && next == 'l') return true;
        return false;
    }
    
    private static Node17 getLowestDistanceNode(Set<Node17> unsettledNodes) {
        Node17 lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Node17 node: unsettledNodes) {
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

    private static void calculateMinimumDistance(Node17 evaluationNode, Integer edgeWeigh, Node17 sourceNode) {
        Integer sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<Node17> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }

    private static int minDistance(int[] dist, boolean[] visited) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int v = 0; v < dist.length; v++) {
            if (!visited[v] && dist[v] <= min) {
                min = dist[v];
                minIndex = v;
            }
        }

        return minIndex;
    }

    public class Graph {
        private Map<Node17, Integer> nodes = new HashMap<>();

        public void addNode(Node17 nodeA, Integer value) {
            nodes.put(nodeA, value);
        }

        public Node17 getNodeByName(String name) {
            for (Map.Entry<Node17, Integer> entry : nodes.entrySet()) {
                if (entry.getKey().getName().equals(name)) {
                    var newNode = new Node17(entry.getKey().getName());
                    newNode.setDistance(entry.getValue());

                    return newNode;
                }
            }
            return null; // Return null if no node with the given name is found
        }
    }

    public class Node17 {
        private String name;
        private List<Node17> shortestPath = new LinkedList<>();
        private Integer distance = Integer.MAX_VALUE;
        Map<Node17, Integer> adjacentNodes = new HashMap<>();

        public Node17(String name) {
            this.name = name;
        }

        public Integer getDistance() { return this.distance; }
        public void setDistance(Integer distance) { this.distance = distance; }
        public List<Node17> getShortestPath() { return this.shortestPath; }
        public void setShortestPath(List<Node17> path) { this.shortestPath = path; }
        public Map<Node17, Integer> getAdjacentNodes(Graph g) { 
            var parts = name.split("-");
            var x = Integer.parseInt(parts[0]);
            var y = Integer.parseInt(parts[1]);

            Map<Node17, Integer> m = new HashMap<>();
            var first = g.getNodeByName(getKey(x, y+1));
            if (first != null) {
                m.put(first, first.getDistance());
            }
            var second = g.getNodeByName(getKey(x, y-1));
            if (second != null) {
                m.put(second, second.getDistance());
            }
            var third = g.getNodeByName(getKey(x+1, y));
            if (third != null) {
                m.put(third, third.getDistance());
            }
            var fourth = g.getNodeByName(getKey(x-1, y));
            if (fourth != null) {
                m.put(fourth, fourth.getDistance());
            }

            return m; 
        }
        public String getName() { return this.name; }
        private static String getKey(int x, int y) {
            return MessageFormat.format("{0}-{1}", x, y);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
    
            if (obj.getClass() != this.getClass()) {
                return false;
            }
    
            final Node17 other = (Node17) obj;
            return this.name.equals(other.name);
        }

        @Override
        public int hashCode() {
            return this.name.hashCode();
        }
    }

    public static class Direction {
        public Direction() {}
        public char direction;
        public Integer count = 0;
        public void setDirection(char d) { this.direction = d; }
        public char getDirection() {
            return this.direction;
        }
        public void setCount(Integer i){ this.count = i;}
        public Integer getCount() { return this.count; }
    }
}
