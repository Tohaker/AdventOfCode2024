package org.aoc;

import org.aoc.utils.FileUtils;
import org.paukov.combinatorics3.Generator;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

public class Day7 {
    public static void main(String[] args) throws IOException {
        String input = FileUtils.openInput("Day7.txt");

        System.out.println("Day 7 - Part 1: " + part1(input));
        System.out.println("Day 7 - Part 2: " + part2(input));
    }

    static List<Map.Entry<Long, List<Integer>>> parseInput(String input) {
        List<Map.Entry<Long, List<Integer>>> equations = new ArrayList<>();

        input.lines().forEach(line -> {
            String[] splitted = line.split(": ");
            long key = Long.parseLong(splitted[0]);
            List<Integer> value = Arrays.stream(splitted[1].split(" ")).map(Integer::parseInt).toList();

            equations.add(new AbstractMap.SimpleEntry<>(key, value));
        });

        return equations;
    }

    static long sumValidEquations(String input, String[] operators) {
        List<Map.Entry<Long, List<Integer>>> equations = parseInput(input);

        long total = 0;

        for (Map.Entry<Long, List<Integer>> equation : equations) {
            int size = equation.getValue().size() - 1;

            List<List<String>> output = Generator.combination(operators)
                    .multi(size).stream().flatMap(combination ->
                            Generator.permutation(combination).simple().stream()
                    ).toList();

            if (output.stream().anyMatch(list -> {
                List<Integer> values = new ArrayList<>(equation.getValue());

                long subtotal = values.getFirst();

                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).equals("+")) {
                        subtotal += values.get(i + 1);
                    } else if (list.get(i).equals("*")) {
                        subtotal *= values.get(i + 1);
                    } else {
                        subtotal = Long.parseLong(String.valueOf(subtotal) + String.valueOf(values.get(i + 1)));
                    }
                }

                return subtotal == equation.getKey();
            })) {
                total += equation.getKey();
            }
        }

        return total;
    }

    public static long part1(String input) {
        String[] operators = new String[]{"+", "*"};

        return sumValidEquations(input, operators);
    }

    public static long part2(String input) {
        String[] operators = new String[]{"+", "*", "||"};

        return sumValidEquations(input, operators);
    }
}
