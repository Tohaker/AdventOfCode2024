package org.aoc;

import org.aoc.utils.FileUtils;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class Day10 {
    public static void main(String[] args) throws IOException {
        String input = FileUtils.openInput("Day10.txt");

        System.out.println("Day 10 - Part 1: " + part1(input));
//        System.out.println("Day 10 - Part 2: " + part2(input));
    }

    static List<Point> findAdjacentPoints(Point point) {
        List<Point> adjacentPoints = new ArrayList<>();

        adjacentPoints.add(new Point(point.x, point.y + 1));
        adjacentPoints.add(new Point(point.x, point.y - 1));
        adjacentPoints.add(new Point(point.x + 1, point.y));
        adjacentPoints.add(new Point(point.x - 1, point.y));

        return adjacentPoints;
    }

    static int recurseThroughPositions2(Map<Point, Integer> positions, Point currentPosition, int level, int count) {
        if (level == 9) {
            return count + 1;
        }

        List<Point> adjacentPoints = findAdjacentPoints(currentPosition)
                .stream()
                .filter(positions::containsKey)
                .filter(pos -> positions.get(pos).equals(level))
                .toList();

        for (Point point: adjacentPoints) {
            count = recurseThroughPositions2(positions, point, level + 1, count);
        }

        return count;
    }

    static Set<Point> recurseThroughPositions(Map<Point, Integer> positions, Point currentPosition, int level, Set<Point> peaks) {
        if (level == 10) {
            peaks.add(currentPosition);
        }

        List<Point> adjacentPoints = findAdjacentPoints(currentPosition)
                .stream()
                .filter(positions::containsKey)
                .filter(pos -> positions.get(pos).equals(level))
                .toList();

        for (Point point: adjacentPoints) {
            recurseThroughPositions(positions, point, level + 1, peaks);
        }

        return peaks;
    }

    public static int part1(String input) {
        Map<Point, Integer> positions = new HashMap<>();
        List<String> lines = input.lines().toList();

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                positions.put(new Point(x, y), line.charAt(x) - '0');
            }
        }

        List<Point> zeroPositions = positions
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(0))
                .map(Map.Entry::getKey)
                .toList();

        int total = 0;

        for (Point start: zeroPositions) {
            Set<Point> peaks = recurseThroughPositions(positions, start, 1, new HashSet<>());
            total += peaks.size();
        }

        return total;
    }
}
