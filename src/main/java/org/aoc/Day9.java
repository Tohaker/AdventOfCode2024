package org.aoc;

import org.aoc.utils.FileUtils;

import java.io.IOException;
import java.util.*;

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

        Set<Integer> attemptedToMove = new HashSet<>();

        // Keep looking back until we find a block to move
        for (int j = blocks.size() - 1; j >= 0; j--) {
            Integer index = blocks.get(j);
            if (index == null || attemptedToMove.contains(index)) {
                continue;
            }

            int size = ((int) blocks.stream().filter(el -> el != null && el.equals(index)).count());

            for (int i = 0; i < j - size; i++) {
                if (blocks.subList(i, i + size).stream().noneMatch(Objects::nonNull)) {
                    for (int k = 0; k < size; k++) {
                        blocks.set(i + k, blocks.get(j - k));
                        blocks.set(j - k, null);
                    }

                    j -= size - 1;
                    break;
                }
            }

            attemptedToMove.add(index);
        }

        long checksum = 0;

        for (int i = 0; i < blocks.size(); i++) {
            Integer current = blocks.get(i);

            if (current != null) {
                checksum += blocks.get(i) * i;
            }
        }

        return checksum;
    }
}
