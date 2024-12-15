package org.aoc;

import org.aoc.utils.FileUtils;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class WarehouseItem {
    Point position;
    List<Point> walls;
    List<WarehouseItem> boxes;
    String representation;

    WarehouseItem(int x, int y, String representation, List<Point> walls, List<WarehouseItem> boxes) {
        this.position = new Point(x, y);
        this.walls = walls;
        this.boxes = boxes;
        this.representation = representation;
    }

    public int getGPSCoordinate() {
        if (representation.equals("]")) return 0;

        return 100 * position.y + position.x;
    }

    public boolean move(String direction, boolean checkOther) {
        Point nextPosition = new Point(position);

        switch (direction) {
            case "<": {
                nextPosition.translate(-1, 0);
                break;
            }
            case "^": {
                nextPosition.translate(0, -1);
                break;
            }
            case ">": {
                nextPosition.translate(1, 0);
                break;
            }
            case "v": {
                nextPosition.translate(0, 1);
                break;
            }
        }

        var boxesInNextPosition = boxes
                .stream()
                .filter(b -> b.position.equals(nextPosition))
                .toList();

        var verticalMovement = direction.equals("^") || direction.equals("v");

        if (!boxesInNextPosition.isEmpty()) {
            var nextBox = boxesInNextPosition.getFirst();
            if (checkOther && verticalMovement) {
                int newX = nextPosition.x;

                if (nextBox.representation.equals("[")) {
                    newX++;
                } else if (nextBox.representation.equals("]")) {
                    newX--;
                }

                int finalNewX = newX;
                var boxesInOtherPosition = boxes
                        .stream()
                        .filter(b -> b.position.equals(new Point(finalNewX, nextPosition.y)))
                        .toList();

                if (!boxesInOtherPosition.isEmpty()) {
                    var moved = nextBox.move(direction, true) && boxesInOtherPosition.getFirst().move(direction, true);
                    if (!moved) return false;
                }
            } else {
                var moved = boxesInNextPosition.getFirst().move(direction, false);
                if (!moved) return false;
            }
        }

        if (checkOther && verticalMovement && !representation.equals("O") && !representation.equals("@")) {
            int newX = nextPosition.x;

            if (representation.equals("[")) {
                newX++;
            } else if (representation.equals("]")) {
                newX--;
            }

            if (walls.contains(new Point(newX, nextPosition.y)) || walls.contains(nextPosition)) {
                return false;
            }
        }

        if (walls.contains(nextPosition)) {
            return false;
        }

        position.setLocation(nextPosition);
        return true;
    }
}

public class Day15 {
    public static void main(String[] args) throws IOException {
        String input = FileUtils.openInput("Day15.txt");

        System.out.println("Day 15 - Part 1: " + part1(input));
        System.out.println("Day 15 - Part 2: " + part2(input));
    }

    public static int part1(String input) {
        var splitted = input.split("\\n\\n");
        var grid = splitted[0];
        var instructions = Arrays.stream(splitted[1].lines().collect(Collectors.joining()).split("")).toList();

        List<Point> walls = new ArrayList<>();
        List<WarehouseItem> boxes = new ArrayList<>();
        WarehouseItem robot = null;

        var lines = grid.lines().toList();

        for (int y = 0; y < lines.size(); y++) {
            var line = Arrays.stream(lines.get(y).split("")).toList();
            for (int x = 0; x < line.size(); x++) {
                if (line.get(x).equals("#")) {
                    walls.add(new Point(x, y));
                } else if (line.get(x).equals("O")) {
                    boxes.add(new WarehouseItem(x, y, line.get(x), walls, boxes));
                } else if (line.get(x).equals("@")) {
                    robot = new WarehouseItem(x, y, line.get(x), walls, boxes);
                }
            }
        }

        int total = 0;

        if (robot == null) {
            return total;
        }

        for (String instruction : instructions) {
            robot.move(instruction, false);
        }

        for (WarehouseItem box : boxes) {
            total += box.getGPSCoordinate();
        }

        return total;
    }

    public static int part2(String input) {
        var splitted = input.split("\\n\\n");
        var grid = splitted[0];
        var instructions = Arrays.stream(splitted[1].lines().collect(Collectors.joining()).split("")).toList();

        List<Point> walls = new ArrayList<>();
        List<WarehouseItem> boxes = new ArrayList<>();
        WarehouseItem robot = null;

        grid = grid
                .replaceAll("#", "##")
                .replaceAll("O", "[]")
                .replaceAll("\\.", "..")
                .replace("@", "@.");

        var lines = grid.lines().toList();

        for (int y = 0; y < lines.size(); y++) {
            var line = Arrays.stream(lines.get(y).split("")).toList();
            for (int x = 0; x < line.size(); x++) {
                if (line.get(x).equals("#")) {
                    walls.add(new Point(x, y));
                } else if (line.get(x).equals("[") || line.get(x).equals("]")) {
                    boxes.add(new WarehouseItem(x, y, line.get(x), walls, boxes));
                } else if (line.get(x).equals("@")) {
                    robot = new WarehouseItem(x, y, line.get(x), walls, boxes);
                }
            }
        }

        int total = 0;

        if (robot == null) {
            return total;
        }

        for (String instruction : instructions) {
            robot.move(instruction, true);
        }

        for (WarehouseItem box : boxes) {
            total += box.getGPSCoordinate();
        }

        StringBuilder newGrid = new StringBuilder();
        for (int y = 0; y < lines.size(); y++) {
            var line = Arrays.stream(lines.get(y).split("")).toList();
            StringBuilder newLine = new StringBuilder();
            for (int x = 0; x < line.size(); x++) {
                Point p = new Point(x, y);

                if (walls.contains(p)) {
                    newLine.append("#");
                    continue;
                }

                var boxList = boxes
                        .stream()
                        .filter(b -> b.position.equals(p))
                        .toList();

                if (!boxList.isEmpty()) {
                    newLine.append(boxList.getFirst().representation);
                    continue;
                }

                if (p.equals(robot.position)) {
                    newLine.append("@");
                    continue;
                }

                newLine.append(".");
            }

            newGrid.append(newLine).append("\n");
        }

        return total;
    }
}
