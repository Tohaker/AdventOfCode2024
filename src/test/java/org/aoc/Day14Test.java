package org.aoc;

import org.aoc.utils.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day14Test {
    static String exampleInput = """
            p=0,4 v=3,-3
            p=6,3 v=-1,-3
            p=10,3 v=-1,2
            p=2,0 v=2,-1
            p=0,0 v=1,3
            p=3,0 v=-2,-2
            p=7,6 v=-1,-3
            p=3,0 v=-1,-2
            p=9,3 v=2,3
            p=7,3 v=-1,2
            p=2,4 v=2,-3
            p=9,5 v=-3,-3
            """;
    static String realInput;

    @BeforeAll
    static void readInput() throws IOException {
        realInput = FileUtils.openInput("Day14.txt");
    }

    @Test
    @DisplayName("Should calculate safety factor after 100 seconds")
    void testPart1Example() {
        assertEquals(12, Day14.part1(exampleInput, 11, 7));
    }

    @Test
    @DisplayName("Part 1 should return 221142636")
    void testPart1() {
        assertEquals(221142636, Day14.part1(realInput, 101, 103));
    }
}
