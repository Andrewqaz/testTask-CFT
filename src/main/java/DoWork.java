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

    public void filesHandlingNew(String[] args) {
        setCommandLineArgs(args);
        Scanner cmd = new Scanner(System.in);

        if (fileType.equals(Type.INT)) {
            System.out.println("Выбран режим обработки файлов содержащих целые числа (Integer)." +
                    "\nВсе строки содержащие пробелы, либо любые символы кроме: " +
                    "\n-9, -8, -7, -6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, " +
                    "будут проигнорированы!\n");
        } else {
            System.out.println("Выбран режим обработки файлов содержащих строковые данные (String)." +
                    "\nВсе пустые строки и строки содержащие пробелы будут проигнорированы!\n");
        }
        if (srcFiles.size() == 1) {
            System.out.println("\nПри запуске указан только один исходный файл: " + srcFiles.get(0) + "\n");
            if (Sorter.isFileSorted(srcFiles.get(0), fileType)) {
                File tempFile = MergeSortedData.getTempFile();
                tempFile.deleteOnExit();
                File result = MergeSortedData.mergeSortedFiles(srcFiles.get(0), tempFile, fileType);
                if (result.renameTo(outFile)) {
                    System.out.println("\nПрограмма успешно завершена\n");
                } else {
                    System.out.println("\nОшибка при именовании выходного файла\n");
                }
            } else {
                File result = sortingNeed(srcFiles.get(0), fileType, cmd);
                if (directOrder) {
                    outFile.delete();
                    result.renameTo(outFile);
                } else {
                    fileHandler.reversFile(result, outFile);

                }
            }
        } else {
            List<File> sortedFiles = allFilesSortingNeed(srcFiles, fileType, cmd);
            File file = MergeSortedData.mergeFilesList(sortedFiles, fileType);
            if (directOrder) {
                file.renameTo(outFile);
            } else {
                fileHandler.reversFile(file, outFile);
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

    private File sortingNeed(File file, Type fileType, Scanner cmd) {
        String userAnswer;
        boolean userChoice = false;
        while (!userChoice) {
            System.out.println("файл: " + file.getName() + " частично или полностью не отсортирован, \n" +
                    "попытаться отсортировать его? (возможно это займет продолжительное время и потребует \n" +
                    "дополнительного места на диске, в зависимости от размера файла...)" +
                    "\nY - да, попытаться отсортировать файл " + file.getName() +
                    "\nN - нет, попытаться провести слияние без сортировки" +
                    "\nQ - выйти из программы\n");
            userAnswer = cmd.nextLine();
            switch (userAnswer.toUpperCase()) {
                case "Y": {
                    return fileHandler.sortFile(file, fileType);
                }
                case "N": {
                    userChoice = true;
                    break;
                }
                case "Q": {
                    System.exit(0);
                }
                default: {
                    System.out.println("\nВведена неверная команда" +
                            "\nY - да, попытаться отсортировать файл" + outFile.getName() +
                            "\nN - нет, попытаться провести слияние без сортировки" +
                            "\nQ - выйти из программы\n");
                }
            }
        }
        File temp = MergeSortedData.getTempFile();
        temp.deleteOnExit();
        return MergeSortedData.mergeSortedFiles(file, temp, fileType);
    }

    private List<File> allFilesSortingNeed(List<File> srcFiles, Type fileType, Scanner cmd) {
        boolean allSorted = true;
        for (File file : srcFiles) {
            if (!Sorter.isFileSorted(file, fileType)) {
                allSorted = false;
                break;
            }
        }
        List<File> result = new LinkedList<>();
        if (allSorted) {
            return srcFiles;
        } else {
            UserAnswer userAnswer = null;
            String answerCmd;
            boolean userChoice = false;
            while (!userChoice) {
                System.out.println("\nОдин или несколько исходных файлов не отсортированы!" +
                        "\nОтсортировать все не отсортированные файлы? (возможно это займет" +
                        " продолжительное время и потребует дополнительного места на диске, " +
                        "в зависимости от размера файла...)" +
                        "\nY - да, попытаться отсортировать все не отсортированные файлы" +
                        "\nN - нет, попытаться провести слияние без сортировки" +
                        "\nE - принять решение по каждому файлу отдельно" +
                        "\nQ - выйти из программы\n");
                answerCmd = cmd.nextLine();
                switch (answerCmd.toUpperCase()) {
                    case "Y": {
                        userAnswer = UserAnswer.YES;
                        userChoice = true;
                        break;
                    }
                    case "N": {
                        userAnswer = UserAnswer.NO;
                        userChoice = true;
                        break;
                    }
                    case "E": {
                        userAnswer = UserAnswer.EACH;
                        userChoice = true;
                        break;
                    }
                    case "Q": {
                        System.exit(0);
                    }
                    default: {
                        System.out.println("\nВведена неверная команда" +
                                "\nY - да, попытаться отсортировать файл" + outFile.getName() +
                                "\nN - нет, попытаться провести слияние без сортировки" +
                                "\nE - принять решение по каждому файлу отдельно" +
                                "\nQ - выйти из программы\n");
                    }
                }
            }
            switch (userAnswer) {
                case YES: {
                    srcFiles.forEach(file -> {
                        result.add(fileHandler.sortFile(file, fileType));
                    });
                    break;
                }
                case NO: {
                    return srcFiles;
                }
                case EACH: {
                    srcFiles.forEach(file -> {
                        if (Sorter.isFileSorted(file, fileType)) {
                            result.add(file);
                        } else {
                            result.add(sortingNeed(file, fileType, cmd));
                        }
                    });
                }
            }
        }
        return result;
    }
}
