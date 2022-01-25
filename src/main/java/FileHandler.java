import org.apache.commons.io.input.ReversedLinesFileReader;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class FileHandler {

    public File getTempFile() {
        String tempName = "tempFile";
        File file = new File(tempName + ".txt");
        if (file.exists()) {
            int count = 2;
            while (true) {
                file = new File(tempName + "_" + count + ".txt");
                if (!file.exists()) {
                    try {
                        file.createNewFile();
                        return file;
                    } catch (IOException e) {
                        System.out.println("Не удалось создать временный файл! "+e.getMessage());
                    }
                    break;
                } else {
                    count++;
                }
            }
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }


    public void mergeIntFiles(File fileOne, File fileTwo, File out, Scanner cmd) {
        File workFileOne = fileOne;
        File workFileTwo = fileTwo;

        boolean isSortedOne;
        boolean isSortedTwo;
        if (fileOne != null && fileTwo != null) {
            isSortedOne = Sorter.isFileIntSorted(fileOne);
            isSortedTwo = Sorter.isFileIntSorted(fileTwo);
            if (!isSortedOne) {
                boolean userChoice = false;
                while (!userChoice) {
                    System.out.println("файл: " + fileOne.getName() + " частично или полностью не отсортирован, \n" +
                            "попытаться отсортировать его? (возможно это займет продолжительное время и потребует \n" +
                            "дополнительного места на диске, в зависимости от размера файла..." +
                            "\nY - да, попытаться отсортировать файл " + fileOne.getName() +
                            "\nN - нет, попытаться провести слияние без сортировки" +
                            "\nE - выйти из программы\n");
                    String userAnswer1 = "";
                    userAnswer1 = cmd.nextLine();
                    switch (userAnswer1.toUpperCase()) {
                        case "Y": {
                            workFileOne = sortIntFile(fileOne);
                            userChoice = true;
                            break;
                        }
                        case "N": {
                            userChoice = true;
                            isSortedOne = true;
                            break;
                        }
                        case "E": {
                            System.exit(0);
                        }
                        default: {
                            System.out.println("\nВведена неверная команда" +
                                    "\nY - да, попытаться отсортировать файл" + fileOne.getName() +
                                    "\nN - нет, попытаться провести слияние без сортировки" +
                                    "\nE - выйти из программы\n");
                        }
                    }
                }
            }
            if (!isSortedTwo) {
                boolean userChoice = false;
                while (!userChoice) {
                    System.out.println("файл: " + fileTwo.getName() + " частично или полностью не отсортирован, \n" +
                            "попытаться отсортировать его? (возможно это займет продолжительное время и потребует \n" +
                            "дополнительного места на диске, в зависимости от размера файла..." +
                            "\nY - да, попытаться отсортировать файл " + fileTwo.getName() +
                            "\nN - нет, попытаться провести слияние без сортировки" +
                            "\nE - выйти из программы\n");
                    String userAnswer2 = cmd.nextLine();
                    switch (userAnswer2.toUpperCase()) {
                        case "Y": {
                            workFileTwo = sortIntFile(fileTwo);
                            userChoice = true;
                            break;
                        }
                        case "N": {
                            userChoice = true;
                            isSortedTwo = true;
                            break;
                        }
                        case "E": {
                            System.exit(0);
                        }
                        default: {
                            System.out.println("\nВведена неверная команда" +
                                    "\nY - да, попытаться отсортировать файл" + fileTwo.getName() +
                                    "\nN - нет, попытаться провести слияние без сортировки" +
                                    "\nE - выйти из программы\n");
                        }
                    }
                }
            }

            MergeSortedData.mergeSortedIntFiles(workFileOne, workFileTwo, out);
        } else if (fileOne != null && fileTwo == null) {
            isSortedOne = Sorter.isFileIntSorted(fileOne);
            if (!isSortedOne) {
                boolean userChoice = false;
                while (!userChoice) {
                    System.out.println("файл: " + fileOne.getName() + " частично или полностью не отсортирован, \n" +
                            "попытаться отсортировать его? (возможно это займет продолжительное время и потребует \n" +
                            "дополнительного места на диске, в зависимости от размера файла..." +
                            "\nY - да, попытаться отсортировать файл " + fileOne.getName() +
                            "\nN - нет, попытаться провести слияние без сортировки" +
                            "\nE - выйти из программы\n");
                    String userAnswer1 = "";
                    userAnswer1 = cmd.nextLine();
                    switch (userAnswer1.toUpperCase()) {
                        case "Y": {
                            workFileOne = sortIntFile(fileOne);
                            userChoice = true;
                            break;
                        }
                        case "N": {
                            userChoice = true;
                            isSortedOne = true;
                            break;
                        }
                        case "E": {
                            System.exit(0);
                        }
                        default: {
                            System.out.println("\nВведена неверная команда" +
                                    "\nY - да, попытаться отсортировать файл" + fileOne.getName() +
                                    "\nN - нет, попытаться провести слияние без сортировки" +
                                    "\nE - выйти из программы\n");
                        }
                    }
                }
            }
            workFileTwo = getTempFile();
            try {
                workFileTwo.createNewFile();
                workFileTwo.deleteOnExit();
            } catch (IOException e) {
                System.out.println("Внутренняя ошибка, не удалось создать временный файл.\n" + e.getMessage());
                System.exit(0);
            }
            MergeSortedData.mergeSortedIntFiles(workFileOne, workFileTwo, out);
        }
    }

    public void mergeStringFiles(File fileOne, File fileTwo, File out, Scanner cmd) {
        File workFileOne = fileOne;
        File workFileTwo = fileTwo;
        boolean isSortedOne;
        boolean isSortedTwo;

        if (fileOne != null && fileTwo != null) {
            isSortedOne = Sorter.isFileStringSorted(fileOne);
            isSortedTwo = Sorter.isFileStringSorted(fileTwo);
            if (!isSortedOne) {
                boolean userChoice = false;
                while (!userChoice) {
                    System.out.println("файл: " + fileOne.getName() + " частично или полностью не отсортирован, \n" +
                            "попытаться отсортировать его? (возможно это займет продолжительное время и потребует \n" +
                            "дополнительного места на диске, в зависимости от размера файла..." +
                            "\nY - да, попытаться отсортировать файл " + fileOne.getName() +
                            "\nN - нет, попытаться провести слияние без сортировки" +
                            "\nE - выйти из программы\n");
                    String userAnswer1 = "";
                    userAnswer1 = cmd.nextLine();
                    switch (userAnswer1.toUpperCase()) {
                        case "Y": {
                            workFileOne = sortStringFile(fileOne);
                            userChoice = true;
                            break;
                        }
                        case "N": {
                            userChoice = true;
                            break;
                        }
                        case "E": {
                            System.exit(0);
                        }
                        default: {
                            System.out.println("\nВведена неверная команда" +
                                    "\nY - да, попытаться отсортировать файл" + fileOne.getName() +
                                    "\nN - нет, попытаться провести слияние без сортировки" +
                                    "\nE - выйти из программы\n");
                        }
                    }
                }
            }
            if (!isSortedTwo) {
                boolean userChoice = false;
                while (!userChoice) {
                    System.out.println("файл: " + fileTwo.getName() + " частично или полностью не отсортирован, \n" +
                            "попытаться отсортировать его? (возможно это займет продолжительное время и потребует \n" +
                            "дополнительного места на диске, в зависимости от размера файла..." +
                            "\nY - да, попытаться отсортировать файл " + fileTwo.getName() +
                            "\nN - нет, попытаться провести слияние без сортировки" +
                            "\nE - выйти из программы\n");
                    String userAnswer2 = cmd.nextLine();
                    switch (userAnswer2.toUpperCase()) {
                        case "Y": {
                            workFileTwo = sortStringFile(fileTwo);
                            userChoice = true;
                            break;
                        }
                        case "N": {
                            userChoice = true;
                            break;
                        }
                        case "E": {
                            System.exit(0);
                        }
                        default: {
                            System.out.println("\nВведена неверная команда" +
                                    "\nY - да, попытаться отсортировать файл" + fileTwo.getName() +
                                    "\nN - нет, попытаться провести слияние без сортировки" +
                                    "\nE - выйти из программы\n");
                        }
                    }
                }
            }

            MergeSortedData.mergeSortedStringFiles(workFileOne, workFileTwo, out);
        } else if (fileOne != null && fileTwo == null) {
            isSortedOne = Sorter.isFileStringSorted(fileOne);
            if (!isSortedOne) {
                boolean userChoice = false;
                while (!userChoice) {
                    System.out.println("файл: " + fileOne.getName() + " частично или полностью не отсортирован, \n" +
                            "попытаться отсортировать его? (возможно это займет продолжительное время и потребует \n" +
                            "дополнительного места на диске, в зависимости от размера файла..." +
                            "\nY - да, попытаться отсортировать файл " + fileOne.getName() +
                            "\nN - нет, попытаться провести слияние без сортировки" +
                            "\nE - выйти из программы\n");
                    String userAnswer1 = "";
                    userAnswer1 = cmd.nextLine();
                    switch (userAnswer1.toUpperCase()) {
                        case "Y": {
                            workFileOne = sortStringFile(fileOne);
                            userChoice = true;
                            break;
                        }
                        case "N": {
                            userChoice = true;
                            isSortedOne = true;
                            break;
                        }
                        case "E": {
                            System.exit(0);
                        }
                        default: {
                            System.out.println("\nВведена неверная команда" +
                                    "\nY - да, попытаться отсортировать файл" + fileOne.getName() +
                                    "\nN - нет, попытаться провести слияние без сортировки" +
                                    "\nE - выйти из программы\n");
                        }
                    }
                }
            }
            workFileTwo = getTempFile();
            try {
                workFileTwo.createNewFile();
                workFileTwo.deleteOnExit();
            } catch (IOException e) {
                System.out.println("Внутренняя ошибка, не удалось создать временный файл.\n" + e.getMessage());
                System.exit(0);
            }
            MergeSortedData.mergeSortedStringFiles(workFileOne, workFileTwo, out);
        }
    }


    public File sortIntFile(File srcFile) {

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

                File tempFile = getTempFile();
                tempFile.deleteOnExit();
                tempFiles.add(tempFile);
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
                    for (int i = 0; i < sortedIntegers.length; i++) {
                        writer.write(sortedIntegers[i].toString());
                        writer.newLine();
                        writer.flush();
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка доступа к файлу при попытке сортировки! "+ e.getMessage());
        }


        if (tempFiles.size() == 1) {
            return tempFiles.get(0);
        } else {
            for (int i = 1; i < tempFiles.size(); i++) {
                File tempOut = getTempFile();
                tempOut.deleteOnExit();
                if (tempFiles.size() == i) {
                    return tempFiles.get(0);
                }
                MergeSortedData.mergeSortedIntFiles(tempFiles.get(0), tempFiles.get(i), tempOut);
                tempFiles.set(0, tempOut);
            }
        }
        return tempFiles.get(0);
    }

    public File sortStringFile(File srcFile) {

        List<File> tempFiles = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(srcFile))
        ) {
            String line = reader.readLine();
            while (line != null) {
                List<String> strings = new LinkedList<>();
                int counter = 0;
                while (counter < 1000 && line != null) {
                    if (line.contains(" ") || line.equals("")) {
                        line = reader.readLine();
                        continue;
                    }
                    strings.add(line);
                    line = reader.readLine();
                    counter++;
                }
                String[] sortedStrings = Sorter.sortStringArray(strings.toArray(new String[0]));

                File tempFile = getTempFile();
                tempFile.deleteOnExit();
                tempFiles.add(tempFile);
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
                    for (int i = 0; i < sortedStrings.length; i++) {
                        writer.write(sortedStrings[i].toString());
                        writer.newLine();
                        writer.flush();
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка доступа к файлу при попытке сортировки" + e.getMessage());
        }


        if (tempFiles.size() == 1) {
            return tempFiles.get(0);
        } else {
            for (int i = 1; i < tempFiles.size(); i++) {
                File tempOut = getTempFile();
                tempOut.deleteOnExit();
                if (tempFiles.size() == i) {
                    return tempFiles.get(0);
                }
                MergeSortedData.mergeSortedStringFiles(tempFiles.get(0), tempFiles.get(i), tempOut);
                tempFiles.set(0, tempOut);
            }
        }
        return tempFiles.get(0);
    }

    public void reversFile(File srcFile, File outFile) {

        try (ReversedLinesFileReader reversReader = new ReversedLinesFileReader(srcFile, StandardCharsets.UTF_8);
             BufferedWriter writer = new BufferedWriter(new FileWriter(outFile))
        ) {
            String line = reversReader.readLine();
            while (line!=null){
                writer.write(line);
                writer.newLine();
                line = reversReader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Файл " + "\""+srcFile.getName()+"\" недоступен или не существует\n"+e.getMessage());
        }
    }


}
