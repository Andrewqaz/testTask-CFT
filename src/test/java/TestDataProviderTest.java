import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestDataProviderTest {



    @Test
    public void getSortIntArr() {
        Integer[] intArr = TestDataProvider.getSortIntArr();
        assertTrue(TestUtil.isIntArraySorted(intArr));
    }

    @Test
    public void getRndIntArr() {
        Integer[] intArr = TestDataProvider.getRndIntArr();
        assertFalse(TestUtil.isIntArraySorted(intArr));
    }

    @Test
    public void getIntFile() {
        File intFile = TestDataProvider.getIntFile(TestDataProvider.getSortIntArr());
        assertTrue(intFile.exists());
    }

    @Test
    public void getRndIntFile() {
        File rndIntFile = TestDataProvider.getRndIntFile();
        assertFalse(TestUtil.isIntFileSorted(rndIntFile));

    }

    @Test
    public void getSortedIntFile() {
        File sortIntFile = TestDataProvider.getSortedIntFile();
        assertTrue(TestUtil.isIntFileSorted(sortIntFile));
    }

    @Test
    public void getRandomString() {
        String rndString = TestDataProvider.getRandomString();
        boolean sorted = true;
        char[] chars = rndString.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (i + 1 == chars.length) {
                break;
            }
            Character one = chars[i];
            Character two = chars[i + 1];
            if (one.compareTo(two) > 0) {
                sorted = false;
                break;
            }
        }
        assertFalse(sorted);
    }

    @Test
    public void getRndStrArr() {
        String[] rndStrArr = TestDataProvider.getRndStrArr();
        assertFalse(TestUtil.isStringArraySorted(rndStrArr));
    }

    @Test
    public void getSortStrArr() {
        String[] sortStrArr = TestDataProvider.getSortStrArr();
        assertTrue(TestUtil.isStringArraySorted(sortStrArr));
    }

    @Test
    public void getStrFile() {
        File strFile = TestDataProvider.getStrFile(TestDataProvider.getSortStrArr());
        assertTrue(strFile.exists());
    }

    @Test
    public void getRndStrFile() {
        File rndStrFile = TestDataProvider.getRndStrFile();
        assertFalse(TestUtil.isStrFileSorted(rndStrFile));
    }

    @Test
    public void getSortStrFile() {
        File sortStrFile = TestDataProvider.getSortStrFile();
        assertTrue(TestUtil.isStrFileSorted(sortStrFile));
    }

    public static void main(String[] args) {
        TestDataProvider.getRndStrFile(1500);
        TestDataProvider.getRndStrFile(1500);
        TestDataProvider.getRndStrFile(1500);
        TestDataProvider.getRndIntFile(1500);
        TestDataProvider.getRndIntFile(1500);
        TestDataProvider.getRndIntFile(1500);
        int i =1;
    }
}