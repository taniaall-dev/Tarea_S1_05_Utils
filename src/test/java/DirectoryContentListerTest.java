import com.itacademy.nivel1.DirectoryContentLister;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.*;

public class DirectoryContentListerTest {

    private final DirectoryContentLister directoryContentLister = new DirectoryContentLister();

    @TempDir
    private Path tempDirectory;


    @Test
    public void shouldThrowIllegalArgumentExceptionWhenDirectoryIsNull() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> directoryContentLister.sortedListFilesByName(null));
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenDirectoryDoesNotExist() {
        Path directory = tempDirectory.resolve("missing");

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> directoryContentLister.sortedListFilesByName(directory));
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenFileIsNotDirectory() throws IOException {
        Path file = Files.createFile(tempDirectory.resolve("tempFile.text"));
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> directoryContentLister.sortedListFilesByName(file));
    }

    @Test
    public void shouldReturnEmptyArrayWhenDirectoryIsEmpty() throws IOException {
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
}
