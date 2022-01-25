import java.io.*;

public class MergeSortedData {

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
}
