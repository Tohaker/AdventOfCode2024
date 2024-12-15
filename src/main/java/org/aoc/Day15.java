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

    WarehouseItem(int x, int y, List<Point> walls, List<WarehouseItem> boxes) {
        this.position = new Point(x, y);
        this.walls = walls;
        this.boxes = boxes;
    }

    public int getGPSCoordinate() {
        return 100 * position.y + position.x;
    }

    public boolean move(String direction) {
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

        var boxesInNextPosition = boxes.stream().filter(b -> b.position.equals(nextPosition)).toList();

        if (!boxesInNextPosition.isEmpty()) {
           var moved = boxesInNextPosition.getFirst().move(direction);
           if (!moved) return false;
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
//        System.out.println("Day 15 - Part 2: " + part2(input));
    }

    public static int part1(String input) {
        var splitted = input.split(System.lineSeparator() + System.lineSeparator());
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
                    boxes.add(new WarehouseItem(x, y, walls, boxes));
                } else if (line.get(x).equals("@")) {
                    robot = new WarehouseItem(x, y, walls, boxes);
                }
            }
        }

        int total = 0;

        if (robot == null) {
            return total;
        }

        for (String instruction : instructions) {
            robot.move(instruction);
        }

        for (WarehouseItem box : boxes) {
            total += box.getGPSCoordinate();
        }

        return total;
    }
}
