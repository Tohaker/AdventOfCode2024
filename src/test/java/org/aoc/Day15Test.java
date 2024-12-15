package org.aoc;

import org.aoc.utils.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day15Test {
    static String realInput;

    @BeforeAll
    static void readInput() throws IOException {
        realInput = FileUtils.openInput("Day15.txt");
    }

    @Test
    @DisplayName("Should calculate the sum of all GPS coords of boxes in example 1")
    void testPart1Example1() {
        String exampleInput = """
                ########
                #..O.O.#
                ##@.O..#
                #...O..#
                #.#.O..#
                #...O..#
                #......#
                ########
                
                <^^>>>vv<v>>v<<
                """;

        assertEquals(2028, Day15.part1(exampleInput));
    }

    @Test
    @DisplayName("Should calculate the sum of all GPS coords of boxes in example 2")
    void testPart1Example2() {
        String exampleInput = """
                ##########
                #..O..O.O#
                #......O.#
                #.OO..O.O#
                #..O@..O.#
                #O#..O...#
                #O..O..O.#
                #.OO.O.OO#
                #....O...#
                ##########
                
                <vv>^<v^>v>^vv^v>v<>v^v<v<^vv<<<^><<><>>v<vvv<>^v^>^<<<><<v<<<v^vv^v>^
                vvv<<^>^v^^><<>>><>^<<><^vv^^<>vvv<>><^^v>^>vv<>v<<<<v<^v>^<^^>>>^<v<v
                ><>vv>v^v^<>><>>>><^^>vv>v<^^^>>v^v^<^^>v^^>v^<^v>v<>>v^v^<v>v^^<^^vv<
                <<v<^>>^^^^>>>v^<>vvv^><v<<<>^^^vv^<vvv>^>v<^^^^v<>^>vvvv><>>v^<<^^^^^
                ^><^><>>><>^^<<^^v>>><^<v>^<vv>>v>>>^v><>^v><<<<v>>v<v<v>vvv>^<><<>^><
                ^>><>^v<><^vvv<^^<><v<<<<<><^v<<<><<<^^<v<^^^><^>>^<v^><<<^>>^v<v^v<v^
                >^>>^v>vv>^<<^v<>><<><<v<<v><>v<^vv<<<>^^v^>^^>>><<^v>>v^v><^^>>^<>vv^
                <><^^>^^^<><vvvvv^v<v<<>^v<v>v<<^><<><<><<<^^<<<^<<>><<><^^^>^^<>^>v<>
                ^^>vv<^v^v<vv>^<><v<^v>^^^>>>^^vvv^>vvv<>>>^<^>>>>>^<<^v>^vvv<>^<><<v>
                v^^>>><<^^<>>^v^<v^vv<>v^<<>^<^v^v><^<<<><<^<v><v<>vv>>v><v^<vv<>v^<<^
                """;

        assertEquals(10092, Day15.part1(exampleInput));
    }

    @Test
    @DisplayName("Part 1 should return 1538871")
    void testPart1() {
        assertEquals(1538871, Day15.part1(realInput));
    }

    @Test
    @DisplayName("Should calculate the sum of all GPS coords of big boxes in example 1")
    void testPart2Example1() {
        String exampleInput = """
                #######
                #...#.#
                #.....#
                #..OO@#
                #..O..#
                #.....#
                #######
                
                <vv<<^^<<^^
                """;

        assertEquals(105 + 207 + 306, Day15.part2(exampleInput));
    }

    @Test
    @DisplayName("Should calculate the sum of all GPS coords of big boxes in example 2")
    void testPart2Example2() {
        String exampleInput = """
                ##########
                #..O..O.O#
                #......O.#
                #.OO..O.O#
                #..O@..O.#
                #O#..O...#
                #O..O..O.#
                #.OO.O.OO#
                #....O...#
                ##########
                
                <vv>^<v^>v>^vv^v>v<>v^v<v<^vv<<<^><<><>>v<vvv<>^v^>^<<<><<v<<<v^vv^v>^
                vvv<<^>^v^^><<>>><>^<<><^vv^^<>vvv<>><^^v>^>vv<>v<<<<v<^v>^<^^>>>^<v<v
                ><>vv>v^v^<>><>>>><^^>vv>v<^^^>>v^v^<^^>v^^>v^<^v>v<>>v^v^<v>v^^<^^vv<
                <<v<^>>^^^^>>>v^<>vvv^><v<<<>^^^vv^<vvv>^>v<^^^^v<>^>vvvv><>>v^<<^^^^^
                ^><^><>>><>^^<<^^v>>><^<v>^<vv>>v>>>^v><>^v><<<<v>>v<v<v>vvv>^<><<>^><
                ^>><>^v<><^vvv<^^<><v<<<<<><^v<<<><<<^^<v<^^^><^>>^<v^><<<^>>^v<v^v<v^
                >^>>^v>vv>^<<^v<>><<><<v<<v><>v<^vv<<<>^^v^>^^>>><<^v>>v^v><^^>>^<>vv^
                <><^^>^^^<><vvvvv^v<v<<>^v<v>v<<^><<><<><<<^^<<<^<<>><<><^^^>^^<>^>v<>
                ^^>vv<^v^v<vv>^<><v<^v>^^^>>>^^vvv^>vvv<>>>^<^>>>>>^<<^v>^vvv<>^<><<v>
                v^^>>><<^^<>>^v^<v^vv<>v^<<>^<^v^v><^<<<><<^<v><v<>vv>>v><v^<vv<>v^<<^
                """;

        assertEquals(9021, Day15.part2(exampleInput));
    }

    @Test
    // https://github.com/Fadi88/AoC/blob/master/2024/day15/test_corner.txt
    @DisplayName("Should calculate the sum of all GPS coords of big boxes in example 3")
    void testPart2Example3() {
        String exampleInput = """
                #######
                #.....#
                #.....#
                #.@O..#
                #..#O.#
                #...O.#
                #..O..#
                #.....#
                #######
                
                >><vvv>v>^^^
                """;

        assertEquals(1430, Day15.part2(exampleInput));
    }

    @Test
    void testPart2Example4() {
        String exampleInput = """
                #######
                #...#.#
                #.....#
                #.....#
                #.....#
                #.....#
                #.OOO@#
                #.OOO.#
                #..O..#
                #.....#
                #.....#
                #######
                
                v<vv<<^^^^^
                """;

        assertEquals(2339, Day15.part2(exampleInput));
    }

//    ##############
//    ##......##..##
//    ##..[][][]..##
//    ##...[][]...##
//    ##....[]....##
//    ##.....@....##
//    ##..........##
//    ##.[].......##
//    ##..........##
//    ##..........##
//    ##..........##
//    ##############
}
