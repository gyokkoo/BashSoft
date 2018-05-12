package io;

import staticData.ExceptionMessages;
import staticData.SessionData;

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

    public static void traverseDirectory(int depth) {
        LinkedList<File> subFolders = new LinkedList<>();

        String path = SessionData.currentPath;
        int initialIndentation = path.split("\\\\").length;
        File root = new File(path);

        subFolders.add(root);
        while (subFolders.size() != 0) {
            File currentFolder = subFolders.removeFirst();
            int currentIndentation = currentFolder.toString().split("\\\\").length - initialIndentation;
            if (depth - currentIndentation < 0) {
                break;
            }
            OutputWriter.writeMessageOnNewLine(currentFolder.toString());
            if (currentFolder.listFiles() != null) {
                for (File file : Objects.requireNonNull(currentFolder.listFiles())) {
                    if (file.isDirectory()) {
                        subFolders.add(file);
                    } else {
                        int indexOfLastSlash = file.toString().lastIndexOf("\\");
                        for (int i = 0; i < indexOfLastSlash; i++) {
                            OutputWriter.writeMessage("-");
                        }

                        OutputWriter.writeMessageOnNewLine(file.getName());
                    }
                }
            }

            System.out.println(currentFolder.toString());
        }
    }

    public static void changeCurrentDirRelativePath(String relativePath) {
        if (relativePath.equals("..")) {
            // go one directory up
            try {
                String currentPath = SessionData.currentPath;
                int indexOfLastSlash = currentPath.lastIndexOf("\\");
                String newPath = currentPath.substring(0, indexOfLastSlash);
                SessionData.currentPath = newPath;
            } catch (StringIndexOutOfBoundsException sioobe) {
                OutputWriter.displayException(ExceptionMessages.INVALID_DESTINATION);
            }
        } else {
            // go one directory down
            String currentPath = SessionData.currentPath;
            currentPath += "\\" + relativePath;
            changeCurrentDirRelativePath(currentPath);
        }
    }

    public static void changeCurrentDirAbsolute(String absolutePath) {
        File file = new File(absolutePath);
        if (!file.exists()) {
            OutputWriter.displayException(ExceptionMessages.INVALID_PATH);
            return;
        }

        SessionData.currentPath = absolutePath;
    }
}
