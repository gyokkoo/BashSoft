package org.io.commands;

import org.contracts.AsynchDownloader;
import org.contracts.ContentComparer;
import org.contracts.Database;
import org.contracts.DirectoryManager;
import org.exceptions.InvalidCommandException;
import org.io.OutputWriter;

public class DropDatabaseCommand extends Command {

    public DropDatabaseCommand(String input,
                               String[] data,
                               Database repository,
                               ContentComparer tester,
                               DirectoryManager ioManager,
                               AsynchDownloader downloader) {
        super(input, data, repository, tester, ioManager, downloader);
    }

    @Override
    public void execute() throws Exception {
        if (this.getData().length != 1) {
            throw new InvalidCommandException(this.getInput());
        }

        this.getRepository().unloadData();
        OutputWriter.writeMessageOnNewLine("Database dropped!");
    }
}