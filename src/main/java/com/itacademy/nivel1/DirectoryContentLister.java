package com.itacademy.nivel1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class DirectoryContentLister {

    public Path[] sortedListFilesByName(Path directory) throws IOException{
        validateDirectory(directory);

        Path[] files = getContentOfDirectory(directory);

        Arrays.sort(files, Comparator.comparing(
                path ->path.getFileName().toString(),
                String.CASE_INSENSITIVE_ORDER
        ));

        return files;
    }

    public List<DirectoryEntry> listDirectoryTree(Path directory) throws IOException {
        List<DirectoryEntry> directoryEntries = new ArrayList<>();
        listDirectoryTreeRecursively(directory, directoryEntries);

        return directoryEntries;
    }

    private void listDirectoryTreeRecursively(Path directory, List<DirectoryEntry> entries) throws IOException {
        Path[] sortedPaths = sortedListFilesByName(directory);

        for (Path path : sortedPaths) {
            BasicFileAttributes fileAttributes = Files.readAttributes(path, BasicFileAttributes.class);

            boolean isDirectory = fileAttributes.isDirectory();
            FileTime lastModified = fileAttributes.lastModifiedTime();

            DirectoryEntry directoryEntry = new DirectoryEntry(path, isDirectory, lastModified);

            entries.addLast(directoryEntry);

            if (isDirectory) {
                listDirectoryTreeRecursively(path, entries);
            }
        }
    }

    private static void validateDirectory(Path directory) {
        if (directory == null) {
            throw new IllegalArgumentException("Error: File is null");
        }

        if (!Files.exists(directory)) {
            throw new IllegalArgumentException("Error: File doesn't exist");
        }

        if (!Files.isDirectory(directory)) {
            throw new IllegalArgumentException("Error: File is not a directory");
        }
    }

    private static Path[] getContentOfDirectory(Path directory) throws IOException {

        try (Stream<Path> paths = Files.list(directory)) {

            return paths.toArray(Path[]::new);
        }
    }
}
