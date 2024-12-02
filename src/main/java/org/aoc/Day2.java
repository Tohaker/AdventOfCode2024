package org.aoc;

import org.aoc.utils.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Day2 {
    public static void main(String[] args) throws IOException {
        String input = FileUtils.openInput("Day2.txt");

        System.out.println("Day 2 - Part 1: " + part1(input));
        System.out.println("Day 2 - Part 2: " + part2(input));
    }

   static List<List<Integer>> parseInput(String input) {
       List<List<Integer>> reports = new ArrayList<>();

       input.lines().forEach(report -> {
          reports.add(Arrays.stream(report.split(" ")).map(Integer::valueOf).toList());
       });

       return reports;
   }

   static boolean isSafe(List<Integer> report) {
       // Check if report is identical when sorted

       List<Integer> cloneReport = new ArrayList<>(report);
       Collections.sort(cloneReport);

       if (!report.equals(cloneReport) && !report.equals(cloneReport.reversed())) {
           return false;
       }

       for (int i = 0; i < report.size() - 1; i++) {
           int currLevel = report.get(i);
           int nextLevel = report.get(i + 1);
           int diff = Math.abs(currLevel - nextLevel);

           if (diff < 1 || diff > 3 ) {
               return false;
           }
       }

       return true;
   }

   public static int part1(String input) {
       List<List<Integer>> reports = parseInput(input);

       AtomicInteger safeReports = new AtomicInteger();

       reports.forEach(report -> {
           if (isSafe(report)) safeReports.getAndIncrement();
       });

       return safeReports.get();
   }

   public static int part2(String input) {
       List<List<Integer>> reports = parseInput(input);

       AtomicInteger safeReports = new AtomicInteger();

       reports.forEach(report -> {
           if (isSafe(report)) {
               safeReports.getAndIncrement();
           } else {
               // Create permutations of reports with 1 level missing

               List<List<Integer>> permutations = new ArrayList<>();

               for (int i = 0; i < report.size(); i++) {
                   List<Integer> clonedReport = new ArrayList<>(report);
                   clonedReport.remove(i);
                   permutations.add(clonedReport);
               }

               if (permutations.stream().anyMatch(Day2::isSafe)) {
                   safeReports.getAndIncrement();
               }
           }
       });

       return safeReports.get();
   }
}
