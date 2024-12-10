package org.aoc;

import org.aoc.utils.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day10Test {
    static String exampleInput = """
            89010123
            78121874
            87430965
            96549874
            45678903
            32019012
            01329801
            10456732
            """;
    static String realInput;

    @BeforeAll
    static void readInput() throws IOException {
        realInput = FileUtils.openInput("Day10.txt");
    }

    @Test
    @DisplayName("Should sum all trailhead scores")
    void testPart1Example() {
        assertEquals(36, Day10.part1(exampleInput));
    }

    @Test
    @DisplayName("Part 1 should return 667")
    void testPart1() {
        assertEquals(667, Day10.part1(realInput));
    }
}
