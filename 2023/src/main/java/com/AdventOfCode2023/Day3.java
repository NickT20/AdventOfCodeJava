package com.AdventOfCode2023;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    public int ExecutePart1(String file) throws IOException {
        var lines = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + file))) {
            while (br.ready()) {
                var line = br.readLine();
                lines.add(line);
            }
        }

        var symbols = new HashSet<String>();
        FindSpecialCharacters(symbols, lines);
        return FindDigits(symbols, lines);
    }

    private void FindSpecialCharacters(HashSet<String> symbols, ArrayList<String> lines) {
        for (int x = 0; x < lines.size(); x++) {
            String pattern = "[^.\\d]+"; // Match one or more characters that are not '.'

            Pattern regex = Pattern.compile(pattern);
            Matcher matcher = regex.matcher(lines.get(x));

            while (matcher.find()) {
                symbols.add(MessageFormat.format("{0}-{1}", x, matcher.start()));
            }
        }
    }

    private int FindDigits(HashSet<String> symbols, ArrayList<String> lines) {
        var result = 0;
        for (int x = 0; x < lines.size(); x++) {
            String pattern = "[\\d]+";

            Pattern regex = Pattern.compile(pattern);
            Matcher matcher = regex.matcher(lines.get(x));

            while (matcher.find()) {
                // Does symbol exist around digit?
                var start = matcher.start();
                var end = matcher.end() - 1;
                var found = false;
                if (symbols.contains(MessageFormat.format("{0}-{1}", x, start - 1)) || symbols.contains(MessageFormat.format("{0}-{1}", x, end + 1))) {
                    found = true;
                } else {
                    for(var i = start - 1; i <= end + 1; i++) {
                        if (symbols.contains(MessageFormat.format("{0}-{1}", x - 1, i))) {
                            found = true;
                            break;
                        }
                    }
                    for(var i = start - 1; i <= end + 1; i++) {
                        if (symbols.contains(MessageFormat.format("{0}-{1}", x + 1, i))) {
                            found = true;
                            break;
                        }
                    }
                }
                if (found) {
                    result += Integer.parseInt(matcher.group(0));
                }
            }
        }
        return result;
    }

    public int ExecutePart2(String file) throws IOException {
        var lines = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + file))) {
            while (br.ready()) {
                var line = br.readLine();
                lines.add(line);
            }
        }

        var symbols = new HashMap<String, Integer>();
        FindAndStoreDigits(symbols, lines);
        var result = FindGear(symbols, lines);
        return result;
    }

    private void FindAndStoreDigits(HashMap<String, Integer> symbols, ArrayList<String> lines) {
        for (int x = 0; x < lines.size(); x++) {
            String pattern = "[\\d]+";

            Pattern regex = Pattern.compile(pattern);
            Matcher matcher = regex.matcher(lines.get(x));

            while (matcher.find()) {
                // Does symbol exist around digit?
                var start = matcher.start();
                var end = matcher.end();
                for(var i = start; i < end; i++) {
                    symbols.put(MessageFormat.format("{0}-{1}", x, i), Integer.parseInt(matcher.group(0)));
                }
            }
        }
    }

    private int FindGear(HashMap<String, Integer> symbols, ArrayList<String> lines) {
        var result = 0;
        for (int x = 0; x < lines.size(); x++) {
            String pattern = "\\*"; // Match one or more characters that are not '.'

            Pattern regex = Pattern.compile(pattern);
            Matcher matcher = regex.matcher(lines.get(x));

            while (matcher.find()) {
                // Does symbol exist around digit?
                var start = matcher.start();
                var foundDigits = new HashSet<Integer>();
                if (symbols.containsKey(MessageFormat.format("{0}-{1}", x, start - 1))) {
                    foundDigits.add(symbols.get(MessageFormat.format("{0}-{1}", x, start - 1)));
                }
                if (symbols.containsKey(MessageFormat.format("{0}-{1}", x, start + 1))) {
                    foundDigits.add(symbols.get(MessageFormat.format("{0}-{1}", x, start + 1)));
                }
                for(var i = start - 1; i <= start + 1; i++) {
                    if (symbols.containsKey(MessageFormat.format("{0}-{1}", x - 1, i))) {
                        foundDigits.add(symbols.get(MessageFormat.format("{0}-{1}", x - 1, i)));
                    }
                }
                for(var i = start - 1; i <= start + 1; i++) {
                    if (symbols.containsKey(MessageFormat.format("{0}-{1}", x + 1, i))) {
                        foundDigits.add(symbols.get(MessageFormat.format("{0}-{1}", x + 1, i)));
                    }
                }
                if (foundDigits.size() == 2) {
                    var array = foundDigits.toArray();
                    result += Integer.parseInt(array[0].toString()) * Integer.parseInt(array[1].toString());
                }
            }
        }
        return result;
    }
}