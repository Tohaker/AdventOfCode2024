package org.aoc;

import org.aoc.utils.FileUtils;

import java.io.IOException;
import java.util.*;

public class Day1 {
    public static void main(String[] args) throws IOException {
        String input = FileUtils.openInput("Day1.txt");

        System.out.println("Day 1 - Part 1: " + part1(input));
        System.out.println("Day 1 - Part 2: " + part2(input));
    }

    static List<List<Integer>> parseInput(String input) {
        List<Integer> leftList = new ArrayList<>();
        List<Integer> rightList = new ArrayList<>();

        input.lines().forEach(line -> {
            String[] splitted = line.split("\\s+");

            leftList.add(Integer.valueOf(splitted[0]));
            rightList.add(Integer.valueOf(splitted[1]));
        });

        return Arrays.asList(leftList, rightList);
    }

    public static int part1(String input) {
        List<List<Integer>> lists = parseInput(input);

        List<Integer> leftList = lists.getFirst();
        List<Integer> rightList = lists.getLast();

        Collections.sort(leftList);
        Collections.sort(rightList);

        Iterator<Integer> leftIterator = leftList.iterator();
        Iterator<Integer> rightIterator = rightList.iterator();

        int result = 0;

        while (leftIterator.hasNext() && rightIterator.hasNext()) {
          result += Math.abs(leftIterator.next() - rightIterator.next());
        }

        return result;
    }

    public static int part2(String input) {
        List<List<Integer>> lists = parseInput(input);

        List<Integer> leftList = lists.getFirst();
        List<Integer> rightList = lists.getLast();

        Iterator<Integer> leftIterator = leftList.iterator();

        int result = 0;

        while (leftIterator.hasNext()) {
            int leftValue = leftIterator.next();
            int occurrences = Collections.frequency(rightList, leftValue);

            result += leftValue * occurrences;
        }

        return result;
    }
}
