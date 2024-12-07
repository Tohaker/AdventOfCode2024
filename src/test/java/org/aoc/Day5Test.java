package org.aoc;

import org.aoc.utils.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day5Test {
    static String exampleInput = """
            47|53
            97|13
            97|61
            97|47
            75|29
            61|13
            75|53
            29|13
            97|29
            53|29
            61|53
            97|53
            61|29
            47|13
            75|47
            97|75
            47|61
            75|61
            47|29
            75|13
            53|13
            
            75,47,61,53,29
            97,61,53,29,13
            75,29,13
            75,97,47,61,53
            61,13,29
            97,13,75,29,47
            """;
    static String realInput;

    @BeforeAll
    static void readInput() throws IOException {
        realInput = FileUtils.openInput("Day5.txt");
    }

    @Test
    @DisplayName("Should add the middle values of valid printing orders")
    void testPart1Example() {
        assertEquals(143, Day5.part1(exampleInput));
    }

    @Test
    @DisplayName("Part 1 should return 5275")
    void testPart1() {
        assertEquals(5275, Day5.part1(realInput));
    }

    @Test
    @DisplayName("Should add the middle values of invalid printing orders")
    void testPart2Example() {
        assertEquals(123, Day5.part2(exampleInput));
    }

    @Test
    @DisplayName("Part 2 should return 6191")
    void testPart2() {
        assertEquals(6191, Day5.part2(realInput));
    }
}
