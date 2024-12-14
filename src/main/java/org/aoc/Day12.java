package org.aoc;

import org.aoc.utils.FileUtils;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class Day12 {
    public static void main(String[] args) throws IOException {
        String input = FileUtils.openInput("Day12.txt");

        System.out.println("Day 12 - Part 1: " + part1(input));
        System.out.println("Day 12 - Part 2: " + part2(input));
    }

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

    static void traverseRegion(List<List<String>> farm, int width, int height, Point p, String plot, List<Point> region) {
        if (!Objects.equals(farm.get(p.y).get(p.x), plot) || region.contains(p)) {
            return;
        }

        region.add(p);

        if (p.x - 1 >= 0) {
            traverseRegion(farm, width, height, new Point(p.x - 1, p.y), plot, region);
        }

        if (p.x + 1 < width) {
            traverseRegion(farm, width, height, new Point(p.x + 1, p.y), plot, region);
        }

        if (p.y - 1 >= 0) {
            traverseRegion(farm, width, height, new Point(p.x, p.y - 1), plot, region);
        }

        if (p.y + 1 < height) {
            traverseRegion(farm, width, height, new Point(p.x, p.y + 1), plot, region);
        }
    }

    static List<List<Point>> findRegions(List<List<String>> farm) {
        int height = farm.size();
        int width = farm.getFirst().size();

        List<List<Point>> regions = new ArrayList<>();
        Set<Point> visited = new HashSet<>();

        for (int y = 1; y < height - 1; y++) {
            for (int x = 1; x < width - 1; x++) {
                Point p = new Point(x, y);

                if (!visited.contains(p)) {
                    List<Point> region = new ArrayList<>();
                    traverseRegion(farm, width, height, p, farm.get(y).get(x), region);
                    visited.addAll(region);

                    regions.add(region);
                }
            }
        }

        return regions;
    }

    static int findPerimeter(List<Point> plot, Point p) {
        int fenceRequired = 4;

        if (plot.contains(new Point(p.x, p.y + 1))) {
            fenceRequired--;
        }

        if (plot.contains(new Point(p.x, p.y - 1))) {
            fenceRequired--;
        }

        if (plot.contains(new Point(p.x + 1, p.y))) {
            fenceRequired--;
        }

        if (plot.contains(new Point(p.x - 1, p.y))) {
            fenceRequired--;
        }

        return fenceRequired;
    }

    public static int part1(String input) {
        var farm = parseInput(input);
        var plots = findRegions(farm);

        int price = 0;

        for (List<Point> plot : plots) {
            int perimeter = plot.stream().map(p -> findPerimeter(plot, p)).reduce(0, Integer::sum);

            price += plot.size() * perimeter;
        }

        return price;
    }

    static int findCorners(List<Point> plot) {
        int corners = 0;

        for (Point p : plot) {
            // Check combinations of corners in list
            Point up = new Point(p.x, p.y - 1);
            Point left = new Point(p.x - 1, p.y);
            Point down = new Point(p.x, p.y + 1);
            Point right = new Point(p.x + 1, p.y);

            Point upLeft = new Point(p.x - 1, p.y - 1);
            Point upRight = new Point(p.x + 1, p.y - 1);
            Point downLeft = new Point(p.x - 1, p.y + 1);
            Point downRight = new Point(p.x + 1, p.y + 1);

            if (!plot.contains(up) && !plot.contains(left)) corners++;
            if (!plot.contains(up) && !plot.contains(right)) corners++;
            if (!plot.contains(down) && !plot.contains(left)) corners++;
            if (!plot.contains(down) && !plot.contains(right)) corners++;

            if (plot.contains(up) && plot.contains(left) && !plot.contains(upLeft)) corners++;
            if (plot.contains(up) && plot.contains(right) && !plot.contains(upRight)) corners++;
            if (plot.contains(down) && plot.contains(left) && !plot.contains(downLeft)) corners++;
            if (plot.contains(down) && plot.contains(right) && !plot.contains(downRight)) corners++;
        }

        return corners;
    }

    public static int part2(String input) {
        var farm = parseInput(input);
        var plots = findRegions(farm);

        int price = 0;

        for (List<Point> plot : plots) {
            price += plot.size() * findCorners(plot);
        }

        return price;
    }
}
