package com.itacademy.nivel1;

import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.util.Objects;

public class DirectoryEntry {
    private final Path path;
    private final boolean directory;
    private final FileTime lastModified;

    public DirectoryEntry(Path path, boolean directory, FileTime lastModified) {
        this.path = Objects.requireNonNull(path);
        this.directory = directory;
        this.lastModified = Objects.requireNonNull(lastModified);
    }

    public Path getPath() {
        return path;
    }

    public boolean isDirectory() {
        return directory;
    }

    public FileTime getLastModified() {
        return lastModified;
    }
}
