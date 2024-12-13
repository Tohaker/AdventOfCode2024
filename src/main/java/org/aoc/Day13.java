package org.aoc;

import org.aoc.utils.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Pair {
    long a;
    long b;

    Pair(long a, long b) {
        this.a = a;
        this.b = b;
    }
}

class Equation {
    int A;
    int B;
    long prize;

    Equation(int A, int B, int prize, long offset) {
        this.A = A;
        this.B = B;
        this.prize = prize + offset;
    }

    public Pair solveSimultaneousEquation(Equation e) {
        int determinant = (this.A * e.B) - (this.B * e.A);

        if (determinant == 0) {
            return new Pair(0, 0); // No unique solution
        }

        double a = (double) (this.prize * e.B - e.prize * this.B) / determinant;
        double b = (double) (e.prize * this.A - this.prize * e.A) / determinant;

        if ((long) a == a && (long) b == b) {
            return new Pair((long) a, (long) b);
        }

        return new Pair(0, 0); // No integer solution
    }
}

public class Day13 {
    public static void main(String[] args) throws IOException {
        String input = FileUtils.openInput("Day13.txt");

        System.out.println("Day 13 - Part 1: " + solvePart(input, 0));
        System.out.println("Day 13 - Part 2: " + solvePart(input, 10000000000000L));
    }

    static List<ArrayList<Equation>> parseInput(String input, long offset) {
        Pattern p = Pattern.compile("(\\d+), \\D+(\\d+)");

        return Arrays.stream(input.split("\\n\\n")).map(machine -> {
            List<String> lines = machine.lines().toList();

            Matcher buttonA = p.matcher(lines.getFirst());
            Matcher buttonB = p.matcher(lines.get(1));
            Matcher prizes = p.matcher(lines.getLast());

            buttonA.find();
            buttonB.find();
            prizes.find();

            Equation eq1 = new Equation(
                    Integer.parseInt(buttonA.group(1)),
                    Integer.parseInt(buttonB.group(1)),
                    Integer.parseInt(prizes.group(1)),
                    offset
            );

            Equation eq2 = new Equation(
                    Integer.parseInt(buttonA.group(2)),
                    Integer.parseInt(buttonB.group(2)),
                    Integer.parseInt(prizes.group(2)),
                    offset
            );

            return new ArrayList<>(List.of(eq1, eq2));
        }).toList();
    }

    public static long solvePart(String input, long offset) {
        var equations = parseInput(input, offset);

        long total = 0;

        for (List<Equation> pair: equations) {
            var solved = pair.getFirst().solveSimultaneousEquation(pair.getLast());

            total += solved.a * 3 + solved.b;
        }

        return total;
    }
}
