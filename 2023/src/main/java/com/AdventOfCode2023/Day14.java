package com.AdventOfCode2023;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Array;
import java.util.*;

public class Day14 {
    public long ExecutePart1(String file) throws IOException {
        var roundRocks = new ArrayList<Node>();
        var cubeRocks = new ArrayList<Node>();
        var result = 0;
        var row = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + file))) {
            while (br.ready()) {
                var line = br.readLine();
                for(var i = 0; i < line.length(); i++) {
                    var character = line.charAt(i);
                    switch (character) {
                        case 'O':
                            roundRocks.add(new Node(i, row));
                            break;
                        case '#':
                            cubeRocks.add(new Node(i, row));
                            break;
                    }
                }
                row++;
            }
        }

        for (var rock: roundRocks) {
            var foundRock = cubeRocks.stream().filter(r -> r.Y < rock.Y && r.X == rock.X).max(Comparator.comparingInt(obj -> obj.Y));
            if (foundRock.isEmpty()) {
                foundRock = roundRocks.stream().filter(r -> r.Y < rock.Y && r.X == rock.X).max(Comparator.comparingInt(obj -> obj.Y));

                if (foundRock.isEmpty()) {
                    rock.Y = 0;
                } else {
                    rock.Y = foundRock.get().Y + 1;
                }
            } else {
                var foundRock2 = roundRocks.stream().filter(r -> r.Y < rock.Y && r.X == rock.X).max(Comparator.comparingInt(obj -> obj.Y));

                if (foundRock2.isEmpty()) {
                    rock.Y = foundRock.get().Y + 1;
                } else {
                    rock.Y = foundRock2.get().Y > foundRock.get().Y ?  foundRock2.get().Y + 1 : foundRock.get().Y + 1;
                }
            }
        }

        var width = roundRocks.stream().max(Comparator.comparingInt(obj -> obj.X)).get().X + 1;
        for (var y = 0; y < row; y++) {
            for (var x = 0; x < width; x++) {
                var finalX = x;
                var finalY = y;
                var rock1 = cubeRocks.stream().filter(r -> r.Y == finalY && r.X == finalX).findFirst();
                if (rock1.isEmpty()) {
                    rock1 = roundRocks.stream().filter(r -> r.Y == finalY && r.X == finalX).findFirst();

                    if (rock1.isEmpty()) {
                        System.out.print('.');
                    } else {
                        result += row - y;
                        System.out.print('O');
                    }
                } else {
                    System.out.print('#');
                }
            }
            System.out.println();
        }

        return result;
    }

    public long ExecutePart2(String file) throws IOException {
        var roundRocks = new ArrayList<Node>();
        var cubeRocks = new ArrayList<Node>();
        var result = 0;
        var row = 0;
        var length = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + file))) {
            while (br.ready()) {
                var line = br.readLine();
                length = line.length();
                for(var i = 0; i < line.length(); i++) {
                    var character = line.charAt(i);
                    switch (character) {
                        case 'O':
                            roundRocks.add(new Node(i, row));
                            break;
                        case '#':
                            cubeRocks.add(new Node(i, row));
                            break;
                    }
                }
                row++;
            }
        }

        var previousValues = new HashMap<Integer, Integer>();
        for (var x = 0; x < 1000; x++) {
            roundRocks.sort(Comparator.comparingInt(Node::GetY).thenComparingInt(Node::GetX));
            ShiftUp(roundRocks, cubeRocks);

            roundRocks.sort(Comparator.comparingInt(Node::GetY).thenComparingInt(Node::GetX));
            ShiftLeft(roundRocks, cubeRocks);

            roundRocks.sort(Comparator.comparingInt(Node::GetY).thenComparingInt(Node::GetX).reversed());
            ShiftDown(roundRocks, cubeRocks, row - 1);

            roundRocks.sort(Comparator.comparingInt(Node::GetY).thenComparingInt(Node::GetX).reversed());
            ShiftRight(roundRocks, cubeRocks, length - 1);
//            Print(roundRocks, row, cubeRocks, result);
//            System.out.println();
            var value = Print(roundRocks, row, cubeRocks);

            if (x % 10 == 0) {
                if (previousValues.containsKey(value)) {
                    previousValues.put(value, previousValues.get(value) + 1);
                } else {
                    previousValues.put(value, 1);
                }
            }
            System.out.println(Print(roundRocks, row, cubeRocks));
        }
