package org.aoc;

import org.aoc.utils.FileUtils;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Page implements Comparable<Page> {
    private final int pageNumber;
    private final List<Integer> smallerNumbers;
    private final List<Integer> largerNumbers;

    Page(int pageNumber) {
        this.pageNumber = pageNumber;
        smallerNumbers = new ArrayList<>();
        largerNumbers = new ArrayList<>();
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void addSmallerNumber(int number) {
        smallerNumbers.add(number);
    }

    public void addLargerNumber(int number) {
        largerNumbers.add(number);
    }

    @Override
    public int compareTo(Page o) {
        if (smallerNumbers.contains(o.pageNumber)) {
            return 1;
        } else if (largerNumbers.contains(o.pageNumber)) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public int hashCode() {
        return this.pageNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == this.getClass()) {
            return ((Page) obj).pageNumber == this.pageNumber;
        } else {
            return false;
        }
    }
}

public class Day5 {
    public static void main(String[] args) throws IOException {
        String input = FileUtils.openInput("Day5.txt");

        System.out.println("Day 5 - Part 1: " + part1(input));
        System.out.println("Day 5 - Part 2: " + part2(input));
    }

    public static int part1(String input) {
        Pattern ruleRegex = Pattern.compile("(\\d+)\\|(\\d+)");
        Map<Integer, Page> pages = new HashMap<>();
        List<List<Integer>> productionRuns = new ArrayList<>();

        input.lines().forEach(line -> {
            Matcher m = ruleRegex.matcher(line);

            if (m.matches()) {
                int lowerPageNumber = Integer.parseInt(m.group(1));
                int higherPageNumber = Integer.parseInt(m.group(2));

                if (pages.containsKey(lowerPageNumber)) {
                    pages.get(lowerPageNumber).addLargerNumber(higherPageNumber);
                } else {
                    Page lowerPage = new Page(lowerPageNumber);
                    lowerPage.addLargerNumber(higherPageNumber);

                    pages.put(lowerPageNumber, lowerPage);
                }

                if (pages.containsKey(higherPageNumber)) {
                    pages.get(higherPageNumber).addSmallerNumber(lowerPageNumber);
                } else {
                    Page higherPage = new Page(higherPageNumber);
                    higherPage.addSmallerNumber(lowerPageNumber);

                    pages.put(higherPageNumber, higherPage);
                }
            }
            else if (!line.isEmpty()) {
                productionRuns.add(Arrays.stream(line.split(",")).map(Integer::parseInt).toList());
            }
        });

        int total = 0;

        for (List<Integer> run : productionRuns) {
            List<Integer> sorted = run.stream().map(pages::get).sorted().map(Page::getPageNumber).toList();

            if (sorted.equals(run)) {
                int middleValue = run.get((run.size() / 2));

                total += middleValue;
            }
        }

        return total;
    }

    public static int part2(String input) {
        Pattern ruleRegex = Pattern.compile("(\\d+)\\|(\\d+)");
        Map<Integer, Page> pages = new HashMap<>();
        List<List<Integer>> productionRuns = new ArrayList<>();

        input.lines().forEach(line -> {
            Matcher m = ruleRegex.matcher(line);

            if (m.matches()) {
                int lowerPageNumber = Integer.parseInt(m.group(1));
                int higherPageNumber = Integer.parseInt(m.group(2));

                if (pages.containsKey(lowerPageNumber)) {
                    pages.get(lowerPageNumber).addLargerNumber(higherPageNumber);
                } else {
                    Page lowerPage = new Page(lowerPageNumber);
                    lowerPage.addLargerNumber(higherPageNumber);

                    pages.put(lowerPageNumber, lowerPage);
                }

                if (pages.containsKey(higherPageNumber)) {
                    pages.get(higherPageNumber).addSmallerNumber(lowerPageNumber);
                } else {
                    Page higherPage = new Page(higherPageNumber);
                    higherPage.addSmallerNumber(lowerPageNumber);

                    pages.put(higherPageNumber, higherPage);
                }
            }
            else if (!line.isEmpty()) {
                productionRuns.add(Arrays.stream(line.split(",")).map(Integer::parseInt).toList());
            }
        });

        int total = 0;

        for (List<Integer> run : productionRuns) {
            List<Integer> sorted = run.stream().map(pages::get).sorted().map(Page::getPageNumber).toList();

            if (!sorted.equals(run)) {
                int middleValue = sorted.get((run.size() / 2));

                total += middleValue;
            }
        }

        return total;
    }
}
