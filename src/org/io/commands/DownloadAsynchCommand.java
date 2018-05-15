package org.io.commands;

import org.contracts.AsynchDownloader;
import org.contracts.ContentComparer;
import org.contracts.Database;
import org.contracts.DirectoryManager;
import org.exceptions.InvalidCommandException;

public class DownloadAsynchCommand extends Command {

    public DownloadAsynchCommand(String input,
                                 String[] data,
                                 Database repository,
                                 ContentComparer tester,
                                 DirectoryManager ioManager,
                                 AsynchDownloader downloader) {
        super(input, data, repository, tester, ioManager, downloader);
    }

    @Override
    public void execute() throws Exception {
        if (this.getData().length != 2) {
            throw new InvalidCommandException(this.getInput());
        }

        String fileUrl = this.getData()[1];
        this.getDownloadManager().downloadOnNewThread(fileUrl);
    }
}
