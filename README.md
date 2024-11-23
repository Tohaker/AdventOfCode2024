# Advent of Code 2024

Solutions to the puzzles from the [Advent of Code 2024](https://adventofcode.com/2024).

## Getting started

This project uses Java, so download and install a distribution of OpenJDK 23 or higher. 
This can be acquired from [Oracle](https://jdk.java.net/23/) or through an IDE such as [IntelliJ IDEA](https://www.jetbrains.com/idea/download).

Once loaded into your favourite IDE, run the build command;

```shell
./gradlew build
```

## Testing

Tests can be run with;

```shell
./gradlew test
```

## Running a specific day's solution

There's no single point of entry for the project, so each day's solution must be run with the following command;

```shell
./gradlew -PmainClass='org.aoc.<classname>' run
```

Where `classname` is the name of the solution to run. For example, to run Day 1, it would be;

```shell
./gradlew -PmainClass='org.aoc.Day1' run
```