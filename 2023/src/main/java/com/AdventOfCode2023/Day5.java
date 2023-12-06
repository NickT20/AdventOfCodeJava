package com.AdventOfCode2023;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day5 {
    public int ExecutePart1(String file) throws IOException {
        var lines = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + file))) {
            while (br.ready()) {
                var line = br.readLine();
                lines.add(line);
            }
        }
        var result = 0;
        var seeds = FindDigits(lines.get(0));
        var seedToSoil = new ArrayList<AreaMap>();
        var mapToUse = seedToSoil;
        for (int x = 2; x < lines.size(); x++) {
            if (lines.get(x).isEmpty()) {
                continue;
            }

            if (lines.get(x).equals("seed-to-soil map:")) {
                mapToUse = seedToSoil;
                continue;
            }

            CreateMaps(lines.get(x), mapToUse);
        }
        return result;
    }

    private void CreateMaps(String line, ArrayList<AreaMap> areaMap) {
        var digits = FindDigits(line);
        areaMap.add(new AreaMap(BigInteger.valueOf(digits.get(0)), BigInteger.valueOf(digits.get(1)), BigInteger.valueOf(digits.get(1) - digits.get(0))));
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

    public class AreaMap {
        private BigInteger _min;
        public void setMin(BigInteger min) {
            _min = min;
        }
        public BigInteger getMin() {
            return _min;
        }

        private BigInteger _max;
        public void setMax(BigInteger max) {
            _max = max;
        }
        public BigInteger getMax() {
            return _max;
        }

        private BigInteger _difference;
        public void setDifference(BigInteger difference) {
            _difference = difference;
        }
        public BigInteger getDifference() {
            return _difference;
        }

        public AreaMap(BigInteger min, BigInteger max, BigInteger difference) {
            _min = min;
            _max = max;
            _difference = difference;
        }
    }
}
