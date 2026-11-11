import com.itacademy.nivel1.DirectoryContentLister;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DirectoryContentListerTest {

    DirectoryContentLister directoryContentLister = new DirectoryContentLister();

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenFileIsNull() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> directoryContentLister.sortedListFilesByName(null));
    }
    @Test
    public void shouldThrowIllegalArgumentExceptionWhenFileDoesNotExist() {
        File file = new File("C:\\Users\\Tania\\Desktop\\prueba1");
        assertThatExceptionOfType(IllegalArgumentException.class).
                isThrownBy(() -> directoryContentLister.sortedListFilesByName(file));
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenFileIsNotDirectory() {
        File file = new File("C:\\Users\\Tania\\Desktop\\imTextFile");
        assertThatExceptionOfType(IllegalArgumentException.class).
                isThrownBy(() -> directoryContentLister.sortedListFilesByName(file));
    }

    @Test
    public void shouldReturnEmptyArrayWhenDirectoryIsEmpty() {
        File file = new File("C:\\Users\\Tania\\Desktop\\pruebaVacia");
        File[] sortedList = directoryContentLister.sortedListFilesByName(file);
        assertThat(sortedList.length).isEqualTo(0);
    }

    @Test
    public void sortedListFilesByName_shouldSortFilesByName() {
        File file = new File("C:\\Users\\Tania\\Desktop\\prueba");

        File[] sortedList = directoryContentLister.sortedListFilesByName(file);
        assertThat(sortedList[0].getName()).isEqualTo("Archivo.txt");
        assertThat(sortedList[1].getName()).isEqualTo("Beta.txt");
        assertThat(sortedList[2].getName()).isEqualTo("casa.txt");
        assertThat(sortedList[3].getName()).isEqualTo("zebra.txt");
    }
}
