package org.io.commands;

import org.contracts.DirectoryManager;
import org.exceptions.InvalidCommandException;
import org.judge.Tester;
import org.network.DownloadManager;
import org.repository.StudentsRepository;

public class DownloadAsynchCommand extends Command {

    public DownloadAsynchCommand(String input,
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
            throw new InvalidCommandException(this.getInput());
        }

        String fileUrl = this.getData()[1];
        this.getDownloadManager().downloadOnNewThread(fileUrl);
    }
}
