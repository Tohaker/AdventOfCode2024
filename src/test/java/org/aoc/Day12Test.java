package org.aoc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day12Test {

    @Test
    @DisplayName("Should calculate the total price of fencing for example 1")
    void testPart1Example1() {
        String exampleInput = """
                AAAA
                BBCD
                BBCC
                EEEC
                """;

        assertEquals(140, Day12.part1(exampleInput));
    }

    @Test
    @DisplayName("Should calculate the total price of fencing for example 2")
    void testPart1Example2() {
        String exampleInput = """
                OOOOO
                OXOXO
                OOOOO
                OXOXO
                OOOOO
                """;

        assertEquals(772, Day12.part1(exampleInput));
    }

    @Test
    @DisplayName("Should calculate the total price of fencing for example 3")
    void testPart1Example3() {
        String exampleInput = """
            RRRRIICCFF
            RRRRIICCCF
            VVRRRCCFFF
            VVRCCCJFFF
            VVVVCJJCFE
            VVIVCCJJEE
            VVIIICJJEE
            MIIIIIJJEE
            MIIISIJEEE
            MMMISSJEEE
            """;

        assertEquals(1930, Day12.part1(exampleInput));
    }
}
