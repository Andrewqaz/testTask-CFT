import org.apache.commons.io.input.ReversedLinesFileReader;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

public class FileHandler {


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

                File tempFile = MergeSortedData.getTempFile();
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
            System.out.println("Ошибка доступа к файлу при попытке сортировки! " + e.getMessage());
        }


        if (tempFiles.size() == 1) {
            return tempFiles.get(0);
        } else {
            for (int i = 1; i < tempFiles.size(); i++) {
                File tempOut = MergeSortedData.getTempFile();
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

                File tempFile = MergeSortedData.getTempFile();
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
                File tempOut = MergeSortedData.getTempFile();
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

    public File sortFile(File srcFile, Type fileType) {
        if (fileType.equals(Type.INT)) {
            return sortIntFile(srcFile);
        } else {
            return sortStringFile(srcFile);
        }
    }

    public List<File> sortFileList(List<File> files, Type fileType) {
        List<File> result = new LinkedList<>();

        for (File file :
                files) {
            result.add(sortFile(file, fileType));
        }
        return result;
    }

    public File reversFile(File srcFile, File outFile) {

        try (ReversedLinesFileReader reversReader = new ReversedLinesFileReader(srcFile, StandardCharsets.UTF_8);
             BufferedWriter writer = new BufferedWriter(new FileWriter(outFile))
        ) {
            String line = reversReader.readLine();
            while (line != null) {
                writer.write(line);
                writer.newLine();
                line = reversReader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Файл " + "\"" + srcFile.getName() + "\" недоступен или не существует\n" + e.getMessage());
        }
        return outFile;
    }


}
