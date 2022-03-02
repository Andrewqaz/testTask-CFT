import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.fail;

public class TestDataProvider {
    private static final String SYMBOLS = "0123456789abcdefghigklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static Random random = new Random();

    public static Integer[] getSortIntArr(int arraySize) {
        Integer[] arr = new Integer[arraySize];
        int startInt = random.nextInt(500);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = startInt + i;
        }
        return arr;
    }

    public static Integer[] getSortIntArr() {
        return getSortIntArr(100);
    }

    public static Integer[] getRndIntArr(int arraySize) {
        Integer[] arr = new Integer[arraySize];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(500);
        }

        return arr;
    }

    public static Integer[] getRndIntArr() {

        return getRndIntArr(100);
    }

    public static File getIntFile(Integer[] intArray) {
        File testIntFile = MergeSortedData.getFile("testIntFile");
        testIntFile.deleteOnExit();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(testIntFile))) {
            for (int i = 0; i < intArray.length; i++) {
                writer.write(intArray[i].toString());
                writer.newLine();
            }
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return testIntFile;
    }

    public static File getRndIntFile() {
        return getIntFile(getRndIntArr());
    }

    public static File getRndIntFile(int numberOfLines) {
        return getIntFile(getRndIntArr(numberOfLines));
    }

    public static File getSortedIntFile() {
        return getIntFile(getSortIntArr());
    }

    public static File getSortedIntFile(int numberOfLines) {
        return getIntFile(getSortIntArr(numberOfLines));
    }

    public static String getRandomString() {

        StringBuilder resultString = new StringBuilder();

        for (int i = 0; i < random.nextInt(20) + 10; i++) {
            int symbolNum = random.nextInt(SYMBOLS.length());
            resultString.append(SYMBOLS, symbolNum, symbolNum + 1);
        }
        return resultString.toString();
    }

    public static String[] getRndStrArr(int arraySize) {
        String[] resultArr = new String[arraySize];
        for (int i = 0; i < resultArr.length; i++) {
            resultArr[i] = getRandomString();
        }
        return resultArr;
    }

    public static String[] getRndStrArr() {
        return getRndStrArr(100);
    }

    public static String[] getSortStrArr(int arraySize) {
        String[] stringArr = getRndStrArr(arraySize);
        Arrays.sort(stringArr);
        return stringArr;


    }

    public static String[] getSortStrArr() {
        return getSortStrArr(100);
    }

    public static File getStrFile(String[] strArray) {
        File testStrFile = MergeSortedData.getFile("testStrFile");
        testStrFile.deleteOnExit();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(testStrFile))) {
            for (int i = 0; i < strArray.length; i++) {
                writer.write(strArray[i]);
                writer.newLine();
            }
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return testStrFile;
    }

    public static File getRndStrFile() {
        return getStrFile(getRndStrArr());
    }

    public static File getRndStrFile(int numberOfLines) {
        return getStrFile(getRndStrArr(numberOfLines));
    }

    public static File getSortStrFile() {
        return getStrFile(getSortStrArr());
    }

    public static File getSortStrFile(int numberOfLines) {
        return getStrFile(getSortStrArr(numberOfLines));
    }
}
