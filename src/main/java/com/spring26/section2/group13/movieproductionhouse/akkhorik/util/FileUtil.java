package com.spring26.section2.group13.movieproductionhouse.akkhorik.util;


import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileUtil {
    private static final String DATA_DIR = "data/";

    static {
        // Ensure data directory exists
        try {
            Files.createDirectories(Paths.get(DATA_DIR));
        } catch (IOException e) {
            System.err.println("Could not create data directory: " + e.getMessage());
        }
    }

    public static List<String> readLines(String filename) {
        try {
            Path filePath = Paths.get(DATA_DIR + filename);
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
            return Files.readAllLines(filePath);
        } catch (IOException e) {
            System.err.println("Error reading file: " + filename + " - " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void writeLines(String filename, List<String> lines) {
        try {
            Files.write(Paths.get(DATA_DIR + filename), lines);
        } catch (IOException e) {
            System.err.println("Error writing file: " + filename + " - " + e.getMessage());
        }
    }

    public static void appendLine(String filename, String line) {
        try {
            Files.write(Paths.get(DATA_DIR + filename),
                    Collections.singletonList(line),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Error appending to file: " + filename + " - " + e.getMessage());
        }
    }

    public static boolean fileExists(String filename) {
        return Files.exists(Paths.get(DATA_DIR + filename));
    }
}
