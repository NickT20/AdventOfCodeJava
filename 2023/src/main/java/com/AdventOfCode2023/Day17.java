package com.AdventOfCode2023;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.*;

public class Day17 {
    public long ExecutePart1(String file) throws IOException {
        var graph = new HashMap<String, Integer>();
        try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + file))) {
            var y = 0;
            while (br.ready()) {
                var line = br.readLine();
                for (var x = 0; x < line.length(); x++) {
                    var character = line.charAt(x);
                    graph.put(getKey(x, y), character - '0');
                }
                y++;
            }
        }
        return 0;
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
}