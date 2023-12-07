package com.AdventOfCode2023;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day7 {
    public int ExecutePart1(String file) throws IOException {
        var lines = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + file))) {
            while (br.ready()) {
                var line = br.readLine();
                lines.add(line);
            }
        }
        var hands = new ArrayList<Hand>();
        for(var i = 0; i < lines.size(); i++) {
            var line = lines.get(i);
            var parts = line.split(" ");
            hands.add(new Hand(parts[0], Integer.parseInt(parts[1])));
        }

        var fiveOfAKind = new HashSet<Hand>();
        var fourOfAKind = new HashSet<Hand>();
        var fullHouse = new HashSet<Hand>();
        var three = new HashSet<Hand>();
        var two = new HashSet<Hand>();
        var one = new HashSet<Hand>();
        var none = new HashSet<Hand>();

        for(var i = 0; i < hands.size(); i++) {
            switch (hands.get(i).getHandValue()) {
                case "Five of a kind":
                    fiveOfAKind.add(hands.get(i));
                    break;
                case "Four of a kind":
                    fourOfAKind.add(hands.get(i));
                    break;
                case "Full house":
                    fullHouse.add(hands.get(i));
                    break;
                case "Three of a kind":
                    three.add(hands.get(i));
                    break;
                case "Two pair":
                    two.add(hands.get(i));
                    break;
                case "One pair":
                    one.add(hands.get(i));
                    break;
                default:
                    none.add(hands.get(i));
            }
        }

        // Define a custom comparator based on the order of values
        Comparator<Hand> customComparator = (hand1, hand2) -> {
            String values = "AKQJT98765432";
            int minLength = Math.min(hand1.getHand().length(), hand2.getHand().length());

            for (int i = 0; i < minLength; i++) {
                int result = values.indexOf(hand2.getHand().charAt(i)) - values.indexOf(hand1.getHand().charAt(i));
                if (result != 0) {
                    return result;
                }
            }

            return Integer.compare(hand1.getHand().length(), hand2.getHand().length());
        };
        var place = new AtomicInteger(1);
        var result = new AtomicInteger(0);

        none.stream().sorted(customComparator).toList().forEach(hand -> {
            result.addAndGet(hand.getBid() * place.get());
            place.getAndIncrement();
        });
        one.stream().sorted(customComparator).toList().forEach(hand -> {
            result.addAndGet(hand.getBid() * place.get());
            place.getAndIncrement();
        });
        two.stream().sorted(customComparator).toList().forEach(hand -> {
            result.addAndGet(hand.getBid() * place.get());
            place.getAndIncrement();
        });
        three.stream().sorted(customComparator).toList().forEach(hand -> {
            result.addAndGet(hand.getBid() * place.get());
            place.getAndIncrement();
        });
        fullHouse.stream().sorted(customComparator).toList().forEach(hand -> {
            result.addAndGet(hand.getBid() * place.get());
            place.getAndIncrement();
        });
        fourOfAKind.stream().sorted(customComparator).toList().forEach(hand -> {
            result.addAndGet(hand.getBid() * place.get());
            place.getAndIncrement();
        });
        fiveOfAKind.stream().sorted(customComparator).toList().forEach(hand -> {
            result.addAndGet(hand.getBid() * place.get());
            place.getAndIncrement();
        });

        return result.get();
    }

    public int ExecutePart2(String file) throws IOException {
        var lines = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + file))) {
            while (br.ready()) {
                var line = br.readLine();
                lines.add(line);
            }
        }
        var hands = new ArrayList<Hand>();
        for(var i = 0; i < lines.size(); i++) {
            var line = lines.get(i);
            var parts = line.split(" ");
            hands.add(new Hand(parts[0], Integer.parseInt(parts[1])));
        }

        var fiveOfAKind = new HashSet<Hand>();
        var fourOfAKind = new HashSet<Hand>();
        var fullHouse = new HashSet<Hand>();
        var three = new HashSet<Hand>();
        var two = new HashSet<Hand>();
        var one = new HashSet<Hand>();
        var none = new HashSet<Hand>();

        for(var i = 0; i < hands.size(); i++) {
            switch (hands.get(i).getHandValue2()) {
                case "Five of a kind":
                    fiveOfAKind.add(hands.get(i));
                    break;
                case "Four of a kind":
                    fourOfAKind.add(hands.get(i));
                    break;
                case "Full house":
                    fullHouse.add(hands.get(i));
                    break;
                case "Three of a kind":
                    three.add(hands.get(i));
                    break;
                case "Two pair":
                    two.add(hands.get(i));
                    break;
                case "One pair":
                    one.add(hands.get(i));
                    break;
                default:
                    none.add(hands.get(i));
            }
        }

        // Define a custom comparator based on the order of values
        Comparator<Hand> customComparator = (hand1, hand2) -> {
            String values = "AKQT98765432J";
            int minLength = Math.min(hand1.getHand().length(), hand2.getHand().length());

            for (int i = 0; i < minLength; i++) {
                int result = values.indexOf(hand2.getHand().charAt(i)) - values.indexOf(hand1.getHand().charAt(i));
                if (result != 0) {
                    return result;
                }
            }

            return -1;
        };
        var place = new AtomicInteger(1);
        var result = new AtomicInteger(0);

        none.stream().sorted(customComparator).toList().forEach(hand -> {
            System.out.println(hand.getHand());
            result.addAndGet(hand.getBid() * place.get());
            place.getAndIncrement();
        });
        one.stream().sorted(customComparator).toList().forEach(hand -> {
            System.out.println(hand.getHand());
            result.addAndGet(hand.getBid() * place.get());
            place.getAndIncrement();
        });
        two.stream().sorted(customComparator).toList().forEach(hand -> {
            System.out.println(hand.getHand());
            result.addAndGet(hand.getBid() * place.get());
            place.getAndIncrement();
        });
        three.stream().sorted(customComparator).toList().forEach(hand -> {
            System.out.println(hand.getHand());
            result.addAndGet(hand.getBid() * place.get());
            place.getAndIncrement();
        });
        fullHouse.stream().sorted(customComparator).toList().forEach(hand -> {
            System.out.println(hand.getHand());
            result.addAndGet(hand.getBid() * place.get());
            place.getAndIncrement();
        });
        fourOfAKind.stream().sorted(customComparator).toList().forEach(hand -> {
            System.out.println(hand.getHand());
            result.addAndGet(hand.getBid() * place.get());
            place.getAndIncrement();
        });
        fiveOfAKind.stream().sorted(customComparator).toList().forEach(hand -> {
            System.out.println(hand.getHand());
            result.addAndGet(hand.getBid() * place.get());
            place.getAndIncrement();
        });

        return result.get();
    }
    public class Hand {
        private String _hand;
        private Integer _bid;
        public String getHand() {
            return this._hand;
        }

        public Integer getBid() {
            return this._bid;
        }

        public Integer getNumberOfWildCards() {
            var count = 0;
            for (int i = 0; i < 5; i++) {
                if (_hand.charAt(i) == 'J') {
                    count++;
                }
            }
            return count;
        }

        public String getHandWithoutWildCards() {
            StringBuilder result = new StringBuilder();

            for (int i = 0; i < 5; i++) {
                char currentChar = _hand.charAt(i);

                // Only append characters that are not equal to the target character
                if (currentChar != 'J') {
                    result.append(currentChar);
                }
            }

            return result.toString();
        }

        public Hand(String hand, Integer bid) {
            _hand = hand;
            _bid = bid;
        }

        public String getHandValue() {
            var charArray = _hand.toCharArray();
            Arrays.sort(charArray);

            if (charArray[0] == charArray[4]) {
                return "Five of a kind";
            }

            if (charArray[0] == charArray[3] || charArray[1] == charArray[4]) {
                return "Four of a kind";
            }

            if (charArray[0] != charArray[1] && charArray[1] != charArray[2] && charArray[2] != charArray[3] && charArray[3] != charArray[4]) {
                return "High card";
            }

            var map = new HashMap<Character, Integer>();
            for(var i = 0; i < 5; i++) {
                if (map.containsKey(charArray[i])) {
                    map.put(charArray[i], map.get(charArray[i]) + 1);
                } else {
                    map.put(charArray[i], 1);
                }
            }

            var values = new ArrayList<>(map.values());
            values.sort(Collections.reverseOrder());

            if (values.get(0) == 3) {
                if (values.size() == 2)
                    return "Full house";
                else
                    return "Three of a kind";
            }

            if (values.get(0) == 2 && values.get(1) == 2) {
                return "Two pair";
            }

            return "One pair";
        }

        public String getHandValue2() {
            var charArray = getHandWithoutWildCards().toCharArray();
            var wildCards = getNumberOfWildCards();
            Arrays.sort(charArray);

            if (wildCards == 0) {
                return getHandValue();
            }

            if (wildCards >= 4) {
                return "Five of a kind";
            }

            var map = new HashMap<Character, Integer>();
            for(var i = 0; i < charArray.length; i++) {
                if (map.containsKey(charArray[i])) {
                    map.put(charArray[i], map.get(charArray[i]) + 1);
                } else {
                    map.put(charArray[i], 1);
                }
            }
            var values = new ArrayList<>(map.values());
            values.sort(Collections.reverseOrder());

            if (wildCards == 3) {
                if (values.size() == 1) {
                    return "Five of a kind";
                }
                return "Four of a kind";
            }

            if (wildCards == 2) {
                if (values.size() == 1) {
                    return "Five of a kind";
                }

                if (values.size() == 2) {
                    return "Four of a kind";
                }

                return "Three of a kind";
            }

            // else 1 wild card
            if (values.size() == 1) {
                return "Five of a kind";
            }

            if (values.size() == 2 && values.get(0) == 3) {
                return "Four of a kind";
            }

            if (values.size() == 2 && values.get(0) == 2) {
                return "Full house";
            }

            if (values.size() == 3) {
                return "Three of a kind";
            }

            return "One pair";
        }
    }
}
