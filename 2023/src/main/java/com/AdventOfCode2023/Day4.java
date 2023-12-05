package com.AdventOfCode2023;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day4 {
    public int ExecutePart1(String file) throws IOException {
        var lines = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + file))) {
            while (br.ready()) {
                var line = br.readLine();
                lines.add(line);
            }
        }
        var result = 0;
        Pattern cardNumberPattern = Pattern.compile(":\\s(\\d+\\s*)+\\|");
        var cardNumberCount = FindNumberCount(cardNumberPattern, lines.get(0));
        Pattern winningNumberPattern = Pattern.compile("\\|\\s(\\d+\\s*)+");
        var winningNumberCount = FindNumberCount(winningNumberPattern, lines.get(0));

        for (int x = 0; x < lines.size(); x++) {
            var numbers = FindDigits(lines.get(x));
            var cardNumbers = new HashSet<Integer>();
            for (var i = 1; i <= cardNumberCount; i++) {
                cardNumbers.add(numbers.get(i));
            }

            var winningNumbers = new HashSet<Integer>();
            for (var i = 1; i <= winningNumberCount; i++) {
                winningNumbers.add(numbers.get(i + cardNumberCount));
            }

            cardNumbers.retainAll(winningNumbers);
            if (cardNumbers.size() == 1) {
                result += 1;
            } else {
                result += Math.pow(2, cardNumbers.size() - 1);
            }
        }
        return result;
    }

    private int FindNumberCount(Pattern pattern, String line) {
        var count = 0;
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            String matchedNumbers = matcher.group(0);
            // Extract and print the numbers
            String[] numbers = matchedNumbers.split("\\s");
            for (String number : numbers) {
                if (!number.equals(":") && !number.equals("|") && !number.isEmpty()) {
                    count++;
                }
            }
        }
        return count;
    }

    private ArrayList<Integer> FindDigits(String line) {
        var result = new ArrayList<Integer>();
            String pattern = "[\\d]+";

            Pattern regex = Pattern.compile(pattern);
            Matcher matcher = regex.matcher(line);

            while(matcher.find()) {
                result.add(Integer.parseInt(matcher.group()));
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
        Pattern cardNumberPattern = Pattern.compile(":\\s(\\d+\\s*)+\\|");
        var cardNumberCount = FindNumberCount(cardNumberPattern, lines.get(0));
        Pattern winningNumberPattern = Pattern.compile("\\|\\s(\\d+\\s*)+");
        var winningNumberCount = FindNumberCount(winningNumberPattern, lines.get(0));

        var cards = new HashMap<Integer, Integer>();
        for (int x = 0; x < lines.size(); x++) {
            var numbers = FindDigits(lines.get(x));
            var cardNumbers = new HashSet<Integer>();
            for (var i = 1; i <= cardNumberCount; i++) {
                cardNumbers.add(numbers.get(i));
            }

            var winningNumbers = new HashSet<Integer>();
            for (var i = 1; i <= winningNumberCount; i++) {
                winningNumbers.add(numbers.get(i + cardNumberCount));
            }

            cardNumbers.retainAll(winningNumbers);
            cards.put(x + 1, cardNumbers.size());
        }
        var result = cards.size();
        for (int x = 1; x <= cards.size(); x++) {
            result += CountCards(x, cards);
        }

        return result;
    }

    private int CountCards(Integer cardNumber, HashMap<Integer, Integer> cards) {
        var count = 0;
        var cardsWon = cards.get(cardNumber);
        for(var i = 1; i <= cardsWon; i++) {
            count++;
            count += CountCards(cardNumber + i, cards);
        }
        return count;
    }
}