package org.io.commands;

import org.contracts.DirectoryManager;
import org.exceptions.InvalidCommandException;
import org.io.OutputWriter;
import org.judge.Tester;
import org.network.DownloadManager;
import org.repository.StudentsRepository;

public class DropDatabaseCommand extends Command {

    public DropDatabaseCommand(String input,
                               String[] data,
                               StudentsRepository repository,
                               Tester tester,
                               DirectoryManager ioManager,
                               DownloadManager downloadManager) {
        super(input, data, repository, tester, ioManager, downloadManager);
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