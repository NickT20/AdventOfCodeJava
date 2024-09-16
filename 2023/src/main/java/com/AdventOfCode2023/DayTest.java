package com.AdventOfCode2023;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.*;

public class DayTest {
    public static class Graph {

        private Set<Node> nodes = new HashSet<>();
        
        public void addNode(Node nodeA) {
            nodes.add(nodeA);
        }

        public Node getNodeByName(String name) {
            for (var node : nodes) {
                if (node.getName().equals(name)) {
                    return node;
                }
            }
            return null; // Return null if no node with the given name is found
        }
    }

    public static class Node {
        private String name;
        private List<Node> shortestPath = new LinkedList<>();
        private Direction shortestDirection;
        private Integer distance = Integer.MAX_VALUE;
        private Integer edgeValue = Integer.MAX_VALUE;
        Map<Node, Integer> adjacentNodes = new HashMap<>();
    
        public void addDestination(Node destination, int distance) {
            adjacentNodes.put(destination, distance);
        }
     
        public Node(String name, Integer edgeValue) {
            this.name = name;
            this.edgeValue = edgeValue;
            this.shortestDirection = new Direction(' ', 1);
        }
        
        public Integer getDistance() { return this.distance; }
        public void setDistance(Integer distance) { this.distance = distance; }
        public List<Node> getShortestPath() { return this.shortestPath; }
        public void setShortestPath(List<Node> path) { this.shortestPath = path; }
        public Map<Node, Integer> getAdjacentNodes() { return this.adjacentNodes; }
        public Integer getEdgeValue() { return this.edgeValue; }
        public String getName() { return this.name; }
        public void setShortestDistance(Direction d){ this.shortestDirection = d; }
        public Direction getShortesDirection() { return this.shortestDirection; }
    }

    private static String getKey(int x, int y) {
        return MessageFormat.format("{0}-{1}", x, y);
    }

    public static Integer calculateShortestPathFromSource(String file) throws IOException {
        var nodes = new HashMap<String, Node>();
        var graph = new Graph();
        Node source = null;
        try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + file))) {
            var y = 0;
            while (br.ready()) {
                var line = br.readLine();
                for (var x = 0; x < line.length(); x++) {
                    var character = line.charAt(x);
                    nodes.put(getKey(x, y), new Node(getKey(x, y), character - '0'));
                }
                y++;
            }
        }
        for (Map.Entry<String, Node> entry : nodes.entrySet()) {
            String key = entry.getKey();
            Node value = entry.getValue();
            var parts = key.split("-");
            var x = Integer.parseInt(parts[0]);
            var y = Integer.parseInt(parts[1]);

            if (nodes.get(getKey(x, y + 1)) != null) {
                var a = nodes.get(getKey(x, y + 1));
                value.addDestination(a, a.getEdgeValue());
            }
            if (nodes.get(getKey(x, y - 1)) != null) {
                var a = nodes.get(getKey(x, y - 1));
                value.addDestination(a, a.getEdgeValue());
            }
            if (nodes.get(getKey(x + 1, y)) != null) {
                var a = nodes.get(getKey(x + 1, y));
                value.addDestination(a, a.getEdgeValue());
            }
            if (nodes.get(getKey(x - 1, y)) != null) {
                var a = nodes.get(getKey(x - 1, y));
                value.addDestination(a, a.getEdgeValue());
            }
        }

        for (var entry : nodes.values()) {
            graph.addNode(entry);
        } 

        source = nodes.get("0-0");
        source.setDistance(0);

        Set<Node> settledNodes = new HashSet<>();
        Set<Node> unsettledNodes = new HashSet<>();

        unsettledNodes.add(source);

        while (unsettledNodes.size() != 0) {
            Node currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            String[] parts = currentNode.getName().split("-");
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);
            for (var adjacencyPair:currentNode.getAdjacentNodes().entrySet()) {
                var d1 = currentNode.getShortesDirection();
                var direction = new Direction(d1.getDirection(), d1.getCount());
                Node adjacentNode = adjacencyPair.getKey();
                Integer edgeWeight = adjacencyPair.getValue();
                String[] adjParts = adjacentNode.getName().split("-");
                int adjX = Integer.parseInt(adjParts[0]);
                int adjY = Integer.parseInt(adjParts[1]);
    
                // Determine the direction you're moving in
                char nextDirection = ' '; // Default value
    
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
                if (isReverseDirection(direction.getDirection(), nextDirection)) {
                    continue; // Skip this adjacent node
                }

                if (direction.getDirection() == nextDirection) {
                    if (direction.getCount() == 3)
                        continue;
                    direction.incrementCount();
                } else {
                    direction.setDirection(nextDirection);
                }

                if (!settledNodes.contains(adjacentNode)) {
                    calculateMinimumDistance(adjacentNode, edgeWeight, currentNode, direction);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }

        var a = graph.getNodeByName("12-12");
        return a.getDistance();
    }
    
    // Helper function to determine if we're moving in the reverse direction
    private static boolean isReverseDirection(char current, char next) {
        if (current == 'u' && next == 'd') return true;
        if (current == 'd' && next == 'u') return true;
        if (current == 'l' && next == 'r') return true;
        if (current == 'r' && next == 'l') return true;
        return false;
    }

    private static Node getLowestDistanceNode(Set < Node > unsettledNodes) {
        Node lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Node node: unsettledNodes) {
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

    private static void calculateMinimumDistance(Node evaluationNode, Integer edgeWeigh, Node sourceNode, Direction direction) {
        Integer sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
            evaluationNode.setShortestDistance(direction);
        }
    }

    public static class Direction {
        public char direction;

        public Direction(char dir, int count) {
            this.direction = dir;
            this.count = count;
        }
        public int count = 1;
        public void setDirection(char d) { this.direction = d; count = 1; }
        public char getDirection() {
            return this.direction;
        }
        public Integer getCount() { return this.count; }
        public void incrementCount() { this.count++; }
    }
}
