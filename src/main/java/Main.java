import org.apache.commons.io.input.ReversedLinesFileReader;

import java.io.File;
import java.util.Random;

public class Main {

    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }


    public static void main(String[] args) throws Exception {
        System.out.println("start");
        DoWork worker = new DoWork();
        worker.filesHandling(args);
    }
}