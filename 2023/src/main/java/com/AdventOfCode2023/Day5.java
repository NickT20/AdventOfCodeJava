package com.AdventOfCode2023;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day5 {
    public BigInteger ExecutePart1(String file) throws IOException {
        var lines = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + file))) {
            while (br.ready()) {
                var line = br.readLine();
                lines.add(line);
            }
        }
        var result = new ArrayList<String>();
        var seeds = FindDigits(lines.get(0));
        var seedToSoil = new ArrayList<AreaMap>();
        var soilToFertilizer = new ArrayList<AreaMap>();
        var fertilizerToWater = new ArrayList<AreaMap>();
        var waterToLight = new ArrayList<AreaMap>();
        var lightToTemperature = new ArrayList<AreaMap>();
        var temperatureToHumidity = new ArrayList<AreaMap>();
        var humidityToLocation = new ArrayList<AreaMap>();
        var mapToUse = seedToSoil;
        for (int x = 2; x < lines.size(); x++) {
            if (lines.get(x).isEmpty()) {
                continue;
            }

            if (lines.get(x).equals("seed-to-soil map:")) {
                mapToUse = seedToSoil;
                continue;
            }

            if (lines.get(x).equals("soil-to-fertilizer map:")) {
                mapToUse = soilToFertilizer;
                continue;
            }

            if (lines.get(x).equals("fertilizer-to-water map:")) {
                mapToUse = fertilizerToWater;
                continue;
            }

            if (lines.get(x).equals("water-to-light map:")) {
                mapToUse = waterToLight;
                continue;
            }

            if (lines.get(x).equals("light-to-temperature map:")) {
                mapToUse = lightToTemperature;
                continue;
            }

            if (lines.get(x).equals("temperature-to-humidity map:")) {
                mapToUse = temperatureToHumidity;
                continue;
            }

            if (lines.get(x).equals("humidity-to-location map:")) {
                mapToUse = humidityToLocation;
                continue;
            }

            CreateMaps(lines.get(x), mapToUse);
        }

        seeds.forEach(seed -> {
            var soil = seedToSoil.stream().filter(am -> seed.compareTo(am.getSource().getMin()) >= 0 && seed.compareTo(am.getSource().getMax()) <= 0).findFirst();
            var soilToUse = soil.isEmpty() ? seed : soil.get().getDestination().getMin().subtract(soil.get().getSource().getMin()).add(seed);
            var fertilizer = soilToFertilizer.stream().filter(am -> soilToUse.compareTo(am.getSource().getMin()) >= 0 && soilToUse.compareTo(am.getSource().getMax()) <= 0).findFirst();
            var fertilizerToUse = fertilizer.isEmpty() ? soilToUse : fertilizer.get().getDestination().getMin().subtract(fertilizer.get().getSource().getMin()).add(soilToUse);
            var water = fertilizerToWater.stream().filter(am -> fertilizerToUse.compareTo(am.getSource().getMin()) >= 0 && fertilizerToUse.compareTo(am.getSource().getMax()) <= 0).findFirst();
            var waterToUse = water.isEmpty() ? fertilizerToUse : water.get().getDestination().getMin().subtract(water.get().getSource().getMin()).add(fertilizerToUse);
            var light = waterToLight.stream().filter(am -> waterToUse.compareTo(am.getSource().getMin()) >= 0 && waterToUse.compareTo(am.getSource().getMax()) <= 0).findFirst();
            var lightToUse = light.isEmpty() ? waterToUse : light.get().getDestination().getMin().subtract(light.get().getSource().getMin()).add(waterToUse);
            var temperature = lightToTemperature.stream().filter(am -> lightToUse.compareTo(am.getSource().getMin()) >= 0 && lightToUse.compareTo(am.getSource().getMax()) <= 0).findFirst();
            var temperatureToUse = temperature.isEmpty() ? lightToUse : temperature.get().getDestination().getMin().subtract(temperature.get().getSource().getMin()).add(lightToUse);
            var humdity = temperatureToHumidity.stream().filter(am -> temperatureToUse.compareTo(am.getSource().getMin()) >= 0 && temperatureToUse.compareTo(am.getSource().getMax()) <= 0).findFirst();
            var humdityToUse = humdity.isEmpty() ? temperatureToUse : humdity.get().getDestination().getMin().subtract(humdity.get().getSource().getMin()).add(temperatureToUse);
            var humidity = humidityToLocation.stream().filter(am -> humdityToUse.compareTo(am.getSource().getMin()) >= 0 && humdityToUse.compareTo(am.getSource().getMax()) <= 0).findFirst();
            var location = humidity.isEmpty() ? humdityToUse : humidity.get().getDestination().getMin().subtract(humidity.get().getSource().getMin()).add(humdityToUse);
            result.add(location.toString());
        });

        return findMinBigInteger(result);
    }

    // This sucked and never actually finished. May be best to try to go about this the other direction. Start at the lowest range of locations and find the seed that are in that. Then find the lowest seed value.
    public BigInteger ExecutePart2(String file) throws IOException {
        var lines = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + file))) {
            while (br.ready()) {
                var line = br.readLine();
                lines.add(line);
            }
        }
        var result = new ArrayList<BigInteger>();
        var seeds1 = FindDigits(lines.get(0));
        var seeds = CreateSeedRanges(seeds1);
        var seedToSoil = new ArrayList<AreaMap>();
        var soilToFertilizer = new ArrayList<AreaMap>();
        var fertilizerToWater = new ArrayList<AreaMap>();
        var waterToLight = new ArrayList<AreaMap>();
        var lightToTemperature = new ArrayList<AreaMap>();
        var temperatureToHumidity = new ArrayList<AreaMap>();
        var humidityToLocation = new ArrayList<AreaMap>();
        var mapToUse = seedToSoil;
        for (int x = 2; x < lines.size(); x++) {
            if (lines.get(x).isEmpty()) {
                continue;
            }

            if (lines.get(x).equals("seed-to-soil map:")) {
                mapToUse = seedToSoil;
                continue;
            }

            if (lines.get(x).equals("soil-to-fertilizer map:")) {
                mapToUse = soilToFertilizer;
                continue;
            }

            if (lines.get(x).equals("fertilizer-to-water map:")) {
                mapToUse = fertilizerToWater;
                continue;
            }

            if (lines.get(x).equals("water-to-light map:")) {
                mapToUse = waterToLight;
                continue;
            }

            if (lines.get(x).equals("light-to-temperature map:")) {
                mapToUse = lightToTemperature;
                continue;
            }

            if (lines.get(x).equals("temperature-to-humidity map:")) {
                mapToUse = temperatureToHumidity;
                continue;
            }

            if (lines.get(x).equals("humidity-to-location map:")) {
                mapToUse = humidityToLocation;
                continue;
            }

            CreateMaps(lines.get(x), mapToUse);
        }
        seeds.forEach(minMax -> {
            for(var i = minMax.getMin(); i.compareTo(minMax.getMax()) < 0; i = i.add(BigInteger.ONE)) {
                var seed = i;
                var soil = seedToSoil.stream().filter(am -> seed.compareTo(am.getSource().getMin()) >= 0 && seed.compareTo(am.getSource().getMax()) <= 0).findFirst();
                var soilToUse = soil.isEmpty() ? seed : soil.get().getDestination().getMin().subtract(soil.get().getSource().getMin()).add(seed);
                var fertilizer = soilToFertilizer.stream().filter(am -> soilToUse.compareTo(am.getSource().getMin()) >= 0 && soilToUse.compareTo(am.getSource().getMax()) <= 0).findFirst();
                var fertilizerToUse = fertilizer.isEmpty() ? soilToUse : fertilizer.get().getDestination().getMin().subtract(fertilizer.get().getSource().getMin()).add(soilToUse);
                var water = fertilizerToWater.stream().filter(am -> fertilizerToUse.compareTo(am.getSource().getMin()) >= 0 && fertilizerToUse.compareTo(am.getSource().getMax()) <= 0).findFirst();
                var waterToUse = water.isEmpty() ? fertilizerToUse : water.get().getDestination().getMin().subtract(water.get().getSource().getMin()).add(fertilizerToUse);
                var light = waterToLight.stream().filter(am -> waterToUse.compareTo(am.getSource().getMin()) >= 0 && waterToUse.compareTo(am.getSource().getMax()) <= 0).findFirst();
                var lightToUse = light.isEmpty() ? waterToUse : light.get().getDestination().getMin().subtract(light.get().getSource().getMin()).add(waterToUse);
                var temperature = lightToTemperature.stream().filter(am -> lightToUse.compareTo(am.getSource().getMin()) >= 0 && lightToUse.compareTo(am.getSource().getMax()) <= 0).findFirst();
                var temperatureToUse = temperature.isEmpty() ? lightToUse : temperature.get().getDestination().getMin().subtract(temperature.get().getSource().getMin()).add(lightToUse);
                var humdity = temperatureToHumidity.stream().filter(am -> temperatureToUse.compareTo(am.getSource().getMin()) >= 0 && temperatureToUse.compareTo(am.getSource().getMax()) <= 0).findFirst();
                var humdityToUse = humdity.isEmpty() ? temperatureToUse : humdity.get().getDestination().getMin().subtract(humdity.get().getSource().getMin()).add(temperatureToUse);
                var humidity = humidityToLocation.stream().filter(am -> humdityToUse.compareTo(am.getSource().getMin()) >= 0 && humdityToUse.compareTo(am.getSource().getMax()) <= 0).findFirst();
                var location = humidity.isEmpty() ? humdityToUse : humidity.get().getDestination().getMin().subtract(humidity.get().getSource().getMin()).add(humdityToUse);
                var test = new BigInteger(location.toString());
                if (result.isEmpty()) {
                    result.add(test);
                } else if(test.compareTo(result.get(0)) < 0) {
                    result.remove(0);
                    result.add(test);
                }
            }
        });
