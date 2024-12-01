package org.aoc;

import org.aoc.utils.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day1Test {
    static String exampleInput = """
            3   4
            4   3
            2   5
            1   3
            3   9
            3   3
            """;
    static String realInput;

    @BeforeAll
    static void readInput() throws IOException {
        realInput = FileUtils.openInput("Day1.txt");
    }

    @Test
    @DisplayName("Should add up the differences in smallest pairs in the list")
    void testPart1Example() {
        assertEquals(11, Day1.part1(exampleInput));
    }

    @Test
    @DisplayName("Part 1 should return 1660292")
    void testPart1() {
        assertEquals(1660292, Day1.part1(realInput));
    }

    @Test
    @DisplayName("Should calculate the similarity score between the lists")
    void testPart2Example() {
        assertEquals(31, Day1.part2(exampleInput));
    }

    @Test
    @DisplayName("Part 2 should return 22776016")
    void testPart2() {
        assertEquals(22776016, Day1.part2(realInput));
    }
}
