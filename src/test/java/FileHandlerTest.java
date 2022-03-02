import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class FileHandlerTest {

    FileHandler fileHandler = new FileHandler();

    @Test
    public void sortIntFile() {
        File rndIntFile100 = TestDataProvider.getRndIntFile();
        File rndIntFile5000 = TestDataProvider.getRndIntFile(5000);
        File file100 = fileHandler.sortIntFile(rndIntFile100);
        File file5000 = fileHandler.sortIntFile(rndIntFile5000);

        Integer[] intsFrom100 = TestUtil.fromFileToIntArray(file100);
        Integer[] intsFrom5000 = TestUtil.fromFileToIntArray(file5000);

        assertTrue(TestUtil.isIntArraySorted(intsFrom100));
        assertTrue(TestUtil.isIntArraySorted(intsFrom5000));

        assertEquals(100, intsFrom100.length);
        assertEquals(5000, intsFrom5000.length);
    }

    @Test
    public void sortStringFile() {
    }

    @Test
    public void sortFile() {
    }

    @Test
    public void sortFileList() {
    }

    @Test
    public void reversFile() {
    }
}