//        seeds.forEach(seed -> {
//            var soil = seedToSoil.stream().filter(am -> seed.compareTo(am.getSource().getMin()) >= 0 && seed.compareTo(am.getSource().getMax()) <= 0).findFirst();
//            var soilToUse = soil.isEmpty() ? seed : soil.get().getDestination().getMin().subtract(soil.get().getSource().getMin()).add(seed);
//            var fertilizer = soilToFertilizer.stream().filter(am -> soilToUse.compareTo(am.getSource().getMin()) >= 0 && soilToUse.compareTo(am.getSource().getMax()) <= 0).findFirst();
//            var fertilizerToUse = fertilizer.isEmpty() ? soilToUse : fertilizer.get().getDestination().getMin().subtract(fertilizer.get().getSource().getMin()).add(soilToUse);
//            var water = fertilizerToWater.stream().filter(am -> fertilizerToUse.compareTo(am.getSource().getMin()) >= 0 && fertilizerToUse.compareTo(am.getSource().getMax()) <= 0).findFirst();
//            var waterToUse = water.isEmpty() ? fertilizerToUse : water.get().getDestination().getMin().subtract(water.get().getSource().getMin()).add(fertilizerToUse);
//            var light = waterToLight.stream().filter(am -> waterToUse.compareTo(am.getSource().getMin()) >= 0 && waterToUse.compareTo(am.getSource().getMax()) <= 0).findFirst();
//            var lightToUse = light.isEmpty() ? waterToUse : light.get().getDestination().getMin().subtract(light.get().getSource().getMin()).add(waterToUse);
//            var temperature = lightToTemperature.stream().filter(am -> lightToUse.compareTo(am.getSource().getMin()) >= 0 && lightToUse.compareTo(am.getSource().getMax()) <= 0).findFirst();
//            var temperatureToUse = temperature.isEmpty() ? lightToUse : temperature.get().getDestination().getMin().subtract(temperature.get().getSource().getMin()).add(lightToUse);
//            var humdity = temperatureToHumidity.stream().filter(am -> temperatureToUse.compareTo(am.getSource().getMin()) >= 0 && temperatureToUse.compareTo(am.getSource().getMax()) <= 0).findFirst();
//            var humdityToUse = humdity.isEmpty() ? temperatureToUse : humdity.get().getDestination().getMin().subtract(humdity.get().getSource().getMin()).add(temperatureToUse);
//            var humidity = humidityToLocation.stream().filter(am -> humdityToUse.compareTo(am.getSource().getMin()) >= 0 && humdityToUse.compareTo(am.getSource().getMax()) <= 0).findFirst();
//            var location = humidity.isEmpty() ? humdityToUse : humidity.get().getDestination().getMin().subtract(humidity.get().getSource().getMin()).add(humdityToUse);
//            result.add(location.toString());
//        });

        return result.get(0);
    }

    private void CreateMaps(String line, ArrayList<AreaMap> areaMap) {
        var digits = FindDigits(line);
        var destination = new MinMax(digits.get(0), digits.get(0).add(digits.get(2).subtract(BigInteger.valueOf(1))));
        var source = new MinMax(digits.get(1), digits.get(1).add(digits.get(2).subtract(BigInteger.valueOf(1))));
        areaMap.add(new AreaMap(source, destination, digits.get(1).subtract(digits.get(0))));
    }

    private ArrayList<MinMax> CreateSeedRanges(ArrayList<BigInteger> seedValues) {
        var results = new ArrayList<MinMax>();
        for(var i = 0; i < seedValues.size(); i += 2) {
            var start = seedValues.get(i);
            var range = seedValues.get(i + 1);
            results.add(new MinMax(start, start.add(range)));
        }
        return results;
    }

    private ArrayList<BigInteger> FindDigits(String line) {
        var result = new ArrayList<BigInteger>();
        String pattern = "[\\d]+";

        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(line);

        while(matcher.find()) {
            result.add(BigInteger.valueOf(Long.parseLong(matcher.group())));
        }
        return result;
    }

    private static BigInteger findMinBigInteger(ArrayList<String> stringList) {
        if (stringList.isEmpty()) {
            throw new IllegalArgumentException("List is empty");
        }

        // Convert the first string to a BigInteger and use it as the initial minimum
        BigInteger minBigInteger = new BigInteger(stringList.get(0));

        // Iterate through the rest of the strings and update the minimum if needed
        for (int i = 1; i < stringList.size(); i++) {
            BigInteger currentBigInteger = new BigInteger(stringList.get(i));
            minBigInteger = minBigInteger.min(currentBigInteger);
        }

        return minBigInteger;
    }

    public class AreaMap {

        private MinMax _source;
        private MinMax _destination;
        private BigInteger _difference;
        public void setSource(MinMax source) {
            _source = source;
        }
        public MinMax getSource() {
            return _source;
        }
        public void setDestination(MinMax destination) {
            _destination = destination;
        }
        public MinMax getDestination() {
            return _destination;
        }
        public void setDifference(BigInteger difference) {
            _difference = difference;
        }
        public BigInteger getDifference() {
            return _difference;
        }

        public AreaMap(MinMax source, MinMax destination, BigInteger difference) {
            _source = source;
            _destination = destination;
            _difference = difference;
        }
    }

    public class MinMax {
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
        public MinMax(BigInteger min, BigInteger max) {
            _min = min;
            _max = max;
        }
    }
}
