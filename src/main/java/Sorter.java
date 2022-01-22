import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class Sorter {
    public static boolean isSortedInts(List<Integer> list) {
        boolean isSorted = true;

        for (int i = 0; i < list.size(); i++) {
            if (list.size() - i <= 1) {
                break;
            }
            if (list.get(i) > list.get(i + 1)) {
                isSorted = false;
                break;
            }
        }
        return isSorted;
    }

    public static boolean isReversSortedInts(List<Integer> list) {
        boolean isSorted = true;

        for (int i = 0; i < list.size(); i++) {
            if (list.size() - i <= 1) {
                break;
            }
            if (list.get(i) < list.get(i + 1)) {
                isSorted = false;
                break;
            }
        }
        return isSorted;
    }

    public static boolean isSortedStrings(List<String> list) {
        boolean isSorted = true;

        for (int i = 0; i < list.size(); i++) {
            if (list.size() - i <= 1) {
                break;
            }
            if (list.get(i).compareTo(list.get(i + 1)) > 0) {
                isSorted = false;
                break;
            }
        }
        return isSorted;
    }

    public static boolean isReverseSortedStrings(List<String> list) {
        boolean isSorted = true;

        for (int i = 0; i < list.size(); i++) {
            if (list.size() - i <= 1) {
                break;
            }
            if (list.get(i).compareTo(list.get(i + 1)) < 0) {
                isSorted = false;
                break;
            }
        }
        return isSorted;
    }

    public static Integer[] sortIntArray(Integer[] arraySrc) {
        if (arraySrc.length < 2) {
            return arraySrc;
        }

        int lengthArrayA = arraySrc.length / 2;
        Integer[] arrayA = new Integer[lengthArrayA];
        System.arraycopy(arraySrc, 0, arrayA, 0, lengthArrayA);

        Integer lengthArrayB = arraySrc.length - arraySrc.length / 2;
        Integer[] arrayB = new Integer[lengthArrayB];
        System.arraycopy(arraySrc, arraySrc.length / 2, arrayB, 0, lengthArrayB);

        sortIntArray(arrayA);
        sortIntArray(arrayB);

        return mergeIntArray(arraySrc, arrayA, arrayB);
    }

    public static String[] sortStringArray(String[] arraySrc) {
        if (arraySrc.length < 2) {
            return arraySrc;
        }

        int lengthArrayA = arraySrc.length / 2;
        String[] arrayA = new String[lengthArrayA];
        System.arraycopy(arraySrc, 0, arrayA, 0, lengthArrayA);

        Integer lengthArrayB = arraySrc.length - arraySrc.length / 2;
        String[] arrayB = new String[lengthArrayB];
        System.arraycopy(arraySrc, arraySrc.length / 2, arrayB, 0, lengthArrayB);

        sortStringArray(arrayA);
        sortStringArray(arrayB);

        return mergeStringArray(arraySrc, arrayA, arrayB);
    }


    public static Integer[] mergeIntArray(Integer[] arraySrc, Integer[] arrayA, Integer[] arrayB) {
        int positionA = 0;
        int positionB = 0;

        for (int i = 0; i < arraySrc.length; i++) {
            if (positionA == arrayA.length) {
                arraySrc[i] = arrayB[positionB];
                positionB++;
            } else if (positionB == arrayB.length) {
                arraySrc[i] = arrayA[positionA];
                positionA++;
            } else if (arrayA[positionA] < arrayB[positionB]) {
                arraySrc[i] = arrayA[positionA];
                positionA++;
            } else if (arrayB[positionB] < arrayA[positionA]) {
                arraySrc[i] = arrayB[positionB];
                positionB++;
            } else if (arrayA[positionA].equals(arrayB[positionB])) {
                arraySrc[i] = arrayA[positionA];
                i++;
                arraySrc[i] = arrayB[positionB];
                positionA++;
                positionB++;
            }
        }
        return arraySrc;
    }

    public static String[] mergeStringArray(String[] arraySrc, String[] arrayA, String[] arrayB) {
        int positionA = 0;
        int positionB = 0;

        for (int i = 0; i < arraySrc.length; i++) {
            if (positionA == arrayA.length) {
                arraySrc[i] = arrayB[positionB];
                positionB++;
            } else if (positionB == arrayB.length) {
                arraySrc[i] = arrayA[positionA];
                positionA++;
            } else if (arrayA[positionA].compareTo(arrayB[positionB]) < 0) {
                arraySrc[i] = arrayA[positionA];
                positionA++;
            } else if (arrayB[positionB].compareTo(arrayA[positionA]) < 0) {
                arraySrc[i] = arrayB[positionB];
                positionB++;
            } else if (arrayA[positionA].compareTo(arrayB[positionB]) == 0) {
                arraySrc[i] = arrayA[positionA];
                i++;
                arraySrc[i] = arrayB[positionB];
                positionA++;
                positionB++;
            }
        }
        return arraySrc;
    }

    public static boolean isFileIntSorted(File srcFile) {
        boolean isSorted = true;
        try (BufferedReader reader = new BufferedReader(new FileReader(srcFile))) {
            String line = reader.readLine();

            Integer intPast = null;

            if (line != null) {
                while (intPast == null) {
                    try {
                        intPast = Integer.parseInt(line);
                    } catch (NumberFormatException e) {
                        line = reader.readLine();
                    }
                }
            }
            line = reader.readLine();
            Integer intNext = null;

            if (line!=null) {
                while (line != null) {
                    try {
                        intNext = Integer.parseInt(line);
                    } catch (NumberFormatException e) {
                        line = reader.readLine();
                        continue;
                    }
                    if (intPast > intNext) {
                        isSorted = false;
                        break;
                    }
                    intPast = intNext;
                    line = reader.readLine();

                    try {
                        intNext = Integer.parseInt(line);
                    } catch (NumberFormatException e) {
                        line = reader.readLine();
                    }
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Не верно введено имя файла, либо файл " + "\"" + srcFile.getName() + "\" не существует" +
                    "попробуйте перезапустить программу указав верное имя файла...");
        } catch (IOException e) {
            System.out.println("непредвиденная ошибка ввода/вывода, не удалось прочитать файл " + "\"" +
                    srcFile.getName() + "\"");
        }
        return isSorted;
    }

    public static boolean isFileStringSorted(File srcFile) {
        boolean isSorted = true;
        try (BufferedReader reader = new BufferedReader(new FileReader(srcFile))) {
            String line = reader.readLine();
            String pastString = line;
            String nextString = null;
            if (line.contains(" ")||line.equals("")) {
                while (line != null) {
                    line = reader.readLine();
                    if (!line.contains(" ")) {
                        pastString = line;
                        break;
                    }
                }
            }

            line = reader.readLine();
            nextString = line;
            if (line.contains(" ")||line.equals("")) {
                while (line != null) {
                    line = reader.readLine();
                    if (!line.contains(" ")) {
                        nextString = line;
                        break;
                    }
                }
            }

            while (line != null) {
                if (pastString.compareTo(nextString) > 0) {
                    isSorted = false;
                    break;
                }
                pastString = nextString.toString();
                line = reader.readLine();
                nextString = line;
                if (line.contains(" ")||line.equals("")) {
                    while (line != null) {
                        line = reader.readLine();
                        if (!line.contains(" ")) {
                            nextString = line;
                            break;
                        }
                    }
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Не верно введено имя файла, либо файл " + "\"" + srcFile.getName() + "\" не существует" +
                    "попробуйте перезапустить программу указав верное имя файла...");
        } catch (IOException e) {
            System.out.println("непредвиденная ошибка ввода/вывода, не удалось прочитать файл " + "\"" +
                    srcFile.getName() + "\"");
        }
        return isSorted;
    }


}
