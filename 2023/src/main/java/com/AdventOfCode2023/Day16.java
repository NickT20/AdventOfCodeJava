package com.AdventOfCode2023;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.*;

public class Day16 {

    private ArrayList<Block> _blocks = new ArrayList<>();
    private HashSet<String> _energized = new HashSet<String>();
    private HashSet<String> _history = new HashSet<>();
    private int _yMax = 0;
    private int _xMax = 0;
    public long ExecutePart1(String file) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + file))) {
            var row= 0;
            while (br.ready()) {
                var line = br.readLine();
                for (var x = 0; x < line.length(); x++) {
                    var character = line.charAt(x);
                    switch (character) {
                        case '-':
                            _blocks.add(new Block(x, row, "LR", "U"));
                            _blocks.add(new Block(x, row, "LR", "D"));
                            break;
                        case '|':
                            _blocks.add(new Block(x, row, "UD", "L"));
                            _blocks.add(new Block(x, row, "UD", "R"));
                            break;
                        case '/':
                            _blocks.add(new Block(x, row, "D", "L"));
                            _blocks.add(new Block(x, row, "L", "D"));
                            _blocks.add(new Block(x, row, "R", "U"));
                            _blocks.add(new Block(x, row, "U", "R"));
                            break;
                        case '\\':
                            _blocks.add(new Block(x, row, "U", "L"));
                            _blocks.add(new Block(x, row, "R", "D"));
                            _blocks.add(new Block(x, row, "L", "U"));
                            _blocks.add(new Block(x, row, "D", "R"));
                            break;
                    }
                }
                row++;
                _yMax = row - 1;
                _xMax = line.length() - 1;
            }
        }
        var direction = "R";
        _energized.add(getKey(0, 0));
//        _history.add(getHistoryKey(0,0,"R"));

        // Converted this to "D" for part one since my input actually start going down in the first block
        Move("D", 0, 0);
        Print(_xMax, _yMax);
        return _energized.size();
    }

    public long ExecutePart2(String file) throws IOException {
        var lines = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + file))) {
            var row= 0;
            while (br.ready()) {
                var line = br.readLine();
                lines.add(line);
                for (var x = 0; x < line.length(); x++) {
                    var character = line.charAt(x);
                    switch (character) {
                        case '-':
                            _blocks.add(new Block(x, row, "LR", "U"));
                            _blocks.add(new Block(x, row, "LR", "D"));
                            break;
                        case '|':
                            _blocks.add(new Block(x, row, "UD", "L"));
                            _blocks.add(new Block(x, row, "UD", "R"));
                            break;
                        case '/':
                            _blocks.add(new Block(x, row, "D", "L"));
                            _blocks.add(new Block(x, row, "L", "D"));
                            _blocks.add(new Block(x, row, "R", "U"));
                            _blocks.add(new Block(x, row, "U", "R"));
                            break;
                        case '\\':
                            _blocks.add(new Block(x, row, "U", "L"));
                            _blocks.add(new Block(x, row, "R", "D"));
                            _blocks.add(new Block(x, row, "L", "U"));
                            _blocks.add(new Block(x, row, "D", "R"));
                            break;
                    }
                }
                row++;
                _yMax = row - 1;
                _xMax = line.length() - 1;
            }
        }
        var direction = "R";
        _energized.add(getKey(0, 0));
