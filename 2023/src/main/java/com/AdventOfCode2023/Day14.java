package com.AdventOfCode2023;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

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

        var width = roundRocks.stream().max((obj1, obj2) -> Integer.compare(obj1.X, obj2.X)).get().X + 1;

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

    public class Node {
        int X;
        int Y;

        public Node (int x, int y) {
            X = x;
            Y = y;
        }
    }
}