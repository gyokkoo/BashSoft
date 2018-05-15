package org.io.commands;

import org.contracts.DirectoryManager;
import org.exceptions.InvalidInputException;
import org.judge.Tester;
import org.network.DownloadManager;
import org.repository.StudentsRepository;

public class MakeDirectoryCommand extends Command {

    public MakeDirectoryCommand(String input,
                                String[] data,
                                StudentsRepository repository,
                                Tester tester,
                                DirectoryManager ioManager,
                                DownloadManager downloadManager) {
        super(input, data, repository, tester, ioManager, downloadManager);
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
