package org.io.commands;

import org.contracts.AsynchDownloader;
import org.contracts.ContentComparer;
import org.contracts.Database;
import org.contracts.DirectoryManager;
import org.exceptions.InvalidCommandException;

public class CompareFilesCommand extends Command {

    public CompareFilesCommand(String input,
                               String[] data,
                               Database repository,
                               ContentComparer tester,
                               DirectoryManager ioManager,
                               AsynchDownloader downloader) {
        super(input, data, repository, tester, ioManager, downloader);
    }

    @Override
    public void execute() throws Exception {
        if (this.getData().length != 3) {
            throw new InvalidCommandException(this.getInput());
        }

        String firstPath = this.getData()[1];
        String secondPath = this.getData()[2];
        this.getTester().compareContent(firstPath, secondPath);
    }
}
