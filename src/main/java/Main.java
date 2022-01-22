import org.apache.commons.io.input.ReversedLinesFileReader;

import java.io.*;
import java.util.*;

public class Main {
    private static final String PREF_OUT_FILE_NAME = "out";
    private static final String SUF_OUT_FILE_NAME = ".txt";
    private static final String UNDERSCORE = "_";

    public static void main(String[] args) throws Exception {
//
        File one = new File("one.txt");
        File two = new File("two.txt");

        FileHandler handler = new FileHandler();
        handler.mergeIntFiles(one, two);

//        BufferedWriter oneWriter = new BufferedWriter(new FileWriter(one));
//        BufferedWriter twoWriter = new BufferedWriter(new FileWriter(two));
//        Random random = new Random();
//
//        for (int i = 0; i < 10000; i++) {
//            oneWriter.write(random.nextInt(1000)+1000+"");
//            oneWriter.newLine();
//            twoWriter.write(random.nextInt(10000)+10000+"");
//            twoWriter.newLine();
//        }

//        BufferedReader reader = new BufferedReader(new FileReader("zzz.txt"));
//        int intOne = 0;
//        try {
//            intOne = Integer.parseInt(reader.readLine());
//        } catch (NumberFormatException e) {
//        }
//
//        int intTwo = 0;
//        try {
//            intTwo = Integer.parseInt(reader.readLine());
//        } catch (NumberFormatException e) {
//        }
//
//        System.out.println("one = "+intOne);
//        System.out.println("two = "+intTwo);



    }
}