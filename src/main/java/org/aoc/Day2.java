package org.aoc;

import org.aoc.utils.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

class Report {
    List<Integer> levels;

    public Report(List<Integer> levels) {
        this.levels = levels;
    }

    public Report(String input) {
        this(Arrays.stream(input.split(" ")).map(Integer::valueOf).toList());
    }

    public boolean isSafe() {
        List<Integer> clonedLevels = new ArrayList<>(this.levels);
        Collections.sort(clonedLevels);

        if (!this.levels.equals(clonedLevels) && !this.levels.equals(clonedLevels.reversed())) {
            return false;
        }

        for (int i = 0; i < this.levels.size() - 1; i++) {
            int currLevel = this.levels.get(i);
            int nextLevel = this.levels.get(i + 1);
            int diff = Math.abs(currLevel - nextLevel);

            if (diff < 1 || diff > 3) {
                return false;
            }
        }

        return true;
    }

    public List<Report> createPermutations() {
        List<Report> permutations = new ArrayList<>();

        for (int i = 0; i < this.levels.size(); i++) {
            List<Integer> clonedLevels = new ArrayList<>(this.levels);
            clonedLevels.remove(i);
            permutations.add(new Report(clonedLevels));
        }

        return permutations;
    }
}

public class Day2 {
    public static void main(String[] args) throws IOException {
        String input = FileUtils.openInput("Day2.txt");

        System.out.println("Day 2 - Part 1: " + part1(input));
        System.out.println("Day 2 - Part 2: " + part2(input));
    }

    static List<Report> parseInput(String input) {
        List<Report> reports = new ArrayList<>();

        input.lines().forEach(line -> {
            reports.add(new Report(line));
        });

        return reports;
    }

    public static long part1(String input) {
        return parseInput(input).stream().filter(Report::isSafe).count();
    }

    public static int part2(String input) {
        List<Report> reports = parseInput(input);

        AtomicInteger safeReports = new AtomicInteger();

        reports.forEach(report -> {
            if (report.isSafe()) {
                safeReports.getAndIncrement();
            } else if (report.createPermutations().stream().anyMatch(Report::isSafe)) {
                safeReports.getAndIncrement();
            }
        });

        return safeReports.get();
    }
}
