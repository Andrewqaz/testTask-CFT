import java.io.File;
import java.util.*;

public class DoWork {
    private final FileHandler fileHandler;
    private Type fileType;
    private boolean directOrder;
    private List<File> srcFiles;
    private File outFile;

    public DoWork() {
        fileHandler = new FileHandler();
        directOrder = true;
        srcFiles = new ArrayList<>();
    }

    public void filesHandling(String[] args) {
        setCommandLineArgs(args);
        Scanner cmd = new Scanner(System.in);
        if (fileType.equals(Type.INT)) {
            System.out.println("Выбран режим обработки файлов содержащих целые числа (Integer)." +
                    "\nВсе строки содержащие пробелы, либо любые символы кроме: " +
                    "\n-9, -8, -7, -6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, " +
                    "будут проигнорированы!\n");
            if (srcFiles.size() == 1) {
                System.out.println("\nПри запуске указан только один исходный файл: " + srcFiles.get(0)+"\n");
                if (directOrder) {
                    fileHandler.mergeIntFiles(srcFiles.get(0), null, outFile, cmd);
                } else {
                    File tempFile = fileHandler.getTempFile();
                    tempFile.deleteOnExit();

                    fileHandler.mergeIntFiles(srcFiles.get(0), null, tempFile, cmd);
                    fileHandler.reversFile(tempFile, outFile);
                }
            } else if (srcFiles.size() == 2) {
                if (directOrder) {
                    fileHandler.mergeIntFiles(srcFiles.get(0), srcFiles.get(1), outFile, cmd);
                } else {
                    File tempFile = fileHandler.getTempFile();
                    tempFile.deleteOnExit();

                    fileHandler.mergeIntFiles(srcFiles.get(0), srcFiles.get(1), tempFile, cmd);
                    fileHandler.reversFile(tempFile, outFile);

                }
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
                        if (directOrder) {
                            fileHandler.mergeIntFiles(partRes.get(0), partRes.get(1), outFile, cmd);
                        } else {
                            File tempFile = fileHandler.getTempFile();
                            tempFile.deleteOnExit();

                            fileHandler.mergeIntFiles(partRes.get(0), partRes.get(1), tempFile, cmd);
                            fileHandler.reversFile(tempFile, outFile);
                            break;
                        }
                        break;
                    } else if (partOne.isEmpty() && partTwo.isEmpty() && partRes.size() == 1) {
                        if (directOrder) {
                            fileHandler.mergeIntFiles(partRes.get(0), null, outFile, cmd);
                        } else {
                            File tempFile = fileHandler.getTempFile();
                            tempFile.deleteOnExit();
                            fileHandler.mergeIntFiles(partRes.get(0), partRes.get(1), tempFile, cmd);
                            fileHandler.reversFile(tempFile, outFile);
                            tempFile.deleteOnExit();
                        }
                        break;
                    }

                    while (true) {
                        File tempRes = fileHandler.getTempFile();
                        tempRes.deleteOnExit();

                        if (!partOne.isEmpty() && !partTwo.isEmpty()) {
                            fileHandler.mergeIntFiles(partOne.get(0), partTwo.get(0), tempRes, cmd);
                            partOne.remove(0);
                            partTwo.remove(0);
                            partRes.add(tempRes);
                        } else if (!partOne.isEmpty() && partTwo.isEmpty()) {
                            fileHandler.mergeIntFiles(partOne.get(0), null, tempRes, cmd);
                            partOne.remove(0);
                            partRes.add(tempRes);
                            if (partOne.isEmpty()) {
                                break;
                            }
                        } else if (!partTwo.isEmpty() && partOne.isEmpty()) {
                            fileHandler.mergeIntFiles(partTwo.get(0), null, tempRes, cmd);
                            partTwo.remove(0);
                            partRes.add(tempRes);
                            if (partTwo.isEmpty()) {
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                }
            }
        } else if (fileType.equals(Type.STR)) {
            System.out.println("Выбран режим обработки файлов содержащих строковые данные (String)." +
                    "\nВсе пустые строки и строки содержащие пробелы будут проигнорированы!\n");
            if (srcFiles.size() == 1) {
                System.out.println("\nПри запуске указан только один исходный файл: " + srcFiles.get(0)+"\n");
                if (directOrder) {
                    fileHandler.mergeStringFiles(srcFiles.get(0), null, outFile, cmd);
                } else {
                    File tempFile = fileHandler.getTempFile();
                    tempFile.deleteOnExit();

                    fileHandler.mergeStringFiles(srcFiles.get(0), null, tempFile, cmd);
                    fileHandler.reversFile(tempFile, outFile);
                }
            } else if (srcFiles.size() == 2) {
                if (directOrder) {
                    fileHandler.mergeStringFiles(srcFiles.get(0), srcFiles.get(1), outFile, cmd);
                } else {
                    File tempFile = fileHandler.getTempFile();
                    tempFile.deleteOnExit();

                    fileHandler.mergeStringFiles(srcFiles.get(0), srcFiles.get(1), tempFile, cmd);
                    fileHandler.reversFile(tempFile, outFile);
                }
            } else if (srcFiles.size() > 2) {
                List<File> partOne = new LinkedList<>();
                List<File> partTwo = new LinkedList<>();
                List<File> partRes = new LinkedList<>();

                for (int i = 0; i < srcFiles.size(); i++) {
                    if (srcFiles.size() - srcFiles.size() / 2 <= i) {
                        partOne.add(srcFiles.get(i));
                    } else {
                        partTwo.add(srcFiles.get(i));
                    }
                }

                while (true) {
                    if (partOne.isEmpty() && partTwo.isEmpty() && partRes.size() > 2) {
                        for (int i = 0; i < partRes.size(); i++) {
                            if (partRes.size() - partRes.size() / 2 <= i) {
                                partOne.add(partRes.get(i));
                            } else {
                                partTwo.add(partRes.get(i));
                            }
                            partRes.remove(i);
                        }
                    } else if (partOne.isEmpty() && partTwo.isEmpty() && partRes.size() == 2) {
                        if (directOrder) {
                            fileHandler.mergeStringFiles(partRes.get(0), partRes.get(1), outFile, cmd);
                        }else {
                            File tempFile = fileHandler.getTempFile();
                            tempFile.deleteOnExit();

                            fileHandler.mergeStringFiles(partRes.get(0), partRes.get(1), tempFile, cmd);
                            fileHandler.reversFile(tempFile, outFile);
                        }
                        break;
                    } else if (partOne.isEmpty() && partTwo.isEmpty() && partRes.size() == 1) {
                        if (directOrder) {
                            fileHandler.mergeStringFiles(partRes.get(0), null, outFile, cmd);
                        } else {
                            File tempFile = fileHandler.getTempFile();
                            fileHandler.mergeStringFiles(partRes.get(0), null, tempFile, cmd);
                            fileHandler.reversFile(tempFile, outFile);
                            tempFile.deleteOnExit();
                        }
                        break;
                    }

                    while (true) {
                        File tempRes = fileHandler.getTempFile();
                        tempRes.deleteOnExit();

                        if (!partOne.isEmpty() && !partTwo.isEmpty()) {
                            fileHandler.mergeStringFiles(partOne.get(0), partTwo.get(0), tempRes, cmd);
                            partOne.remove(0);
                            partTwo.remove(0);
                            partRes.add(tempRes);
                        } else if (!partOne.isEmpty() && partTwo.isEmpty()) {
                            fileHandler.mergeStringFiles(partOne.get(0), null, tempRes, cmd);
                            partOne.remove(0);
                            partRes.add(tempRes);
                            if (partOne.isEmpty()) {
                                break;
                            }
                        } else if (!partTwo.isEmpty() && partOne.isEmpty()) {
                            fileHandler.mergeStringFiles(partTwo.get(0), null, tempRes, cmd);
                            partTwo.remove(0);
                            partRes.add(tempRes);
                            if (partTwo.isEmpty()) {
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                }
            }
        }
    }


    private void setCommandLineArgs(String[] args) {
        boolean directOrderCmd = false;
        String HELP = "" +
                "\n===========================================================================================" +
                "\nСПРАВКА:" +
                "\nДля корректной работы программы необходимо установить настройки с помощью аргументов " +
                "\nкоммандной строки. Дополнительными параметрами при запуске необходимо указать: " +
                "\n 1. режим сортировки (-a или -d), необязательный, по умолчанию сортировка по возрастанию;" +
                "\n 2. тип данных (-s или -i), обязательный;" +
                "\n 3. имя выходного файла, обязательное;" +
                "\n 4. остальные параметры – имена входных файлов, не менее одного." +
                "\n" +
                "\nПример запуска > mvn exec:java -Dexec.args=\"-d -s out.txt one.txt two.txt three.txt four.txt five.txt\"";
        if (args.length > 0) {
            switch (args[0]) {
                case "-a": {
                    directOrder = true;
                    directOrderCmd = true;
                    break;
                }
                case "-d": {
                    directOrder = false;
                    directOrderCmd = true;
                    break;
                }
                case "-s": {
                    fileType = Type.STR;
                    break;
                }
                case "-i": {
                    fileType = Type.INT;
                    break;
                }
                default: {
                    System.out.println("\nНеверно указан первый аргумент для запуска программы!" +
                            "\n" +
                            "\nПерезапустите программу убедившись что он соответствует одному " +
                            "из следующих значений: " +
                            "\n\"-a\" - прямой порядок сортировки (не обязательный параметр)" +
                            "\n\"-d\" - обратный порядок сортировки (необязательный параметр)" +
                            "\n\"-s\" - исходные файлы содержат строковые данные" +
                            "\n\"-i\" - исходные файлы содержат целые числа" +
                            "\n");
                    System.out.println(HELP);
                    System.exit(0);
                }
            }

            if (directOrderCmd && args.length > 1) {
                switch (args[1]) {
                    case "-s": {
                        fileType = Type.STR;
                        break;
                    }
                    case "-i": {
                        fileType = Type.INT;
                        break;
                    }
                    default: {
                        System.out.println("\nНеверно указан второй аргумент для запуска программы!" +
                                "\n" +
                                "\nПерезапустите программу убедившись что он соответствует одному " +
                                "из следующих значений: " +
                                "\n\"-s\" - исходные файлы содержат строковые данные" +
                                "\n\"-i\" - исходные файлы содержат целые числа" +
                                "\n");
                        System.out.println(HELP);
                        System.exit(0);
                    }
                }
                if (args.length > 2) {
                    outFile = new File(args[2]);
                } else {
                    System.out.println("\nНе указаны имена выходного файла и исходных файлов!" +
                            "\nПерезапустите программу добавив к текущим параметрам:" +
                            "\n - имя выходного файла, обязательный параметр;" +
                            "\n - имена входных файлов, не менее одного." +
                            "\n");
                    System.out.println(HELP);
                    System.exit(0);
                }
                if (args.length > 3) {
                    for (int i = 3; i < args.length; i++) {
                        File file = new File(args[i]);
                        if (file.exists() && file.isFile()) {
                            srcFiles.add(file);
                        } else {
                            System.out.println("\nНеверно указано имя входного файла \"" + file.getName() + "\"," +
                                    "либо файл с таким именем не существует!" +
                                    "\nДанный файл будет исключён из обработки" +
                                    "\n");
                        }
                    }
                    if (srcFiles.isEmpty()) {
                        System.out.println("\nНе найдено ни одного входного файла! " +
                                "\nПерезапустите программу, указав верные имена " +
                                "сущществующих входных файлов для обработки, " +
                                "не менее одного!\n");
                        System.out.println(HELP);
                        System.exit(0);
                    }
                } else {
                    System.out.println("\nНе указан ни один входной файл для обработки!" +
                            "\nПерезапустите программу, указав имена входных файлов, не менее одного!\n");
                    System.out.println(HELP);
                    System.exit(0);
                }
            } else if (!directOrderCmd) {
                switch (args[0]) {
                    case "-s": {
                        fileType = Type.STR;
                        break;
                    }
                    case "-i": {
                        fileType = Type.INT;
                        break;
                    }
                    default: {
                        System.out.println("\nНеверно указан первый аргумент для запуска программы!" +
                                "\n" +
                                "\nПерезапустите программу убедившись что он соответствует одному " +
                                "из следующих значений: " +
                                "\n\"-a\" - прямой порядок сортировки (не обязательный параметр)" +
                                "\n\"-d\" - обратный порядок сортировки (необязательный параметр)" +
                                "\n\"-s\" - исходные файлы содержат строковые данные" +
                                "\n\"-i\" - исходные файлы содержат целые числа" +
                                "\n");
                        System.out.println(HELP);
                        System.exit(0);
                    }
                }
                if (args.length > 1) {
                    outFile = new File(args[1]);
                } else {
                    System.out.println("\nНе указаны имена выходного файла и исходных файлов!" +
                            "\nПерезапустите программу добавив к текущим параметрам:" +
                            "\n - имя выходного файла, обязательный параметр;" +
                            "\n - имена входных файлов, не менее одного." +
                            "\n");
                    System.out.println(HELP);
                    System.exit(0);
                }
                if (args.length > 2) {
                    for (int i = 2; i < args.length; i++) {
                        File file = new File(args[i]);
                        if (file.exists() && file.isFile()) {
                            srcFiles.add(file);
                        } else {
                            System.out.println("\nНеверно указано имя входного файла \"" + file.getName() + "\"," +
                                    "либо файл с таким именем не существует!" +
                                    "\nДанный файл будет исключён из обработки" +
                                    "\n");
                        }
                    }
                    if (srcFiles.isEmpty()) {
                        System.out.println("\nНе найдено ни одного входного файла! " +
                                "\nПерезапустите программу, указав верные имена " +
                                "сущществующих входных файлов для обработки, " +
                                "не менее одного!\n");
                        System.out.println(HELP);
                        System.exit(0);
                    }
                } else {
                    System.out.println("\nНе указан ни один входной файл для обработки!" +
                            "\nПерезапустите программу, указав имена входных файлов, не менее одного!\n");
                    System.out.println(HELP);
                    System.exit(0);
                }
            } else {
                System.out.println("\nНе указаны все необходимые параметры!\n");
                System.out.println(HELP);
                System.exit(0);
            }
        } else {
            System.out.println("\nНе указан не один из аргументов для запуска программы!" +
                    "\nПерезапустите программу используя следующие параметры:" +
                    "\n 1. режим сортировки (-a или -d), необязательный, по умолчанию сортировка по возрастанию;" +
                    "\n 2. тип данных (-s или -i), обязательный;" +
                    "\n 3. имя выходного файла, обязательное;" +
                    "\n 4. остальные параметры – имена входных файлов, не менее одного." +
                    "\n");
            System.out.println(HELP);
            System.exit(0);
        }
    }
}
