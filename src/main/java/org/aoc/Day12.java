package org.aoc;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Day12 {
    static List<List<String>> parseInput(String input) {
        List<List<String>> farm = new ArrayList<>();

        input.lines().forEach(line -> {
            var row = new ArrayList<>(Arrays.stream(line.split("")).toList());

            row.addFirst(".");
            row.addLast(".");
            farm.add(row);
        });

        var extraRow = Collections.nCopies(farm.getFirst().size(), ".");

        farm.addFirst(extraRow);
        farm.addLast(extraRow);

        return farm;
    }

    static Map<String, List<Point>> findPlots(List<List<String>> farm) {
        Map<String, List<Point>> plots = new HashMap<>();

        // TODO: Figure out how to separate plots
        for (int y = 1; y < farm.size() - 1; y++) {
            List<String> row = farm.get(y);
            
            for (int x = 1; x < row.size() - 1; x++) {
                String key = row.get(x);
                Point plot = new Point(x, y);
                
                if (plots.containsKey(key)) {
                    plots.get(key).add(plot);
                } else {
                    plots.put(key, new ArrayList<>(List.of(plot)));
                }
            }
        }

        return plots;
    }

    static int findPerimeter(String key, Point plot, List<List<String>> farm) {
        int fenceRequired = 0;

        if (!Objects.equals(farm.get(plot.y + 1).get(plot.x), key)) {
            fenceRequired++;
        }

        if (!Objects.equals(farm.get(plot.y - 1).get(plot.x), key)) {
            fenceRequired++;
        }

        if (!Objects.equals(farm.get(plot.y).get(plot.x + 1), key)) {
            fenceRequired++;
        }

        if (!Objects.equals(farm.get(plot.y).get(plot.x - 1), key)) {
            fenceRequired++;
        }

        return fenceRequired;
    }

    public static int part1(String input) {
        var farm = parseInput(input);
        var plots = findPlots(farm);

        int price = 0;

        for (String key: plots.keySet()) {
            var plants = plots.get(key);
            var perimeter = plants.stream()
                    .map(plant -> findPerimeter(key, plant, farm))
                    .reduce(0, Integer::sum);

            price += plants.size() * perimeter;
        }

        return price;
    }
}
