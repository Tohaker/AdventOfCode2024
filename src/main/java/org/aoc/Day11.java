package org.aoc;

import org.aoc.utils.FileUtils;

import java.io.IOException;
import java.util.*;

public class Day11 {
    static Map<Map.Entry<Long, Integer>, Long> cache = new HashMap<>();

    public static void main(String[] args) throws IOException {
        String input = FileUtils.openInput("Day11.txt");

        System.out.println("Day 11 - Part 1: " + runPart(input, 25));
        System.out.println("Day 11 - Part 2: " + runPart(input, 75));
    }

    static long runPart(String input, int blinks) {
        List<Long> stones = Arrays.stream(input.split(" ")).map(Long::parseLong).toList();

        long total = 0;

        for (Long stone: stones) {
            total += blink(stone, blinks);
        }

        return total;
    }

    static long blink(long stone, int n) {
        Map.Entry<Long, Integer> cacheKey = new AbstractMap.SimpleEntry<>(stone, n);

        // We don't want to recalculate stones we've already come across
        if (cache.containsKey(cacheKey)) return cache.get(cacheKey);

        if (n == 0) {
            cache.put(cacheKey, 1L);
            // We've hit the end, there's only 1 stone per stone
            return 1;
        }

        if (stone == 0) {
            // As the order doesn't change, calculate blinks for this stone
            long res = blink(1, n - 1);
            cache.put(cacheKey, res);
            return res;
        }

        long nDigits = (long) Math.floor(Math.log10(stone) + 1);
        if (nDigits > 0 && nDigits % 2 == 0) {
            long leftValue = stone / (long) Math.pow(10, (double) nDigits / 2);
            long rightValue = stone % (long) Math.pow(10, (double) nDigits / 2);

            // As the order doesn't change, calculate blinks for each stone in sequence
            long res = blink(leftValue, n - 1) + blink(rightValue, n - 1);
            cache.put(cacheKey, res);
            return res;
        }

        long res = blink(stone * 2024, n - 1);
        cache.put(cacheKey, res);
        return res;
    }
}
