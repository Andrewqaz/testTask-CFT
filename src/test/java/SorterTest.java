import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class SorterTest {

    @Test
    public void sortIntArray() {
        Integer[] beforeSort = TestDataProvider.getRndIntArr();
        Integer[] pastSort = Sorter.sortIntArray(beforeSort);

        assertTrue(TestUtil.isIntArraySorted(pastSort));
    }

    @Test
    public void sortStringArray() {
        String[] beforeSort = TestDataProvider.getRndStrArr();
        String[] pastSort = Sorter.sortStringArray(beforeSort);

        assertTrue(TestUtil.isStringArraySorted(pastSort));
    }

    @Test
    public void mergeIntArray() {
        Integer[] array1 = TestDataProvider.getRndIntArr();
        Integer[] array2 = TestDataProvider.getRndIntArr();
        Integer[] resultArray = new Integer[array1.length + array2.length];
        Sorter.mergeIntArray(resultArray, array1, array2);

        boolean containOne = TestUtil.containsArray(resultArray, array1);
        boolean containTwo = TestUtil.containsArray(resultArray, array2);

        assertTrue(containOne);
        assertTrue(containTwo);
        assertEquals(resultArray.length, array1.length + array2.length);
    }

    @Test
    public void mergeStringArray() {
        String[] array1 = TestDataProvider.getRndStrArr();
        String[] array2 = TestDataProvider.getRndStrArr();
        String[] resultArray = new String[array1.length + array2.length];
        Sorter.mergeStringArray(resultArray, array1, array2);

        boolean containOne = TestUtil.containsArray(resultArray, array1);
        boolean containTwo = TestUtil.containsArray(resultArray, array2);

        assertTrue(containOne);
        assertTrue(containTwo);
        assertEquals(resultArray.length, array1.length + array2.length);
    }

    @Test
    public void isFileIntSorted() {
        File rndFile = TestDataProvider.getRndIntFile();
        File srtFile = TestDataProvider.getSortedIntFile();

        assertTrue(TestUtil.isStrFileSorted(srtFile));
        assertFalse(TestUtil.isStrFileSorted(rndFile));
    }

    @Test
    public void isFileStringSorted() {
        File rndStrFile = TestDataProvider.getRndStrFile();
        File srtStrFile = TestDataProvider.getSortStrFile();

        assertTrue(TestUtil.isStrFileSorted(srtStrFile));
        assertFalse(TestUtil.isStrFileSorted(rndStrFile));
    }

}