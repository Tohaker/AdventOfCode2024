package org.aoc;

import org.aoc.utils.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day3Test {
    static String exampleInput = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))";
    static String exampleInput2 = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))";
    static String realInput;

    @BeforeAll
    static void readInput() throws IOException {
        realInput = FileUtils.openInput("Day3.txt").replaceAll("[\r\n]+", "");
    }


    @Test
    @DisplayName("Should add up all the real mul instructions")
    void testPart1Example() {
        assertEquals(161, Day3.part1(exampleInput));
    }

    @Test
    @DisplayName("Part 1 should return 174103751")
    void testPart1() {
        assertEquals(174103751, Day3.part1(realInput));
    }

    @Test
    @DisplayName("Should also handle do and dont instructions")
    void testPart2Example() {
        assertEquals(48, Day3.part2(exampleInput2));
    }

    @Test
    @DisplayName("Part 2 should return 100411201")
    void testPart2() {
        assertEquals(100411201, Day3.part2(realInput));
    }
}
