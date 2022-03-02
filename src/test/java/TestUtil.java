import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.fail;

public class TestUtil {
    public static boolean isIntArraySorted(Integer[] intArray) {
        boolean sorted = true;
        for (int i = 0; i < intArray.length; i++) {
            if (i + 1 == intArray.length) {
                break;
            }
            if (intArray[i] > intArray[i + 1]) {
                sorted = false;
                break;
            }
        }
        return sorted;
    }

    public static boolean isIntFileSorted(File file) {
        boolean sorted = true;
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                Integer one = null;
                Integer two = null;
                String line = reader.readLine();
                try {
                    if (line != null) {
                        one = Integer.parseInt(line);
                    } else {
                        fail("empty file");
                    }
                    line = reader.readLine();
                    if (line != null) {
                        two = Integer.parseInt(line);
                    }
                } catch (NumberFormatException e) {
                    fail(e.getMessage());
                }

                while (line != null) {
                    if (one > two) {
                        sorted = false;
                        break;
                    }
                    one = two;
                    line = reader.readLine();
                    if (line == null) {
                        break;
                    }
                    try {
                        two = Integer.parseInt(line);
                    } catch (NumberFormatException e) {
                        fail(e.getMessage());
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            fail("file does not exist");
        }
        return sorted;
    }

    public static boolean isStringArraySorted(String[] strings) {
        boolean sorted = true;
        if (strings == null) {
            fail("array = null");
        }

        for (int i = 0; i < strings.length; i++) {
            if (i + 1 == strings.length) {
                break;
            }
            if (strings[i].compareTo(strings[i + 1]) > 0) {
                sorted = false;
                break;
            }
        }
        return sorted;
    }

    public static boolean isStrFileSorted(File file) {
        boolean sorted = true;
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String one = null;
                String two = null;
                String line = reader.readLine();

                if (line != null) {
                    one = line;
                } else {
                    fail("empty file");
                }
                line = reader.readLine();
                if (line != null) {
                    two = line;
                }

                while (line != null) {
                    if (one.compareTo(two) > 0) {
                        sorted = false;
                        break;
                    }
                    one = two;
                    line = reader.readLine();
                    two = line;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            fail("file does not exist");
        }
        return sorted;
    }

    public static boolean containsArray(Object[] where, Object[] what) {
        List<Object> whereList = Arrays.asList(where);
        for (Object i : what) {
            if (!whereList.contains(i)) {
                return false;
            }
        }
        return true;
    }

    public static Integer[] fromFileToIntArray(File file) {
        List<Integer> resultList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            while (line != null) {
                try {
                    resultList.add(Integer.parseInt(line));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultList.toArray(new Integer[0]);
    }

    public static String[] fromFileToStrArray(File file) {
        List<String> resultList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            while (line != null) {
                resultList.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultList.toArray(new String[0]);
    }

}
