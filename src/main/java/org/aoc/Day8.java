package org.aoc;

import org.aoc.utils.FileUtils;

import java.awt.Point;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

class AntennaeMap {
    Map<String, List<Point>> antennae;
    int mapWidth;
    int mapHeight;

    AntennaeMap(String input) {
        this.antennae =  new HashMap<>();

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

        this.mapHeight = y.get();
        this.mapWidth = x.get();
    }
}

public class Day8 {
    public static void main(String[] args) throws IOException {
        String input = FileUtils.openInput("Day8.txt");

        System.out.println("Day 8 - Part 1: " + part1(input));
        System.out.println("Day 8 - Part 2: " + part2(input));
    }

    public static int part1(String input) {
        AntennaeMap antennaeMap = new AntennaeMap(input);

        // Find antinodes for each frequency
        Set<Point> antinodes = new HashSet<>();

        antennaeMap.antennae.forEach((_, locations) -> {
            for (int i = 0; i < locations.size(); i++) {
                Point currentLocation = locations.get(i);

                for (int j = 0; j < locations.size(); j++) {
                    if (i == j) continue; // Don't compare it to itself

                    // Find distance to next location
                    Point nextLocation = locations.get(j);

                    Point antinode = getAntinode(currentLocation, nextLocation);

                    if (
                            antinode.x < 0 ||
                            antinode.x >= antennaeMap.mapWidth ||
                            antinode.y < 0 ||
                            antinode.y >= antennaeMap.mapHeight
                    ) continue;

                    antinodes.add(antinode);
                }
            }
        });

        return antinodes.size();
    }

    public static int part2(String input) {
        AntennaeMap antennaeMap = new AntennaeMap(input);

        // Find antinodes for each frequency
        Set<Point> antinodes = new HashSet<>();

        antennaeMap.antennae.forEach((_, locations) -> {
            for (int i = 0; i < locations.size(); i++) {
                for (int j = 0; j < locations.size(); j++) {
                    if (i == j) continue; // Don't compare it to itself

                    // Find distance to next location
                    Point currentLocation = locations.get(i);
                    Point nextLocation = locations.get(j);

                    Point antinode = getAntinode(currentLocation, nextLocation);

                    while(
                            antinode.x >= 0 &&
                            antinode.x < antennaeMap.mapWidth &&
                            antinode.y >= 0 &&
                            antinode.y < antennaeMap.mapHeight
                    ) {
                        antinodes.add(antinode);

                        currentLocation = new Point(nextLocation);
                        nextLocation = new Point(antinode);
                        antinode = getAntinode(currentLocation, nextLocation);
                    }
                }

                antinodes.add(locations.get(i));
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
