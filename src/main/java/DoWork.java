import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class DoWork {
    private static FileHandler fileHandler;
    private static Type fileType;
    private static boolean directOrder;
    private static List<File> srcFiles;
    private static File outFile;
    private static final String HELP = "" +
            "\n===========================================================================================" +
            "\nСПРАВКА:" +
            "\nДля корректной работы программы необходимо установить настройки с помощью аргументов " +
            "\nкоммандной строки. Дополнительными параметрами при запуске необходимо указать: " +
            "\n 1. режим сортировки (-a или -d), необязательный, по умолчанию сортировка по возрастанию;" +
            "\n 2. тип данных (-s или -i), обязательный;" +
            "\n 3. имя выходного файла, обязательное;" +
            "\n 4. остальные параметры – имена входных файлов, не менее одного." +
            "\n" +
            "\nПример запуска: ........ testTask-CFT -a -i out.txt srcOne.txt srcTwo.txt";
    //todo поправить пример запуска

    static {
        fileHandler = new FileHandler();
        directOrder = true;
        srcFiles = new LinkedList<>();
    }


    public static void main(String[] args) {
        setCommandLineArgs(args);
        System.out.println("тип:" + fileType);
        System.out.println("порядок: " + directOrder);
        System.out.println("выход: " + outFile.getName());
        srcFiles.forEach(file -> {
            System.out.println("вход №"+(srcFiles.indexOf(file)+1)+": "+file.getName());
        });
    }


    private static void setCommandLineArgs(String[] args) {
        boolean directOrderCmd = false;
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
                        if (file.exists()) {
                            srcFiles.add(file);
                        } else {
                            System.out.println("\nНеверно указано имя входного файла \"" + file.getName() + "\"," +
                                    "либо файл с таким именем не существует!" +
                                    "\nДанный файл будет исключён из обработки" +
                                    "\n");
                        }
                    }
                    if (srcFiles.isEmpty()){
                        System.out.println("\nНе найдено ни одного входного файла! " +
                                "\nПерезапустите программу, указав верные имена " +
                                "сущществующих входных файлов для обработки, " +
                                "не менее одного!");
                        System.out.println(HELP);
                        System.exit(0);
                    }
                }else {
                    System.out.println("\nНе указан ни один входной файл для обработки!" +
                            "\nПерезапустите программу, указав имена входных файлов, не менее одного!");
                    System.out.println(HELP);
                    System.exit(0);
                }
            }else if (!directOrderCmd){
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
                        if (file.exists()) {
                            srcFiles.add(file);
                        } else {
                            System.out.println("\nНеверно указано имя входного файла \"" + file.getName() + "\"," +
                                    "либо файл с таким именем не существует!" +
                                    "\nДанный файл будет исключён из обработки" +
                                    "\n");
                        }
                    }
                    if (srcFiles.isEmpty()){
                        System.out.println("\nНе найдено ни одного входного файла! " +
                                "\nПерезапустите программу, указав верные имена " +
                                "сущществующих входных файлов для обработки, " +
                                "не менее одного!");
                        System.out.println(HELP);
                        System.exit(0);
                    }
                }else {
                    System.out.println("\nНе указан ни один входной файл для обработки!" +
                            "\nПерезапустите программу, указав имена входных файлов, не менее одного!");
                    System.out.println(HELP);
                    System.exit(0);
                }
            }else {
                System.out.println("\nНе указаны все необходимые параметры!");
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
