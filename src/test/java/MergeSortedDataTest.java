import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MergeSortedDataTest {

    @Test
    public void getFile() {
        String name = "test";
        File testFile = MergeSortedData.getFile(name);
        testFile.deleteOnExit();
        boolean equalsTargetName = testFile.getName().substring(0, 4).equals(name);
        boolean isFileExist = testFile.exists();
        assertTrue(equalsTargetName);
        assertTrue(isFileExist);
    }

    @Test
    public void mergeSortedIntFiles() {
        File one = TestDataProvider.getRndIntFile();
        File two = TestDataProvider.getRndIntFile();
        File out = MergeSortedData.getFile("testOut");
        out.deleteOnExit();

        MergeSortedData.mergeSortedIntFiles(one, two, out);

        Integer[] arrayOne = TestUtil.fromFileToIntArray(one);
        Integer[] arrayTwo = TestUtil.fromFileToIntArray(two);
        Integer[] arrayOut = TestUtil.fromFileToIntArray(out);

        boolean containOne = TestUtil.containsArray(arrayOut, arrayOne);
        boolean containTwo = TestUtil.containsArray(arrayOut, arrayTwo);

        assertTrue(containOne);
        assertTrue(containTwo);
        assertEquals(arrayOut.length, arrayOne.length + arrayTwo.length);
    }

    @Test
    public void mergeSortedStringFiles() {
        File one = TestDataProvider.getRndStrFile();
        File two = TestDataProvider.getRndStrFile();
        File out = MergeSortedData.getFile("testOut");
        out.deleteOnExit();

        MergeSortedData.mergeSortedStringFiles(one, two, out);

        String[] arrayOne = TestUtil.fromFileToStrArray(one);
        String[] arrayTwo = TestUtil.fromFileToStrArray(two);
        String[] arrayOut = TestUtil.fromFileToStrArray(out);

        boolean containOne = TestUtil.containsArray(arrayOut, arrayOne);
        boolean containTwo = TestUtil.containsArray(arrayOut, arrayTwo);

        assertTrue(containOne);
        assertTrue(containTwo);
        assertEquals(arrayOut.length, arrayOne.length + arrayTwo.length);
    }

    @Test
    public void mergeFilesList() {
        List<File> files = new LinkedList<>();
        files.add(TestDataProvider.getRndStrFile());
        files.add(TestDataProvider.getRndStrFile());
        files.add(TestDataProvider.getRndStrFile());

        File out = MergeSortedData.mergeFilesList(files, Type.STR);

        boolean containsAllFiles = true;
        int lengthAllFiles = 0;

        List<String> stringsOutList = Arrays.asList(TestUtil.fromFileToStrArray(out));
        int lengthOutFile = stringsOutList.size();


        for (File f : files) {
            String[] stringsF = TestUtil.fromFileToStrArray(f);
            for (String s : stringsF) {
                if (!stringsOutList.contains(s)) {
                    containsAllFiles = false;
                    break;
                }
            }
            if (!containsAllFiles) {
                break;
            }
            lengthAllFiles += stringsF.length;
        }
        assertTrue(containsAllFiles);
        assertEquals(lengthAllFiles, lengthOutFile);

    }

}