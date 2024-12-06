package org.aoc;

import org.aoc.utils.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day6Test {
    static String exampleInput = """
            ....#.....
            .........#
            ..........
            ..#.......
            .......#..
            ..........
            .#..^.....
            ........#.
            #.........
            ......#...
            """;
    static String realInput;

    @BeforeAll
    static void readInput() throws IOException {
        realInput = FileUtils.openInput("Day6.txt");
    }

    @Test
    @DisplayName("Should count the number of unique positions of the guard")
    void testPart1Example() {
        assertEquals(41, Day6.part1(exampleInput));
    }

    @Test
    @DisplayName("Should count the number of unique positions of the guard")
    void testPart1() {
        assertEquals(4964, Day6.part1(realInput));
    }

    @Test
    @DisplayName("Should count the number of unique positions of obstacles to cause a loop")
    void testPart2Example() {
        assertEquals(6, Day6.part2(exampleInput));
    }
}
