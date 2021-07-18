package com.urise.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class MainFile {
    private static final StringBuilder stringBuilder = new StringBuilder("\t");
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

        System.out.println(getFileNames(new File(".\\src"), stringBuilder));
    }

    private static StringBuilder getFileNames(File dir, StringBuilder stringBuilder) {
        for (File file : Objects.requireNonNull(dir.listFiles())) {
            if (file.isDirectory()) {
                stringBuilder.append("Dir name: ").append(file.getName()).append("\n").append("\t");
                getFileNames(file, stringBuilder);
            } else {
                stringBuilder.append("File name: ").append(file.getName()).append("\n").append("\t");
            }
            stringBuilder.delete(0, 3);
        }
        return stringBuilder;
    }
}