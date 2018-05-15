package org.io.commands;

import org.contracts.AsynchDownloader;
import org.contracts.ContentComparer;
import org.contracts.Database;
import org.contracts.DirectoryManager;
import org.exceptions.InvalidCommandException;

public class DownloadFileCommand extends Command {

    public DownloadFileCommand(String input,
                               String[] data,
                               Database repository,
                               ContentComparer contentComparer,
                               DirectoryManager ioManager,
                               AsynchDownloader downloader) {
        super(input, data, repository, contentComparer, ioManager, downloader);
    }

    @Override
    public void execute() throws Exception {
        if (this.getData().length != 2) {
            throw new InvalidCommandException(this.getInput());
        }

        String fileUrl = this.getData()[1];
        this.getDownloadManager().download(fileUrl);
    }
}
