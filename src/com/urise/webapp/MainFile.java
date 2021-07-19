package com.urise.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.stream.IntStream;

public class MainFile {
    public static void main(String[] args) {
        String filePath = ".\\.gitignore";
        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

        File dir = new File(".\\src\\com\\urise\\webapp");
        System.out.println(dir.isDirectory());
        for (String name : Objects.requireNonNull(dir.list())) {
            System.out.println(name);
        }

        try (FileInputStream fileInputStream = new FileInputStream(filePath);) {
            System.out.println(fileInputStream.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        printFileNames(new File(".\\src"), 0);
    }

    private static void printFileNames(File dir, int depth) {
        for (File file : Objects.requireNonNull(dir.listFiles())) {
            if (file.isDirectory()) {
                System.out.println(getTabulation(depth) + "Dir name: " + file.getName());
                depth++;
                printFileNames(file, depth);
            } else {
                System.out.println(getTabulation(depth) + "File name: " + file.getName());
                continue;
            }
            depth--;
        }
    }

    public static StringBuilder getTabulation(int depth) {
        StringBuilder stringBuilder = new StringBuilder();
        IntStream.range(0, depth).forEach(s -> stringBuilder.append("\t"));
        return stringBuilder;
    }
}