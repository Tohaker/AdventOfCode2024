package org.aoc;

import org.aoc.utils.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day7Test {
    static String exampleInput = """
            190: 10 19
            3267: 81 40 27
            83: 17 5
            156: 15 6
            7290: 6 8 6 15
            161011: 16 10 13
            192: 17 8 14
            21037: 9 7 18 13
            292: 11 6 16 20
            """;
    static String realInput;

    @BeforeAll
    static void readInput() throws IOException {
        realInput = FileUtils.openInput("Day7.txt");
    }

    @Test
    @DisplayName("Should sum all possibly true equations")
    void testPart1Example() {
        assertEquals(3749, Day7.part1(exampleInput));
    }

    @Test
    @DisplayName("Part 1 should return 5030892084481")
    void testPart1() {
        assertEquals(Double.valueOf("5030892084481"), Day7.part1(realInput));
    }
}
