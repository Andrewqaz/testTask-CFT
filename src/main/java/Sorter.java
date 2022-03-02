import java.io.*;


public class Sorter {
    public static Integer[] sortIntArray(Integer[] arraySrc) {
        if (arraySrc.length < 2) {
            return arraySrc;
        }

        int lengthArrayA = arraySrc.length / 2;
        Integer[] arrayA = new Integer[lengthArrayA];
        System.arraycopy(arraySrc, 0, arrayA, 0, lengthArrayA);

        int lengthArrayB = arraySrc.length - arraySrc.length / 2;
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


    public static Integer[] mergeIntArray(Integer[] arrayResult, Integer[] arrayA, Integer[] arrayB) {
        int positionA = 0;
        int positionB = 0;

        for (int i = 0; i < arrayResult.length; i++) {
            if (positionA == arrayA.length) {
                arrayResult[i] = arrayB[positionB];
                positionB++;
            } else if (positionB == arrayB.length) {
                arrayResult[i] = arrayA[positionA];
                positionA++;
            } else if (arrayA[positionA] < arrayB[positionB]) {
                arrayResult[i] = arrayA[positionA];
                positionA++;
            } else if (arrayB[positionB] < arrayA[positionA]) {
                arrayResult[i] = arrayB[positionB];
                positionB++;
            } else if (arrayA[positionA].equals(arrayB[positionB])) {
                arrayResult[i] = arrayA[positionA];
                i++;
                arrayResult[i] = arrayB[positionB];
                positionA++;
                positionB++;
            }
        }
        return arrayResult;
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
                        if (line == null) {
                            return true;
                        }
                    }
                }
            } else {
                return true;
            }

            line = reader.readLine();
            Integer intNext = null;

            if (line != null) {
                while (line != null) {
                    try {
                        intNext = Integer.parseInt(line);
                    } catch (NumberFormatException e) {
                        line = reader.readLine();
                        continue;
                    }
                    if (intPast > intNext) {
                        return false;
                    }
                    intPast = intNext;
                    line = reader.readLine();

                    try {
                        intNext = Integer.parseInt(line);
                    } catch (NumberFormatException e) {
                        line = reader.readLine();
                    }
                }
            } else {
                return true;
            }

        } catch (FileNotFoundException e) {
            System.out.println("Не верно введено имя файла, либо файл " + "\"" + srcFile.getName() + "\" не существует, " +
                    "попробуйте перезапустить программу указав верное имя файла...");
        } catch (IOException e) {
            System.out.println("непредвиденная ошибка ввода/вывода, не удалось прочитать файл " + "\"" +
                    srcFile.getName() + "\"");
        }
        return true;
    }

    public static boolean isFileStringSorted(File srcFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(srcFile))) {
            String line = reader.readLine();
            if (line == null) {
                return true;
            }
            while (true) {
                if (line.contains(" ") || line.equals("")) {
                    line = reader.readLine();
                    if (line == null) {
                        return true;
                    }
                } else {
                    break;
                }
            }
            String pastString = line;
            String nextString = null;

            line = reader.readLine();
            while (true) {
                if (line.contains(" ") || line.equals("")) {
                    line = reader.readLine();
                    if (line == null) {
                        return true;
                    }
                } else break;

            }
            nextString = line;
            while (true) {
                if (pastString.compareTo(nextString) > 0) {
                    return false;
                } else {
                    line = reader.readLine();
                    if (line == null) {
                        break;
                    } else {
                        while (true) {
                            if (line.contains(" ") || line.equals("")) {
                                line = reader.readLine();
                                if (line == null) {
                                    return true;
                                }
                            } else {
                                pastString = nextString;
                                nextString = line;
                                break;
                            }
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
        return true;
    }

    public static boolean isFileSorted(File file, Type fileType) {
        if (fileType.equals(Type.INT)) {
            return isFileIntSorted(file);
        } else {
            return isFileStringSorted(file);
        }
    }


}
