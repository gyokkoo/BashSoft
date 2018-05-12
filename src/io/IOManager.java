package io;

import staticData.SessionData;

import java.io.File;

public class IOManager {

    public static void createDirectoryInCurrentFolder(String name) {
        String path = getCurrentDirectoryPath() + "\\" + name;
        File file = new File(path);
        file.mkdir();
    }

    private static String getCurrentDirectoryPath() {
        return SessionData.currentPath;
    }
}
