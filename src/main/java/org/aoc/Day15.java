package org.aoc;

import org.aoc.utils.FileUtils;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

class WarehouseItem {
    List<Point> position;
    List<Point> walls;
    List<WarehouseItem> boxes;

    WarehouseItem(List<Point> position, List<Point> walls, List<WarehouseItem> boxes) {
        this.position = position;
        this.walls = walls;
        this.boxes = boxes;
    }

    public int getGPSCoordinate() {
        return 100 * position.getFirst().y + position.getFirst().x;
    }

    public List<Point> move(String direction) {
        List<Point> nextPosition = position.stream().map(Point::new).toList();

        switch (direction) {
            case "<": {
                nextPosition.getFirst().translate(-1, 0);

                if (nextPosition.size() > 1) {
                    nextPosition.getLast().translate(-1, 0);
                }
                break;
            }
            case "^": {
                nextPosition.getFirst().translate(0, -1);

                if (nextPosition.size() > 1) {
                    nextPosition.getLast().translate(0, -1);
                }
                break;
            }
            case ">": {
                nextPosition.getFirst().translate(1, 0);

                if (nextPosition.size() > 1) {
                    nextPosition.getLast().translate(1, 0);
                }
                break;
            }
            case "v": {
                nextPosition.getFirst().translate(0, 1);

                if (nextPosition.size() > 1) {
                    nextPosition.getLast().translate(0, 1);
                }
                break;
            }
        }

        var boxesInNextPosition = boxes
                .stream()
                .filter(b -> b.position.stream().anyMatch(nextPosition::contains))
                .filter(b -> !b.equals(this))
                .toList();

        if (walls.stream().anyMatch(nextPosition::contains)) {
            return null;
        }

        if (!boxesInNextPosition.isEmpty()) {
            // All boxes must resolve to whether they can be moved (not a single one hits a wall)
            // before committing to moving.

            var movements = boxesInNextPosition.stream().map(b -> b.move(direction)).toList();
            var moved = movements.stream().allMatch(Objects::nonNull);
            if (moved) {
                for (int i = 0; i < movements.size(); i++) {
                    boxesInNextPosition.get(i).position.getFirst().setLocation(movements.get(i).getFirst());

                    if (movements.get(i).size() > 1) {
                        boxesInNextPosition.get(i).position.getLast().setLocation(movements.get(i).getLast());
                    }
                }
            } else return null;
        }

        return nextPosition;
    }
}

public class Day15 {
    public static void main(String[] args) throws IOException {
        String input = FileUtils.openInput("Day15.txt");

        System.out.println("Day 15 - Part 1: " + part1(input));
        System.out.println("Day 15 - Part 2: " + part2(input));
    }

    public static int part1(String input) {
        var splitted = input.split("\\r\\n\\r\\n");
        var grid = splitted[0];
        var instructions = Arrays.stream(splitted[1].lines().collect(Collectors.joining()).split("")).toList();

        List<Point> walls = new ArrayList<>();
        List<WarehouseItem> boxes = new ArrayList<>();
        WarehouseItem robot = null;

        var lines = grid.lines().toList();

        for (int y = 0; y < lines.size(); y++) {
            var line = Arrays.stream(lines.get(y).split("")).toList();
            for (int x = 0; x < line.size(); x++) {
                switch (line.get(x)) {
                    case "#" -> walls.add(new Point(x, y));
                    case "O" -> boxes.add(new WarehouseItem(new ArrayList<>(List.of(new Point(x, y))), walls, boxes));
                    case "@" -> robot = new WarehouseItem(new ArrayList<>(List.of(new Point(x, y))), walls, boxes);
                }
            }
        }

        int total = 0;

        if (robot == null) {
            return total;
        }

        for (String instruction : instructions) {
            var nextPos = robot.move(instruction);

            if (nextPos != null) robot.position.getFirst().setLocation(nextPos.getFirst());
        }

        for (WarehouseItem box : boxes) {
            total += box.getGPSCoordinate();
        }

        return total;
    }

    public static int part2(String input) {
//        var splitted = input.split("\\n\\n\\r\\n");
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
                switch (line.get(x)) {
                    case "#" -> walls.add(new Point(x, y));
                    case "[" -> {
                        var position = new ArrayList<>(List.of(new Point(x, y), new Point(x + 1, y)));

                        boxes.add(new WarehouseItem(position, walls, boxes));
                    }
                    case "@" -> robot = new WarehouseItem(new ArrayList<>(List.of(new Point(x, y))), walls, boxes);
                }
            }
        }

        int total = 0;

        if (robot == null) {
            return total;
        }

        for (String instruction : instructions) {
           var nextPos = robot.move(instruction);

           if (nextPos != null) robot.position.getFirst().setLocation(nextPos.getFirst());
        }

        for (WarehouseItem box : boxes) {
            total += box.getGPSCoordinate();
        }

        return total;
    }
}
