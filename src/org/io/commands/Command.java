package org.io.commands;

import org.contracts.DirectoryManager;
import org.contracts.Executable;
import org.exceptions.InvalidCommandException;
import org.judge.Tester;
import org.network.DownloadManager;
import org.repository.StudentsRepository;

public abstract class Command implements Executable{

    private String input;
    private String[] data;

    private StudentsRepository repository;
    private Tester tester;
    private DirectoryManager ioManager;
    private DownloadManager downloadManager;

    protected Command(String input,
                   String[] data,
                   StudentsRepository repository,
                   Tester tester,
                   DirectoryManager ioManager,
                   DownloadManager downloadManager) {
        this.setInput(input);
        this.setData(data);
        this.repository = repository;
        this.tester = tester;
        this.ioManager = ioManager;
        this.downloadManager = downloadManager;
    }

    public abstract void execute() throws Exception;

    protected StudentsRepository getRepository() {
        return repository;
    }

    protected Tester getTester() {
        return tester;
    }

    protected DirectoryManager getIoManager() {
        return ioManager;
    }

    protected DownloadManager getDownloadManager() {
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
