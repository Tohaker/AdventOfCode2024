package org.aoc;

import org.aoc.utils.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day2Test {
    static String exampleInput = """
            7 6 4 2 1
            1 2 7 8 9
            9 7 6 2 1
            1 3 2 4 5
            8 6 4 4 1
            1 3 6 7 9
            """;
    static String realInput;

    @BeforeAll
    static void readInput() throws IOException {
        realInput = FileUtils.openInput("Day2.txt");
    }

    @Test
    @DisplayName("Should count the number of safe reports")
    void testPart1Example() {
        assertEquals(2, Day2.part1(exampleInput));
    }

    @Test
    @DisplayName("Part 1 should return 224")
    void testPart1() {
        assertEquals(224, Day2.part1(realInput));
    }

    @Test
    @DisplayName("Should count the number of safe dampened reports")
    void testPart2Example() {
        assertEquals(4, Day2.part2(exampleInput));
    }

    @Test
    @DisplayName("Part 2 should return 293")
    void testPart2() {
        assertEquals(293, Day2.part2(realInput));
    }
}
