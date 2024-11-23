package org.aoc.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class FileUtils {
    public static String openInput(String name) throws IOException {
        InputStream stream = Objects.requireNonNull(ClassLoader.getSystemResourceAsStream(name));

        return new String(stream.readAllBytes(), StandardCharsets.UTF_8);
    }
}