//        var toCompare = previousValues.subList(90, 99);
//        while(true) {
//            System.arraycopy(previousValues, 0, previousValues, 1, previousValues.size() - 1);
//        }

        result = Print(roundRocks, row, cubeRocks);

        return result;
    }

    private static int Print(ArrayList<Node> roundRocks, int row, ArrayList<Node> cubeRocks) {
        var width = roundRocks.stream().max(Comparator.comparingInt(obj -> obj.X)).get().X + 1;
        var result = 0;
        for (var y = 0; y < row; y++) {
            for (var x = 0; x < width; x++) {
                var finalX = x;
                var finalY = y;
                var rock1 = cubeRocks.stream().filter(r -> r.Y == finalY && r.X == finalX).findFirst();
                if (rock1.isEmpty()) {
                    rock1 = roundRocks.stream().filter(r -> r.Y == finalY && r.X == finalX).findFirst();

                    if (rock1.isEmpty()) {
//                        System.out.print('.');
                    } else {
                        result += row - y;
//                        System.out.print('O');
                    }
                } else {
//                    System.out.print('#');
                }
            }
//            System.out.println();
        }
        return result;
    }

    private static void ShiftUp(ArrayList<Node> roundRocks, ArrayList<Node> cubeRocks) {
        for (var rock: roundRocks) {
            var foundRock = cubeRocks.stream().filter(r -> r.Y < rock.Y && r.X == rock.X).max(Comparator.comparingInt(obj -> obj.Y));
            if (foundRock.isEmpty()) {
                foundRock = roundRocks.stream().filter(r -> r.Y < rock.Y && r.X == rock.X).max(Comparator.comparingInt(obj -> obj.Y));

                if (foundRock.isEmpty()) {
                    rock.Y = 0;
                } else {
                    rock.Y = foundRock.get().Y + 1;
                }
            } else {
                var foundRock2 = roundRocks.stream().filter(r -> r.Y < rock.Y && r.X == rock.X).max(Comparator.comparingInt(obj -> obj.Y));

                if (foundRock2.isEmpty()) {
                    rock.Y = foundRock.get().Y + 1;
                } else {
                    rock.Y = foundRock2.get().Y > foundRock.get().Y ?  foundRock2.get().Y + 1 : foundRock.get().Y + 1;
                }
            }
        }
    }

    private static void ShiftDown(ArrayList<Node> roundRocks, ArrayList<Node> cubeRocks, int bottomRow) {
        for (var rock: roundRocks) {
            var foundRock = cubeRocks.stream().filter(r -> r.Y > rock.Y && r.X == rock.X).min(Comparator.comparingInt(obj -> obj.Y));
            if (foundRock.isEmpty()) {
                foundRock = roundRocks.stream().filter(r -> r.Y > rock.Y && r.X == rock.X).min(Comparator.comparingInt(obj -> obj.Y));

                if (foundRock.isEmpty()) {
                    rock.Y = bottomRow;
                } else {
                    rock.Y = foundRock.get().Y - 1;
                }
            } else {
                var foundRock2 = roundRocks.stream().filter(r -> r.Y > rock.Y && r.X == rock.X).min(Comparator.comparingInt(obj -> obj.Y));

                if (foundRock2.isEmpty()) {
                    rock.Y = foundRock.get().Y - 1;
                } else {
                    rock.Y = foundRock2.get().Y < foundRock.get().Y ?  foundRock2.get().Y - 1 : foundRock.get().Y - 1;
                }
            }
        }
    }

    private static void ShiftLeft(ArrayList<Node> roundRocks, ArrayList<Node> cubeRocks) {
        for (var rock: roundRocks) {
            var foundCubeRock = cubeRocks.stream().filter(r -> r.X < rock.X && r.Y == rock.Y).max(Comparator.comparingInt(obj -> obj.X));
            if (foundCubeRock.isEmpty()) {
                foundCubeRock = roundRocks.stream().filter(r -> r.X < rock.X && r.Y == rock.Y).max(Comparator.comparingInt(obj -> obj.X));

                if (foundCubeRock.isEmpty()) {
                    rock.X = 0;
                } else {
                    rock.X = foundCubeRock.get().X + 1;
                }
            } else {
                var foundRoundRock = roundRocks.stream().filter(r -> r.X < rock.X && r.Y == rock.Y).max(Comparator.comparingInt(obj -> obj.X));

                if (foundRoundRock.isEmpty()) {
                    rock.X = foundCubeRock.get().X + 1;
                } else {
                    rock.X = foundRoundRock.get().X > foundCubeRock.get().X ? foundRoundRock.get().X + 1 : foundCubeRock.get().X + 1;
                }
            }
        }
    }

    private static void ShiftRight(ArrayList<Node> roundRocks, ArrayList<Node> cubeRocks, int rightRow) {
        for (var rock: roundRocks) {
            var foundRock = cubeRocks.stream().filter(r -> r.X > rock.X && r.Y == rock.Y).min(Comparator.comparingInt(obj -> obj.X));
            if (foundRock.isEmpty()) {
                foundRock = roundRocks.stream().filter(r -> r.X > rock.X && r.Y == rock.Y).min(Comparator.comparingInt(obj -> obj.X));

                if (foundRock.isEmpty()) {
                    rock.X = rightRow;
                } else {
                    rock.X = foundRock.get().X - 1;
                }
            } else {
                var foundRock2 = roundRocks.stream().filter(r -> r.X > rock.X && r.Y == rock.Y).min(Comparator.comparingInt(obj -> obj.X));

                if (foundRock2.isEmpty()) {
                    rock.X = foundRock.get().X - 1;
                } else {
                    rock.X = foundRock2.get().X < foundRock.get().X ?  foundRock2.get().X - 1 : foundRock.get().X - 1;
                }
            }
        }
    }

    public class Node {
        int X;
        int Y;

        public int GetX() {
            return X;
        }

        public int GetY() {
            return Y;
        }

        public Node (int x, int y) {
            X = x;
            Y = y;
        }
    }
}