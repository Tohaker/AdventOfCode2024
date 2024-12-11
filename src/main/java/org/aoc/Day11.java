package org.aoc;

import org.aoc.utils.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day11 {
    public static void main(String[] args) throws IOException {
        String input = FileUtils.openInput("Day11.txt");

        System.out.println("Day 11 - Part 1: " + part1(input, 25));
//        System.out.println("Day 11 - Part 2: " + part2(input));
    }

    static int part1(String input, int blinks) {
        List<Long> stones = Arrays.stream(input.split(" ")).map(Long::parseLong).toList();

        for (int i = 0; i < blinks; i++) {
            List<Long> newStones = new ArrayList<>();

            for (Long stone : stones) {
                String stringifiedValue = String.valueOf(stone);

                if (stone == 0) {
                    newStones.add(1L);
                    // If we take their log in base 10 and round it up, we’ll get the number of digits in that number
                } else if (stringifiedValue.length() % 2 == 0) {
                    newStones.add(Long.parseLong(stringifiedValue.substring(0, stringifiedValue.length() / 2)));
                    newStones.add(Long.parseLong(stringifiedValue.substring(stringifiedValue.length() / 2)));
                } else {
                    newStones.add(stone * 2024);
                }
            }

            stones = new ArrayList<>(newStones);
        }

        return stones.size();
    }
}
