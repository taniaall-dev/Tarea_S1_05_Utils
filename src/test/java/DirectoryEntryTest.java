import com.itacademy.nivel1.DirectoryEntry;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.attribute.FileTime;

import static org.assertj.core.api.Assertions.*;


public class DirectoryEntryTest {

    @Test
    public void should_throwNullPointerException_whenPathIsNull() {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> new DirectoryEntry(null, false, FileTime.fromMillis(1000)));
    }

    @Test
    public void should_throwNullPointerException_whenLastModifiedIsNull() {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> new DirectoryEntry( Path.of("resources/test.txt"), false, null));
    }


    @Test
    public void should_getPath() {
        Path expectedPath = Path.of("resources/test.txt");
        boolean directory = false;
        FileTime expectedLastModified = FileTime.fromMillis(1000);

        DirectoryEntry directoryEntry = new DirectoryEntry(expectedPath, directory, expectedLastModified);

        Path actualPath = directoryEntry.getPath();

        assertThat(actualPath).isEqualTo(expectedPath);
    }

    @Test
    public void should_returnTrue_whenEntryIsDirectory() {
        Path expectedPath = Path.of("resources/documents");
        boolean directory = true;
        FileTime expectedLastModified = FileTime.fromMillis(1000);

        DirectoryEntry directoryEntry = new DirectoryEntry(expectedPath, directory, expectedLastModified);

        boolean actualDirectory = directoryEntry.isDirectory();

        assertThat(actualDirectory).isTrue();
    }

    @Test
    public void should_returnFalse_whenEntryIsNotDirectory() {
        Path expectedPath = Path.of("resources/test.txt");
        boolean directory = false;
        FileTime expectedLastModified = FileTime.fromMillis(1000);

        DirectoryEntry directoryEntry = new DirectoryEntry(expectedPath, directory, expectedLastModified);

        boolean actualDirectory = directoryEntry.isDirectory();

        assertThat(actualDirectory).isFalse();
    }

    @Test
    public void should_getLastModified() {
        Path expectedPath = Path.of("resources/test.txt");
        boolean directory = false;
        FileTime expectedLastModified = FileTime.fromMillis(1000);

        DirectoryEntry directoryEntry = new DirectoryEntry(expectedPath, directory, expectedLastModified);

        FileTime actualLastModified = directoryEntry.getLastModified();

        assertThat(actualLastModified).isEqualTo(expectedLastModified);
    }
}
