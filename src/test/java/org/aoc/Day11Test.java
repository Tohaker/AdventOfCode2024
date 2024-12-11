package org.aoc;

import org.aoc.utils.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day11Test {
    static String realInput;

    @BeforeAll
    static void readInput() throws IOException {
        realInput = FileUtils.openInput("Day11.txt");
    }

    @Test
    @DisplayName("Should have 7 stones after 1 blink")
    void testPart1Example1() {
        assertEquals(7, Day11.part1("0 1 10 99 999", 1));
    }

    @Test
    @DisplayName("Should have 55312 stones after 25 blinks")
    void testPart1Example2() {
        assertEquals(55312, Day11.part1("125 17", 25));
    }

    @Test
    @DisplayName("Part 1 should return 186175")
    void testPart1() {
        assertEquals(186175, Day11.part1(realInput, 25));
    }
}
