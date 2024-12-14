package org.aoc;

import org.aoc.utils.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day12Test {
    static String realInput;

    @BeforeAll
    static void readInput() throws IOException {
        realInput = FileUtils.openInput("Day12.txt");
    }

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

    @Test
    @DisplayName("Part 1 should return 1396562")
    void testPart1() {
        assertEquals(1396562, Day12.part1(realInput));
    }

    @Test
    @DisplayName("Should calculate the total price of bulk fencing for example 1")
    void testPart2Example1() {
        String exampleInput = """
                AAAA
                BBCD
                BBCC
                EEEC
                """;

        assertEquals(80, Day12.part2(exampleInput));
    }

    @Test
    @DisplayName("Should calculate the total price of bulk fencing for example 2")
    void testPart2Example2() {
        String exampleInput = """
                OOOOO
                OXOXO
                OOOOO
                OXOXO
                OOOOO
                """;

        assertEquals(436, Day12.part2(exampleInput));
    }

    @Test
    @DisplayName("Should calculate the total price of bulk fencing for example 3")
    void testPart2Example3() {
        String exampleInput = """
                EEEEE
                EXXXX
                EEEEE
                EXXXX
                EEEEE
                """;

        assertEquals(236, Day12.part2(exampleInput));
    }

    @Test
    @DisplayName("Should calculate the total price of bulk fencing for example 4")
    void testPart2Example4() {
        String exampleInput = """
                AAAAAA
                AAABBA
                AAABBA
                ABBAAA
                ABBAAA
                AAAAAA
                """;

        assertEquals(368, Day12.part2(exampleInput));
    }

    @Test
    @DisplayName("Should calculate the total price of bulk fencing for example 5")
    void testPart2Example5() {
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

        assertEquals(1206, Day12.part2(exampleInput));
    }

    @Test
    @DisplayName("Part 2 should return 844132")
    void testPart2() {
        assertEquals(844132, Day12.part2(realInput));
    }
}
