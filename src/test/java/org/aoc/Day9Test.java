package org.aoc;

import org.aoc.utils.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day9Test {
    static String exampleInput = "2333133121414131402";
    static String realInput;

    @BeforeAll
    static void readInput() throws IOException {
        realInput = FileUtils.openInput("Day9.txt");
    }

    @Test
    @DisplayName("Should defragment and checksum the filesystem")
    void testPart1Example() {
        assertEquals(1928, Day9.part1(exampleInput));
    }

    @Test
    @DisplayName("Part 1 should return 6356833654075")
    void testPart1() {
        assertEquals(Long.parseLong("6356833654075"), Day9.part1(realInput));
    }

    @Test
    @DisplayName("Should defragment whole blocks and checksum the filesystem")
    void testPart2Example() {
        assertEquals(2858, Day9.part2(exampleInput));
    }
}
