package org.aoc;

import org.aoc.utils.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day4 {
    public static void main(String[] args) throws IOException {
        String input = FileUtils.openInput("Day4.txt");

        System.out.println("Day 4 - Part 1: " + part1(input));
//        System.out.println("Day 1 - Part 2: " + part2(input));
    }

    static List<List<String>> parseInput(String input) {
        List<List<String>> grid = new ArrayList<>();

        input.lines().forEach(line -> {
            grid.add(Arrays.stream(line.split("")).toList());
        });

        return grid;
    }

    static List<List<String>> rotate90Degrees(List<List<String>> grid) {
        List<List<String>> rotatedGrid = new ArrayList<>();
        int size = grid.size();

        for (int i = 0; i < size; i++) {
            List<String> newLine = new ArrayList<>();

            for (List<String> strings : grid) {
                newLine.add(strings.get(i));
            }

            rotatedGrid.add(newLine);
        }

        return rotatedGrid;
    }

    static List<List<String>> flipVertically(List<List<String>> grid) {
        List<List<String>> flippedgrid = new ArrayList<>();

        for (List<String> strings : grid) {
            flippedgrid.add(strings.reversed());
        }

        return flippedgrid;
    }

    static List<List<String>> rotate45Degrees(List<List<String>> grid) {
        String[][] rotatedGrid = new String[2 * grid.size() - 1][2 * grid.size() - 1];

        int size = grid.size();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                rotatedGrid[i + j][size - 1 - i + j] = grid.get(i).get(j);
            }
        }

        return Arrays.stream(rotatedGrid).map(Arrays::asList).collect(Collectors.toList());
    }

    static int findXMAS(List<List<String>> grid) {
        AtomicInteger count = new AtomicInteger();
        Pattern pattern = Pattern.compile("(?=(XMAS))|(?=(SAMX))");

        grid.forEach(line -> {
            String joined = String.join("",line.stream().filter(Objects::nonNull).toList());
            Matcher m = pattern.matcher(joined);

            count.addAndGet((int) m.results().count());
        });

        return count.get();
    }

    static int part1(String input) {
        int count = 0;

        List<List<String>> grid = parseInput(input);
        List<List<String>> rotatedGrid = rotate90Degrees(grid);
        List<List<String>> rotated45Grid = rotate45Degrees(grid);
        List<List<String>> rotated135Grid = rotate45Degrees(flipVertically(rotatedGrid));

        count += findXMAS(grid);
        count += findXMAS(rotatedGrid);
        count += findXMAS(rotated45Grid);
        count += findXMAS(rotated135Grid);

        return count;
    }
}