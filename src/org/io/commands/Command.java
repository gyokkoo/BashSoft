package org.io.commands;

import org.contracts.*;
import org.exceptions.InvalidCommandException;

public abstract class Command implements Executable{

    private String input;
    private String[] data;

    private Database repository;
    private ContentComparer tester;
    private DirectoryManager ioManager;
    private AsynchDownloader downloadManager;

    public Command(String input,
                   String[] data,
                   Database repository,
                   ContentComparer contentComparer,
                   DirectoryManager ioManager,
                   AsynchDownloader downloader) {
        this.setInput(input);
        this.setData(data);
        this.repository = repository;
        this.tester = contentComparer;
        this.ioManager = ioManager;
        this.downloadManager = downloader;
    }

    public abstract void execute() throws Exception;

    protected Database getRepository() {
        return repository;
    }

    protected ContentComparer getTester() {
        return tester;
    }

    protected DirectoryManager getIoManager() {
        return ioManager;
    }

    protected AsynchDownloader getDownloadManager() {
        return downloadManager;
    }

    protected String getInput() {
        return input;
    }

    protected String[] getData() {
        return data;
    }

    private void setInput(String input) {
        if (input == null || input.equals("")) {
            throw new InvalidCommandException(input);
        }

        this.input = input;
    }

    private void setData(String[] data) {
        if (data == null || data.length < 1) {
            throw new InvalidCommandException(this.input);
        }

        this.data = data;
    }
}
