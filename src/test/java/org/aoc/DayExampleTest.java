package org.aoc;

import org.aoc.utils.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DayExampleTest {
    static String realInput;

    @BeforeAll
    static void readInput() throws IOException {
        realInput = FileUtils.openInput("DayExample.txt");
    }

    @ParameterizedTest(name = "{0} goes to floor {1}")
    @CsvSource({
            "(()), 0",
            "()(), 0",
            "(((, 3",
            "(()(()(, 3",
            "))(((((, 3",
            "()), -1",
            "))(, -1",
            "))), -3",
            ")())()), -3"
    })
    void testPart1Examples(String input, int expected) {
        assertEquals(expected, DayExample.part1(input));
    }

    @Test
    @DisplayName("Part 1 should return 74")
    void testPart1Real() {
        assertEquals(74, DayExample.part1(realInput));
    }

    @ParameterizedTest(name = "{0} enters the basement at position {1}")
    @CsvSource({
            "), 1",
            "()()), 5"
    })
    void testPart2Examples(String input, int expected) {
        assertEquals(expected, DayExample.part2(input));
    }

    @Test
    @DisplayName("Part 2 should return 1795")
    void testPart2Real() {
        assertEquals(1795, DayExample.part2(realInput));
    }
}