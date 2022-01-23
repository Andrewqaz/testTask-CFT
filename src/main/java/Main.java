import java.util.Random;

public class Main {
    private static final String PREF_OUT_FILE_NAME = "out";
    private static final String SUF_OUT_FILE_NAME = ".txt";
    private static final String UNDERSCORE = "_";

    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {


//        File one = new File("one.txt");
//        File two = new File("two.txt");
//
//        FileHandler handler = new FileHandler();
//        handler.mergeStringFiles(one, two);



//        BufferedWriter oneWriter = new BufferedWriter(new FileWriter(one));
//        BufferedWriter twoWriter = new BufferedWriter(new FileWriter(two));
//
//        for (int i = 0; i < 1000000; i++) {
//            String rnd1 = getRandomString(5);
//            oneWriter.write(rnd1);
//            oneWriter.newLine();
//            String rnd2 = getRandomString(10);
//            twoWriter.write(rnd2);
//            twoWriter.newLine();
//            System.out.println(rnd1+"\n"+rnd2);
//            System.out.println();
//        }
//        oneWriter.close();
//        twoWriter.close();


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