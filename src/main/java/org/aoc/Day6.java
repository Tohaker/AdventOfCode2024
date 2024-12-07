package org.aoc;

import org.aoc.utils.FileUtils;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST
}

public class Day6 {
    public static void main(String[] args) throws IOException {
        String input = FileUtils.openInput("Day6.txt");

        System.out.println("Day 6 - Part 1: " + part1(input));
        System.out.println("Day 6 - Part 2: " + part2(input));
    }

    static Point determineNextPosition(Point position, Direction direction) {
        switch (direction) {
            case Direction.NORTH: {
                position = new Point(position.x, position.y - 1);
                break;
            }
            case Direction.EAST: {
                position = new Point(position.x + 1, position.y);
                break;
            }
            case Direction.SOUTH: {
                position = new Point(position.x, position.y + 1);
                break;
            }
            case Direction.WEST: {
                position = new Point(position.x - 1, position.y);
                break;
            }
        }

        return position;
    }

    static Direction determineNextDirection(Direction direction) {
        switch (direction) {
            case Direction.NORTH: {
                direction = Direction.EAST;
                break;
            }
            case Direction.EAST: {
                direction = Direction.SOUTH;
                break;
            }
            case Direction.SOUTH: {
                direction = Direction.WEST;
                break;
            }
            case Direction.WEST: {
                direction = Direction.NORTH;
                break;
            }
        }

        return direction;
    }

    static Set<Point> findPositionsWalked(List<Point> obstacles, Point start, int width, int height) {
        Set<Point> positions = new HashSet<>();
        Point nextPosition = new Point(start);
        Point currentPosition = null;
        Direction direction = Direction.NORTH;

        while (nextPosition.getY() >= 0 &&
                nextPosition.getY() < height &&
                nextPosition.getX() >= 0 &&
                nextPosition.getX() < width) {
            if (obstacles.contains(nextPosition)) {
                direction = determineNextDirection(direction);

                // Reset nextPosition to currentPosition, as we can't go past an obstacle.
                nextPosition = currentPosition;
            } else {
                currentPosition = nextPosition;
                positions.add(currentPosition);
            }

            nextPosition = determineNextPosition(nextPosition, direction);
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
        Point start = null;
        Point currentPosition = null;
        Point nextPosition = null;
        Direction direction = Direction.NORTH;

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
            // Store point with direction for each step
            Set<Map.Entry<Point, Direction>> coveredGround = new HashSet<>();

            while (nextPosition.getY() >= 0 && nextPosition.getY() < height && nextPosition.getX() >= 0 && nextPosition.getX() < width) {
                if (coveredGround.contains(new AbstractMap.SimpleEntry<>(nextPosition, direction))) {
                    loopsFound++;
                    break;
                }

                if (obstacles.contains(nextPosition) || nextPosition.equals(obstacle)) {
                    direction = determineNextDirection(direction);

                    // Reset nextPosition to currentPosition, as we can't go past an obstacle.
                    nextPosition = currentPosition;
                } else {
                    currentPosition = nextPosition;
                    coveredGround.add(new AbstractMap.SimpleEntry<>(currentPosition, direction));
                }

                nextPosition = determineNextPosition(nextPosition, direction);
            }

            // Reset for next permutation
            nextPosition = new Point(start);
            direction = Direction.NORTH;
        }

        return loopsFound;
    }
}
