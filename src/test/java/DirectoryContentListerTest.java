import com.itacademy.nivel1.DirectoryContentLister;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class DirectoryContentListerTest {

    DirectoryContentLister directoryContentLister = new DirectoryContentLister();

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenDirectoryIsNull() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> directoryContentLister.sortedListFilesByName(null));
    }
    @Test
    public void shouldThrowIllegalArgumentExceptionWhenDirectoryDoesNotExist() {
        File directory = new File("C:\\Users\\Tania\\Desktop\\test1");
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> directoryContentLister.sortedListFilesByName(directory));
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenFileIsNotDirectory() {
        File file = new File("C:\\Users\\Tania\\Desktop\\imTextFile");
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> directoryContentLister.sortedListFilesByName(file));
    }

    @Test
    public void shouldReturnEmptyArrayWhenDirectoryIsEmpty() {
        File directory = new File("C:\\Users\\Tania\\Desktop\\emptyTest");

        File[] sortedList = directoryContentLister.sortedListFilesByName(directory);

        assertThat(sortedList).isEmpty();
    }

    @Test
    public void sortedListFilesByName_shouldSortFilesByName() {
        File directory = new File("C:\\Users\\Tania\\Desktop\\test");

        File[] sortedList = directoryContentLister.sortedListFilesByName(directory);

        assertThat(sortedList)
                .extracting(File::getName)
                .containsExactly(
                        "apple.txt",
                                "Beta.txt",
                                "house.txt",
                                "zebra.txt"
                );
    }
}