//        _history.add(getHistoryKey(0,0,"R"));

        // Converted this to "D" for part one since my input actually start going down in the first block

        var max = 0;
        for (var x = 0; x < _xMax; x++) {
            var block = lines.get(0).charAt(x);


            switch (block) {
                case '/':
                    Move("L", x, 0);
                    break;
                case '\\':
                    Move("R", x, 0);
                    break;
                case '-':
                    Move("L", x, 0);
                    Move("R", x, 0);
                    break;
                default:
                    Move("D", x, 0);
                    break;
            }
            if (_energized.size() > max) { max = _energized.size(); }
            _energized = new HashSet<>();
            _history = new HashSet<>();
        }

        for (var x = 0; x < _xMax; x++) {
            var block = lines.get(0).charAt(x);

            switch (block) {
                case '/':
                    Move("R", x, _yMax);
                    break;
                case '\\':
                    Move("L", x, _yMax);
                    break;
                case '-':
                    Move("L", x, _yMax);
                    Move("R", x, _yMax);
                    break;
                default:
                    Move("U", x, _yMax);
                    break;
            }
            if (_energized.size() > max) { max = _energized.size(); }
            _energized = new HashSet<>();
            _history = new HashSet<>();
        }

        for (var y = 0; y < _yMax; y++) {
            var block = lines.get(y).charAt(0);

            switch (block) {
                case '/':
                    Move("U", 0, y);
                    break;
                case '\\':
                    Move("D", 0, y);
                    break;
                case '|':
                    Move("U", 0, y);
                    Move("D", 0, y);
                    break;
                default:
                    Move("R", 0, y);
                    break;
            }
            if (_energized.size() > max) { max = _energized.size(); }
            _energized = new HashSet<>();
            _history = new HashSet<>();
        }

        for (var y = 0; y < _yMax; y++) {
            var block = lines.get(y).charAt(_xMax);

            switch (block) {
                case '/':
                    Move("D", _xMax, y);
                    break;
                case '\\':
                    Move("U", _xMax, y);
                    break;
                case '|':
                    Move("U", _xMax, y);
                    Move("D", _xMax, y);
                    break;
                default:
                    Move("L", _xMax, y);
                    break;
            }
            if (_energized.size() > max) { max = _energized.size(); }
            _energized = new HashSet<>();
            _history = new HashSet<>();
        }

        return max;
    }

    public void Move(String direction, int x, int y) {
        var finalX = x;
        var finalY = y;
        Block blockHit = null;

        if (_history.contains(getHistoryKey(x, y, direction))) {
            return;
        }

        switch (direction) {
            case "R":
                var block = _blocks.stream().filter(b -> Objects.equals(b.EnterDirection, "R") && b.Y == finalY && b.X > finalX).min(Comparator.comparingInt(b -> b.X));
                if (block.isEmpty()) {
                    while (x <= _xMax) {
                        _energized.add(getKey(x, y));
                        _history.add(getHistoryKey(x, y,"R"));
                        x++;
                    }
                    return;
                }
                blockHit = block.get();
                // moving right
                while (x < blockHit.X) {
                    _energized.add(getKey(x, y));
                    _history.add(getHistoryKey(x, y, "R"));
                    x++;
                }
                break;
            case "L":
                var block1 = _blocks.stream().filter(b -> Objects.equals(b.EnterDirection, "L") && b.Y == finalY && b.X < finalX).max(Comparator.comparingInt(b -> b.X));
                if (block1.isEmpty()) {
                    while (x >= 0) {
                        _energized.add(getKey(x, y));
                        _history.add(getHistoryKey(x, y, "L"));
                        x--;
                    }
                    return;
                }
                blockHit = block1.get();
                // moving left
                while (x > blockHit.X) {
                    _energized.add(getKey(x, y));
                    _history.add(getHistoryKey(x, y, "L"));
                    x--;
                }
                break;
            case "U":
                var block2 = _blocks.stream().filter(b -> Objects.equals(b.EnterDirection, "U") && b.X == finalX && b.Y < finalY).max(Comparator.comparingInt(b -> b.Y));
                if (block2.isEmpty()) {
                    while (y >= 0) {
                        _energized.add(getKey(x, y));
                        _history.add(getHistoryKey(x, y, "U"));
                        y--;
                    }
                    return;
                }
                blockHit = block2.get();
                // moving left
                while (y > blockHit.Y) {
                    _energized.add(getKey(x, y));
                    _history.add(getHistoryKey(x, y, "U"));
                    y--;
                }
                break;
            case "D":
                var block3 = _blocks.stream().filter(b -> Objects.equals(b.EnterDirection, "D") && b.X == finalX && b.Y > finalY).min(Comparator.comparingInt(b -> b.Y));
                if (block3.isEmpty()) {
                    while (y <= _yMax) {
                        _energized.add(getKey(x, y));
                        _history.add(getHistoryKey(x, y, "D"));
                        y++;
                    }
                    return;
                }
                blockHit = block3.get();
                // moving left
                while (y < blockHit.Y) {
                    _energized.add(getKey(x, y));
                    _history.add(getHistoryKey(x, y, "D"));
                    y++;
                }
                break;
        }

        switch (blockHit.ExitDirection) {
                case "UD":
                    Move("U", blockHit.X, blockHit.Y);
                    Move("D", blockHit.X, blockHit.Y);
                    return;
                case "LR":
                    Move("L", blockHit.X, blockHit.Y);
                    Move("R", blockHit.X, blockHit.Y);
                    return;
                default:
                    Move(blockHit.ExitDirection, blockHit.X, blockHit.Y);
                    return;
            }
    }

    public String getKey(int x, int y) {
        return MessageFormat.format("{0}-{1}", x, y);
    }

    public String getHistoryKey(int x, int y, String direction) {
        return MessageFormat.format("{0}-{1}-{2}", x, y, direction);
    }

    public void Print(int maxX, int maxY) {
        for(var y = 0; y <= maxY; y++) {
            for(var x = 0; x <= maxX; x++) {
                if (_energized.contains(getKey(x, y))) {
                    System.out.print("#");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }

    public class Block {
        int X;
        int Y;
        String ExitDirection;
        String EnterDirection;
        public Block(int x, int y, String direction, String enterDirection) {
            X = x;
            Y = y;
            ExitDirection = direction;
            EnterDirection = enterDirection;
        }
    }
}