import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MergeSortedData {

    public static List<Integer> mergeInt(List<Integer> first, List<Integer> second) {
        LinkedList<Integer> result = new LinkedList<>();
        while (!first.isEmpty() && !second.isEmpty()) {
            if (first.get(0) < second.get(0)) {
                result.add(first.get(0));
                first.remove(0);
            } else if (second.get(0) < first.get(0)) {
                result.add(second.get(0));
                second.remove(0);
            } else if (first.get(0).equals(second.get(0))) {
                result.add(first.get(0));
                result.add(second.get(0));
                first.remove(0);
                second.remove(0);
            }
        }

        if (!first.isEmpty()) {
            result.addAll(first);
        } else if (!second.isEmpty()) {
            result.addAll(second);
        }
        return result;
    }

    public static List<String> mergeString(List<String> first, List<String> second) {
        LinkedList<String> result = new LinkedList<>();

        while (!first.isEmpty() && !second.isEmpty()) {
            if (first.get(0).compareTo(second.get(0)) < 0) {
                result.add(first.get(0));
                first.remove(0);
            } else if (second.get(0).compareTo(first.get(0)) < 0) {
                result.add(second.get(0));
                second.remove(0);
            } else if (first.get(0).compareTo(second.get(0)) == 0) {
                result.add(first.get(0));
                result.add(second.get(0));
                first.remove(0);
                second.remove(0);
            }
        }
        if (!first.isEmpty()) {
            result.addAll(first);
        } else if (!second.isEmpty()) {
            result.addAll(second);
        }
        return result;
    }

    public static File mergeSortedIntFiles(File fileOne, File fileTwo, File outFile) {
        try (BufferedReader readerOne = new BufferedReader(new FileReader(fileOne));
             BufferedReader readerTwo = new BufferedReader(new FileReader(fileTwo));
             BufferedWriter writerOut = new BufferedWriter(new FileWriter(outFile))) {
            String lineFromOne = readerOne.readLine();
            String lineFromTwo = readerTwo.readLine();
            if (lineFromOne == null && lineFromTwo == null) {
                writerOut.write("Нет данных в исходных файлах");
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
}
