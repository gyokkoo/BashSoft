package org.io.commands;

import org.contracts.AsynchDownloader;
import org.contracts.ContentComparer;
import org.contracts.Database;
import org.contracts.DirectoryManager;
import org.exceptions.InvalidCommandException;

public class TraverseFoldersCommand extends Command {

    public TraverseFoldersCommand(String input,
                                  String[] data,
                                  Database repository,
                                  ContentComparer tester,
                                  DirectoryManager ioManager,
                                  AsynchDownloader downloader) {
        super(input, data, repository, tester, ioManager, downloader);
    }

    @Override
    public void execute() throws Exception {
        if (this.getData().length != 1 && this.getData().length != 2) {
            throw new InvalidCommandException(this.getInput());
        }

        if (this.getData().length == 1) {
            this.getIoManager().traverseDirectory(0);
        }

        if (this.getData().length == 2) {
            this.getIoManager().traverseDirectory(Integer.valueOf(this.getData()[1]));
        }
    }
}
