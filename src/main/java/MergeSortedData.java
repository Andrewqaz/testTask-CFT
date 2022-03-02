import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class MergeSortedData {
    public static File getFile(String fileName){
        File file = new File(fileName + ".txt");
        if (file.exists()) {
            int count = 2;
            while (true) {
                file = new File(fileName + "_" + count + ".txt");
                if (!file.exists()) {
                    try {
                        file.createNewFile();
                        return file;
                    } catch (IOException e) {
                        System.out.println("Не удалось создать временный файл! " + e.getMessage());
                        break;
                    }
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

    public static File getTempFile() {
        String tempName = "tempFile";
        return getFile(tempName);
    }

    public static File mergeSortedIntFiles(File fileOne, File fileTwo, File outFile) {
        try (BufferedReader readerOne = new BufferedReader(new FileReader(fileOne));
             BufferedReader readerTwo = new BufferedReader(new FileReader(fileTwo));
             BufferedWriter writerOut = new BufferedWriter(new FileWriter(outFile))) {
            String lineFromOne = readerOne.readLine();
            String lineFromTwo = readerTwo.readLine();
            if (lineFromOne == null && lineFromTwo == null) {
                return outFile;
            }
            while (true) {
                if (lineFromOne != null && lineFromTwo == null) {
                    Integer intFromOne = null;
                    try {
                        intFromOne = Integer.parseInt(lineFromOne);
                    } catch (NumberFormatException e) {
                        lineFromOne = readerOne.readLine();
                        continue;
                    }
                    writerOut.write(String.valueOf(intFromOne));
                    writerOut.newLine();
                    lineFromOne = readerOne.readLine();
                } else if (lineFromTwo != null && lineFromOne == null) {
                    Integer intFromTwo = null;
                    try {
                        intFromTwo = Integer.parseInt(lineFromTwo);
                    } catch (NumberFormatException e) {
                        lineFromTwo = readerTwo.readLine();
                        continue;
                    }
                    writerOut.write(String.valueOf(intFromTwo));
                    writerOut.newLine();
                    lineFromTwo = readerTwo.readLine();
                } else if (lineFromOne != null && lineFromTwo != null) {
                    Integer intOne = null;
                    try {
                        intOne = Integer.parseInt(lineFromOne);
                    } catch (NumberFormatException e) {
                        lineFromOne = readerOne.readLine();
                        continue;
                    }
                    Integer intTwo = null;
                    try {
                        intTwo = Integer.parseInt(lineFromTwo);
                    } catch (NumberFormatException e) {
                        lineFromTwo = readerTwo.readLine();
                        continue;
                    }
                    if (intOne < intTwo) {
                        writerOut.write(intOne.toString());
                        writerOut.newLine();
                        lineFromOne = readerOne.readLine();
                    } else if (intTwo < intOne) {
                        writerOut.write(intTwo.toString());
                        writerOut.newLine();
                        lineFromTwo = readerTwo.readLine();
                    } else if (intOne.equals(intTwo)) {
                        writerOut.write(intOne.toString());
                        writerOut.newLine();
                        writerOut.write(intTwo.toString());
                        writerOut.newLine();
                        lineFromOne = readerOne.readLine();
                        lineFromTwo = readerTwo.readLine();
                    }
                }
                if (lineFromOne == null && lineFromTwo == null) {
                    break;
                }
            }
        } catch (IOException e) {
        }
        return outFile;
    }

    public static File mergeSortedStringFiles(File fileOne, File fileTwo, File outFile) {
        try (BufferedReader readerOne = new BufferedReader(new FileReader(fileOne));
             BufferedReader readerTwo = new BufferedReader(new FileReader(fileTwo));
             BufferedWriter writerOut = new BufferedWriter(new FileWriter(outFile))) {
            String lineFromOne = readerOne.readLine();
            String lineFromTwo = readerTwo.readLine();
            if (lineFromOne == null && lineFromTwo == null) {
                return outFile;
            }
            while (true) {
                if (lineFromOne != null && lineFromTwo == null) {
                    if (lineFromOne.contains(" ") || lineFromOne.equals("")) {
                        lineFromOne = readerOne.readLine();
                        continue;
                    }
                    writerOut.write(lineFromOne);
                    writerOut.newLine();
                    lineFromOne = readerOne.readLine();
                } else if (lineFromTwo != null && lineFromOne == null) {
                    if (lineFromTwo.contains(" ") || lineFromTwo.equals("")) {
                        lineFromTwo = readerOne.readLine();
                        continue;
                    }
                    writerOut.write(lineFromTwo);
                    writerOut.newLine();
                    lineFromTwo = readerTwo.readLine();
                } else if (lineFromOne != null && lineFromTwo != null) {
                    if (lineFromOne.contains(" ") || lineFromOne.equals("")) {
                        lineFromOne = readerOne.readLine();
                        continue;
                    }
                    if (lineFromTwo.contains(" ") || lineFromOne.equals("")) {
                        lineFromTwo = readerOne.readLine();
                        continue;
                    }
                    if (lineFromOne.compareTo(lineFromTwo) < 0) {
                        writerOut.write(lineFromOne);
                        writerOut.newLine();
                        lineFromOne = readerOne.readLine();
                    } else if (lineFromTwo.compareTo(lineFromOne) < 0) {
                        writerOut.write(lineFromTwo);
                        writerOut.newLine();
                        lineFromTwo = readerTwo.readLine();
                    } else if (lineFromOne.compareTo(lineFromTwo) == 0) {
                        writerOut.write(lineFromOne);
                        writerOut.newLine();
                        writerOut.write(lineFromTwo);
                        writerOut.newLine();
                        lineFromOne = readerOne.readLine();
                        lineFromTwo = readerTwo.readLine();
                    }
                }
                if (lineFromOne == null && lineFromTwo == null) {
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка доступа к одному из файлов!!!\n" +
                    "Данные, или их часть, могли быть потеряны! \n" + e.getMessage());
        }
        return outFile;
    }

    public static File mergeSortedFiles(File one, File two, Type fileType) {
        File out = getTempFile();
        out.deleteOnExit();
        if (fileType.equals(Type.INT)) {
            return mergeSortedIntFiles(one, two, out);
        } else {
            return mergeSortedStringFiles(one, two, out);
        }
    }

    public static File mergeFilesList(List<File> srcFiles, Type fileType) {
        File outFile = getTempFile();
        outFile.deleteOnExit();
        if (srcFiles.size() == 1) {
            outFile = srcFiles.get(0);
        } else if (srcFiles.size() == 2) {
            outFile = mergeSortedFiles(srcFiles.get(0), srcFiles.get(1), fileType);
        } else if (srcFiles.size() > 2) {
            List<File> partOne = new LinkedList<>();
            List<File> partTwo = new LinkedList<>();
            List<File> partRes = new LinkedList<>();

            for (int i = 0; i < srcFiles.size(); i++) {
                if (srcFiles.size() / 2 <= i) {
                    partOne.add(srcFiles.get(i));
                } else {
                    partTwo.add(srcFiles.get(i));
                }
            }
            while (true) {
                if (partOne.isEmpty() && partTwo.isEmpty() && partRes.size() > 2) {
                    for (int i = 0; i < partRes.size(); i++) {
                        if (partRes.size() / 2 <= i) {
                            partOne.add(partRes.get(i));
                        } else {
                            partTwo.add(partRes.get(i));
                        }
                        partRes.remove(i);
                    }
                } else if (partOne.isEmpty() && partTwo.isEmpty() && partRes.size() == 2) {
                    outFile = mergeSortedFiles(partRes.get(0), partRes.get(1), fileType);
                    break;
                } else if (partOne.isEmpty() && partTwo.isEmpty() && partRes.size() == 1) {
                    outFile = partRes.get(0);
                    break;
                }
                while (true) {
                    if (!partOne.isEmpty() && !partTwo.isEmpty()) {
                        partRes.add(mergeSortedFiles(partOne.get(0), partTwo.get(0), fileType));
                        partOne.remove(0);
                        partTwo.remove(0);
                    } else if (!partOne.isEmpty() && partTwo.isEmpty()) {
                        partRes.add(partOne.get(0));
                        partOne.remove(0);
                        if (partOne.isEmpty()) {
                            break;
                        }
                    } else if (!partTwo.isEmpty() && partOne.isEmpty()) {
                        partRes.add(partTwo.get(0));
                        partTwo.remove(0);

                    } else {
                        break;
                    }
                }
            }
        }
        return outFile;
    }
}
