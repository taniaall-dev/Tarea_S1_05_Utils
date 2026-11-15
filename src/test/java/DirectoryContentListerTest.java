import com.itacademy.nivel1.DirectoryContentLister;
import com.itacademy.nivel1.DirectoryEntry;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class DirectoryContentListerTest {

    private final DirectoryContentLister directoryContentLister = new DirectoryContentLister();

    @TempDir
    private Path tempDirectory;

    @Test
    public void sortedListFilesByName_shouldThrowIllegalArgumentExceptionWhenDirectoryIsNull() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> directoryContentLister.sortedListFilesByName(null));
    }

    @Test
    public void sortedListFilesByName_shouldThrowIllegalArgumentExceptionWhenDirectoryDoesNotExist() {
        Path directory = tempDirectory.resolve("missing");

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> directoryContentLister.sortedListFilesByName(directory));
    }

    @Test
    public void sortedListFilesByName_shouldThrowIllegalArgumentExceptionWhenFileIsNotDirectory() throws IOException {
        Path file = Files.createFile(tempDirectory.resolve("tempFile.text"));
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> directoryContentLister.sortedListFilesByName(file));
    }

    @Test
    public void sortedListFilesByName_shouldReturnEmptyArrayWhenDirectoryIsEmpty() throws IOException {
        Path[] sortedList = directoryContentLister.sortedListFilesByName(tempDirectory);

        assertThat(sortedList).isEmpty();
    }

    @Test
    public void sortedListFilesByName_shouldSortFilesByName() throws IOException {
        Path directory = createDirectoryWithContent();

        Path[] sortedList = directoryContentLister.sortedListFilesByName(directory);

        assertThat(sortedList)
                .extracting(path -> path.getFileName().toString())
                .containsExactly(
                        "apple.txt",
                        "Beta.txt",
                        "documents",
                        "house.txt",
                        "zebra.txt"
                );
    }

    private Path createDirectoryWithContent() throws IOException {
        Files.createFile(tempDirectory.resolve("apple.txt"));
        Files.createFile(tempDirectory.resolve("Beta.txt"));
        Files.createFile(tempDirectory.resolve("house.txt"));
        Files.createFile(tempDirectory.resolve("zebra.txt"));

        Files.createDirectory(tempDirectory.resolve("documents"));

        return tempDirectory;
    }

    private Path createDirectoryWithNestedContent() throws IOException {
        Files.createFile(tempDirectory.resolve("apple.txt"));
        Files.createFile(tempDirectory.resolve("Beta.txt"));
        Files.createFile(tempDirectory.resolve("house.txt"));
        Files.createFile(tempDirectory.resolve("zebra.txt"));

        Path subdirectory = Files.createDirectory(tempDirectory.resolve("documents"));
        Files.createFile(subdirectory.resolve("docText1.txt"));

        Path secondSubdirectory = Files.createDirectory(subdirectory.resolve("subdocuments"));
        Files.createFile(secondSubdirectory.resolve("docText2.txt"));

        return tempDirectory;
    }

    @Test
    public void listDirectoryTree_shouldReturnEmptyListWhenDirectoryIsEmpty() throws IOException {
        List<DirectoryEntry> entries = directoryContentLister.listDirectoryTree(tempDirectory);

        assertThat(entries).isEmpty();
    }

    @Test
    public void listDirectoryTree_shouldIdentifyDirectoriesAndFiles() throws IOException {
        Path directory = createDirectoryWithContent();

        List<DirectoryEntry> entries = directoryContentLister.listDirectoryTree(directory);

        assertThat(entries)
                .filteredOn(entry -> entry.getPath().equals(directory.resolve("documents")))
                .extracting(DirectoryEntry::isDirectory)
                .containsExactly(true);
        assertThat(entries)
                .filteredOn(entry -> entry.getPath().equals(directory.resolve("apple.txt")))
                .extracting(DirectoryEntry::isDirectory)
                .containsExactly(false);
    }

    @Test
    public void listDirectoryTree_shouldReturnLastModifiedTime() throws IOException {
        Path directory = createDirectoryWithContent();

        Path file = directory.resolve("apple.txt");
        Path subdirectory = directory.resolve("documents");

        FileTime expectedFileLastModified = Files.getLastModifiedTime(file);
        FileTime expectedDirectoryLastModified = Files.getLastModifiedTime(subdirectory);

        List<DirectoryEntry> entries = directoryContentLister.listDirectoryTree(directory);

        assertThat(entries)
                .filteredOn(entry -> entry.getPath().equals(file))
                .extracting(DirectoryEntry::getLastModified)
                .containsExactly(expectedFileLastModified);

        assertThat(entries)
                .filteredOn(entry -> entry.getPath().equals(subdirectory))
                .extracting(DirectoryEntry::getLastModified)
                .containsExactly(expectedDirectoryLastModified);
    }

    @Test
    public void listDirectoryTree_shouldReturnRootDirectoryContent() throws IOException {
        Path directory = createDirectoryWithContent();

        List<DirectoryEntry> entries = directoryContentLister.listDirectoryTree(directory);

        assertThat(entries)
                .extracting(DirectoryEntry::getPath)
                .containsExactlyInAnyOrder(
                        directory.resolve("apple.txt"),
                        directory.resolve("Beta.txt"),
                        directory.resolve("documents"),
                        directory.resolve("house.txt"),
                        directory.resolve("zebra.txt")
                );
    }

    @Test
    public void listDirectoryTree_shouldRecursivelyIncludeSubdirectoryContent() throws IOException {
        Path directory = createDirectoryWithNestedContent();

        List<DirectoryEntry> entries = directoryContentLister.listDirectoryTree(directory);

        assertThat(entries)
                .extracting(DirectoryEntry::getPath)
                .contains(
                        directory.resolve("documents"),
                        directory.resolve("documents").resolve("docText1.txt"),
                        directory.resolve("documents").resolve("subdocuments"),
                        directory.resolve("documents").resolve("subdocuments").resolve("docText2.txt")
                );
    }

    @Test
    public void listDirectoryTree_shouldReturnEntriesInAlphabeticalOrderWithinEachDirectory() throws IOException {
        Path directory = createDirectoryWithNestedContent();

        List<DirectoryEntry> entries = directoryContentLister.listDirectoryTree(directory);

        assertThat(entries)
                .extracting(DirectoryEntry::getPath)
                .containsExactly(
                        directory.resolve("apple.txt"),
                        directory.resolve("Beta.txt"),
                        directory.resolve("documents"),
                        directory.resolve("documents").resolve("docText1.txt"),
                        directory.resolve("documents").resolve("subdocuments"),
                        directory.resolve("documents").resolve("subdocuments").resolve("docText2.txt"),
                        directory.resolve("house.txt"),
                        directory.resolve("zebra.txt")
                );
    }
}
