package org.io.commands;

import org.contracts.AsynchDownloader;
import org.contracts.ContentComparer;
import org.contracts.Database;
import org.contracts.DirectoryManager;
import org.exceptions.InvalidInputException;

public class MakeDirectoryCommand extends Command {

    public MakeDirectoryCommand(String input,
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
            throw new InvalidInputException(this.getInput());
        }

        String folderName = this.getData()[1];
        this.getIoManager().createDirectoryInCurrentFolder(folderName);
    }
}
