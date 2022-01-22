import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class FileHandler {
    private static final String PREF_OUT_FILE_NAME = "out";
    private static final String TEMP_FILE_PREF = "tempFile";
    private static final String TEMP_OUT_FILE_PREF = "tempOutFile";
    private static final String UNDERSCORE = "_";

    public static File getFile(String prefix) {
        File file = new File(prefix + ".txt");
        if (file.exists()) {
            int count = 2;
            while (true) {
                file = new File(prefix + UNDERSCORE + count + ".txt");
                if (!file.exists()) {
                    break;
                } else {
                    count++;
                }
            }
        }
        return file;
    }


    public void mergeIntFiles(File fileOne, File fileTwo) {
        File out = getFile(PREF_OUT_FILE_NAME);
        File workFileOne = fileOne;
        File workFileTwo = fileTwo;

        Scanner cmd = new Scanner(System.in);
        boolean isSortedOne = Sorter.isFileIntSorted(fileOne);
        boolean isSortedTwo = Sorter.isFileIntSorted(fileTwo);
        if (!isSortedOne) {
            System.out.println("файл: " + fileOne.getName() + " частично или полностью не отсортирован, \n" +
                    "попытаться отсортировать его? (возможно это займет продолжительное время и потребует \n" +
                    "дополнительного места на диске, в зависимости от размера файла...\n" + "\n" +
                    "\nY - да, попытаться отсортировать файл " + fileOne.getName() +
                    "\nN - нет, попытаться провести слияние без сортировки" +
                    "\nE - выйти из программы");
            String userAnswer1 = "";
            userAnswer1 = cmd.nextLine();
            switch (userAnswer1.toUpperCase()) {
                case "Y": {
                    workFileOne = sortIntFile(fileOne);
                    break;
                }
                case "N": {
                    break;
                }
                case "E": {
                    System.exit(0);
                }
                default: {
                    System.out.println("Введена неверная команда" +
                            "\nY - да, попытаться отсортировать файл" + fileOne.getName() +
                            "\nN - нет, попытаться провести слияние без сортировки" +
                            "\nE - выйти из программы");
                }
            }
        }
        if (!isSortedTwo) {
            System.out.println("файл: " + fileTwo.getName() + " частично или полностью не отсортирован, \n" +
                    "попытаться отсортировать его? (возможно это займет продолжительное время и потребует \n" +
                    "дополнительного места на диске, в зависимости от размера файла...\n" + "\n" +
                    "\nY - да, попытаться отсортировать файл " + fileTwo.getName() +
                    "\nN - нет, попытаться провести слияние без сортировки" +
                    "\nE - выйти из программы");
            String userAnswer2 = cmd.nextLine();
            switch (userAnswer2.toUpperCase()) {
                case "Y": {
                    workFileTwo = sortIntFile(fileTwo);
                }
                case "N": {
                    break;
                }
                case "E": {
                    System.exit(0);
                }
                default: {
                    System.out.println("Введена неверная команда" +
                            "\nY - да, попытаться отсортировать файл" + fileTwo.getName() +
                            "\nN - нет, попытаться провести слияние без сортировки" +
                            "\nE - выйти из программы");
                }
            }
        }

        MergeSortedData.mergeSortedIntFiles(workFileOne, workFileTwo, out);
        if (!isSortedOne){
            workFileOne.delete();
        }
        if (!isSortedTwo){
            workFileTwo.delete();
        }
        cmd.close();
    }

    public void mergeStringFiles(File fileOne, File fileTwo) {
        File out = getFile(PREF_OUT_FILE_NAME);
        File workFileOne = fileOne;
        File workFileTwo = fileTwo;

        Scanner cmd = new Scanner(System.in);
        boolean isSortedOne = Sorter.isFileStringSorted(fileOne);
        boolean isSortedTwo = Sorter.isFileStringSorted(fileTwo);
        if (!isSortedOne) {
            System.out.println("файл: " + fileOne.getName() + " частично или полностью не отсортирован, \n" +
                    "попытаться отсортировать его? (возможно это займет продолжительное время и потребует \n" +
                    "дополнительного места на диске, в зависимости от размера файла...\n" + "\n" +
                    "\nY - да, попытаться отсортировать файл " + fileOne.getName() +
                    "\nN - нет, попытаться провести слияние без сортировки" +
                    "\nE - выйти из программы");
            String userAnswer1 = "";
            userAnswer1 = cmd.nextLine();
            switch (userAnswer1.toUpperCase()) {
                case "Y": {
                    workFileOne = sortStringFile(fileOne);
                    break;
                }
                case "N": {
                    break;
                }
                case "E": {
                    System.exit(0);
                }
                default: {
                    System.out.println("Введена неверная команда" +
                            "\nY - да, попытаться отсортировать файл" + fileOne.getName() +
                            "\nN - нет, попытаться провести слияние без сортировки" +
                            "\nE - выйти из программы");
                }
            }
        }
        if (!isSortedTwo) {
            System.out.println("файл: " + fileTwo.getName() + " частично или полностью не отсортирован, \n" +
                    "попытаться отсортировать его? (возможно это займет продолжительное время и потребует \n" +
                    "дополнительного места на диске, в зависимости от размера файла...\n" + "\n" +
                    "\nY - да, попытаться отсортировать файл " + fileTwo.getName() +
                    "\nN - нет, попытаться провести слияние без сортировки" +
                    "\nE - выйти из программы");
            String userAnswer2 = cmd.nextLine();
            switch (userAnswer2.toUpperCase()) {
                case "Y": {
                    workFileTwo = sortStringFile(fileTwo);
                }
                case "N": {
                    break;
                }
                case "E": {
                    System.exit(0);
                }
                default: {
                    System.out.println("Введена неверная команда" +
                            "\nY - да, попытаться отсортировать файл" + fileTwo.getName() +
                            "\nN - нет, попытаться провести слияние без сортировки" +
                            "\nE - выйти из программы");
                }
            }
        }

        MergeSortedData.mergeSortedStringFiles(workFileOne, workFileTwo, out);
        if (!isSortedOne){
            workFileOne.delete();
        }
        if (!isSortedTwo){
            workFileTwo.delete();
        }
        cmd.close();
    }


    private File sortIntFile(File srcFile) {

        List<File> tempFiles = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(srcFile))
        ) {
            String line = reader.readLine();
            while (line != null) {
                List<Integer> ints = new LinkedList<>();
                int counter = 0;
                while (counter < 1000 && line != null) {

                    Integer intFromLine = null;
                    try {
                        intFromLine = Integer.parseInt(line);
                    } catch (NumberFormatException e) {
                        line = reader.readLine();
                        counter++;
                        continue;
                    }

                    ints.add(intFromLine);
                    line = reader.readLine();
                    counter++;
                }
                Integer[] sortedIntegers = Sorter.sortIntArray(ints.toArray(new Integer[0]));

                File tempFile = getFile(TEMP_FILE_PREF);
                tempFiles.add(tempFile);
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
                    for (int i = 0; i < sortedIntegers.length; i++) {
                        writer.write(sortedIntegers[i].toString());
                        writer.newLine();
                        writer.flush();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            //todo
        } catch (IOException e) {
            //todo
        }


        if (tempFiles.size() == 1) {
            return tempFiles.get(0);
        } else {
            for (int i = 1; i < tempFiles.size(); i++) {
                File tempOut = getFile(TEMP_OUT_FILE_PREF);
                if (tempFiles.size() == i) {
                    return tempFiles.get(0);
                }
                MergeSortedData.mergeSortedIntFiles(tempFiles.get(0), tempFiles.get(i), tempOut);
                tempFiles.get(0).delete();
                tempFiles.get(i).delete();
                tempFiles.set(0, tempOut);
            }
        }
        return tempFiles.get(0);
    }

    private File sortStringFile(File srcFile) {

        List<File> tempFiles = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(srcFile))
        ) {
            String line = reader.readLine();
            while (line != null) {
                List<String> strings = new LinkedList<>();
                int counter = 0;
                while (counter < 1000 && line != null) {
                    if (line.contains(" ")||line.equals("")){
                        line = reader.readLine();
                        continue;
                    }
                    strings.add(line);
                    line = reader.readLine();
                    counter++;
                }
                String[] sortedStrings = Sorter.sortStringArray(strings.toArray(new String[0]));

                File tempFile = getFile(TEMP_FILE_PREF);
                tempFiles.add(tempFile);
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
                    for (int i = 0; i < sortedStrings.length; i++) {
                        writer.write(sortedStrings[i].toString());
                        writer.newLine();
                        writer.flush();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            //todo
        } catch (IOException e) {
            //todo
        }


        if (tempFiles.size() == 1) {
            return tempFiles.get(0);
        } else {
            for (int i = 1; i < tempFiles.size(); i++) {
                File tempOut = getFile(TEMP_OUT_FILE_PREF);
                if (tempFiles.size() == i) {
                    return tempFiles.get(0);
                }
                MergeSortedData.mergeSortedStringFiles(tempFiles.get(0), tempFiles.get(i), tempOut);
                tempFiles.get(0).delete();
                tempFiles.get(i).delete();
                tempFiles.set(0, tempOut);
            }
        }
        return tempFiles.get(0);
    }

}
