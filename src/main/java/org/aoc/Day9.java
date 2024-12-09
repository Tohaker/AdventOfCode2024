package org.aoc;

import org.aoc.utils.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day9 {
    public static void main(String[] args) throws IOException {
        String input = FileUtils.openInput("Day9.txt");

        System.out.println("Day 9 - Part 1: " + part1(input));
        System.out.println("Day 9 - Part 2: " + part2(input));
    }

    public static long part1(String input) {
        List<Integer> blocks = new ArrayList<>();

        for (int i = 0; i < input.length(); i += 2) {
            int index = i / 2;
            int files = input.charAt(i) - '0'; // Effective conversion of char to int for ASCII characters
            int freeSpace = 0;

            if (i + 1 < input.length()) {
                freeSpace = input.charAt(i + 1) - '0';
            }

            blocks.addAll(Collections.nCopies(files, index));
            blocks.addAll(Collections.nCopies(freeSpace, null));
        }

        for (int i = 0; i < blocks.size(); i++) {
            if (blocks.get(i) == null) {
              blocks.set(i, blocks.getLast());
              blocks.removeLast();
              while (blocks.getLast() == null) {
                  blocks.removeLast();
              }
            }
        }

        blocks.removeAll(Collections.singleton(null));

        long checksum = 0;

        for (int i = 0; i < blocks.size(); i++) {
            checksum += blocks.get(i) * i;
        }

        return checksum;
    }

    public static long part2(String input) {
        List<Integer> fileBlocks = new ArrayList<>();
        List<Integer> freeBlocks = new ArrayList<>();

        for (int i = 0; i < input.length(); i += 2) {
            int files = input.charAt(i) - '0'; // Effective conversion of char to int for ASCII characters
            int freeSpace = 0;

            if (i + 1 < input.length()) {
                freeSpace = input.charAt(i + 1) - '0';
            }

            fileBlocks.add(files);
            freeBlocks.add(freeSpace);
        }

        List<Integer> blocks = new ArrayList<>();

        // Start at the end of the file blocks
        for (int i = fileBlocks.size() - 1; i >= 0; i--) {
            // Get last file block size and index
            int size = fileBlocks.get(i);
            int index = i;

            // Get first free space that can fit the file
            for (int j = 0; j < freeBlocks.size(); j++) {
                if (freeBlocks.get(i) >= size) {
                    blocks
                }
            }
        }

        return 0;
    }
}
