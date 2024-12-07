package org.aoc;

import org.aoc.utils.FileUtils;
import org.paukov.combinatorics3.Generator;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

public class Day7 {
    public static void main(String[] args) throws IOException {
        String input = FileUtils.openInput("Day7.txt");

        DecimalFormat df = new DecimalFormat("#");
        df.setMaximumFractionDigits(20);

        System.out.println("Day 7 - Part 1: " + df.format(part1(input)));
//        System.out.println("Day 7 - Part 2: " + part2(input));
    }

    static List<Map.Entry<Double, List<Integer>>> parseInput(String input) {
        List<Map.Entry<Double, List<Integer>>> equations = new ArrayList<>();

        input.lines().forEach(line -> {
            String[] splitted = line.split(": ");
            double key = Double.parseDouble(splitted[0]);
            List<Integer> value = Arrays.stream(splitted[1].split(" ")).map(Integer::parseInt).toList();

            equations.add(new AbstractMap.SimpleEntry<>(key, value));
        });

        return equations;
    }

    public static double part1(String input) {
        List<Map.Entry<Double, List<Integer>>> equations = parseInput(input);

        double total = 0;

        String[] operators = new String[]{"+", "*"};

        for (Map.Entry<Double, List<Integer>> equation : equations) {
            int size = equation.getValue().size() - 1;

            List<List<String>> output = Generator.combination(operators)
                    .multi(size).stream().flatMap(combination ->
                            Generator.permutation(combination).simple().stream()
                    ).toList();


            if (output.stream().anyMatch(list -> {
                double subtotal = equation.getValue().getFirst();

                for (int i = 0; i < size; i++) {
                    if (list.get(i).equals("+")) {
                        subtotal += equation.getValue().get(i + 1);
                    } else {
                        subtotal *= equation.getValue().get(i + 1);
                    }
                }

                return subtotal == equation.getKey();
            })) {
                total += equation.getKey();
            }
        }

        return total;
    }
}
