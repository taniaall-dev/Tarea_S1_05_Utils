package com.itacademy.nivel1;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class DirectoryContentLister {

    public File[] sortedListFilesByName (File file) {

        validateDirectory(file);
        File[] files = getContentOfDirectory(file);
        Arrays.sort(files, Comparator.comparing(File::getName,String.CASE_INSENSITIVE_ORDER));

        return files;
    }

    private static void validateDirectory(File file) {
        if(file == null) {
            throw new IllegalArgumentException("Error: File is null");
        }

        if(!file.exists()) {
            throw new IllegalArgumentException("Error: File doesn't exist");
        }
        if(!file.isDirectory()) {
            throw new IllegalArgumentException("Error: File is not a directory");
        }
    }

    private static File[] getContentOfDirectory(File file) {

        File[] files = file.listFiles();
        if(files == null) {
            throw new IllegalStateException("Error: The method listFiles can't get your content");
        }
        return files;
    }

}
