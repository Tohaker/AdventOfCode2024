package org.aoc;

import org.aoc.utils.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day13Test {
    static String exampleInput = """
            Button A: X+94, Y+34
            Button B: X+22, Y+67
            Prize: X=8400, Y=5400
            
            Button A: X+26, Y+66
            Button B: X+67, Y+21
            Prize: X=12748, Y=12176
            
            Button A: X+17, Y+86
            Button B: X+84, Y+37
            Prize: X=7870, Y=6450
            
            Button A: X+69, Y+23
            Button B: X+27, Y+71
            Prize: X=18641, Y=10279
            """;
    static String realInput;

    @BeforeAll
    static void readInput() throws IOException {
        realInput = FileUtils.openInput("Day13.txt");
    }

    @Test
    @DisplayName("Should find the fewest tokens to win the most prizes")
    void testPart1Example() {
        assertEquals(480, Day13.part1(exampleInput));
    }

    @Test
    @DisplayName("Part 1 return 35574")
    void testPart1() {
        assertEquals(35574, Day13.part1(realInput));
    }

    @Test
    @DisplayName("Should find the fewest tokens to win the most big offset prizes")
    void testPart2Example() {
        assertEquals(875318608908L, Day13.part2(exampleInput));
    }

    @Test
    @DisplayName("Part 2 return 80882098756071")
    void testPart2() {
        assertEquals(80882098756071L, Day13.part2(realInput));
    }
}
