package io;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class OutputWriter {

    public static void writeMessage(String message) {
        System.out.print(message);
    }

    public static void writeMessageOnNewLine(String message) {
        System.out.println(message);
    }

    public static void writeEmptyLine() {
        System.out.println();
    }

    public static void displayException(String message) {
        System.out.println(message);
    }

    public static void printStudent(String name, List<Integer> marks) {
        String output = String.format("%s - %s", name, marks.toString());
        OutputWriter.writeMessageOnNewLine(output);
    }

    public static void traverseDirectory(String path) {
        LinkedList<File> subFolders = new LinkedList<>();
        File root = new File(path);

        subFolders.add(root);
        while (subFolders.size() != 0) {
            File currentFolder = subFolders.removeFirst();
            if (currentFolder.listFiles() != null) {
                for (File file : Objects.requireNonNull(currentFolder.listFiles())) {
                    if (file.isDirectory()) {
                        subFolders.add(file);
                    }
                }
            }

            System.out.println(currentFolder.toString());
        }
    }
}
