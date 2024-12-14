package org.aoc;


import org.aoc.utils.FileUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;

class Robot {
    Point position;
    Point velocity;
    Point gridSize;

    Robot(String input, Point gridSize) {
        Pattern p = Pattern.compile("(\\d+),(\\d+) v=(-?\\d+),(-?\\d+)");
        Matcher m = p.matcher(input);

        while (m.find()) {
            this.position = new Point(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)));
            this.velocity = new Point(Integer.parseInt(m.group(3)), Integer.parseInt(m.group(4)));
        }

        this.gridSize = gridSize;
    }

    public void move() {
        Point nextPosition = new Point(position);
        nextPosition.translate(velocity.x, velocity.y);

        // Would move off the grid?
        if (nextPosition.x < 0) {
            nextPosition.move(gridSize.x + nextPosition.x, nextPosition.y);
        } else if (nextPosition.x >= gridSize.x) {
            nextPosition.move(nextPosition.x - gridSize.x, nextPosition.y);
        }

        if (nextPosition.y < 0) {
            nextPosition.move(nextPosition.x, gridSize.y + nextPosition.y);
        } else if (nextPosition.y >= gridSize.y) {
            nextPosition.move(nextPosition.x, nextPosition.y - gridSize.y);
        }

        position.setLocation(nextPosition);
    }
}

public class Day14 {
    public static void main(String[] args) throws IOException {
        String input = FileUtils.openInput("Day14.txt");

        System.out.println("Day 14 - Part 1: " + part1(input, 101, 103));
        System.out.println("Day 14 - Part 2: " + part2(input, 101, 103));
    }

    public static int part1(String input, int maxX, int maxY) {
        Point gridSize = new Point(maxX, maxY);
        List<Robot> robots = input.lines().map(line -> new Robot(line, gridSize)).toList();

        for (int i = 0; i < 100; i ++) {
            robots.forEach(Robot::move);
        }

        Point middle = new Point((maxX - 1)/ 2, (maxY - 1) / 2);

        int q1 = 0, q2 = 0, q3 = 0, q4 = 0;

        for (Robot r: robots) {
            if (r.position.x < middle.x && r.position.y < middle.y) {
                q1++;
            } else if (r.position.x > middle.x && r.position.y < middle.y) {
                q2++;
            } else if (r.position.x < middle.x && r.position.y > middle.y) {
                q3++;
            } else if (r.position.x > middle.x && r.position.y > middle.y) {
                q4++;
            }
        }

        return q1 * q2 * q3 * q4;
    }

    public static int part2(String input, int maxX, int maxY) throws IOException {
        Point gridSize = new Point(maxX, maxY);
        List<Robot> robots = input.lines().map(line -> new Robot(line, gridSize)).toList();

        String dir = "day14_part2";
        File directory = new File(dir);
        if (!directory.exists())
            directory.mkdirs();

        for (int i = 0; i < 10000; i++) {
            robots.forEach(Robot::move);


            BufferedImage image = new BufferedImage(maxX, maxY, BufferedImage.TYPE_INT_ARGB);

            robots.forEach(r -> {
                image.setRGB(r.position.x, r.position.y, 0xffffffff);
            });

            File output = new File(directory, String.format("%d.png", i + 1));
            ImageIO.write(image, "png", output);
        }

        return 7916; // Found by visual inspection of the files
    }
}
