package org.example;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    // Константа с массивом путей к корневым папкам
    private static final String[] ROOT_PATHS = {"вставьте полный путь до директории A", "вставьте полный путь до директории B"};
    // Константа с числом N
    private static final int N = 2;

    public static void main(String[] args) {
        // Хранение папок с файлами .js
        List<String> jsFolders = new ArrayList<>();

        // Проходимся по каждой корневой папке
        for (String rootPath : ROOT_PATHS) {
            File rootFolder = new File(rootPath);
            if (!rootFolder.exists() || !rootFolder.isDirectory()) {
                continue;
            }
            // Рекурсивно находим папки с файлами .js
            findJSFolders(rootFolder, jsFolders);
        }

        // Выводим результат для задач 1 и 2
        System.out.println("\nвыход (задачи 1 и 2):");
        for (String folder : jsFolders) {
            System.out.println(folder);
        }

        // Разделяем полученные папки на N частей
        System.out.println("\nвыход (задачи 3):");
        divideFolders(jsFolders, N);
    }

    // Рекурсивный метод для поиска папок с файлами .js
    private static void findJSFolders(File folder, List<String> jsFolders) {
        if (folder == null || !folder.isDirectory()) {
            return;
        }
        File[] files = folder.listFiles();
        if (files == null) {
            return;
        }

        // Подсчитываем количество файлов .js в текущей папке
        int jsFileCount = countJSFiles(folder);

        // Если в текущей папке есть файлы .js, добавляем ее в список
        if (jsFileCount > 0) {
            jsFolders.add(folder.getPath() + " (" + jsFileCount + ")");
        } else {
            for (File file : files) {
                if (file.isDirectory()) {
                    findJSFolders(file, jsFolders);
                }
            }
        }
    }

    // Метод для подсчета файлов .js в папке
    private static int countJSFiles(File folder) {
        if (folder == null || !folder.isDirectory()) {
            return 0;
        }
        File[] files = folder.listFiles();
        if (files == null) {
            return 0;
        }
        int count = 0;
        for (File file : files) {
            if (file.isFile() && file.getName().toLowerCase().endsWith(".js")) {
                count++;
            }
        }
        return count;
    }

    // Метод для разделения списка папок на N частей
    private static void divideFolders(List<String> folders, int n) {
        Map<Integer, List<String>> dividedFolders = new HashMap<>();
        for (int i = 0; i < n; i++) {
            dividedFolders.put(i, new ArrayList<>());
        }

        int index = 0;
        for (String folder : folders) {
            dividedFolders.get(index % n).add(folder);
            index++;
        }

        for (int i = 0; i < n; i++) {
            System.out.println("[" + (i + 1) + "]");
            for (String path : dividedFolders.get(i)) {
                System.out.println(path);
            }
        }
    }
}