package org.aoc;

import org.aoc.utils.FileUtils;

import java.io.IOException;

public class DayExample {
    public static void main(String[] args) throws IOException {
        String input = FileUtils.openInput("DayExample.txt");

        System.out.println("Day 1 - Part 1: " + part1(input));
        System.out.println("Day 1 - Part 2: " + part2(input));
    }

    public static int part1(String input) {
        int floor = 0;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (c == '(') {
                floor++;
            } else {
                floor--;
            }
        }

        return floor;
    }

    public static int part2(String input) {
        int floor = 0;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (c == '(') {
                floor++;
            } else {
                floor--;
            }

            if (floor == -1) {
                return i + 1;
            }
        }

        return 0;
    }
}
