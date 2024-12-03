package org.aoc;

import org.aoc.utils.FileUtils;

import java.io.IOException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    // Finds a string matching "mul(1,2)" and places each number into its own group
    static String multiplierPattern = "mul\\((\\d{1,3}),(\\d{1,3})\\)";

    public static void main(String[] args) throws IOException {
        String input = FileUtils.openInput("Day3.txt").replaceAll("[\r\n]+", "");

        System.out.println("Day 3 - Part 1: " + part1(input));
        System.out.println("Day 3 - Part 2: " + part2(input));
    }

    public static int part1(String input) {
        Pattern r = Pattern.compile(multiplierPattern);

        Matcher m  = r.matcher(input);

        int total = 0;

        while(m.find()) {
            int leftVal = Integer.parseInt(m.group(1));
            int rightVal = Integer.parseInt(m.group(2));

            total += (leftVal * rightVal);
        }

        return total;
    }

    public static int part2(String input) {
        String dontPattern = "(don't\\(\\))"; // Finds the string literal "don't()"
        String doPattern = "(do\\(\\))"; // Finds the string literal "do()"

        Pattern r = Pattern.compile(dontPattern + "|" + doPattern + "|" + multiplierPattern);

        Matcher m  = r.matcher(input);

        int total = 0;
        boolean active = true;

        while (m.find()) {
           if (Objects.equals(m.group(1), "don't()")) {
               active = false;
           } else if (Objects.equals(m.group(2), "do()")) {
               active = true;
           } else if (active){
               int leftVal = Integer.parseInt(m.group(3));
               int rightVal = Integer.parseInt(m.group(4));

               total += (leftVal * rightVal);
           }
        }

        return total;
    }
}
