package org.aoc;

import org.aoc.utils.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day8Test {
    static String exampleInput = """
            ............
            ........0...
            .....0......
            .......0....
            ....0.......
            ......A.....
            ............
            ............
            ........A...
            .........A..
            ............
            ............
            """;
    static String realInput;

    @BeforeAll
    static void readInput() throws IOException {
        realInput = FileUtils.openInput("Day8.txt");
    }

    @Test
    @DisplayName("Should find number of unique antinodes")
    void testPart1Example() {
        assertEquals(14, Day8.part1(exampleInput));
    }
    @Test
    @DisplayName("Part 1 should return 369")
    void testPart1() {
        assertEquals(369, Day8.part1(realInput));
    }
}
