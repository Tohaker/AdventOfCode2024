package org.aoc;

import org.aoc.utils.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day4Test {
    static String exampleInput = """
            MMMSXXMASM
            MSAMXMSMSA
            AMXSXMAAMM
            MSAMASMSMX
            XMASAMXAMM
            XXAMMXXAMA
            SMSMSASXSS
            SAXAMASAAA
            MAMMMXMMMM
            MXMXAXMASX
            """;
    static String realInput;

    @BeforeAll
    static void readInput() throws IOException {
        realInput = FileUtils.openInput("Day4.txt");
    }

    @Test
    @DisplayName("Should find all the XMAS words in the grid")
    void testPart1Example() {
        assertEquals(18, Day4.part1(exampleInput));
    }

    @Test
    @DisplayName("Part 1 should return 2390")
    void testPart1() {
        assertEquals(2390, Day4.part1(realInput));
    }
}
