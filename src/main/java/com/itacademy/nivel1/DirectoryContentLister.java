package com.itacademy.nivel1;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class DirectoryContentLister {

    public File[] sortedListFilesByName(File directory) {
        validateDirectory(directory);

        File[] files = getContentOfDirectory(directory);

        Arrays.sort(files, Comparator.comparing(File::getName, String.CASE_INSENSITIVE_ORDER));

        return files;
    }

    private static void validateDirectory(File directory) {
        if (directory == null) {
            throw new IllegalArgumentException("Error: File is null");
        }

        if (!directory.exists()) {
            throw new IllegalArgumentException("Error: File doesn't exist");
        }
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("Error: File is not a directory");
        }
    }

    private static File[] getContentOfDirectory(File directory) {
        File[] files = directory.listFiles();

        if (files == null) {
            throw new IllegalStateException("Error: The method listFiles can't get your content");
        }
        return files;
    }
}
