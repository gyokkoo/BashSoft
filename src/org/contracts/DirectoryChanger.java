package org.contracts;

public interface DirectoryChanger {

    void changeCurrentDirRelativePath(String relativePath);

    void changeCurrentDirAbsolute(String absolutePath);
}
