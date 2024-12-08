package org.aoc;

import org.aoc.utils.FileUtils;

import java.awt.Point;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Day8 {
    public static void main(String[] args) throws IOException {
        String input = FileUtils.openInput("Day8.txt");

        System.out.println("Day 8 - Part 1: " + part1(input));
//        System.out.println("Day 8 - Part 2: " + part2(input));
    }

    public static int part1(String input) {
        // Parse map

        Map<String, List<Point>> antennae =  new HashMap<>();
        AtomicInteger x = new AtomicInteger();
        AtomicInteger y = new AtomicInteger();

        input.lines().forEach(line -> {
            x.set(0);

            Arrays.stream(line.split("")).toList().forEach(val -> {
                if (!val.equals(".")) {

                    Point coord = new Point(x.get(), y.get());

                    if (antennae.containsKey(val)) {
                        antennae.get(val).add(coord);
                    } else {
                        antennae.put(val, new ArrayList<>(List.of(coord)));
                    }
                }

                x.getAndIncrement();
            });

            y.getAndIncrement();
        });

        // Find antinodes for each frequency
        Set<Point> antinodes = new HashSet<>();

        antennae.forEach((frequency, locations) -> {
            for (int i = 0; i < locations.size(); i++) {
                Point currentLocation = locations.get(i);

                for (int j = 0; j < locations.size(); j++) {
                    if (i == j) continue; // Don't compare it to itself

                    // Find distance to next location
                    Point nextLocation = locations.get(j);

                    Point antinode = getAntinode(currentLocation, nextLocation);

                    if (antinode.x < 0 || antinode.x >= x.get() || antinode.y < 0 || antinode.y >= y.get()) continue;

                    antinodes.add(antinode);
                }
            }
        });

        return antinodes.size();
    }

    private static Point getAntinode(Point currentLocation, Point nextLocation) {
        int dy = Math.abs(currentLocation.y - nextLocation.y);
        int dx = Math.abs(currentLocation.x - nextLocation.x);

        int y;
        int x;

        // Y value
        if (nextLocation.y > currentLocation.y) {
            y = nextLocation.y + dy;
        } else {
            y = nextLocation.y - dy;
        }

        // X value
        if (nextLocation.x > currentLocation.x) {
            x = nextLocation.x + dx;
        } else {
            x = nextLocation.x - dx;
        }

        return new Point(x, y);
    }
}
