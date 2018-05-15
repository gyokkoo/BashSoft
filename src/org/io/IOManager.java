package org.io;

import org.contracts.DirectoryManager;
import org.exceptions.InvalidFileNameException;
import org.exceptions.InvalidPathException;
import org.staticData.SessionData;

import java.io.File;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class IOManager implements DirectoryManager {

    public void traverseDirectory(int depth) {
        Queue<File> subFolders = new LinkedList<>();

        String path = SessionData.currentPath;
        int initialIndentation = path.split("\\\\").length;
        File root = new File(path);

        subFolders.add(root);
        while (subFolders.size() != 0) {
            File currentFolder = subFolders.poll();
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

    public void createDirectoryInCurrentFolder(String name) {
        String path = getCurrentDirectoryPath() + "\\" + name;
        File file = new File(path);
        boolean isMade = file.mkdir();
        if (!isMade) {
            throw new InvalidFileNameException();
        }
    }

    public void changeCurrentDirRelativePath(String relativePath) {
        if (relativePath.equals("..")) {
            // go one directory up
            try {
                String currentPath = SessionData.currentPath;
                int indexOfLastSlash = currentPath.lastIndexOf("\\");
                SessionData.currentPath = currentPath.substring(0, indexOfLastSlash);
            } catch (StringIndexOutOfBoundsException e) {
                throw new InvalidPathException();
            }
        } else {
            // go one directory down
            String currentPath = SessionData.currentPath;
            currentPath += "\\" + relativePath;
            changeCurrentDirAbsolute(currentPath);
        }
    }

    public void changeCurrentDirAbsolute(String absolutePath) {
        File file = new File(absolutePath);
        if (!file.exists()) {
            throw new InvalidPathException();
        }

        SessionData.currentPath = absolutePath;
    }

    private String getCurrentDirectoryPath() {
        return SessionData.currentPath;
    }
}
