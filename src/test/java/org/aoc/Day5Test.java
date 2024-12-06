package org.aoc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

    @Test
    @DisplayName("Should add the middle values of valid printing orders")
    void testPart1Example() {
        assertEquals(143, Day5.part1(exampleInput));
    }

    @Test
    @DisplayName("Should add the middle values of invalid printing orders")
    void testPart2Example() {
        assertEquals(123, Day5.part2(exampleInput));
    }
}
