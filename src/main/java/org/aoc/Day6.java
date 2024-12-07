package org.aoc;

import org.aoc.utils.FileUtils;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class Day6 {
    public static void main(String[] args) throws IOException {
        String input = FileUtils.openInput("Day6.txt");

        System.out.println("Day 6 - Part 1: " + part1(input));
        System.out.println("Day 6 - Part 2: " + part2(input));
    }

    static Set<Point> findPositionsWalked(List<Point> obstacles, Point start, int width, int height) {
        Set<Point> positions = new HashSet<>();
        Point nextPosition = new Point(start);
        Point currentPosition = null;
        String direction = "N";

        while (nextPosition.getY() < height && nextPosition.getX() < width) {
            if (obstacles.contains(nextPosition)) {
                switch (direction) {
                    case "N": {
                        direction = "E";
                        break;
                    }
                    case "E": {
                        direction = "S";
                        break;
                    }
                    case "S": {
                        direction = "W";
                        break;
                    }
                    case "W": {
                        direction = "N";
                        break;
                    }
                }

                // Reset nextPosition to currentPosition, as we can't go past an obstacle.
                nextPosition = currentPosition;
            } else {
                currentPosition = nextPosition;
                positions.add(currentPosition);
            }

            switch (direction) {
                case "N": {
                    nextPosition = new Point(nextPosition.x, nextPosition.y - 1);
                    break;
                }
                case "E": {
                    nextPosition = new Point(nextPosition.x + 1, nextPosition.y);
                    break;
                }
                case "S": {
                    nextPosition = new Point(nextPosition.x, nextPosition.y + 1);
                    break;
                }
                case "W": {
                    nextPosition = new Point(nextPosition.x - 1, nextPosition.y);
                    break;
                }
            }
        }

        return positions;
    }

    public static int part1(String input) {
        // Parse input

        List<Point> obstacles = new ArrayList<>();
        Point start = null;

        int height = 0;
        int width = 0;

        for (String line : input.lines().toList()) {
            width = 0;

            for (String cell : Arrays.stream(line.split("")).toList()) {
                if (cell.equals("#")) {
                    obstacles.add(new Point(width, height));
                } else if (cell.equals("^")) {
                    start = new Point(width, height);
                }
                width++;
            }
            height++;
        }

        // Loop until next nextPosition would leave the grid

        Set<Point> positions = findPositionsWalked(obstacles, start, width, height);

        return positions.size();
    }

    public static int part2(String input) {
        // Parse input

        List<Point> obstacles = new ArrayList<>();
        Set<Point> positions = new HashSet<>();
        Point start = null;
        Point currentPosition = null;
        Point nextPosition = null;
        String direction = "N";

        int height = 0;
        int width = 0;

        for (String line : input.lines().toList()) {
            width = 0;

            for (String cell : Arrays.stream(line.split("")).toList()) {
                if (cell.equals("#")) {
                    obstacles.add(new Point(width, height));
                } else if (cell.equals("^")) {
                    start = new Point(width, height);
                    nextPosition = new Point(start);
                }
                width++;
            }
            height++;
        }

        int loopsFound = 0;

        // Find points on the path

        Set<Point> initialRoute = findPositionsWalked(obstacles, start, width, height);
        initialRoute.remove(start); // Remove start as an obstacle can't be placed there

        // Loop through all possible placement of obstacles

        for (Point obstacle : initialRoute) {
            // Start walking

            // Store point with direction for each step
            Set<Map.Entry<Point, String>> coveredGround = new HashSet<>();

            while (nextPosition.getY() >= 0 && nextPosition.getY() < height && nextPosition.getX() >= 0 && nextPosition.getX() < width) {
                if (coveredGround.contains(new AbstractMap.SimpleEntry<>(nextPosition, direction))) {
                    loopsFound++;
                    break;
                }

                if (obstacles.contains(nextPosition) || nextPosition.equals(obstacle)) {
                    switch (direction) {
                        case "N": {
                            direction = "E";
                            break;
                        }
                        case "E": {
                            direction = "S";
                            break;
                        }
                        case "S": {
                            direction = "W";
                            break;
                        }
                        case "W": {
                            direction = "N";
                            break;
                        }
                    }

                    // Reset nextPosition to currentPosition, as we can't go past an obstacle.
                    nextPosition = currentPosition;
                } else {
                    currentPosition = nextPosition;
                    positions.add(currentPosition);
                    coveredGround.add(new AbstractMap.SimpleEntry<>(currentPosition, direction));
                }

                switch (direction) {
                    case "N": {
                        nextPosition = new Point(nextPosition.x, nextPosition.y - 1);
                        break;
                    }
                    case "E": {
                        nextPosition = new Point(nextPosition.x + 1, nextPosition.y);
                        break;
                    }
                    case "S": {
                        nextPosition = new Point(nextPosition.x, nextPosition.y + 1);
                        break;
                    }
                    case "W": {
                        nextPosition = new Point(nextPosition.x - 1, nextPosition.y);
                        break;
                    }
                }
            }

            // Reset for next permutation

            positions = new HashSet<>();
            nextPosition = new Point(start);
            direction = "N";
        }

        return loopsFound;
    }
}
